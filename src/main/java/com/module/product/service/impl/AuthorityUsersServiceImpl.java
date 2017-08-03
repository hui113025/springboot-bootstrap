package com.module.product.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.module.product.common.constant.SessionConstant;
import com.module.product.common.enums.OperLevel;
import com.module.product.service.AuthorityUsersService;
import com.module.product.common.annotation.AdminLogAnn;
import com.module.product.common.bean.DataTablePageModel;
import com.module.product.common.bean.MenuCacheModel;
import com.module.product.common.constant.SessionConstant;
import com.module.product.common.dto.AuthorityUsersDto;
import com.module.product.common.dto.LoginDto;
import com.module.product.common.enums.OperLevel;
import com.module.product.common.enums.OperType;
import com.module.product.common.util.ShiroUtils;
import com.module.product.orm.mapper.AuthorityUsersMapper;
import com.module.product.orm.model.AuthorityUsers;
import com.module.product.service.AuthorityUsersService;
import com.module.product.service.core.AbstractService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.module.product.common.util.DateUtils.getFirstDateOfDay;
import static com.module.product.common.util.DateUtils.getLastDateOfDay;


@Transactional
@Service
public class AuthorityUsersServiceImpl extends AbstractService<AuthorityUsers> implements AuthorityUsersService {
    @Resource
    private AuthorityUsersMapper authorityUsersMapper;
    @Resource
    private MenuCacheModel menuCache;

    private final static Logger logger = LoggerFactory.getLogger(AuthorityUsersServiceImpl.class);

    @Override
    public DataTablePageModel buildDataTablePageModel(DataTablePageModel page) {
        PageHelper.offsetPage(page.getStart(), page.getLength());
        List<AuthorityUsers> users = authorityUsersMapper.select4DataTable(page);
        page.setData(users);
        long total = ((Page) (users)).getTotal();
        page.setRecordsTotal(total);
        page.setRecordsFiltered(total);
        return page;
    }

    @Override
    public DataTablePageModel buildDataTablePageModelByChannelProxy(DataTablePageModel page) {
        PageHelper.offsetPage(page.getStart(), page.getLength());
        List<AuthorityUsersDto> users = authorityUsersMapper.select4DataTableByChannel(page);

        //学生人数
        Map<Integer,Integer> studentMap = this.getPorxyStudentNum(2);
        //循环覆盖
        for(AuthorityUsersDto usersDto : users) {
            if (studentMap.containsKey(usersDto.getId())) {//学员人数
                usersDto.setStudentNum(studentMap.get(usersDto.getId()));
            }
        }

        page.setData(users);
        long total = ((Page) (users)).getTotal();
        page.setRecordsTotal(total);
        page.setRecordsFiltered(total);
        return page;
    }

    @Override
    public DataTablePageModel buildDataTablePageModelByChannelExpand(DataTablePageModel page) {
        PageHelper.offsetPage(page.getStart(), page.getLength());
        List<AuthorityUsersDto> users = authorityUsersMapper.select4DataTableByChannel(page);

        List<Map<Integer,Integer>> maps = authorityUsersMapper.countProxyNum();
        Map<Integer,Integer> proxyMap = Maps.newHashMap();
        for(Map<Integer,Integer> map :maps){
            Object userId = map.get("userId");
            Object amount = map.get("amount");
            proxyMap.put(Integer.valueOf(userId.toString()),Integer.valueOf(amount.toString()));
        }

        //学生人数
        Map<Integer,Integer> studentMap = this.getPorxyStudentNum(1);

        //拓展用户金额

        //循环覆盖
        for(AuthorityUsersDto usersDto : users){
            if(proxyMap.containsKey(usersDto.getId())){//代理人数
                usersDto.setProxyNum(proxyMap.get(usersDto.getId()));
            }

            if(studentMap.containsKey(usersDto.getId())){//学员人数
                usersDto.setStudentNum(studentMap.get(usersDto.getId()));
            }

        }

        page.setData(users);
        long total = ((Page) (users)).getTotal();
        page.setRecordsTotal(total);
        page.setRecordsFiltered(total);
        return page;
    }

