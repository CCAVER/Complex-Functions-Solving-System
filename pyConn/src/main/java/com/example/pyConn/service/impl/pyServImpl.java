package com.example.pyConn.service.impl;

import com.example.finalwork4.domain.MongoPyDetail;
import com.example.finalwork4.domain.Sol;
import com.example.finalwork4.domain.lim;
import com.example.finalwork4.domain.pyInf;
import com.example.pyConn.dao.AccountDao;
import com.example.pyConn.dao.pyMongoDao;
import com.example.pyConn.service.pyServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service("pyServ")
public class pyServImpl implements pyServ {
    @Autowired
    AccountDao accountDao;
    @Autowired
    pyMongoDao pd;
    final String PATH0 = "/dD:\\学习系列\\毕业论文-定档\\";
    @Override
    public int generate(pyInf inf) throws Exception {
        //先根据ID创建数据
        int newId=0;
        final String PATH = PATH0+inf.getUid();//脚本位置
        String command="";
        String loadCommand="";
        String genCommand="";

        if (inf.getMatha()!=""||inf.getMatha()!=null){
            newId=accountDao.newrow();//新的一行已经建立，且获取到了ID
            pd.newInf(new MongoPyDetail(newId));
            loadCommand="cmd.exe /c cd "
                    + PATH //此处插入python文件的路径
                    + " && python load.py"
                    //+"-f D:\\学习系列\\J2E\\scripts\\";//利用python的命令行机制可以传入参数
                    //+" --matha \"cos(x+8)*(9-2)\" "
                    +" --matha \"("+inf.getMatha()
                    +")\"  --uid "+inf.getUid();
            System.out.println(loadCommand);
            genCommand="cmd.exe /c cd "
                    + PATH //此处插入python文件的路径
                    + " && python picGen.py"
                    //+"-f D:\\学习系列\\J2E\\scripts\\";//利用python的命令行机制可以传入参数
                    //+" --matha \"cos(x+8)*(9-2)\" "
                    +" --matha \"("+inf.getMatha()
                    +")\" --rowid "+newId
                    +" --fname "+inf.getFname()
                    +" --uid "+inf.getUid()
                    +" --mini "+inf.getMini()
                    +" --maxi "+inf.getMaxi()
                    +" --lent "+inf.getLent()
                    +" --hei "+inf.getHei()
                    +" --qal "+inf.getQuality();
            System.out.println(genCommand);
            command= "cmd.exe /c cd "
                    + PATH //此处插入python文件的路径
                    + " && python temp.py"
                    //+"-f D:\\学习系列\\J2E\\scripts\\";//利用python的命令行机制可以传入参数
                    //+" --matha \"cos(x+8)*(9-2)\" "
                    +" --matha \"("+inf.getMatha()
                    +")\" --rowid "+newId
                    +" --fname "+inf.getFname()
                    +" --uid "+inf.getUid()
                    +" --mini "+inf.getMini()
                    +" --maxi "+inf.getMaxi()
                    +" --lent "+inf.getLent()
                    +" --hei "+inf.getHei()
                    +" --qal "+inf.getQuality();
            //System.out.println(command);
            //python pyCharms1.py --matha 'cos(x)' --rowid 23 --fname 1 --uid 2 --mini '-10' --maxi '10' --lent 10 --hei 10 --qal 0.001

        }else {
            throw new NullPointerException("函数为空");
        }//未输入抛出异常
        try {
            Process p = Runtime.getRuntime().exec(command);

            //*****************非常关键*******************
            p.waitFor();//如果去掉，下一步的查询将在python语句未执行完时执行,必然查询出空值
            //******************************************

            return newId;
            //return 1;
        } catch (IOException e) {
            //e.printStackTrace();
            throw new Exception();
        }
    }
    @Override
    public int fix(pyInf inf,int rowid) throws Exception {
        final String PATH = PATH0+inf.getUid();//脚本位置
        String command="";
        String loadCommand="";
        String genCommand="";
        if (inf.getMatha()!=""||inf.getMatha()!=null){
            pd.newInf(new MongoPyDetail(rowid));
            loadCommand="cmd.exe /c cd "
                    + PATH //此处插入python文件的路径
                    + " && python load.py"
                    //+"-f D:\\学习系列\\J2E\\scripts\\";//利用python的命令行机制可以传入参数
                    //+" --matha \"cos(x+8)*(9-2)\" "
                    +" --matha \"("+inf.getMatha()
                    +")\"  --uid "+inf.getUid();
            genCommand="cmd.exe /c cd "
                    + PATH //此处插入python文件的路径
                    + " && python picGen.py"
                    //+"-f D:\\学习系列\\J2E\\scripts\\";//利用python的命令行机制可以传入参数
                    //+" --matha \"cos(x+8)*(9-2)\" "
                    +" --matha \"("+inf.getMatha()
                    +")\" --rowid "+rowid
                    +" --fname "+inf.getFname()
                    +" --uid "+inf.getUid()
                    +" --mini "+inf.getMini()
                    +" --maxi "+inf.getMaxi()
                    +" --lent "+inf.getLent()
                    +" --hei "+inf.getHei()
                    +" --qal "+inf.getQuality();
            command= "cmd.exe /c cd "
                    + PATH //此处插入python文件的路径
                    + " && python temp.py"
                    //+"-f D:\\学习系列\\J2E\\scripts\\";//利用python的命令行机制可以传入参数
                    //+" --matha \"cos(x+8)*(9-2)\" "
                    +" --matha \"("+inf.getMatha()
                    +")\" --rowid "+rowid
                    +" --fname "+inf.getFname()
                    +" --uid "+inf.getUid()
                    +" --mini "+inf.getMini()
                    +" --maxi "+inf.getMaxi()
                    +" --lent "+inf.getLent()
                    +" --hei "+inf.getHei()
                    +" --qal "+inf.getQuality();
            System.out.println(command);
            //python pyCharms1.py --matha 'cos(2*x)' --rowid 2 --fname 1 --uid 2 --mini -10.0 --maxi 10.0 --lent 10.0 --hei 10.0 --qal 0.001
        }else {
            throw new NullPointerException("函数为空");
        }//未输入抛出异常
        try {
            Process p = Runtime.getRuntime().exec(command);
            //*****************非常关键*******************
            p.waitFor();//如果去掉，下一步的查询将在python语句未执行完时执行,必然查询出空值
            //******************************************

            return rowid;
            //return 1;
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception();
        }
    }

