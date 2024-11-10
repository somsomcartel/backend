package com.somsomcartel.crud.comment.exception;

import jakarta.persistence.EntityNotFoundException;

public class CommentNotFoundException extends EntityNotFoundException {
    public CommentNotFoundException() {
    }
}
