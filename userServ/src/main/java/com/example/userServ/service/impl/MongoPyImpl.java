package com.example.userServ.service.impl;

import com.example.finalwork4.domain.MongoPyDetail;
import com.example.userServ.dao.pyMongoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class MongoPyImpl implements pyMongoDao {
    @Autowired
    @Qualifier("TemplateTest")
    private MongoTemplate mongoTemplateTest;

    @Autowired
    @Qualifier("TemplateTest1")
    private MongoTemplate mongoTemplateTest1;
    @Override
    public void newInf(MongoPyDetail py) {

    }

    @Override
    public void del(int id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplateTest.findAllAndRemove(query, MongoPyDetail.class);
    }

    @Override
    public List<MongoPyDetail> findDemoByUId(String uid) {
        Query query = new Query(Criteria.where("uid").is(uid));
        System.out.println("the uid id:"+uid);
        return mongoTemplateTest.find(query,MongoPyDetail.class);
    }

}
