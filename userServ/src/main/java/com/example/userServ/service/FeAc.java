package com.example.userServ.service;

import com.example.finalwork4.domain.Sol;
import com.example.finalwork4.domain.lim;
import com.example.finalwork4.domain.pyInf;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "pyConn")
public interface FeAc {

    @RequestMapping(value = "pyReq/genId", method = RequestMethod.POST)
    String getNewId(@RequestBody pyInf pi) throws Exception;

    @RequestMapping(value = "pyReq/fix", method = RequestMethod.POST)
    String getfix(@RequestBody pyInf pi, @RequestParam(value = "rowid") int rowid) throws Exception;

    @RequestMapping(value = "pyReq/lat", method = RequestMethod.POST)
    String golatex(@RequestBody String uid, @RequestParam(value = "matha") String matha) throws Exception;

    @RequestMapping(value = "pyReq/diff", method = RequestMethod.POST)
    String goDiff(@RequestBody String uid, @RequestParam(value = "matha") String matha) throws Exception;

    @RequestMapping(value = "pyReq/dolim", method = RequestMethod.POST)
    String goLim(@RequestBody lim lm, @RequestParam(value = "uid") String uid) throws Exception;
    @RequestMapping(value = "pyReq/doSol", method = RequestMethod.POST)
    String goSol(@RequestBody Sol sol, @RequestParam(value = "uid") String uid) throws Exception;
}
