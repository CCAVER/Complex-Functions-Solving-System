package com.example.userServ.controller;

import com.example.userServ.service.MySecure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class checker {
    @Autowired
    MySecure mySecure;

//    @RequestMapping("/dochecks-USELESS")
//    public String goPy2(String username, String password, HttpServletRequest request, HttpSession session) throws IOException, InterruptedException
//    {
//        try {
//            String[] tmp=mySecure.login(username,password);
//            if(tmp[0].equals("true")){
//                session.setAttribute("uid",tmp[1]);//获取UID
//                return "py";
//            }else{
//                request.setAttribute("wrong","账户名或密码有误");
//                return "logPage";}
//        }catch (Exception e){
//            request.setAttribute("wrong","用户不存在");
//            return "logPage";}
//    }
//    @RequestMapping("/docheckS")
//    public String goPy3(String username, String password, HttpServletRequest request, HttpSession session) throws IOException, InterruptedException
//    {
//        try {
//            String[] tmp=mySecure.login(username,password);
//            if(tmp[0].equals("true")){
//                session.setAttribute("uid",tmp[1]);//获取UID
//                return "py";
//            }else{
//                request.setAttribute("wrong","账户名或密码有误");
//                return "reg";}
//        }catch (Exception e){
//            request.setAttribute("wrong","用户不存在");
//            return "reg";}
//    }
    public static final String PATH0 = "/dD:\\学习系列\\毕业论文-定档\\";
    @RequestMapping("doreg")
    public String reg(String username,String password,HttpServletRequest request,HttpSession session){
        if(username!=""&&username!=null&&password!=""&&password!=null){
            try {
                String command="";
                String fname0=mySecure.add(username,password);//获取UID
                //final String PATH = "C:\\Users\\hy\\Desktop\\论文相关";//脚本位置
                command= "cmd.exe /c cd "
                        + PATH0 //此处插入创建文件夹路径
                        + " && mkdir "+fname0
                        +" && Xcopy 0 "+fname0
                        +" /E/H/C/I";
                //Xcopy C:\ test D:\test /E/H/C/I
                //cd C:\Users\hy\Desktop\论文相关 && mkdir 20 && Xcopy 0 20 /E/H/C/I
                System.out.println(command);
                Process p = Runtime.getRuntime().exec(command);
                //*****************非常关键*******************//
                p.waitFor();//如果去掉，下一步的查询将立即行完时执行
                //******************************************//
                return "logPage";
            }catch (Exception e){
                System.out.println(e);
                request.setAttribute("wrong","该用户已存在");
                return "reg";}

        }
        return "reg";
    }
}
