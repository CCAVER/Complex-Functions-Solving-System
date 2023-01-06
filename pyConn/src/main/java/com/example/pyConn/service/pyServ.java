package com.example.pyConn.service;

import com.example.finalwork4.domain.Sol;
import com.example.finalwork4.domain.lim;
import com.example.finalwork4.domain.pyInf;

import java.sql.SQLException;
import java.util.HashMap;

public interface pyServ {
    int generate(pyInf inf) throws Exception;
    int fix(pyInf inf,int rowid) throws Exception;

    void latex(String uid,String matha) throws Exception;

    int diff(String uid,String matha) throws Exception;

    String Lim(lim limVal) throws Exception;
    String Solve(Sol sol) throws Exception;
}
