package com.project.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.Person;
import com.project.model.movieInfo;
import com.project.service.movieInfoService;

@RestController
@RequestMapping("/movieInfoController")
public class movieInfoController {
	
	private movieInfoService movieInfoService;

	public movieInfoService getMovieInfoService() {
		return movieInfoService;
	}

	@Autowired
	public void setMovieInfoService(movieInfoService movieInfoService) {
		this.movieInfoService = movieInfoService;
	}
	
	@RequestMapping("/allMovieInfo")
    @ResponseBody
    public List<movieInfo> allMovieInfo(){
    	List<movieInfo> movieList=movieInfoService.allMovieInfo();
        return movieList;
    }
    
    @RequestMapping("/addMovieInfo")
    @ResponseBody
    public void addMovieInfo(String moviename,String content,Date starttime,Date endtime,String state,int sumticket){
    	movieInfo movieInfo =new movieInfo();
    	movieInfo.setMoviename(moviename);
    	movieInfo.setContent(content);
    	movieInfo.setStarttime(starttime);
    	movieInfo.setEndtime(endtime);
    	movieInfo.setState(state);
    	movieInfo.setSumticket(sumticket);
    	movieInfo.setPurchasedticket(0);
    	movieInfo.setSurplusticket(sumticket);
    	movieInfoService.addMovieInfo(movieInfo);
    }
    
    @RequestMapping("/delMovieInfo")
    @ResponseBody
    public void delMovieInfo(int id){
    	movieInfo movieInfo =new movieInfo();
    	movieInfo.setId(id);
    	movieInfoService.delMovieInfo(movieInfo);
    }
    
    @RequestMapping("/updateMovieInfo")
    @ResponseBody
    public void updateMovieInfo(int id,String moviename,String content,Date starttime,Date endtime,String state,int sumticket){
    	System.out.println(moviename);
    	movieInfo movieInfo =new movieInfo();
    	movieInfo.setId(id);
    	movieInfo.setMoviename(moviename);
    	movieInfo.setContent(content);
    	movieInfo.setStarttime(starttime);
    	movieInfo.setEndtime(endtime);
    	movieInfo.setState(state);
    	movieInfo.setSumticket(sumticket);
    	movieInfoService.updateMovieInfo(movieInfo);
    }
    
    @RequestMapping("/queryMovieInfoById")
    @ResponseBody
    public movieInfo queryMovieInfoById(int id){
    	movieInfo movieInfo=movieInfoService.queryMovieInfoById(id);
        return movieInfo;
    }
    
    @RequestMapping("/queryMoiveForName")
    @ResponseBody
    public List<movieInfo> queryMoiveForName(String moivename){
    	List<movieInfo> person=movieInfoService.queryMoiveForName(moivename);
        return person;
    }

}
