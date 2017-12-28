package com.park.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class QzException extends Exception {
	Logger log = LoggerFactory.getLogger(QzException.class);
	public QzException(){
		super();
	}
	
	public QzException(String message){
		super(message);
	}
	
    public QzException(String message, Throwable cause) {
        super(message+":"+cause.getMessage(), cause);
    }
	
}
