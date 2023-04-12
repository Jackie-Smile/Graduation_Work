package com.jackie.service;

import com.jackie.domain.CountryMap;

import java.util.List;

public interface CountryMapService
{
    public List<CountryMap> getChinaMap();
    public List<CountryMap> getUSAMap();
    public void updateChina();
    public void updateUSA();
}
