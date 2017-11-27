package com.kaishengit.crm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageInterceptor;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.AccountDept;
import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.entity.Dept;
import com.kaishengit.crm.example.AccountExample;
import com.kaishengit.crm.example.CustomerExample;
import com.kaishengit.crm.example.DeptExample;
import com.kaishengit.crm.mappers.AccountDeptMapper;
import com.kaishengit.crm.mappers.AccountMapper;
import com.kaishengit.crm.mappers.CustomerMapper;
import com.kaishengit.crm.mappers.DeptMapper;
import com.kaishengit.crm.service.WebService;
import com.kaishengit.crm.service.exception.ServiceException;
import com.kaishengit.utils.Config;
import com.kaishengit.weixin.CrmWeixinUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
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
    private static final Integer PID = 1;


    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private AccountDeptMapper accountDeptMapper;


    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CrmWeixinUtil weixinUtil;

    /**
     * 登陆
     * @param moblie
     * @param password
     * @return 返回map
     */
    @Override
    public Map<String, Object> login(String moblie, String password) {

        Map<String, Object> map = Maps.newHashMap();

        //&& StringUtils.isNumeric(name)
        if(StringUtils.isNotEmpty(moblie) ) {

            List<Account> accountList = accountMapper.selectByMobile(moblie);

            if(accountList != null && !accountList.isEmpty() && accountList.size() == 1) {

                Account account = accountList.get(0);

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

    /**
     * 查询所有部门 树状图
     *
     * @return
     */
    @Override
    public List<Dept> deptList() {

        DeptExample deptExample = new DeptExample();

        List<Dept> depts = deptMapper.selectByExample(deptExample);

        return depts;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveDept(String name) {

        DeptExample deptExample = new DeptExample();
        deptExample.createCriteria().andDeptNameEqualTo(name);

        List<Dept> depts = deptMapper.selectByExample(deptExample);

        if(depts != null && !depts.isEmpty()) {

            throw new ServiceException("该部门已经存在");

        }

        Dept dept = new Dept();
        dept.setDeptName(name);
        dept.setpId(PID);
        deptMapper.insertSelective(dept);

        //同步微信， 在微信端创建部门
        weixinUtil.createDept(dept.getId(), name, PID);

    }

    /**
     * 查找数据給DataTable插件
     *
     * @param map
     * @return
     */
    @Override
    public List<Account> findAccountAndDept(Map<String, Object> map) {

        Integer start = (Integer) map.get("start");
        Integer length = (Integer) map.get("length");
        Integer deptId = (Integer) map.get("deptId");
        String accountName = (String) map.get("key");

        if(deptId == null || PID.equals(deptId)) {
                deptId = null;
        }

        List<Account> accounts = accountMapper.selectAccountAndDept(start, length, deptId, accountName);

        return accounts;
    }

    @Override
    public Long countAll(Integer deptId) {

        if(deptId == null || PID.equals(deptId)) {
            deptId = null;
        }

        Long total = accountMapper.countAllByDeptId(deptId);



        return total;
    }

    @Override
    public Long countfilte(Map<String, Object> map) {

        Integer deptId = (Integer) map.get("deptId");
        String accountName = (String) map.get("key");

        if(deptId == null || PID.equals(deptId)) {
            deptId = null;
        }

        Long filteNum = accountMapper.countfilte(deptId, accountName);

        return filteNum;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveEmployee(String userName, String password, String mobile, Integer[] deptIds) {

        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andmobileEqualTo(mobile);

        List<Account> accounts = accountMapper.selectByExample(accountExample);

        if(accounts != null && !accounts.isEmpty() ) {
            throw new ServiceException("该手机号已经存在");
        }

        String salt = Config.get("user.password.salt");
        String saltPassword = DigestUtils.md5Hex(password + salt);

        Account account = new Account(userName, saltPassword, mobile);

        accountMapper.insertSelective(account);

        List<AccountDept> accountDepts = Lists.newArrayList();

        for(Integer deptId: deptIds) {

            AccountDept accountDept = new AccountDept(account.getId(), deptId);
            accountDepts.add(accountDept);
        }

        accountDeptMapper.insertMany(accountDepts);

        //同步微信，在微信也创建用户
        weixinUtil.craeateAccount(account.getId(), userName, Arrays.asList(deptIds), mobile);


    }

    @Override
    public PageInfo<Customer> myCustomer(Integer p, String key, Integer accId) {

        PageHelper.startPage(p, 10);
        List<Customer> customerList= customerMapper.selectByPage(key, accId);

        return new PageInfo<>(customerList);
    }

    /**
     * 客户详情
     *
     * @param id
     * @return
     */
    @Override
    public Customer customerDetail(Integer id, Integer accId) {

        if(accId > 0) {

            Customer customer = customerMapper.selectByPrimaryKey(id);

            if(customer != null && accId.equals(customer.getAccountId()) ) {

                return customer;

            } else {

                throw new ServiceException("客户不存在");

            }

        } else {

            throw new ServiceException("非法账户，已报警");

        }

    }

    /**
     * 保存用户
     *
     * @param customer
     */
    @Override
    public void saveCustomer(Customer customer) {

        if(customer.getCustName() != null && customer.getMobile() != null) {

            customerMapper.insertSelective(customer);

        } else {

            throw new ServiceException("姓名和手机不能为空");

        }

    }

    /**
     * 查找所有的员工
     *
     * @return
     */
    @Override
    public List<Account> findAllEmployee() {

        return accountMapper.selectByExample(new AccountExample());

    }

    /**
     * 通过moblie查找
     *
     * @param userName
     * @return
     */
    @Override
    public Account findByMoblie(String userName) {

        return accountMapper.selectByMobile(userName).get(0);

    }

    /**
     * 活的当前对象的部门
     *
     * @param id
     * @return
     */
    @Override
    public List<Dept> findDeptById(Integer id) {


        return deptMapper.selectDeptAllById(id);
    }


}
