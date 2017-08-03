package com.module.product.common.bean;


import com.module.product.common.dto.MenuDto;
import com.module.product.orm.model.AuthorityMenu;
import com.module.product.service.AuthorityMenuService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 菜单缓存对象
 * Created by wangsongpeng on 2016/4/8.
 */
@Component
public class MenuCacheModel{

    @Resource
    private AuthorityMenuService authorityMenuService;

    //存放所有用户菜单key为用户ID,value为菜单.concurrentHashMap能保证多线程的并发下安全,同时是在写入时锁住写入的key和value,来提升性能
    private ConcurrentHashMap<Integer, List<MenuDto>> menuMap = new ConcurrentHashMap<Integer,List<MenuDto>>();

    private final Map<String, String> menuUrlCodeMap = new ConcurrentHashMap<>();

    /**
     * 装入用户菜单到menuMap中
     */
    public void loadUserMenu(Integer uid){
        List<MenuDto> menuDtos =  this.authorityMenuService.findUserAuthMenu();//获取当前用户的菜单数据
        this.menuMap.putIfAbsent(uid,menuDtos);
    }

    /**
     * 重新装入用户菜单到menuMap中
     */
    public void reLoadUserMenu(Integer uid){
        this.clearMapMenuByUserId(uid);//清空当前用户菜单
        List<MenuDto> menuDtos =  this.authorityMenuService.findUserAuthMenu();//获取当前用户的菜单数据
        this.menuMap.putIfAbsent(uid,menuDtos);
    }

    /**
     * 清空菜单map
     */
    public void clearMapMenu(){
        this.menuMap.clear();
    }


    /**
     * 清空map中当前用户的菜单
     */
    public void clearMapMenuByUserId(Integer uid){
        this.menuMap.remove(uid);
    }

    /**
     * 清空map中当前用户集合的菜单
     */
    public void clearMapMenuByUserId(List<Integer> uid){
        for(Integer id :uid){
            clearMapMenuByUserId(id);
        }
    }

    /**
     * 根据用户ID获取map中的菜单
     */
    public List<MenuDto> getMenuByUserId(Integer uid){
        List<MenuDto> menuDtoList =  this.menuMap.get(uid);
        if(CollectionUtils.isNotEmpty(menuDtoList)){
            return menuDtoList;
        }else{
            this.loadUserMenu(uid);
            return this.menuMap.get(uid);
        }
    }

    private void refreshMenuUrlCodeMap(){
        if(!menuUrlCodeMap.isEmpty()){
            menuUrlCodeMap.clear();
        }
        List<AuthorityMenu> menuList = authorityMenuService.findAllChildrenMenu();
        for (AuthorityMenu menu : menuList) {
            menuUrlCodeMap.put(menu.getMenuUrl(), menu.getMenuCode());
        }
    }

    public Map<String, String> getMenuUrlCodeMap(){
        return menuUrlCodeMap;
    }
}
