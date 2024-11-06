package com.somsomcartel.crud.user.domain;

import com.somsomcartel.crud.post.domain.Post;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(nullable = false, length = 40)
    private String userEmail;

    @Column(nullable = false, length = 30)
    private String userPassword;

    @Column(nullable = false, length = 20)
    private String userName;

    @Column(nullable = false, length = 50)
    private String userBio;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Post> postList = new ArrayList<>();

    @Builder
    public User(String userEmail, String userPassword, String userName, String userBio) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userBio = userBio;
    }
}
