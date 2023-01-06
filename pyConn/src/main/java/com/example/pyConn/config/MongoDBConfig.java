package com.example.pyConn.config;

import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MongoDBConfig {

    @Value("${mongodb.url}")
    private String mongoUrl;

    @Value("${mongodb.database.test}")
    private String test;

    @Value("${mongodb.database.test1}")
    private String test1;

    @Primary
    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(
                new SimpleMongoClientDatabaseFactory(MongoClients.create(mongoUrl), test));
    }

    @Bean(name = "TemplateTest")//数据源1的database使用test
    public  MongoTemplate mongoTemplateTest() {
        return new MongoTemplate(
                new SimpleMongoClientDatabaseFactory(MongoClients.create(mongoUrl), test));
    }

    @Bean(name = "TemplateTest1")//数据源2的database使用test1
    public MongoTemplate mongoTemplateTest1() {
        return new MongoTemplate(
                new SimpleMongoClientDatabaseFactory(MongoClients.create(mongoUrl), test1));
    }
    // 开启事务
    @Bean
    MongoTransactionManager transactionManager(MongoDatabaseFactory factory){
        return new MongoTransactionManager(factory);
    }
}