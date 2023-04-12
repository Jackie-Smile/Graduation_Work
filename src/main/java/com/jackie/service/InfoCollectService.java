package com.jackie.service;

import com.jackie.domain.ClockInInfo;

import java.util.List;

public interface InfoCollectService
{
    int ClockIn(ClockInInfo clockInInfo);
    List<ClockInInfo> findAll();
}
