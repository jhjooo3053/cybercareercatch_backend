package com.ccc.company.dao;

public class CompanyInfoDAO {
    private final SqlSessionFactory sqlSessionFactory;

    public CompanyInfoDAO() {
        this.sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();
    }

    
}