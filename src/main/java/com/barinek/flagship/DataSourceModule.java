package com.barinek.flagship;

import com.barinek.flagship.projects.ProjectDAO;
import com.barinek.flagship.projects.ProjectMapper;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.guice.MyBatisModule;
import org.mybatis.guice.datasource.builtin.PooledDataSourceProvider;

import static com.google.inject.name.Names.named;

public class DataSourceModule extends MyBatisModule {
    @Override
    protected void initialize() {
        bindConstant().annotatedWith(named("JDBC.driver")).to("com.mysql.jdbc.Driver");
        bindDataSourceProviderType(PooledDataSourceProvider.class);
        bindTransactionFactoryType(JdbcTransactionFactory.class);
        addMapperClass(ProjectMapper.class);
        bind(ProjectDAO.class);
    }
}
