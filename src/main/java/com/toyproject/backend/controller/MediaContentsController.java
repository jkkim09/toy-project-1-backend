package com.toyproject.backend.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toyproject.backend.dto.MediaContentsDto;
import com.toyproject.backend.dto.response.ResponseDto;
import com.toyproject.backend.entity.MediaContents;
import com.toyproject.backend.service.AwsService;
import com.toyproject.backend.service.MediaService;
import com.toyproject.backend.service.response.ResponseMessage;

@RestController
@RequestMapping("/api/media")
public class MediaContentsController {

	@Autowired
	MediaService mediaService;
	
	@Autowired
	ResponseMessage responseMessage;

	@Autowired
	AwsService awsService;
	
	@RequestMapping("/image/{mediaNumber}")
	public ResponseEntity<ResponseDto> getMediaImage(@PathVariable("mediaNumber") long mediaNumber) {
		return responseMessage.getResponseMessage(200, mediaService.getMediaNumberItem(mediaNumber));
	}
	
	@RequestMapping("/image/typeList/{typeList}")
	public ResponseEntity<ResponseDto> getMediaImageType(@PathVariable("typeList") String typeList) throws InterruptedException, ExecutionException {
		HashMap<String, Object> listMap = new HashMap<String, Object>();
		
		String[] searchList = typeList.replaceAll(" ", "").split(",");
		
		for (String search : searchList) {
			CompletableFuture<ArrayList<MediaContents>> re = mediaService.getMediaTypeItem(search);
			ArrayList<HashMap<String, Object>> reList = new ArrayList<HashMap<String,Object>>();
			
			ArrayList<MediaContents> mediaList =  re.get();
			
			for (MediaContents media : mediaList) {
				ObjectMapper oMapper = new ObjectMapper();
				@SuppressWarnings("unchecked")
				HashMap<String, Object> map = oMapper.convertValue(new MediaContentsDto(media), HashMap.class);
				reList.add(map);
			}
	
			listMap.put(search, reList);
		}
		return responseMessage.getResponseMessage(200, listMap);
	}
	
	@RequestMapping("/user/{userNumber}")
	public ResponseEntity<ResponseDto> getUserMediaImages(@PathVariable long userNumber) {
		return responseMessage.getResponseMessage(200, mediaService.getUserMedia(userNumber));
	}
	
	@RequestMapping(value="/aws/imageUpload", method=RequestMethod.POST)
	public void awsImgUpdate(@RequestParam("file") MultipartFile[] files, String bucketKey) throws IOException {
		awsService.uploadMultipartFile(files, "/jktest");
	}
}
