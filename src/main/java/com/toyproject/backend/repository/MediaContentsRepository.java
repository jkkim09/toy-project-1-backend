package com.toyproject.backend.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toyproject.backend.entity.MediaContents;
import com.toyproject.backend.entity.User;

public interface MediaContentsRepository extends JpaRepository<MediaContents, Long>{
	ArrayList<MediaContents> findByUser(User user);
	ArrayList<MediaContents> findByUserOrderByMediaUploadDataDesc(User user);
	MediaContents findByMediaId(long number);
	ArrayList<MediaContents> findByMediaType(String type);
}
