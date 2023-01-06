package com.example.pyConn.dao;

import com.example.pyConn.domain.image;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AccountDao {
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

    @Select("select uid from user where username=\"${username}\";")
    String getId0(@Param("username") String username);

    @Select("select newDiff();")
    int newDiff();

    @Select("select newLim();")
    String newLim();

    @Select("select newRes();")
    String newRes();
}
