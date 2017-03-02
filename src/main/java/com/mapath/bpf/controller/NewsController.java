package com.mapath.bpf.controller;

import com.mapath.bpf.model.NewsModel;
import com.mapath.bpf.service.AdminService;
import com.mapath.bpf.service.NewsService;
import com.mapath.bpf.service.impl.NewsServiceImpl;
import com.mapath.bpf.utils.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhouxiaobo on 2017/2/28.
 */

@Controller
public class NewsController {

     private Logger logger = LoggerFactory.getLogger(NewsController.class);


    @Autowired
    private NewsService newsService;

    /*测试跳转到新闻主体页面(只作为跳转使用)*/
    @RequestMapping(value = "newsContent.html")
    public String newsContent(Model model){

        return "newscontent"; //新闻主体页面
    }

    /*ajax跳转添加数据*/
    @RequestMapping(value = "newsContentAJAX")
    @ResponseBody
    public List<NewsModel> newsContentAJAX(Model model,@RequestParam String keyword){
        List<NewsModel> newsModels=newsService.newslist(keyword);

        return newsModels;
    }

    @RequestMapping(value = "newsList")
    public String newsList(){
        logger.info("enter news page");
        return "news";
    }

    @RequestMapping(value = "queryNews", method = RequestMethod.POST)
    public String queryNews(String keyword, Model model){
        logger.info("execute query");

        List<NewsModel> records = newsService.newslist(keyword);
        logger.info((records == null)?"0条":records.size() + "条");

        model.addAttribute("news", records);
        return "news";
    }
}
