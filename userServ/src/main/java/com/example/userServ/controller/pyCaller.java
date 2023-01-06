package com.example.userServ.controller;

import com.example.finalwork4.domain.lim;
import com.example.finalwork4.domain.pyInf;
import com.example.userServ.service.AcServ;
import com.example.userServ.service.FeAc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class pyCaller {
    @Autowired
    FeAc feAc;

    @Autowired
    AcServ as;

    @RequestMapping(value = "/show")
    public ModelAndView home(final HttpServletResponse response, HttpServletRequest request, HttpSession session) throws Exception {
        //pyInf pi=new pyInf(request.getParameter("fname"),session.getAttribute("uid").toString(),request.getParameter("mathm"));
        session.setAttribute("read","");
        if(request.getParameter("fname")==""||request.getParameter("fname").equals(null)){throw new Exception("is null!!");}
        pyInf pi=new pyInf(
                Double.parseDouble(request.getParameter("quality")),
                request.getParameter("fname"),
                session.getAttribute("uid").toString(),
                request.getParameter("mathm"),
                Double.parseDouble(request.getParameter("lent")),
                Double.parseDouble(request.getParameter("hei")),
                request.getParameter("maxi"),
                request.getParameter("mini")
        );
        HashMap<String,String> pi1=new HashMap<String,String>();
        pi1.put("matha",request.getParameter("mathm"));
        pi1.put("Fname",request.getParameter("fname"));
        pi1.put("uid",session.getAttribute("uid").toString());
        pi1.put("Mini",request.getParameter("mini"));
        pi1.put("Maxi",request.getParameter("maxi"));
        pi1.put("len",request.getParameter("lent"));
        pi1.put("hei",request.getParameter("hei"));
        pi1.put("quality",request.getParameter("quality"));

        //int newid= as.getId(pi);
        //int newid= as.getId2(pi1);
        int newid=Integer.parseInt(feAc.getNewId(pi));
        session.setAttribute("pi",pi);
        session.setAttribute("newid",newid);

        String name=String.valueOf(request.getParameter("fname"));
        ModelAndView view = new ModelAndView("show");
        view.addObject("image_name", name);
        view.addObject(pi);
        return view;
    }
    @RequestMapping("/tofix")
    public ModelAndView tofix(final HttpServletResponse response, HttpServletRequest request, HttpSession session) throws Exception {
        //pyInf pi=new pyInf(request.getParameter("fname"),session.getAttribute("uid").toString(),request.getParameter("mathm"));
        session.setAttribute("read","");
        if(request.getParameter("fname")==""||request.getParameter("fname").equals(null)){throw new Exception("is null!!");}
        pyInf pi=new pyInf(
                Double.parseDouble(request.getParameter("quality")),
                request.getParameter("fname"),
                session.getAttribute("uid").toString(),
                request.getParameter("mathm"),
                Double.parseDouble(request.getParameter("lent")),
                Double.parseDouble(request.getParameter("hei")),
                request.getParameter("maxi"),
                request.getParameter("mini")
        );
        //System.out.println(pi.getMatha());

        //if(session.getAttribute("oldid")!=null&&(String) session.getAttribute("oldid")!=""){
        //session.setAttribute("newid",((String) session.getAttribute("oldid")));
        //}
        int newid=Integer.parseInt(feAc.getfix(pi, Integer.parseInt(String.valueOf(session.getAttribute("newid")))));

        //int newid= (int) session.getAttribute("newid");
        session.setAttribute("pi",pi);
        session.setAttribute("newid",newid);

        String name=String.valueOf(request.getParameter("fname"));
        ModelAndView view = new ModelAndView("show");
        view.addObject("image_name", name);
        view.addObject(pi);
        return view;
    }

}
