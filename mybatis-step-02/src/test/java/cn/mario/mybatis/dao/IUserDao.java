package cn.mario.mybatis.dao;

import cn.mario.mybatis.po.SystemUser;

public interface IUserDao {

    SystemUser queryUserInfoById(Integer id);

}
