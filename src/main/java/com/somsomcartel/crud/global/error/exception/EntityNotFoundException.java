package com.somsomcartel.crud.global.error.exception;

import com.somsomcartel.crud.global.error.ErrorCode;

public class EntityNotFoundException extends BusinessException{
    public EntityNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
