package com.kaishengit.weixin;

import com.alibaba.fastjson.JSON;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.kaishengit.weixin.exception.WeixinException;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.jws.Oneway;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/21
 */
@Component
public class CrmWeixinUtil {

    @Value("${agentId}")
    private String agentid;

    @Value("${corpid}")
    private String corpId;

    @Value("${weixin.secret.nomal}")
    private String nomalSecret;

    @Value("${weixin.secret.callbook}")
    private String callbookSecret;

    /**
     * 获取AccessToken的URL
     */
    @Value("${GET_ACCESS_TOKEN_URL}")
    private String GET_ACCESS_TOKEN_URL;

    /**
     * 创建成员 属于通讯录 callbook管理
     */
    @Value("${POST_CREATE_ACCOUNT_URL}")
    private String POST_CREATE_ACCOUNT_URL;

    /**
     * 删除成员
     */
    @Value("${GET_DELETE_ACCOUNT_URL}")
    private String GET_DELETE_ACCOUNT_URL;

    /**
     * 创建部门
     */
    @Value("${POST_CREATE_DEPT_URL}")
    private String POST_CREATE_DEPT_URL;

    /**
     * 删除部门
     */
    @Value("${GET_DELETE_DEPT_URL}")
    private String GET_DELETE_DEPT_URL;

    /**
     * 发送消息
     */
    @Value("${POST_SEND_MESSAGE_URL}")
    private String POST_SEND_MESSAGE_URL;

    public static final String ACCESSTOKEN_TYPE_CALLBOOK = "callbook";
    public static final String ACCESSTOKEN_TYPE_NOMAL = "nomal";

    /**
     * 获取AccessToken并缓存
     * 腾讯限制获取次数吗，一次获取可以用两个小时，所以需要缓存
     *
     */
    private LoadingCache<String, String> accessTokenCache = CacheBuilder.newBuilder()
            .expireAfterWrite(7200, TimeUnit.SECONDS)
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String type) throws Exception {
                    String url;

                    if(ACCESSTOKEN_TYPE_CALLBOOK.equals(type)) {
                        url = String.format(GET_ACCESS_TOKEN_URL, corpId, callbookSecret);
                    } else {
                        url = String.format(GET_ACCESS_TOKEN_URL, corpId, nomalSecret);
                    }

                    String resultJson = sendHttpGetRequest(url);
                    Map<String, Object> map = JSON.parseObject(resultJson, HashMap.class);

                    if(map.get("errcode").equals(0)) {
                        return (String) map.get("access_token");
                    }

                    throw new WeixinException(resultJson);

                }

            });

    /**
     * 创建成员
     */
    public void craeateAccount(Integer userid, String name, List<Integer> departmentList, String mobile) {

        String url = String.format(POST_CREATE_ACCOUNT_URL, getAccessToken(ACCESSTOKEN_TYPE_CALLBOOK));

        Map<String, Object> data = new HashMap<String, Object>();

        data.put("userid", userid);
        data.put("name", name);
        data.put("department", departmentList);
        data.put("mobile", mobile);

        String resultJson = sendHttpPostRequest(url, JSON.toJSONString(data));

        isSuccess("添加成员异常", resultJson);

    }

    /**
     * 删除成员
     * @param userid
     */
    public void deleteAccount(Integer userid) {

        String url = String.format(GET_DELETE_ACCOUNT_URL, getAccessToken(ACCESSTOKEN_TYPE_CALLBOOK), userid);
        String resultJson = sendHttpGetRequest(url);

        isSuccess("删除成员失败", resultJson);

    }

    /**
     * 创建部门
     */
    public void createDept(Integer id, String name, Integer pid) {

        String url = String.format(POST_CREATE_DEPT_URL, getAccessToken(ACCESSTOKEN_TYPE_CALLBOOK));
        Map<String, Object> data = new HashMap<String, Object>();

        data.put("name", name);
        data.put("parentid", pid);
        data.put("id", id);

        String resultJson = sendHttpPostRequest(url, JSON.toJSONString(data));

        isSuccess("添加部门异常", resultJson);

    }

    /**
     * 删除部门
     * @param id
     */
    public void deleteDept(Integer id) {

        String url = String.format(GET_DELETE_DEPT_URL, getAccessToken(ACCESSTOKEN_TYPE_CALLBOOK), id);

        String resultJson = sendHttpGetRequest(url);

        isSuccess("删除部门异常", resultJson);

    }

    /**
     *发送文本消息
     */
    public void sendTextMessage(String touser, String content) {

        String url = String.format(POST_SEND_MESSAGE_URL, getAccessToken(ACCESSTOKEN_TYPE_NOMAL));

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("touser", touser);
        data.put("msgtype", "text");
        data.put("agentid", agentid);

        Map<String, String> textMap = new HashMap<String, String>(3);
        textMap.put("content", content);

        data.put("text", textMap);

        String resultJson = sendHttpPostRequest(url, JSON.toJSONString(data));

        isSuccess("发送消息异常", resultJson);

    }

    /**
     * 判断结果是否成功
     * 0 ：成功
     * @param message
     * @param resultJson
     */
    private void isSuccess(String message, String resultJson) {

        Map<String, Object> map = JSON.parseObject(resultJson, HashMap.class);

        String key = "errcode";

        if(!map.get(key).equals(0)) {

            System.out.println(resultJson);
            throw new WeixinException(message);

        }
    }

    /**
     * 获得缓存里的AccessToken
     * @param type
     * @return
     */
    public String getAccessToken(String type) {

        try {
            return accessTokenCache.get(type);
        } catch (ExecutionException e) {
            e.printStackTrace();
            throw new WeixinException("获取AccessToken异常");
        }
    }


    /**
     * GET
     * 发出Http的get请求 使用 OKHttp
     * @param url 目标的url
     * @return
     */
    private String sendHttpGetRequest(String url) {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        try {

            Response response = client.newCall(request).execute();
            return response.body().string();

        } catch (IOException e) {
            e.printStackTrace();
            throw new WeixinException("Get请求失败");
        }

    }

    /**
     * POST
     *发出Http的Post请求
     * @param url 目标的url
     * @param json 传递的值
     * @return
     */
    private String sendHttpPostRequest(String url, String json) {

        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        //以json的格式构建Post的请求所传递值
        RequestBody body = RequestBody.create(JSON, json);

        Request request = new Request.Builder().url(url).post(body).build();

        try {

            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            throw new WeixinException("POST请求异常");
        }

    }

}