    private Map<Integer,Integer> getPorxyStudentNum(Integer roleType){
        Map<Integer,Integer> studentMap = Maps.newHashMap();
        return studentMap;
    }

    /**
     * 根据用户名密码验证登录,并返回响应
     */
    @Override
    public LoginDto checkLogin(String email, String password, boolean isrem) {
        LoginDto loginDto = new LoginDto(false, "用户名不存在!");
        try {
            List<AuthorityUsers> us = findUsersByEmail(email);//获取用户
            if (!CollectionUtils.isEmpty(us)) {
                if (us.size() > 1) {
                    //用户名对应对个账号
                    loginDto.setMessage("账号出现冲突,对应多个用户!");
                } else {
                    AuthorityUsers u = us.get(0);/*获取用户*/
                    if (u.getUserLock() == 0) {
                        //账号被锁定
                        loginDto.setMessage("账号被锁定!");
                    } else {
                        String pwd = DigestUtils.md5Hex(password);
                        if (!u.getPassword().trim().equals(pwd)) {
                            loginDto.setMessage("密码错误!");
                        } else {
                            //处理登录成功逻辑
                            loginDto.setSuccess(true);
                            //shiro登录
                            UsernamePasswordToken token = new UsernamePasswordToken(u.getId().toString(), u.getPassword(), isrem);
                            SecurityUtils.getSubject().login(token);
                            ShiroUtils.getSession().setAttribute(SessionConstant.LOGIN_USER, u);
                            //修改最后一次登录时间.
                            u.setLastLoginDatetime(new Date());
                            this.authorityUsersMapper.updateUserLoginDate(u);
                            //存放用户菜单到缓存对象中
                            menuCache.reLoadUserMenu(u.getId());
                        }
                    }

                }
            }
        } catch (Exception e) {
            logger.error("用户登录出现异常!异常信息", e);
            loginDto.setMessage("系统异常!");
        }
        return loginDto;
    }

    /**
     * 根据邮箱获取用户
     *
     * @param email
     * @return
     */
    @Override
    public List<AuthorityUsers> findUsersByEmail(String email) {
        return this.authorityUsersMapper.getUsersByEmail(email);
    }

    /**
     * 根据角色ID获取当前角色下的所有用户
     *
     * @param roleId
     * @return
     */
    @Override
    public List<AuthorityUsers> findUsersByRoleId(Integer roleId) {
        return this.authorityUsersMapper.getUsersByRoleId(roleId);
    }

    /**
     * 根据邮箱和角色ID,模糊获取不在当前角色下的用户
     *
     * @param map
     * @return
     */
    @Override
    public List<AuthorityUsers> findUserByEmailAndRoleId(Map map) {
        return this.authorityUsersMapper.getUserByEmailAndRoleId(map);
    }

    /**
     * 根据用户ID锁定或解锁该用户
     *
     * @param users
     */
    @AdminLogAnn(operType = OperType.SYSTEM, operLevel = OperLevel.MAX, operDescribe = "锁定或解锁用户")
    @Override
    public void LockingUser(AuthorityUsers users) {
        this.authorityUsersMapper.lockingUser(users);
    }

    /**
     * 验证用户邮箱是否存在
     *
     * @param users
     * @return true不存在 false存在
     */
    @Override
    public boolean checkUserEmailUnique(AuthorityUsers users) {
        return this.authorityUsersMapper.checkUserEmailUnique(users) == 0 ? true : false;
    }

    /**
     * 验证用户邮箱是否存在
     *
     * @param users
     * @return true不存在 false存在
     */
    @Override
    public boolean checkUserNumUnique(AuthorityUsers users) {
        return this.authorityUsersMapper.checkUserNumUnique(users) == 0 ? true : false;
    }

    /**
     * 验证用户手机号是否存在
     *
     * @param users
     * @return true不存在 false存在
     */
    @Override
    public boolean checkUserMobileUnique(AuthorityUsers users) {
        return this.authorityUsersMapper.checkUserMobileUnique(users) == 0 ? true : false;
    }

}
