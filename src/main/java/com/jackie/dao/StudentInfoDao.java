package com.jackie.dao;

import com.jackie.domain.ClockInInfo;

import java.util.List;

public interface StudentInfoDao
{
    int ClockIn(ClockInInfo clockInInfo);
    List<ClockInInfo> findAll();
}
