package com.cmh.framework.web.service;

import com.cmh.common.core.domain.entity.SysDept;
import com.cmh.common.utils.DateUtils;
import com.cmh.framework.web.JDBCUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import com.cmh.common.constant.CacheConstants;
import com.cmh.common.constant.Constants;
import com.cmh.common.constant.UserConstants;
import com.cmh.common.core.domain.entity.SysUser;
import com.cmh.common.core.domain.model.RegisterBody;
import com.cmh.common.core.redis.RedisCache;
import com.cmh.common.exception.user.CaptchaException;
import com.cmh.common.exception.user.CaptchaExpireException;
import com.cmh.common.utils.MessageUtils;
import com.cmh.common.utils.SecurityUtils;
import com.cmh.common.utils.StringUtils;
import com.cmh.framework.manager.AsyncManager;
import com.cmh.framework.manager.factory.AsyncFactory;
import com.cmh.system.service.ISysConfigService;
import com.cmh.system.service.ISysUserService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 注册校验方法
 *
 * @author cmh
 */
@Component
public class SysRegisterService
{
    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private RedisCache redisCache;

    /**
     * 注册
     */
    public String register(RegisterBody registerBody) throws SQLException {
        String msg = "";
        String tenantName = registerBody.getTenantName();
        String contactName = registerBody.getContactName();
        String username = registerBody.getUsername();
        String password = registerBody.getPassword();
        String phoneNumber = registerBody.getPhone();


        SysUser sysUser = new SysUser();
        sysUser.setUserName(username);
        sysUser.setPhonenumber(phoneNumber);
        sysUser.setRoleIds(new Long[]{100L}); // 租户管理员角色的ID

        // 验证码开关
        boolean captchaEnabled = configService.selectCaptchaEnabled();
        if (captchaEnabled)
        {
            validateCaptcha(username, registerBody.getCode(), registerBody.getUuid());
        }

        if (StringUtils.isEmpty(username))
        {
            msg = "用户名不能为空";
        }
        else if (StringUtils.isEmpty(password))
        {
            msg = "用户密码不能为空";
        }
        else if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            msg = "账户长度必须在2到20个字符之间";
        }
        else if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            msg = "密码长度必须在5到20个字符之间";
        }
        else if (!userService.checkUserNameUnique(sysUser))
        {
            msg = "保存用户'" + username + "'失败，注册账号已存在";
        }
        else
        {
            sysUser.setNickName(tenantName);
            sysUser.setPassword(SecurityUtils.encryptPassword(password));


            // 向租户表里面加一条数据
            Connection con = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            try {
                //获取connection连接对象
                con = JDBCUtils.getConnection();
                //定义sql语句
                String sql = "insert into tenant(name,icon,contact,phone,admin,notes,createBy) values(?,?,?,?,?,?,?)";
                //预编译sql语句
                stmt = con.prepareStatement(sql);
                //定义上面6个问号对应值
                stmt.setString(1, tenantName);
                stmt.setString(2, "/profile/upload/2024/06/26/008cjMlfly1hq0ii0wuopj32bc1awhdv_20240626211401A002.jpg");
                stmt.setString(3, contactName);
                stmt.setString(4, phoneNumber);
                stmt.setString(5, username);
                stmt.setString(6, "当前租户是注册的时候创建的");
                stmt.setString(7, username);
                stmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                JDBCUtils.close(rs, stmt, con);
            }

            //向部门表里添加一条数据
            Connection con_1 = null;
            PreparedStatement stmt_1 = null;
            ResultSet rs_1 = null;
            try {
                //获取connection连接对象
                con_1 = JDBCUtils.getConnection();
                //定义sql语句
                String sql = "insert into sys_dept(dept_id,parent_id,ancestors,dept_name,order_num,create_by,create_time,update_by,update_time) values(?,?,?,?,?,?,?,?,?)";
                //预编译sql语句
                stmt_1 = con_1.prepareStatement(sql);
                //定义上面6个问号对应值
                stmt_1.setString(1, null);
                stmt_1.setString(2, "100");
                stmt_1.setString(3, "0,100");
                stmt_1.setString(4, tenantName);
                stmt_1.setString(5, "1");
                stmt_1.setString(6, username);
                DateUtils dateUtils = new DateUtils();
                Date currentDate = dateUtils.getNowDate();
                // 使用 SimpleDateFormat 格式化 Date 为 String
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedDate = dateFormat.format(currentDate);
                stmt_1.setString(7,formattedDate);
                stmt_1.setString(8, null);
                stmt_1.setString(9, null);
                stmt_1.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                JDBCUtils.close(rs_1, stmt_1, con_1);
            }


            // 将dept_id找出来添加到用户数据库
            Connection con_3 = JDBCUtils.getConnection();
            System.out.println(con_3);
            PreparedStatement stmt_3 = null;
            ResultSet rs_3 = null;

            try {
                // 创建并编译 SQL 查询语句，查询 deptId
                String sql = "SELECT dept_id FROM sys_dept WHERE dept_name = ?";
                stmt_3 = con_3.prepareStatement(sql);
                stmt_3.setString(1, tenantName); // 假设 deptName 是你要查询的部门名称
                rs_3 = stmt_3.executeQuery();

                Long deptId = null;
                if (rs_3.next()) {
                    deptId = rs_3.getLong("dept_id");
                } else {
                    // 处理未找到部门的情况，例如抛出异常或者设置默认值
                    throw new RuntimeException("部门 " + tenantName + " 不存在！");
                }

                // 将查询到的 deptId 设置给 sysUser 对象
                sysUser.setDeptId(deptId);

            } catch (SQLException e) {
                e.printStackTrace(); // 可以根据具体情况处理异常
            } finally {
                // 关闭数据库资源
                try {
                    if (rs_3 != null) rs_3.close();
                    if (stmt_3 != null) stmt_3.close();
                    con_3.close();
                } catch (SQLException e) {
                    e.printStackTrace(); // 可以根据具体情况处理异常
                }
            }

            // 注册用户
            int regFlag = userService.insertUser(sysUser);
            if (regFlag != 1)
            {
                msg = "注册失败,请联系系统管理人员";
            }
            else
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.REGISTER, MessageUtils.message("user.register.success")));
            }
        }
        return msg;
    }



    /**
     * 校验验证码
     *
     * @param username 用户名
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    public void validateCaptcha(String username, String code, String uuid)
    {
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + StringUtils.nvl(uuid, "");
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null)
        {
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha))
        {
            throw new CaptchaException();
        }
    }
}
