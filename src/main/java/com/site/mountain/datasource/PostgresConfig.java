package com.site.mountain.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
//@MapperScan(basePackages = "zsh.demos.postgres.dao.mapper", sqlSessionFactoryRef = "pgSqlSessionFactory")
@MapperScan(basePackages = "com.site.mountain.dao.mysql", sqlSessionTemplateRef = "pgSqlSessionTemplate")
public class PostgresConfig {

//    @Value("${mybatis.mapper-locations}")
    private String MAPPER_LOCATION = "classpath:mybatis/mapper/mysql/*.xml";
//    @Value("${mybatis.type-handlers-package}")
//    private String TYPE_HANDLERS_PACKAGE;

    // @Primary 确定此数据源为master
    @Bean(name = "pgDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.postgres")
    @Primary
    public DruidDataSource pgDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "pgSqlSessionFactory")
    @Primary
    public SqlSessionFactory postgresSqlSessionFactory(@Qualifier("pgDataSource") DruidDataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);

        // case change.
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);

        sqlSessionFactoryBean.setConfiguration(configuration);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
//        sqlSessionFactoryBean.setTypeHandlersPackage(TYPE_HANDLERS_PACKAGE);
        return sqlSessionFactoryBean.getObject();
    }

    //配置事务管理器
    @Bean(name = "pgSqlTransactionManager")
    @Primary
    public DataSourceTransactionManager pgSqlTransactionManager(@Qualifier("pgDataSource") DruidDataSource druidDataSource) {
        return new DataSourceTransactionManager(druidDataSource);
    }

    @Bean(name = "pgSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate pgSqlSessionTemplate(@Qualifier("pgSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}