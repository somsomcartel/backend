package com.somsomcartel.crud.user.exception;

import com.somsomcartel.crud.global.error.ErrorCode;
import com.somsomcartel.crud.global.error.exception.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {
    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
