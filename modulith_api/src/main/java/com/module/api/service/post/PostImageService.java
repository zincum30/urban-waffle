package com.module.api.service.post;

import com.module.api.entity.post.PostImageEntity;
import com.module.api.exception.CustomErrorCode;
import com.module.api.exception.CustomException;
import com.module.api.repository.post.image.PostImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
//@PropertySource("classpath:application.yml")
public class PostImageService {

    private final PostImageRepository imageRepository;

//    @Value("${servlet.multipart.location}")
//    private final String STORAGE;

    public void addImage(Long postId, MultipartFile image) {
//        String defaultPath = System.getProperty("user.dir") + STORAGE;
        String defaultPath = "C:\\Users\\KIM\\Desktop";
        String imageName = UUID.randomUUID() + "_" + image.getOriginalFilename();
        String absolutePath = defaultPath + "\\" + imageName;
        saveImage(absolutePath, image);

        PostImageEntity imageEntity = PostImageEntity.builder()
                .postId(postId)
                .imgUrl(absolutePath)
                .build();

        imageRepository.save(imageEntity);
    }

    private void saveImage(String filePath, MultipartFile file) {
        try {
            BufferedInputStream inputStream = new BufferedInputStream(file.getInputStream());
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath));

            byte[] buffer = new byte[1024 * 4];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            inputStream.close();
            outputStream.close();
        } catch (Exception ex) {
            throw new RuntimeException("이미지 저장 실패");
        }
    }


    // TODO : 이미지 이름을 넘겨줄 필요가 있나...생각중/ 그냥 url을 넘겨도??
    public void deleteImage(Long postId, String imageName) {
        PostImageEntity postImageEntity = imageRepository.getByPostId(postId);
        String imageUrl = postImageEntity.getImgUrl();
        String getImageName = postImageEntity.getImgUrl().substring(imageUrl.lastIndexOf("_")+1);
        if(!getImageName.equals(imageName)) {
            throw new CustomException(CustomErrorCode.BAD_REQUEST);
        }
        imageRepository.delete(postImageEntity);
    }


}
