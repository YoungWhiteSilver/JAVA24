package com.kaishengit.crm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageInterceptor;
import com.google.common.collect.Maps;
import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Dept;
import com.kaishengit.crm.mappers.AccountMapper;
import com.kaishengit.crm.mappers.DeptMapper;
import com.kaishengit.crm.service.WebService;
import com.kaishengit.utils.Config;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author silver
 * @date 2017/11/7
 */
@Service
public class WebServiceImpl implements WebService {

    private static final String SUC = "success";
    private static final String ERROR = "error";

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private DeptMapper deptMapper;

    /**
     * 登陆
     * @param name
     * @param password
     * @return 返回map
     */
    @Override
    public Map<String, Object> login(String name, String password) {

        Map<String, Object> map = Maps.newHashMap();

        //&& StringUtils.isNumeric(name)
        if(StringUtils.isNotEmpty(name) ) {

            Account account = accountMapper.selectByMobile(name);

            if(account != null ) {

                String salt = Config.get("user.password.salt");

                String saltPassword = DigestUtils.md5Hex(password + salt);

                if(account.getPassword().equals(saltPassword)) {

                    map.put("state", SUC);
                    map.put("data", account);

                } else {

                    map.put("state", ERROR);
                    map.put("message", "账号密码错误");

                }

            } else {

                map.put("state", ERROR);
                map.put("message", "用户不存在");

            }

        } else {

            map.put("state", ERROR);
            map.put("message", "账号不能为空");

        }

        return map;

    }

    @Override
    public PageInfo<Dept> homePage(Integer p, Integer id) {

        PageHelper.startPage(p, 10);
        List<Dept> deptList = deptMapper.selectByPage(id);

        return new PageInfo<>(deptList);
    }
}
