package com.example.userServ.dao;

import com.example.finalwork4.domain.MongoPyDetail;

import java.util.List;

public interface pyMongoDao {
    void newInf(MongoPyDetail py);

    void del(int id);

    void clean(String uid);
    List<MongoPyDetail> findDemoByUId(String uid);


}
