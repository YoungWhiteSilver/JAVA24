package com.kaishengit.weixin.exception;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/21
 */
public class WeixinException extends RuntimeException{

    public WeixinException() {}

    public WeixinException(String message) {

        super(message);

    }

    public WeixinException(Throwable throwable) {

        super(throwable);

    }

    public WeixinException(String mesage, Throwable th) {

        super(mesage, th);

    }


}
