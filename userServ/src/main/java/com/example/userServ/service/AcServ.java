package com.example.userServ.service;

import com.example.finalwork4.domain.Sol;
import com.example.finalwork4.domain.lim;
import com.example.finalwork4.domain.pyInf;
import com.example.userServ.domain.account;
import com.example.userServ.domain.diff;
import com.example.userServ.domain.pyDetail;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface AcServ {
    List<account> show();
    String[] acCheck(String uname,String pas);
    byte[] genImg(int newId) throws Exception;
    List<pyDetail> getDetail(String uid);
    void doDel(String id);
    byte[] genLatex(String uid) throws Exception;
    byte[] genDiff(String rowid) throws Exception;
    List<diff> diffDetail(String uid);
    void diffDel(String id);
    lim getLim(String rowid);
    List<lim> getAllLim(String uid);
    void LIMDel(String id);
    Sol getSol(String uid);
    List<Sol> getAllSol(String uid);
    void SOLDel(String uid);
    void delUser(String uid);
}
