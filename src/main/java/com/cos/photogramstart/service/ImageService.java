package com.cos.photogramstart.service;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ImageService {
    private final ImageRepository imageRepository;
    @Transactional(readOnly = true) //영속석 컨텍스트에서 변경감지를 해서 변경되었으면 더티체킹을 하고 ,flush 반영을 함
    public List<Image> 이미지스토리(int principalId){
        List<Image> images = imageRepository.mStory(principalId);
        return  images;
    }


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
