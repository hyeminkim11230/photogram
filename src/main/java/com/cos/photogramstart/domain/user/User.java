package com.cos.photogramstart.domain.user;

import com.cos.photogramstart.domain.image.Image;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

//JPA-Java Persistence API 자바로 데이터를 영구적으로 저장(DB)할 수 있는 API제공

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 30, unique = true)
    private String username;
    private String password;

    private String name; // 이름
    private String website; // 자기 홈페이지
    private String bio; // 자기소개
    private String email;
    private String phone;
    private String gender;

    private String profileImageUrl;
    private String provider; // 제공자 Google, Facebook, Naver

    private String role; // USER, ADMIN

    @OneToMany(mappedBy = "user")
    private List<Image> images;

    @CreationTimestamp
    private Timestamp createDate;
}
