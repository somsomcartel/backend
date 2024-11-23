package com.somsomcartel.crud.comment.exception;

import com.somsomcartel.crud.global.error.ErrorCode;
import com.somsomcartel.crud.global.error.exception.EntityNotFoundException;

public class CommentNotFoundException extends EntityNotFoundException {
    public CommentNotFoundException() {
        super(ErrorCode.COMMENT_NOT_FOUND);
    }
}
