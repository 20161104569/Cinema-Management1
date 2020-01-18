package com.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.PersonMapper;
import com.project.model.movieInfo;
import com.project.service.movieInfoService;
import com.project.dao.movieInfoMapper;

@Service("movieInfoService")
public class movieInfoServiceImpl implements movieInfoService{
	
	@Autowired
	private movieInfoMapper movieInfoMapper;
	
	public movieInfoMapper getMovieInfoMapper() {
		return movieInfoMapper;
	}

	public void setMovieInfoMapper(movieInfoMapper movieInfoMapper) {
		this.movieInfoMapper = movieInfoMapper;
	}

	@Override
	public List<movieInfo> allMovieInfo() {
		// TODO Auto-generated method stub
		return movieInfoMapper.allMovieInfo();
	}

	@Override
	public void addMovieInfo(movieInfo movieInfo) {
		// TODO Auto-generated method stub
		movieInfoMapper.addMovieInfo(movieInfo);
	}

	@Override
	public void delMovieInfo(movieInfo movieInfo) {
		// TODO Auto-generated method stub
		movieInfoMapper.delMovieInfo(movieInfo);
	}

	@Override
	public void updateMovieInfo(movieInfo movieInfo) {
		// TODO Auto-generated method stub
		movieInfoMapper.updateMovieInfo(movieInfo);
	}

	@Override
	public movieInfo queryMovieInfoById(int id) {
		// TODO Auto-generated method stub
		return movieInfoMapper.queryMovieInfoById(id);
	}
}
