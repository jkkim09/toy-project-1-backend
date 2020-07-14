package com.toyproject.backend.service;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.toyproject.backend.dto.SessionUser;
import com.toyproject.backend.entity.MediaContents;

@Service
public class AwsService {
	@Autowired
	private AmazonS3 s3Client;
	
	@Value("${aws.s3.bucket}")
	private String bucketName;
	
	@Value("${aws.cloudfront.domain}")
	private String cloudFrontDomain;
	
	@Autowired
	private MediaService mediaService;
	
	public void uploadMultipartFile(MultipartFile[] files, String mediaType, SessionUser user) throws IOException{
		ObjectMetadata omd = new ObjectMetadata();
		String bucketPath = "/media/" + mediaType + "/" + user.getId();
		
		@SuppressWarnings("unchecked")
		CompletableFuture<MediaContents>[] fAll = new CompletableFuture[files.length];
		
		for(int i=0; i<files.length; i++) {
			omd.setContentType(files[i].getContentType());
			omd.setContentLength(files[i].getSize());
			omd.setHeader("filename",files[i].getOriginalFilename());
			
			String filename = files[i].getOriginalFilename();
			String fullPath = bucketPath + "/" + filename;
			
			s3Client.putObject(new PutObjectRequest(bucketName + bucketPath, filename, files[i].getInputStream(), omd));
			fAll[i] = mediaService.setMediaInfo(mediaType, cloudFrontDomain + fullPath, user.getId());
		}
		CompletableFuture.allOf(fAll).join();
	}
}