package com.example.userServ.controller;

import com.example.finalwork4.domain.MongoPyDetail;
import com.example.userServ.dao.DemoDao;
import com.example.userServ.dao.pyMongoDao;
import com.example.userServ.domain.DemoEntity;
import com.example.userServ.domain.pyDetail;
import com.example.userServ.service.AcServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class mongoControl {
    @Autowired
    DemoDao dd;
    @Autowired
    AcServ as;
    @Autowired
    pyMongoDao pd;


//    @RequestMapping("/dd")
//    public List<DemoEntity> test(){
//        dd.saveDemo(new DemoEntity("tit", "String description", "String by", "String url", "String corn"));
//        return dd.findDemos();
//    }

    @PostMapping("mywork")
    public Map<String, String[]> mywork(HttpSession session) {
        String uid = (String) session.getAttribute("uid");
        List<MongoPyDetail> pdS = pd.findDemoByUId(uid);
        System.out.println(pdS);
        Map<String, String[]> map = new HashMap<String, String[]>();
        for (MongoPyDetail infs : pdS) {
            map.put(String.valueOf(infs.getId()),
                    new String[]{
                            infs.getName(),
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
}
