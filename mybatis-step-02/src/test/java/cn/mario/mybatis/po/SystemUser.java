package cn.mario.mybatis.po;


import lombok.Data;

import java.util.Date;

/**
 * @description: TODO
 * @author: mario
 * @date: 2025/6/30
 */
@Data
public class SystemUser {

    private Long id;
    // 用户名称
    private String userName;
    // 创建时间
    private Date createTime;

}
