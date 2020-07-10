package com.toyproject.backend.dto;

import java.sql.Timestamp;

import com.toyproject.backend.entity.MediaContents;

import lombok.Getter;

@Getter
public class MediaContentsDto {
	private long mediaId;
	
	private long userId;
	
	private Timestamp mediaUploadData;
	
	private String mediaType;
	
	private String mediaUrl;
	
	public MediaContentsDto(MediaContents item) {
		this.mediaId = item.getMediaId();
		this.userId = item.getUser().getId();
		this.mediaUploadData = item.getMediaUploadData();
		this.mediaType = item.getMediaType();
		this.mediaUrl = item.getMediaUrl();
	}
}
