package com.example.userServ.service.impl;

import com.example.finalwork4.domain.Sol;
import com.example.finalwork4.domain.lim;
import com.example.finalwork4.domain.pyInf;
import com.example.userServ.controller.checker;
import com.example.userServ.dao.AccountDao;
import com.example.userServ.dao.pyMongoDao;
import com.example.userServ.domain.account;
import com.example.userServ.domain.diff;
import com.example.userServ.domain.manageUser;
import com.example.userServ.domain.pyDetail;
import com.example.userServ.service.AcServ;
import com.sun.glass.ui.View;
import org.aspectj.weaver.Checker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.*;

@Service("AcServ")
public class AcServImpl implements AcServ {

    @Autowired
    AccountDao accountDao;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    pyMongoDao pd;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate srt;

    @Override
    public List<account> show() {
        List<account> ac=accountDao.findAll();
        return ac;
    }

    @Override
    public String[] acCheck(String uname, String pas) {
        return new String[]{accountDao.check(uname, pas),accountDao.getId(uname, pas)};
    }
    @Override
    public byte[] genImg(int newId) throws Exception {
        try {
            byte[] data=accountDao.getImgD(String.valueOf(newId)).getData();
            return data;
        }catch (Exception e){e.printStackTrace();throw new Exception("genFail");}

    }
    @Override
    public List<pyDetail> getDetail(String uid) {
        return accountDao.getMyInf(uid);
    }

    @Override
    public void doDel(String id) {
        accountDao.doDel(id);
    }

    @Override
    public byte[] genLatex(String uid) throws Exception {
        try {
            byte[] data=accountDao.getLatex(uid).getImg();
            return data;
        }catch (Exception e){e.printStackTrace();throw new Exception("genFail");}
    }

    @Override
    public byte[] genDiff(String rowid) throws Exception {
        try {
            byte[] data=accountDao.getDiff(rowid).getDiffimg();
            //cd C:\Users\hy\Desktop\????????????\19\PyDiff && python temp.py --matha 'cos(x)' --uid 19 --rowid 2
            return data;
        }catch (Exception e){e.printStackTrace();
            //throw new Exception("genFail");
            System.out.println("rowid:"+rowid);
            throw e;
        }
    }

    @Override
    public List<diff> diffDetail(String uid) {
        return accountDao.diffInf(uid);
    }

    @Override
    public void diffDel(String id) {
        accountDao.diffDel(id);
    }

    @Override
    public lim getLim(String rowid) {
        return accountDao.getLim(rowid);
    }

    @Override
    public List<lim> getAllLim(String uid) {
        return accountDao.getAllLim(uid);
    }

    @Override
    public void LIMDel(String id) {
        accountDao.limDel(id);
    }
    @Override
    public Sol getSol(String id) {
        return accountDao.getSol(id);
    }

    @Override
    public List<Sol> getAllSol(String uid) {
        return accountDao.getAllSol(uid);
    }
    @Override
    public void SOLDel(String id) {
        accountDao.SOLDel(id);
    }

    @Override
    public void delUser(String uid) {
        pd.clean(uid);//??????mongoDB
        byte[] finalUid = uid.getBytes(StandardCharsets.UTF_8);
        //redisTemplate.delete(finalUid);
        srt.delete(uid);//??????redis
        System.out.println("????????????UID???:"+uid+" key:"+finalUid);
        accountDao.userDel(uid);//??????mysql
        //rmdir 19 /s/q   ???????????????
        String PATH= checker.PATH0;
        try {
            String command="";
            //final String PATH = "C:\\Users\\hy\\Desktop\\????????????";//????????????
            command= "cmd.exe /c cd "
                    + PATH //?????????????????????????????????
                    + " && rmdir "+uid
                    +" /s/q";
            //Xcopy C:\ test D:\test /E/H/C/I
            //cd C:\Users\hy\Desktop\???????????? && mkdir 20 && Xcopy 0 20 /E/H/C/I
            System.out.println(command);
            Process p = Runtime.getRuntime().exec(command);
            //*****************????????????*******************//
            p.waitFor();//?????????????????????????????????????????????????????????
            //******************************************//
        }catch (Exception e){
            System.out.println(e);
    }}

    @Override
    public List<manageUser> manageUser(String uid, String aut) {
        return accountDao.manageUsers(uid,aut);
    }

    @Override
    public List<String> getUa() {
        return accountDao.getAuts();
    }

    @Override
    public void revoke(String uid, String aut) {
        if(aut.equals("common")){return;}//common??????????????????
        try{accountDao.revoke(uid, aut);}catch (Exception e){e.printStackTrace();}
    }

    @Override
    public void grant(String uid, String aut) {
        try{accountDao.grant(uid, aut);}catch (Exception e){e.printStackTrace();}
    }

    @Override
    public void grantUpdate(String uid, String aut, String oldAut) {
        try{accountDao.grantUpdate(uid, aut,oldAut);}catch (Exception e){e.printStackTrace();}
    }
}
