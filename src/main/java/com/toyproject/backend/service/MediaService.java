package com.toyproject.backend.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toyproject.backend.dto.MediaContentsDto;
import com.toyproject.backend.entity.MediaContents;
import com.toyproject.backend.entity.User;
import com.toyproject.backend.repository.MediaContentsRepository;
import com.toyproject.backend.repository.UserRepository;

@Service
public class MediaService {	
	private static final Logger LOGGER = LoggerFactory.getLogger(MediaService.class);
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MediaContentsRepository mediaContentsRepository;
	
	public ArrayList<HashMap<String, Object>> getUserMedia(long userId) {
		User user = new User();
		user.setId(userId);
		
		ArrayList<MediaContents> list = mediaContentsRepository.findByUserOrderByMediaUploadDataDesc(user);
		ArrayList<HashMap<String, Object>> reList = new ArrayList<HashMap<String,Object>>();
		
		for (MediaContents item : list) {
			ObjectMapper oMapper = new ObjectMapper();
			
			@SuppressWarnings("unchecked")
			HashMap<String, Object> map = oMapper.convertValue(new MediaContentsDto(item), HashMap.class);
			reList.add(map);
		}
		return reList;
	}
	
	public MediaContentsDto getMediaNumberItem(long number) { 
		return new MediaContentsDto(mediaContentsRepository.findByMediaId(number));
	}
	
	@Async
	public CompletableFuture<ArrayList<MediaContents>> getMediaTypeItem(String type) {
		LOGGER.info(Thread.currentThread().getName() + " : " + type);
		return CompletableFuture.completedFuture(mediaContentsRepository.findByMediaType(type));
	}
}
