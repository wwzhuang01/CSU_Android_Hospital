package com.example.palmhospitalservice.dao;

import com.example.palmhospitalservice.bean.Depart;
import com.example.palmhospitalservice.utils.DbUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class DepartDao {
    private QueryRunner queryRunner = new QueryRunner();
    public List<Depart> selectAllDepart(){
        List<Depart> departs = null;
        try {
            departs = queryRunner.query(DbUtil.getConnection(),"select * from depart;",new BeanListHandler<Depart>(Depart.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departs;
    }

}
