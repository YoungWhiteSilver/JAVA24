package com.kaishengit.crm.service.exception;

/**
 * Created by silver on 2017/11/8.
 */
public class ServiceException extends RuntimeException {

    public ServiceException() {}

    public ServiceException(String s) {

        super(s);

    }

    public ServiceException(Throwable th) {

        super(th);

    }

    public ServiceException(String msg, Throwable th) {

        super(msg, th);

    }




}
