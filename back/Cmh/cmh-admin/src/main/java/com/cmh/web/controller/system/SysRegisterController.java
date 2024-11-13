package com.cmh.web.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cmh.common.core.controller.BaseController;
import com.cmh.common.core.domain.AjaxResult;
import com.cmh.common.core.domain.model.RegisterBody;
import com.cmh.common.utils.StringUtils;
import com.cmh.framework.web.service.SysRegisterService;
import com.cmh.system.service.ISysConfigService;

import java.sql.SQLException;

/**
 * 注册验证
 *
 * @author cmh
 */
@RestController
public class SysRegisterController extends BaseController
{
    @Autowired
    private SysRegisterService registerService;

    @Autowired
    private ISysConfigService configService;

    @PostMapping("/register")
    public AjaxResult register(@RequestBody RegisterBody user) throws SQLException {
        String msg = registerService.register(user);
        return StringUtils.isEmpty(msg) ? success() : error(msg);
    }

    public void setRegisterService(SysRegisterService registerService) {
        this.registerService = registerService;
    }
}
