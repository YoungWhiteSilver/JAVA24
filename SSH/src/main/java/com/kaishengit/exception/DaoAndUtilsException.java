package com.kaishengit.exception;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/30
 */
public class DaoAndUtilsException extends RuntimeException{


    public DaoAndUtilsException() {
    }

    public DaoAndUtilsException(String message) {
        super(message);
    }

    public DaoAndUtilsException(String message, Throwable cause) {
        super(message, cause);
    }


    public DaoAndUtilsException(Throwable cause) {
        super(cause);
    }

    public DaoAndUtilsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


}
