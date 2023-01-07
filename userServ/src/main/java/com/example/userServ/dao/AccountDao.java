package com.example.userServ.dao;

import com.example.finalwork4.domain.Sol;
import com.example.finalwork4.domain.lim;
import com.example.userServ.domain.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AccountDao {
    @Select("select * from sale")
    List<account> findAll();

    @Select("select forSale(${num})")
    int sale(@Param("num") int num);

    @Select("select checkU(\"${username}\",\"${password}\");") //字符串必须用 $ 不能用 #
    String check(@Param("username") String username,@Param("password") String password);

    @Select("SELECT uid from user where user.`password`=\"${password}\" and user.username=\"${username}\"")
    String getId(@Param("username") String username,@Param("password") String password);

    @Select("select newrow();")
    int newrow();
/*
    @Select("select data from images where id=${id}")
    image getImg(@Param("id") int newid);

    @Select("select data from images where id=${id};")
    Blob getImgB(@Param("id") String id);

    @Select("select data from images where id=${id};")
    byte[] getImgC(@Param("id") String id);
*/
    @Select("select data from images where id=${id};")
    image getImgD(@Param("id") String id);

    //@Select("select DISTINCT images.id,images.name as Pyname,imginf.matha,imginf.mini,imginf.maxi,imginf.qal,imginf.wei,imginf.lent from images,imginf where images.uid=${uid} and images.id=imginf.ImgId GROUP BY images.id")
    @Select("select DISTINCT images.id,images.name as Pyname,imginf.matha,imginf.mini,imginf.maxi,imginf.qal,imginf.wei,imginf.lent from images,imginf where images.uid=${uid} and images.id=imginf.ImgId")
    List<pyDetail> getMyInf(@Param("uid") String uid);

    @Select("select password from user where username=\"${username}\";")
    String getPas(@Param("username") String username);

    @Insert("insert into user(username,password) values(\"${username}\",\"${password}\")")
    int add(@Param("username") String username,@Param("password") String password);

    @Select("select * from user where username=\"${username}\";")
    user getU(@Param("username") String username);

    @Delete("delete from images where id=${id}")
    void doDel(@Param("id") String id);

    @Select("select uid from user where username=\"${username}\";")
    String getId0(@Param("username") String username);

    @Select("select newUser(\"${username}\",\"${password}\")")
    String add2(@Param("username") String username,@Param("password") String password);

    @Select("SELECT img FROM `latex` where uid=\"${uid}\"")
    latexC getLatex(@Param("uid") String uid);

    @Select("SELECT diffImg FROM `diff` where id=${rowid}")
    diff getDiff(@Param("rowid") String rowid);

    @Select("SELECT * FROM `diff` where uid=${uid}")
    List<diff> diffInf(@Param("uid") String uid);
    @Delete("delete from diff where id=${id}")
    void diffDel(@Param("id") String id);
    @Select("SELECT * FROM `lim` where id=${rowid}")
    lim getLim(@Param("rowid") String rowid);
    @Select("SELECT * FROM `lim` where uid=${uid}")
    List<lim> getAllLim(@Param("uid") String uid);
    @Delete("delete from lim where id=${id}")
    void limDel(@Param("id") String id);
    @Select("SELECT * FROM `res` where id=${id}")
    Sol getSol(@Param("id") String id);
    @Select("SELECT * FROM `res` where uid=${uid}")
    List<Sol> getAllSol(@Param("uid") String uid);
    @Delete("delete from res where id=${id}")
    void SOLDel(@Param("id") String id);
    @Delete("delete from user where uid=${uid}")
    void userDel(@Param("uid") String uid);
    @Update("update user set password=\"${pas}\" where uid=${uid}")
    void changePas(@Param("pas") String pas,@Param("uid") String uid);
    @Select("SELECT user.uid,user.username,ua.authority FROM `user`,ua,aa WHERE user.uid=aa.accid and aa.aid=ua.id and uid!=\"${uid}\" and uid not in (SELECT uid from user,ua,aa WHERE user.uid=aa.accid and aa.aid=ua.id and ua.authority REGEXP \"${aut}\")")
    List<manageUser> manageUsers(@Param("uid") String uid,@Param("aut") String aut);
}
