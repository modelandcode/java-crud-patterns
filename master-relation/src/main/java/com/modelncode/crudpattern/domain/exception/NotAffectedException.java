package com.modelncode.crudpattern.domain.exception;

/**
 * Created by g on 2017-06-12.
 */
public class NotAffectedException extends RuntimeException {
    static final long serialVersionUID = 1;

    public NotAffectedException(Throwable throwable, String msg){
        super(msg, throwable);
    }

    public NotAffectedException(String msg){
        super(msg);
    }

    public NotAffectedException(Throwable throwable){
        super(throwable);
    }

}