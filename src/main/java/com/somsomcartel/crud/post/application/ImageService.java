package com.somsomcartel.crud.post.application;

import com.somsomcartel.crud.post.dao.PostRepository;
import com.somsomcartel.crud.post.domain.Post;
import com.somsomcartel.crud.post.dto.ImageResponseDto;
import com.somsomcartel.crud.post.exception.PostNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.time.Duration;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucketName;

    private final PostRepository postRepository;

    @Transactional
    public ImageResponseDto createPresignedUrl(String imageName) {
        S3Presigner presigner = S3Presigner.create();
        String uuid = UUID.randomUUID().toString();
        String extension = "." + imageName.substring(imageName.lastIndexOf(".") + 1);

        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(uuid + extension)
                .build();

        PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(10))
                .putObjectRequest(objectRequest)
                .build();

        PresignedPutObjectRequest presignedRequest = presigner.presignPutObject(presignRequest);

        return ImageResponseDto.builder()
                .presignedURL(presignedRequest.url().toExternalForm())
                .key(uuid)
                .build();
    }

    public void createPostImageKey(Integer postId, String postImage) {
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        post.updatePostImage(postImage);
    }
}
