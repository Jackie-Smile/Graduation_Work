package com.jackie.service.impl;

import com.jackie.dao.PredictDao;
import com.jackie.domain.Predict;
import com.jackie.service.PredictService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service("PredictService")
public class PredictServiceImpl implements PredictService
{

    @Override
    public List<Predict> findAll()
    {
        try {
            InputStream in= Resources.getResourceAsStream("SqlMapConfig.xml");
            SqlSessionFactoryBuilder build=new SqlSessionFactoryBuilder();
            SqlSessionFactory factory=build.build(in);
            SqlSession sqlSession=factory.openSession();
            PredictDao predictDao =sqlSession.getMapper(PredictDao.class);
            return predictDao.findAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
