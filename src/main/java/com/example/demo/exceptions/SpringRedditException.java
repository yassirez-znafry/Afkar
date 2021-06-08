package com.example.demo.exceptions;

import org.springframework.mail.MailException;

public class SpringRedditException extends RuntimeException {
    public SpringRedditException(String exMessage, MailException exception) {
        super(exMessage, exception);
    }
    public SpringRedditException(String exMessage) {
        super(exMessage);
    }
}
