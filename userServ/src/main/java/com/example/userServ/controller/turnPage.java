package com.example.userServ.controller;

import com.example.finalwork4.domain.pyInf;
import com.example.userServ.dao.pyMongoDao;
import com.example.userServ.service.AcServ;
import com.example.userServ.service.MySecure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class turnPage {
    @Autowired
    AcServ as;
    @Autowired
    MySecure mySecure;
    @Autowired
    pyMongoDao pd;
    @RequestMapping("/read")
    public ModelAndView read(HttpServletRequest request, HttpSession session){
        //System.out.println(request.getParameter("pid"));
        session.setAttribute("newid",request.getParameter("pid"));
        //pyInf pi=(pyInf) session.getAttribute("pi");
        session.setAttribute("read","readonly");
        String name=String.valueOf(request.getParameter("fname"));
        ModelAndView view = new ModelAndView("show");
        view.addObject("image_name", name);
        //view.addObject(pi);
        return view;
    }
    @RequestMapping("/")
    //@ResponseBody
    public String hello() throws IOException, InterruptedException {
        return "logPage";
    }
    @RequestMapping("/reg")
    //@ResponseBody
    public String goreg() throws IOException, InterruptedException {
        return "reg";
    }
    @RequestMapping("/py")
    public String goPy(String username, String password, HttpServletRequest request, HttpSession session) throws IOException, InterruptedException
    {
        try {
            username= SecurityContextHolder.getContext().getAuthentication().getName();
            System.out.println("username:"+username);
            String[] tmp=mySecure.login(username);
            if(tmp[0].equals("true")){
                session.setAttribute("uid",tmp[1]);//获取UID
                return "py";
            }else{
                request.setAttribute("wrong","账户名或密码有误");
                return "logPage";}
        }catch (Exception e){
            request.setAttribute("wrong","用户不存在");
            return "logPage";}
    }
    @PostMapping("doDel")
    public String doDel(HttpServletRequest request){
        String id= request.getParameter("pid");
        as.doDel(id);
        pd.del(Integer.parseInt(id));
        return "py";
    }
    @PostMapping("goLim")
    public String goLim(HttpServletRequest request,HttpSession session){
        return "lim";
    }
    @PostMapping("goSol")
    public String goSol(HttpServletRequest request,HttpSession session){
        return "sol";
    }
    @PostMapping("goDif")
    public String goDif(HttpServletRequest request,HttpSession session){
        return "dif";
    }
}