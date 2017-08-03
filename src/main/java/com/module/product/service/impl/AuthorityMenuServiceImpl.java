package com.module.product.service.impl;


import com.google.common.collect.Lists;
import com.module.product.common.bean.Permission;
import com.module.product.common.constant.PermissionConstant;
import com.module.product.common.dto.MenuDto;
import com.module.product.common.dto.MenuTreeDto;
import com.module.product.common.util.ShiroUtils;
import com.module.product.orm.mapper.AuthorityMenuMapper;
import com.module.product.orm.mapper.AuthorityOperationMapper;
import com.module.product.orm.mapper.AuthorityPermissionMapper;
import com.module.product.orm.model.AuthorityMenu;
import com.module.product.orm.model.AuthorityOperation;
import com.module.product.orm.model.AuthorityUsers;
import com.module.product.service.AuthorityMenuService;
import com.module.product.service.core.AbstractService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.*;

/**
 * Service Implementation:AuthorityMenu
 *
 * @author duia_builder
 * @date 2016-1-20
 */
@Transactional
@Service
public class AuthorityMenuServiceImpl extends AbstractService<AuthorityMenu> implements AuthorityMenuService {

    @Resource
    private AuthorityMenuMapper menuMapper;
    @Resource
    private AuthorityOperationMapper operationMapper;
    @Resource
    private AuthorityPermissionMapper permissionMapper;

    /**
     * 查询出当前用户的用户菜单
     *
     * @return
     */
    @Override
    public List<MenuDto> findUserAuthMenu() {
        List list = Lists.newArrayList();
        AuthorityUsers sessionUser = ShiroUtils.getSessionUser();
        if(sessionUser != null && sessionUser.getId() != 1){
            list = this.menuMapper.getResourcesByUser(sessionUser.getId());
        }
        List<AuthorityMenu> ParentMenus = this.menuMapper.getParentMenus(); //获取所有父菜单
        List<AuthorityMenu> SubMenus = this.menuMapper.getSubMenus(list); //获取所有子菜单
        List<MenuDto> allMenuDto = new ArrayList<MenuDto>();//当前用户的菜单
        //如果不为空
        if (!CollectionUtils.isEmpty(ParentMenus) && !CollectionUtils.isEmpty(SubMenus)) {
            for (AuthorityMenu pm : ParentMenus) {
                MenuDto menus = new MenuDto(pm.getId(), pm.getMenuName(), pm.getMenuUrl(), pm.getMenuIcon(), pm.getMenuCode());//创建父菜单
                for (AuthorityMenu sm : SubMenus) {
                    if (sm.getMenuParentId().intValue() == pm.getId().intValue()) {
                        if (sm.getMenuEnable() == 1) {
                                if (ShiroUtils.hasPermission(new Permission(sm.getMenuCode(), PermissionConstant.DEFAULT_OPER))) {
                                    menus.getSubMenu().add(new MenuDto(sm.getId(), sm.getMenuName(), sm.getMenuUrl(), sm.getMenuUrl(), sm.getMenuCode()));//创建子菜单
                                }
                        }
                    }
                }
                    if (!CollectionUtils.isEmpty(menus.getSubMenu())) {
                        menus.setSubIsExist(true);//如果有子菜单,则设置为true
                    }
                    allMenuDto.add(menus);//添加到当前用户菜单集合中
                }
            }
            return allMenuDto;
        }


        /**
         * 查询所有菜单
         * @return
         */

    public MenuTreeDto findAllAuthMenu() {
        //菜单默认的根级
        MenuTreeDto root = new MenuTreeDto(-1, "系统菜单管理", true, "all");
        root.setOpen(true);
        root.setChildren(this.findMenus());
        return root;
    }

    /**
     * 根据菜单Id查询当前菜单实体
     *
     * @param id 菜单ID
     * @return
     */
    @Override
    public AuthorityMenu findAuthMenuById(Integer id) {
        AuthorityMenu menu = this.findById(id);
        List<AuthorityOperation> opers = this.operationMapper.getAuthOperListByMenuId(id);//获取当前菜单下的操作集合
        if (!CollectionUtils.isEmpty(opers) && null != menu) {
            menu.setOperationList(opers);
        }
        return menu;
    }

    /**
     * 验证菜单的唯一性
     *
     * @param menu
     * @return
     */
    @Override
    public boolean checkMenuUnique(AuthorityMenu menu) {
        return this.menuMapper.checkMenuUnique(menu) == 0 ? true : false;
    }

    @Override
    public List<AuthorityMenu> findAllChildrenMenu() {
        Condition condition = new Condition(AuthorityMenu.class);
        condition.selectProperties("id", "menuUrl","menuCode");
        condition.createCriteria()
                .andNotEqualTo("menuParentId", 0)
                .andNotEqualTo("deleted", 1);
        return   mapper.selectByCondition(condition);
    }

    /**
     * 查询出所有启用的菜单,并对当前角色下的菜单设置勾选状态
     *
     * @param roleId
     * @return
     */
    @Override
    public MenuTreeDto findAllMenuByRoles(Integer roleId) {
        //菜单默认的根级
        MenuTreeDto root = new MenuTreeDto(-1, "全部菜单", true, "all");
        root.setOpen(true);
        List<MenuTreeDto> mts = this.findMenus();
        if (!CollectionUtils.isEmpty(mts)) {
            Map map = new HashMap();
            map.put("roleId", roleId);
            List<Integer> menuIds = this.permissionMapper.getMenuIdByRoleId(map);//获取当前角色下的菜单ID
            for (MenuTreeDto m : mts) {
                if (!CollectionUtils.isEmpty(m.getChildren())) {
                    Iterator<MenuTreeDto> its = m.getChildren().iterator();
                    while (its.hasNext()) {
                        MenuTreeDto mt = its.next();
                        if (mt.getEnable() == 0) {
                            its.remove();
                            continue;
                        }
                        mt.setChecked(menuIds.contains(mt.getId()));
                    }
                }
            }
        }
        root.setChildren(mts);
        return root;
    }

    /**
     * 查询出所有父子菜单
     *
     * @return
     */
    private List<MenuTreeDto> findMenus() {
        List list = Lists.newArrayList();
        AuthorityUsers sessionUser = ShiroUtils.getSessionUser();
        if(sessionUser != null && sessionUser.getId() != 1){
            list = this.menuMapper.getResourcesByUser(sessionUser.getId());
        }
        List<AuthorityMenu> ParentMenus = this.menuMapper.getParentMenus(); //获取所有父菜单
        List<AuthorityMenu> SubMenus = this.menuMapper.getSubMenus(list); //获取所有子菜单
        List<MenuTreeDto> menuTrees = new ArrayList<>();
        //如果不为空
        if (!CollectionUtils.isEmpty(ParentMenus)) {
            for (AuthorityMenu pm : ParentMenus) {
                MenuTreeDto menus = new MenuTreeDto(pm.getId(), pm.getMenuName(), true, pm.getMenuCode());//创建父菜单
                menus.setCode(pm.getMenuCode());
                for (AuthorityMenu sm : SubMenus) {
                    if (sm.getMenuParentId().intValue() == pm.getId().intValue()) {
                        menus.getChildren().add(new MenuTreeDto(sm.getId(), sm.getMenuName(), null, true, false, sm.getMenuEnable(), sm.getMenuCode()));//创建子菜单
                    }
                }
                menuTrees.add(menus);
            }
        }
        return menuTrees;
    }

}
