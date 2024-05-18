package com.cos.photogramstart.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

//JPA-Java Persistence API 자바로 데이터를 영구적으로 저장(DB)할 수 있는 API제공
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//번호 증가 전략이 데이터베이스를 따라감
    private Long id;

    private String username;
    private String password;
    private String website;
    private String bio;
    private String email;
    private String phone;
    private String gender;

    private String profileImageUrl;//사진
    private String role;//권한

    private LocalDateTime createDate;

    @PrePersist //디비에 INSERT되기 직전에 실행
    public void setCreateDate() {
        this.createDate =LocalDateTime.now();
    }
}
