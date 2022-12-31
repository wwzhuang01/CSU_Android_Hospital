package com.example.palmhospitalservice.dao;

import com.example.palmhospitalservice.bean.Order;
import com.example.palmhospitalservice.bean.User;
import com.example.palmhospitalservice.utils.DbUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class OrderDao {
    private QueryRunner queryRunner = new QueryRunner();

    public int addOrder(int sid,int uid){
        String oid = Integer.toString(sid)+"#" + Integer.toString(uid);
        int res = 0;

        System.out.println("添加的oid：" + oid);
        try {
            res = queryRunner.update(DbUtil.getConnection(),"INSERT INTO myOrder values(?,?,?)",oid,sid,uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

}
