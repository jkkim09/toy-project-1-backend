package com.toyproject.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toyproject.backend.dto.response.ResponseDto;
import com.toyproject.backend.service.MediaService;
import com.toyproject.backend.service.response.ResponseMessage;

@RestController
@RequestMapping("/api/media")
public class MediaContentsController {
	
	@Autowired
	MediaService mediaService;
	
	@Autowired
	ResponseMessage responseMessage;

	@RequestMapping("/image/{mediaNumber}")
	public void getMediaImage(@PathVariable("mediaNumber") String mediaNumber) {
		System.out.println(mediaNumber);
	}
	
	@RequestMapping("/user/{userNumber}")
	public ResponseEntity<ResponseDto> getUserMediaImages(@PathVariable long userNumber) {
		return responseMessage.getResponseMessage(200, mediaService.getUserMedia(userNumber));
	}
}