    @Override
    public int diff(String uid, String matha) throws Exception {
        final String PATH = PATH0+uid+"\\PyDiff";//脚本位置
        String command="";
        String loadCommand="";
        String genCommand="";
        int newId=0;
        newId=accountDao.newDiff();
        if (matha!=""){
            command= "cmd.exe /c cd "
                    + PATH //此处插入python文件的路径
                    + " && python temp.py"
                    //+"-f D:\\学习系列\\J2E\\scripts\\";//利用python的命令行机制可以传入参数
                    //+" --matha \"cos(x+8)*(9-2)\" "
                    +" --matha \"("+matha
                    +")\" --uid "+uid
                    +" --rowid "+newId;
            System.out.println(command);
            //cd C:\Users\hy\Desktop\论文相关\0\latex && python temp.py --matha sin(5*x)+7 --uid 0
        }else {
            throw new NullPointerException("函数为空");
        }//未输入抛出异常
        try {
            Process p = Runtime.getRuntime().exec(command);
            //*****************非常关键*******************
            p.waitFor();//如果去掉，下一步的查询将在python语句未执行完时执行,必然查询出空值
            //******************************************
            return newId;
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception();
        }

    }

    @Override
    public String Lim(lim limVal) throws Exception {
        final String PATH = PATH0+limVal.getUid()+"\\lim";//脚本位置
        String rowid=accountDao.newLim();//新的一行已经建立，且获取到了ID
        System.out.println("the new Lim is:"+rowid);
        String command="cmd.exe /c cd "
                + PATH //此处插入python文件的路径
                + " && python temp.py"
                //+"-f D:\\学习系列\\J2E\\scripts\\";//利用python的命令行机制可以传入参数
                //+" --matha \"cos(x+8)*(9-2)\" "
                +" --matha \"("+limVal.getMatha()
                +")\" --uid "+limVal.getUid()
                +" --rowid "+rowid
                +" --lim "+limVal.getLim()
                +" --sym "+limVal.getSym();
        System.out.println(command);
        try {
            Process p = Runtime.getRuntime().exec(command);
            //*****************非常关键*******************
            p.waitFor();//如果去掉，下一步的查询将在python语句未执行完时执行,必然查询出空值
            //******************************************
            return rowid;
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception();
        }

    }

