package com.project.dao;

import java.util.List;

import com.project.model.Person;
import com.project.model.movieInfo;

public interface movieInfoMapper {

	/**
     * 查询所有电影信息
     * @return
     */
    List<movieInfo> allMovieInfo();
    
    /**
     * 添加电影
     * @return
     */
    void addMovieInfo(movieInfo movieInfo);
    
    /**
     * 删除电影
     * @return
     */
    void delMovieInfo(movieInfo movieInfo);
    
    /**
     * 修改电影
     * @return
     */
    void updateMovieInfo(movieInfo movieInfo);
    
    /**
     * 修改电影票数量
     * @return
     */
    void updateMovieInfoSum(movieInfo movieInfo);
    
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
	
}
