package com.example.userServ.controller;

import com.example.finalwork4.domain.Sol;
import com.example.finalwork4.domain.lim;
import com.example.finalwork4.domain.pyInf;
import com.example.userServ.domain.diff;
import com.example.userServ.domain.manageUser;
import com.example.userServ.domain.pyDetail;
import com.example.userServ.service.AcServ;
import com.example.userServ.service.FeAc;
import com.example.userServ.service.MySecure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class dataControler {
    @Autowired
    AcServ as;

    @Autowired
    FeAc feAc;

    @Autowired
    MySecure mySecure;

    @PostMapping(value = "/nowInf")
    public Map<String, String> nowInf(HttpSession session) {
        pyInf pi = (pyInf) session.getAttribute("pi");
        Map<String, String> map = new HashMap<String, String>();
        if(!((String)session.getAttribute("read")).equals("readonly")){
        map.put("quality", String.valueOf(pi.getQuality()));
        map.put("fname", pi.getFname());
        map.put("uid", pi.getUid());
        map.put("mathm", pi.getMatha());
        map.put("lent", String.valueOf(pi.getLent()));
        map.put("hei", String.valueOf(pi.getHei()));
        map.put("maxi", String.valueOf(pi.getMaxi()));
        map.put("mini", String.valueOf(pi.getMini()));
        }
        if(((String)session.getAttribute("read")).equals("readonly")){
            Map<String,String[]> map2= (Map<String, String[]>) session.getAttribute("mywork");
            String[] pd=map2.get(session.getAttribute("newid"));
            map.put("quality",pd[4]);
            map.put("fname",pd[0]);
            map.put("uid", (String) session.getAttribute("uid"));
            map.put("mathm",pd[1]);
            map.put("lent",pd[6]);
            map.put("hei",pd[5]);
            map.put("maxi",pd[3]);
            map.put("mini",pd[2]);
        }
        return map;
    }

    @PostMapping("mywork0")//已弃用
    public Map<String, String[]> mywork(HttpSession session) {
        String uid = (String) session.getAttribute("uid");
        List<pyDetail> pd = as.getDetail(uid);
        Map<String, String[]> map = new HashMap<String, String[]>();
        for (pyDetail infs : pd) {
            map.put(String.valueOf(infs.getId()),
                    new String[]{
                            infs.getPyname(),
                            infs.getMatha(),
                            String.valueOf(infs.getMini()),
                            String.valueOf(infs.getMaxi()),
                            String.valueOf(infs.getQal()),
                            String.valueOf(infs.getWei()),
                            String.valueOf(infs.getLent())
                    });
        }
        session.setAttribute("mywork", map);
        return map;

    }
    @PostMapping("diffwork")
    public Map<String, String[]> diffwork(HttpSession session) {
        String uid = (String) session.getAttribute("uid");
        List<diff> pd = as.diffDetail(uid);
        Map<String, String[]> map = new HashMap<String, String[]>();
        for (diff infs : pd) {
            map.put(String.valueOf(infs.getId()),
                    new String[]{
                            infs.getFx(),
                            infs.getDiff()
                    });
        }
        //session.setAttribute("mywork", map);
        return map;

    }
    @PostMapping("diffdel")
    public void diffDel(String rowid) {
        as.diffDel(rowid);

    }
    @PostMapping("/dolim")
    public Map<String, String[]> dolim(String matha,String lim,String sym,String rowid0,HttpSession session) throws Exception {
        //as.diffDel(rowid);
        String uid="0";
        //uid="1";matha="sin(x)";
        try{uid= (String) session.getAttribute("uid");}catch (Exception e){e.printStackTrace();}
        if(matha==null||matha.equals("")){matha="sin(x)";}
        if(lim==null||lim.equals("")){matha="sin(x)";lim="0";}
        if(sym==null||sym.equals("")){matha="sin(x)";sym="x";}
        String rowid=feAc.goLim(new lim(uid,matha,lim,sym),uid);
        if(rowid0!=null){rowid=rowid0;}//查询输入
        Map<String, String[]> map = new HashMap<String, String[]>();
        lim infs=as.getLim(rowid);
        map.put(String.valueOf(infs.getId()),
                new String[]{
                        String.valueOf(infs.getId()),
                        String.valueOf(infs.getUid()),
                        infs.getMatha(),
                        String.valueOf(infs.getName()),
                        infs.getResult(),
                        infs.getLim(),
                        infs.getSym()
                });
        return map;

    }
    @PostMapping("/listlim")
    public Map<String, String[]> listlim(HttpSession session) throws Exception {
        //as.diffDel(rowid);
        String uid="0";
        //uid="1";matha="sin(x)";
        try{uid= (String) session.getAttribute("uid");}catch (Exception e){e.printStackTrace();}
        Map<String, String[]> map = new HashMap<String, String[]>();
        List<lim> inf=as.getAllLim(uid);
        for (lim infs : inf) {
            map.put(String.valueOf(infs.getId()),
                    new String[]{
                            String.valueOf(infs.getId()),
                            String.valueOf(infs.getUid()),
                            infs.getMatha(),
                            String.valueOf(infs.getName()),
                            infs.getResult(),
                            infs.getLim(),
                            infs.getSym()
                    });
        }
        return map;
    }
    @PostMapping("limdel")
    public void LimDel(String rowid) {
        as.LIMDel(rowid);}
    @PostMapping("/doSol")
    public Map<String, String[]> doSol(String matha,String sym,String f,String type,HttpSession session) throws Exception {
        //as.diffDel(rowid);
        String uid="0";
        //uid="1";matha="sin(x)";
        try{uid= (String) session.getAttribute("uid");}catch (Exception e){e.printStackTrace();}
        if(matha==null||matha.equals("")){matha="sin(x)";}
        if(sym==null||sym.equals("")){matha="sin(x)";sym="x";}
        String rowid=feAc.goSol(new Sol(uid,matha,type,sym,f),uid);
        Map<String, String[]> map = new HashMap<String, String[]>();
        Sol infs=as.getSol(rowid);
        map.put(String.valueOf(infs.getId()),
                new String[]{
                        String.valueOf(infs.getId()),
                        String.valueOf(infs.getUid()),
                        infs.getMatha(),
                        String.valueOf(infs.getName()),
                        infs.getRes(),
                        infs.getType(),
                        infs.getSym(),
                        infs.getFuc()
                });
        return map;

    }
    @PostMapping("/solList")
    public Map<String, String[]> solList(HttpSession session) throws Exception {
        //as.diffDel(rowid);
        String uid="0";
        //uid="1";matha="sin(x)";
        try{uid= (String) session.getAttribute("uid");}catch (Exception e){e.printStackTrace();}
        Map<String, String[]> map = new HashMap<String, String[]>();
        List<Sol> inf=as.getAllSol(uid);
        for (Sol infs : inf) {
            map.put(String.valueOf(infs.getId()),
                    new String[]{
                            String.valueOf(infs.getId()),
                            String.valueOf(infs.getUid()),
                            infs.getMatha(),
                            String.valueOf(infs.getName()),
                            infs.getRes(),
                            infs.getType(),
                            infs.getSym(),
                            infs.getFuc()
                    });
        }
        return map;
    }
    @PostMapping("soldel")
    public void solDel(String rowid) {
        as.SOLDel(rowid);}
    @PostMapping("manageUser")
    public Map<String, String[]> manageUser(HttpSession session) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Map<String, String[]> map = new HashMap<String, String[]>();
        String curAut= (String) session.getAttribute("curAut");

        String uid="";
        String[] tmp=mySecure.login(authentication.getName());//获取UID
        if(tmp[0].equals("true")){
            uid=tmp[1];}else {throw new Exception("无法获取uid");}
        List<manageUser> users = null;

        int c=0;//计数器
//        for (Object auth : authentication.getAuthorities()) {
//            System.out.println("the auth in getter:" + auth.toString());
//            if(auth.toString().equals(curAut)){
//                if(auth.toString().equals("admin")){users=as.manageUser(uid,"admin|owner");}//不能管理权限比自己大的角色以及自己权限；写在此处使得更高以及同等权限不会显示
//                if(auth.toString().equals("owner")){users=as.manageUser(uid,"owner");}
//
//            }
//        }
        if(hasAut("owner")){
            users=as.manageUser(uid,"owner");
        }
        else {
            if(hasAut("admin")){users=as.manageUser(uid,"admin|owner");
            }
        }
        int num=0;
        for (manageUser infs : users) {
            map.put(String.valueOf(num),
                    new String[]{
                            infs.getUsername(),
                            infs.getAuthority(),
                            infs.getUid()
                    });
            num++;
        }
        System.out.println("the admin uid is:"+uid+" the user manage list is:"+users);
        return map;
    }
    @PostMapping("DelUser")//管理员专用
    public void mywork(String pid,HttpSession session) {
        System.out.println(pid);
        String uid = pid;
        as.delUser(uid);
        //return "logPage";
    }
    @PostMapping("getUa")//管理员专用
    public Map<String, String> getUa(String pid,HttpSession session) {
        String curAut= (String) session.getAttribute("curAut");
        Map<String, String> map = new HashMap<String, String>();
        int num=0;
        for(String val:as.getUa()){
            if(hasAut(val)){
            map.put(String.valueOf(num),val);}
            num++;
        }
        return map;
    }
    public Map<String, String> getAut() {//获取当前所有权限
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int c=0;//计数器
        Map<String, String> map = new HashMap<String, String>();
        for (Object auth : authentication.getAuthorities()) {
            map.put(String.valueOf(c),auth.toString());
            c++;
        }
        return map;
    }
    public boolean hasAut(String aut){//判断是否具有该权限
        for(Map.Entry<String, String> entry : getAut().entrySet()){
            if(entry.getValue().equals(aut)){return true;}
        }
        return false;
    }
    @PostMapping("addUa")//管理员专用
    public void addUa(String uid,String aut) {
        as.grant(uid, aut);
    }
    @PostMapping("delUa")//管理员专用
    public void delUa(String uid,String aut) {
        as.revoke(uid, aut);
    }

}
