package com.example.palmhospitalservice.dao;

import com.example.palmhospitalservice.bean.Depart;
import com.example.palmhospitalservice.bean.Doctor;
import com.example.palmhospitalservice.utils.DbUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class DoctorDao {
    private QueryRunner queryRunner = new QueryRunner();
    public List<Doctor> selectDoctorsByDepartid(int departid){
        List<Doctor> doctors = null;
        try {
            doctors = queryRunner.query(DbUtil.getConnection(),"select * from doctor where departid=?;",new BeanListHandler<Doctor>(Doctor.class),departid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }


}
