package com.somsomcartel.crud.post.exception;

import com.somsomcartel.crud.global.error.ErrorCode;
import com.somsomcartel.crud.global.error.exception.EntityNotFoundException;

public class PostNotFoundException extends EntityNotFoundException {
    public PostNotFoundException() {
        super(ErrorCode.POST_NOT_FOUND);
    }
}
