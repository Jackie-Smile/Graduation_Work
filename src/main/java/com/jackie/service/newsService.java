package com.jackie.service;

import com.jackie.domain.ChinaDaily;
import com.jackie.domain.News;

import java.util.List;

public interface newsService
{
    public List<News> getNews();
    public ChinaDaily getTodayData();
}
