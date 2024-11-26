package com.somsomcartel.crud.post.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageResponseDto {
    private String presignedURL;
    private String key;
}
