package com.imnu.service;

import java.util.List;

import com.project.model.Person;
import com.project.model.movieInfo;

public interface movieInfoService {

	/**
     * 查询所有电影信息
     * @return
     */
	List<movieInfo> allMovieInfo();
    
    /**
     * 添加电影
     * @param person
     * @return
     */
	void addMovieInfo(movieInfo movieInfo);
    
    /**
     * 删除电影
     * @param person
     * @return
     */
	void delMovieInfo(movieInfo movieInfo);
    
    /**
     * 修改电影信息
     * @param person
     * @return
     */
	void updateMovieInfo(movieInfo movieInfo);
    
    /**
     * 根据id查询电影信息
     * @return
     */
	movieInfo queryMovieInfoById(int id);
	
	/**
     * 根据moviename查询电影信息
     * @return
     */
    List<movieInfo> queryMoiveForName(String moivename);
    
    /**
     * 修改电影数量信息
     * @param movieInfo
     */
    public void updateMovieInfoSum(movieInfo movieInfo);
	
}