    @Override
    public String Solve(Sol sol) throws Exception {
        final String PATH = PATH0+sol.getUid()+"\\res";//脚本位置
        String rowid=accountDao.newRes();//新的一行已经建立，且获取到了ID
        System.out.println("the new Lim is:"+rowid);
        String command="cmd.exe /c cd "
                + PATH //此处插入python文件的路径
                + " && python temp.py"
                //+"-f D:\\学习系列\\J2E\\scripts\\";//利用python的命令行机制可以传入参数
                //+" --matha \"cos(x+8)*(9-2)\" "
                +" --matha \"("+sol.getMatha()
                +")\" --uid "+sol.getUid()
                +" --rowid "+rowid
                +" --type0 "+sol.getType()
                +" --sym "+sol.getSym()
                +" --fuc "+sol.getFuc();
        System.out.println(command);
        try {
            Process p = Runtime.getRuntime().exec(command);
            //*****************非常关键*******************
            p.waitFor();//如果去掉，下一步的查询将在python语句未执行完时执行,必然查询出空值
            //******************************************
            return rowid;
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception();
        }
    }

    @Override
    public void latex(String uid, String matha) throws Exception {
        final String PATH = PATH0+uid+"\\latex";//脚本位置
        String command="";
        String loadCommand="";
        String genCommand="";

        if (matha!=""){
            loadCommand="cmd.exe /c cd "
                    + PATH //此处插入python文件的路径
                    + " && python load.py"
                    //+"-f D:\\学习系列\\J2E\\scripts\\";//利用python的命令行机制可以传入参数
                    //+" --matha \"cos(x+8)*(9-2)\" "
                    +" --matha \"("+matha
                    +")\" --uid "+uid;
            System.out.println(loadCommand);
            genCommand="cmd.exe /c cd "
                    + PATH //此处插入python文件的路径
                    + " && python picGen.py"
                    //+"-f D:\\学习系列\\J2E\\scripts\\";//利用python的命令行机制可以传入参数
                    //+" --matha \"cos(x+8)*(9-2)\" "
                    +" --matha \"("+matha
                    +")\" --uid "+uid;
            System.out.println(genCommand);
            command= "cmd.exe /c cd "
                    + PATH //此处插入python文件的路径
                    + " && python temp.py"
                    //+"-f D:\\学习系列\\J2E\\scripts\\";//利用python的命令行机制可以传入参数
                    //+" --matha \"cos(x+8)*(9-2)\" "
                    +" --matha \"("+matha
                    +")\" --uid "+uid;
            System.out.println(command);
            //cd C:\Users\hy\Desktop\论文相关\0\latex && python temp.py --matha sin(5*x)+7 --uid 0
        }else {
            throw new NullPointerException("函数为空");
        }//未输入抛出异常
        try {
            Process p = Runtime.getRuntime().exec(command);
            //*****************非常关键*******************
            p.waitFor();//如果去掉，下一步的查询将在python语句未执行完时执行,必然查询出空值
            //******************************************
            return ;
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception();
        }

    }


}
