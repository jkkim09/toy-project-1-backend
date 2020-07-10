package com.toyproject.backend.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class MediaContents {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long mediaId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	User user;
	
	@Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp mediaUploadData;

	private String mediaType;
	
	private String mediaUrl;
}
