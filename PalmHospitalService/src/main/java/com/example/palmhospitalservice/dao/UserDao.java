package com.example.palmhospitalservice.dao;

import com.example.palmhospitalservice.bean.User;
import com.example.palmhospitalservice.utils.DbUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDao {
    private QueryRunner queryRunner = new QueryRunner();

    public User select(String uid) {
        User user = new User();
        try {
            user = queryRunner.query(DbUtil.getConnection(),"select * from user where uid=?",new BeanHandler<User>(User.class),uid);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    public int addUser(User user){
        int res = 0;
        try {
            res = queryRunner.update(DbUtil.getConnection(),"INSERT INTO user values(?,?,?)",user.getUid(),user.getUname(),user.getUpsw());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }


    public User login(String uid,String upsw){
        User user = select(uid);
        if(user!=null && user.getUpsw().equals(upsw)){   // 该账号存在，能查询到用户 且 密码正确
            return user;
        }
        else return null;
    }

}
