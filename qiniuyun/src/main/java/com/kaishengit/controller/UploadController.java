package com.kaishengit.controller;

import com.google.gson.Gson;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Base64;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/20
 */
@Controller
@RequestMapping("/upload")
public class UploadController {

    @GetMapping
    public String uploadByForm(Model model) {

        String accessKey = "jDC0ldtEGf1DHDhZ0fUyMrBmQTruPZML9wmRa9ne";
        String secretKey = "lNx9m5bf0ehECnuNDPHFnYSGAC0g4xOjfeuVstVY";
        String bucket = "crm-silver";

        Auth auth = Auth.create(accessKey,secretKey);

        String upToken = auth.uploadToken(bucket);

        model.addAttribute("upToken", upToken);

        return "upload";

    }

    @GetMapping("/second")
    public String uploadByFormSecond(Model model) {

        String accessKey = "jDC0ldtEGf1DHDhZ0fUyMrBmQTruPZML9wmRa9ne";
        String secretKey = "lNx9m5bf0ehECnuNDPHFnYSGAC0g4xOjfeuVstVY";
        String bucket = "crm-silver";

        Auth auth = Auth.create(accessKey,secretKey);

        //设置回调的JSON格式
        StringMap stringMap = new StringMap();
        stringMap.put("returnBody", "{\"fileKey\":\"${key}\"},\"fsize\":$(fsize)}");

        String upToken = auth.uploadToken(bucket, null, 3600, stringMap);

        model.addAttribute("upToken", upToken);
        return "upload";
    }

    @GetMapping("/third")
    public String uploadByFormThird(Model model) {

        String accessKey = "jDC0ldtEGf1DHDhZ0fUyMrBmQTruPZML9wmRa9ne";
        String secretKey = "lNx9m5bf0ehECnuNDPHFnYSGAC0g4xOjfeuVstVY";
        String bucket = "crm-silver";

        Auth auth = Auth.create(accessKey,secretKey);

        StringMap stringMap = new StringMap();
        stringMap.put("returnUrl", "http://localhost:8080/upload/callback");

        String upToken = auth.uploadToken(bucket, null, 3600, stringMap);
        model.addAttribute("upToken", upToken);
        return "upload";

    }

    /**
     * @param upload_ret 该参数名需要和七牛云的返回的参数名保持一致
     * @return
     */
    @GetMapping("/callback")
    public String uploadCallback(String upload_ret) {

        //通过base64
        String jsonString = new String(Base64.getDecoder().decode(upload_ret));
        System.out.println(jsonString);

        return "redirect:/upload";

    }

    /*=========================以上是表单的直接提交，从表单到七牛
                               下载文件是：
                                七牛给的域名 / 文件名 ？attname =下载到本地的名字.jpg
                                ============================*/


    @GetMapping("/local")
    public String uploadLocal() {

        return "upload_local";

    }

    @PostMapping("/local")
    public String uploadLocal(MultipartFile file) throws IOException {

        Configuration configuration = new Configuration(Zone.zone1());
        UploadManager uploadManager = new UploadManager(configuration);

        String accessKey = "jDC0ldtEGf1DHDhZ0fUyMrBmQTruPZML9wmRa9ne";
        String secretKey = "lNx9m5bf0ehECnuNDPHFnYSGAC0g4xOjfeuVstVY";
        String bucket = "crm-silver";

        Auth auth = Auth.create(accessKey, secretKey);
        String uploadToken = auth.uploadToken(bucket);

        byte[] bytes = file.getBytes();

        Response response = uploadManager.put(bytes, null, uploadToken);

        System.out.println(response.bodyString());

        DefaultPutRet defaultPutRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

        System.out.println("key ---->" + defaultPutRet.key);

        return "redirect:/upload/local";

    }

    @GetMapping("/download")
    public void download(String name, HttpServletResponse response) throws IOException {

        String domainOfBucket = "http://ozp55yohm.bkt.clouddn.com";

        String encodeFileName = URLEncoder.encode(name, "utf-8");
        String finalUrl = String.format("%s/%s", domainOfBucket, encodeFileName);

        URL url = new URL(finalUrl);
        URLConnection urlConnection = url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();

        OutputStream outputStream = response.getOutputStream();

        response.setContentType("application/octet-stream");

        //设置下载框里的名字
        response.addHeader("Content-Disposition", "attachment;filename=\"1.jpg\"");


        IOUtils.copy(inputStream, outputStream);

        outputStream.flush();
        outputStream.close();
        inputStream.close();

    }




}
