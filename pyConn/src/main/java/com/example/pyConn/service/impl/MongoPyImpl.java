package com.example.pyConn.service.impl;

import com.example.finalwork4.domain.MongoPyDetail;
import com.example.pyConn.dao.pyMongoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

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
        mongoTemplateTest.save(py);
    }
}
