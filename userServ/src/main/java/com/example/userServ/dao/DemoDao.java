package com.example.userServ.dao;

import com.example.userServ.domain.DemoEntity;

import java.util.List;

public interface DemoDao {
    void saveDemo(DemoEntity demoEntity);

    void removeDemoById(int id);

    void updateDemo(DemoEntity demoEntity);

    DemoEntity findDemoById(int id);

    List<DemoEntity> findDemos();
}
