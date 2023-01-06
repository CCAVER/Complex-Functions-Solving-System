package com.example.pyConn.controller;
import com.example.finalwork4.domain.MongoPyDetail;
import com.example.finalwork4.domain.Sol;
import com.example.finalwork4.domain.lim;
import com.example.finalwork4.domain.pyInf;
import com.example.pyConn.dao.pyMongoDao;
import com.example.pyConn.service.pyServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//@Controller
@RequestMapping("pyReq")
@RestController
public class pyControl {

    @Autowired
    pyServ pyServ;
    @Autowired
    pyMongoDao pd;

    @RequestMapping (value = "genId",method = RequestMethod.POST)
    public String getNewId(@RequestBody pyInf pi) throws Exception {
        String s = String.valueOf(pyServ.generate(pi));
        return s;
        //return "1";
    }
    @RequestMapping(value = "fix",method = RequestMethod.POST)
    public String fix(@RequestBody pyInf pi,int rowid) throws Exception {
        String s = String.valueOf(pyServ.fix(pi,rowid));
        return s;
    }

    @RequestMapping(value = "lat",method = RequestMethod.POST)
    public void latex(@RequestBody String uid,String matha) throws Exception {
        pyServ.latex(uid,matha);
        return ;
    }
    @RequestMapping(value = "diff",method = RequestMethod.POST)
    public String diff(@RequestBody String uid,String matha) throws Exception {
        String s = String.valueOf(pyServ.diff(uid,matha));
        return s;
    }
    @RequestMapping(value = "dolim",method = RequestMethod.POST)
    public String dolim(@RequestBody lim lm, String uid) throws Exception {
        //String s = String.valueOf(pyServ.diff(uid,matha));
        String rowid=pyServ.Lim(lm);
        return rowid;
    }
    @RequestMapping(value = "doSol",method = RequestMethod.POST)
    public String doSol(@RequestBody Sol sol, String uid) throws Exception {
        //String s = String.valueOf(pyServ.diff(uid,matha));
        String rowid=pyServ.Solve(sol);
        return rowid;
    }
}
