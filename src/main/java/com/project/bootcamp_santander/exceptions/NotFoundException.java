package com.project.bootcamp_santander.exceptions;

import com.project.bootcamp_santander.util.MessageUtils;

public class NotFoundException extends RuntimeException{
    public NotFoundException() {
        super(MessageUtils.NO_RECORDS_FOUND);
    }
}
