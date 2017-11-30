package com.kaishengit.utils;

import com.kaishengit.exception.DaoAndUtilsException;
import com.kaishengit.utils.voentity.RequestQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/30
 */
public class QueryUtil {

    private static final String INT_Type = "i";
    private static final String INTEGER_Type = "I";
    private static final String STRING_Type = "s";
    private static final String DOUBLE_Type = "d";
    private static final String FLOAT_Type = "f";
    private static final String BIGDECIMAL_Type = "bd";

    /**
     * 正则表达式，匹配中文
     */
    private static final Pattern CHINESE = Pattern.compile("[\u4e00-\u9fa5]");


    public static List<RequestQuery> getQueryList(HttpServletRequest request) {

        //获得所有url后所有的键值对。
        Enumeration<String> stringEnumeration =  request.getParameterNames();

        //约定key为 q_列名_eq(执行的方法)_i(数据类型) eg：q_stuAge_eq_i
        List<RequestQuery> requestQueryList = new ArrayList<>();

        //循环获得url键值对集合
        while (stringEnumeration.hasMoreElements()) {

            //获得key
            String queryKey = stringEnumeration.nextElement();

            //通过key获得值
            String value = request.getParameter(queryKey);

            //q_keyName_like_i
            String[] params = queryKey.split("_");

            if("q".equalsIgnoreCase(params[0]) && !"".equals(value) && value != null) {

                RequestQuery requestQuery = new RequestQuery();

                requestQuery.setQueryName(params[1]);

                requestQuery.setQueryMethod(params[2]);

                requestQuery.setQueryValue(tranType(params[3], value));

                requestQueryList.add(requestQuery);

            }

        }

        return requestQueryList;

    }


    /**
     * 类型转换
     * 支持 string转 int Integer double float BigDecimal
     *
     * @param type
     * @param value
     * @return
     */
    public static Object tranType(String type, String value) {


        if(INT_Type.equalsIgnoreCase(type) && StringUtils.isNumeric(value)) {

            return Integer.valueOf(value);

        } else if (INTEGER_Type.equalsIgnoreCase(type) && StringUtils.isNumeric(value)) {

            return Integer.valueOf(value);

        } else if (STRING_Type.equalsIgnoreCase(type)) {

            return value;

        } else if (DOUBLE_Type.equalsIgnoreCase(type) && StringUtils.isNumeric(value)) {

            return Double.valueOf(value);

        } else if (FLOAT_Type.equalsIgnoreCase(type) && StringUtils.isNumeric(value)) {

            return Float.valueOf(value);

        } else if (BIGDECIMAL_Type.equalsIgnoreCase(type) && StringUtils.isNumeric(value)) {

            return new BigDecimal(value);

        }

        return null;
    }

    /**
     * 判断内容是否为中文
     *
     * @param str
     * @return
     */
    public static boolean isContainChinese(String str) {

        Matcher matcher = CHINESE.matcher(str);

        if (matcher.find()) {
            return true;
        }
        return false;
    }

}
