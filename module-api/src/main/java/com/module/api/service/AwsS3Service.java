package com.module.api.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.module.api.exception.s3.S3ErrorCode;
import com.module.api.exception.s3.S3Exception;
import lombok.RequiredArgsConstructor;
import org.cef.handler.CefLoadHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.server.header.StaticServerHttpHeadersWriter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AwsS3Service {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;

    public String upload(MultipartFile image) throws IOException {
        String fileName = image.getOriginalFilename();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(image.getSize());
        metadata.setContentType(image.getContentType());

        amazonS3.putObject(bucket, fileName, image.getInputStream(), metadata);
        return amazonS3.getUrl(bucket, fileName).toString();

    }

//    private String uploadImage(MultipartFile image) {
//        this.validateImageFileExtention(image.getOriginalFilename());
//        try {
//            return this.uploadImageToS3(image);
//        } catch (IOException e) {
//            throw new AmazonS3Exception("IO_EXCEPTION_ON_IMAGE_UPLOAD");
//        }
//    }

    private void validateImageFileExtention(String filename) {int lastDotIndex = filename.lastIndexOf(".");
        if (lastDotIndex == -1) {
            throw new S3Exception(S3ErrorCode.INVALID_FILE_EXTENTION);
        }

        String extention = filename.substring(lastDotIndex + 1).toLowerCase();
        List<String> allowedExtentionList = Arrays.asList("jpg", "jpeg", "png", "gif");

        if (!allowedExtentionList.contains(extention)) {
            throw new AmazonS3Exception("INVALID_FILE_EXTENTION");
        }
    }

    private String uploadImageToS3(MultipartFile image) throws IOException {

        // String extention = originalFilename.substring(originalFilename.lastIndexOf(".")); //확장자 명
        String s3FileName = UUID.randomUUID() + "_" + image.getOriginalFilename();

        BufferedInputStream inputStream = new BufferedInputStream(image.getInputStream());
        byte[] bytes = new byte[1024 * 4];
//        int bytesRead;

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("image/");
        metadata.setContentLength(bytes.length);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

        try{
            PutObjectRequest putObjectRequest =
                    new PutObjectRequest(bucket, s3FileName, byteArrayInputStream, metadata)
                            .withCannedAcl(CannedAccessControlList.PublicRead);
            amazonS3.putObject(putObjectRequest); // put image to S3
        }catch (Exception e){
            throw new AmazonS3Exception("PUT_OBJECT_EXCEPTION");
        }finally {
            byteArrayInputStream.close();
            inputStream.close();
        }

        return amazonS3.getUrl(bucket, s3FileName).toString();
    }

    public void deleteImageFromS3(String imageAddress){
        String key = getKeyFromImageAddress(imageAddress);
        try{
            amazonS3.deleteObject(new DeleteObjectRequest(bucket, key));
        }catch (Exception e){
            throw new AmazonS3Exception("IO_EXCEPTION_ON_IMAGE_DELETE");
        }
    }

    private String getKeyFromImageAddress(String imageAddress){
        try{
            URL url = new URL(imageAddress);
            String decodingKey = URLDecoder.decode(url.getPath(), "UTF-8");
            return decodingKey.substring(1); // 맨 앞의 '/' 제거
        }catch (MalformedURLException | UnsupportedEncodingException e){
            throw new AmazonS3Exception("IO_EXCEPTION_ON_IMAGE_DELETE");
        }
    }

}
