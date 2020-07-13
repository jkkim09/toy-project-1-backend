package com.toyproject.backend.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
@Transactional
public class AwsService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AwsService.class);
	
	@Autowired
	private AmazonS3 s3Client;
	
	@Value("${aws.s3.bucket}")
	private String bucketName;

	
	public void uploadMultipartFile(MultipartFile[] files,String bucketKey) throws IOException{
		System.out.println("AWS Service : " + s3Client);
		System.out.println(bucketName + " , " + files.length);
		ObjectMetadata omd = new ObjectMetadata();
		
		for(int i=0; i<files.length; i++) {
			omd.setContentType(files[i].getContentType());
			omd.setContentLength(files[i].getSize());
			omd.setHeader("filename",files[i].getOriginalFilename());
			s3Client.putObject(new PutObjectRequest(bucketName+bucketKey,files[i].getOriginalFilename(),files[i].getInputStream(),omd));
			uploadFileData();
		}
	}
	
	@Async
	public void uploadFileData() {
		LOGGER.info(Thread.currentThread().getName());
	}
}