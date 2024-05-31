package com.cos.photogramstart.domain.image;

import com.cos.photogramstart.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//번호 증가 전략이 데이터베이스를 따라감
    private Long id;

    private String caption; //사진과 함께 넣을 comment
    private String postImageUrl; //사진을 전송받아서 그 사진을 특정 폴더에 저장-DB에 그 저장된 경로를 insert
    @JoinColumn(name="userId")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user; //누가 업로드 했는지
    //이미지 좋아요
    //댓글

    private LocalDateTime createDate;
    @PrePersist
    public void setCreateDate() {
        this.createDate = LocalDateTime.now();
    }

}
