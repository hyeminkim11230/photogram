package com.cos.photogramstart.domain.user;

import com.cos.photogramstart.domain.image.Image;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)//번호 증가 전략이 데이터베이스를 따라감
    private Long id;

    @Column(length =20, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;
    private String website;
    private String bio;
    @Column(nullable = false)
    private String email;
    private String phone;
    private String gender;

    private String profileImageUrl;//사진
    private String role;//권한

    //Lazy일때 user select시 해당 userid로 등록된 이미지들을 가져오지 말것 - 대신 getImages함수가 호출될 때
    //eager일때 user select시 해당 userid로 등록된 이미지들을 전부 Join해서 가져와
    @OneToMany(mappedBy="user",fetch=FetchType.LAZY) //나는 연관관계의 주인이 아니므로 테이블 칼럼 만들지 말것 & user SELECT할때 해당 userid로 등록된 image를 다 가져올 것
    @JsonIgnoreProperties({"user"}) //무한참조를 방지
    private List<Image> images; //양방향 매핑

    private LocalDateTime createDate;

    @PrePersist //디비에 INSERT되기 직전에 실행
    public void setCreateDate() {
        this.createDate = LocalDateTime.now();
    }
}
