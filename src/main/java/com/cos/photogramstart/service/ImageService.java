package com.cos.photogramstart.service;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ImageService {


    private final ImageRepository imageRepository;

    @Value("${file.path}")
    private String uploadFolder;
    public void 사진업로드(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails){
        UUID uuid = UUID.randomUUID();
        String imageFileName=imageUploadDto.getFile().getOriginalFilename();  //1.jpg
        System.out.println("이미지파일이름:" + imageFileName);

        Path imageFilePath = Paths.get(uploadFolder + imageFileName);

        //통신이나 I/O 외부에서 데이터 받아오거나 파일 읽을때 예외발생가능 컴파일때는 못잡고 런타임시만 잡을수 있음
        try {
            Files.write(imageFilePath,imageUploadDto.getFile().getBytes());
        }catch(Exception e) {
                e.printStackTrace();
        }

        //image테이블에 저장
        Image image = imageUploadDto.toEntity(imageFileName,principalDetails.getUser());
        Image imageEntity = imageRepository.save(image);

        System.out.println(imageEntity);
    }
}
