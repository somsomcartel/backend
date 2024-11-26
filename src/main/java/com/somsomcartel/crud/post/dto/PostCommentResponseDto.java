package com.somsomcartel.crud.post.dto;

import com.somsomcartel.crud.comment.dto.CommentResponseDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCommentResponseDto {
    private PostResponseDto postResponseDto;
    private List<CommentResponseDto> commentList;
}
