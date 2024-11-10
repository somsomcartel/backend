package com.somsomcartel.crud.comment.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentEditRequestDto {
    private String commentText;

    @Builder
    public CommentEditRequestDto(String commentText) {
        this.commentText = commentText;
    }
}
