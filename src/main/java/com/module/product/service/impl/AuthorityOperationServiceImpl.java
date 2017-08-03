package com.module.product.service.impl;


import com.module.product.common.dto.OperTreeDto;
import com.module.product.orm.mapper.AuthorityPermissionMapper;
import com.module.product.common.dto.OperTreeDto;
import com.module.product.orm.mapper.AuthorityOperationMapper;
import com.module.product.orm.mapper.AuthorityPermissionMapper;
import com.module.product.orm.model.AuthorityOperation;
import com.module.product.service.AuthorityOperationService;
import com.module.product.service.core.AbstractService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service Implementation:AuthorityOperation
 *
 * @author duia_builder
 * @date 2016-1-20
 */
@Transactional
@Service
public class AuthorityOperationServiceImpl extends AbstractService<AuthorityOperation> implements AuthorityOperationService {
    @Resource
    private AuthorityOperationMapper operationMapper;
    @Resource
    private AuthorityPermissionMapper permissionMapper;

    /**
     * 查询菜单下的所有操作树,并根据角色权限,对操作设置勾选状态
     *
     * @param menuId
     * @param roleId
     * @return
     */
    @Override
    public OperTreeDto findMenuOperByRoleID(Integer menuId, Integer roleId) {
        //操作默认的根级
        OperTreeDto root = new OperTreeDto(-1, "全部操作", true);
        List<AuthorityOperation> operations = this.operationMapper.getAuthOperListByMenuId(menuId);//获取所有操作
        if (!CollectionUtils.isEmpty(operations)) {
            Map map = new HashMap<>();
            map.put("menuId", menuId);
            map.put("roleId", roleId);
            List<Integer> operIds = this.permissionMapper.getOperIdByRoleMenu(map);//获取当前角色当前菜单下的操作ID
            for (AuthorityOperation oper : operations) {
                OperTreeDto operTreeDto = new OperTreeDto();
                operTreeDto.setId(oper.getId());
                operTreeDto.setName(oper.getName());
                operTreeDto.setChecked(operIds.contains(oper.getId()));
                operTreeDto.setCode(oper.getCode());
                operTreeDto.setParent(false);
                root.getChildren().add(operTreeDto);
            }

        }
        return root;
    }

    /**
     * 验证菜单下操作码的唯一性
     *
     * @param operation
     * @return
     */
    @Override
    public boolean checkOperUnique(AuthorityOperation operation) {
        return this.operationMapper.checkOperUnique(operation) == 0 ? true : false;
    }
}
