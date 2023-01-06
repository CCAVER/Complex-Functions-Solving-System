package com.example.userServ.service.impl;

import com.example.userServ.dao.DemoDao;
import com.example.userServ.domain.DemoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DemoDaoImpl implements DemoDao {

    @Autowired
    @Qualifier("TemplateTest")
    private MongoTemplate mongoTemplateTest;

    @Autowired
    @Qualifier("TemplateTest1")
    private MongoTemplate mongoTemplateTest1;

    @Override
    public void saveDemo(DemoEntity demoEntity) {
        mongoTemplateTest.save(demoEntity);
    }

    @Override
    public void removeDemoById(int id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplateTest.findAllAndRemove(query,DemoEntity.class);
    }

    @Override
    public void updateDemo(DemoEntity demoEntity) {
        Query query = new Query(Criteria.where("id").is(demoEntity.getId()));

        Update update = new Update();
        update.set("title", demoEntity.getTitle());
        update.set("description", demoEntity.getDescription());
        update.set("by", demoEntity.getBy());
        update.set("url", demoEntity.getUrl());
        update.set("corn", demoEntity.getCorn());

        mongoTemplateTest.updateFirst(query, update, DemoEntity.class);
    }

    @Override
    public DemoEntity findDemoById(int id) {
        Query query = new Query(Criteria.where("id").is(id));
        DemoEntity demoEntity = mongoTemplateTest.findOne(query, DemoEntity.class);
        return demoEntity;
    }

    @Override
    public List<DemoEntity> findDemos() {
        return mongoTemplateTest.findAll(DemoEntity.class);
    }
}
