package com.ccc.expo.dao;


import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.ccc.common.config.MyBatisConfig;
import com.ccc.expo.dto.ExpoCalendarDTO;
import com.ccc.expo.dto.ExpoDetailDTO;

public class ExpoDAO {
    private SqlSession sqlSession;

    public ExpoDAO() {
        sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
    }

    public List<ExpoCalendarDTO> selectExpoCalendarList() {
        return sqlSession.selectList("expo.selectExpoCalendarList");
    }

    public List<ExpoDetailDTO> selectExpoDetailList(int expoNumber) {
        return sqlSession.selectList("expo.selectExpoDetailList", expoNumber);
    }
}