package com.tony.springboot.demo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tony.springboot.demo.context.UserConext;
import com.tony.springboot.demo.entity.SysUser;
import com.tony.springboot.demo.enums.StatusEnum;
import com.tony.springboot.demo.mapper.SysRoleResourceMapper;
import com.tony.springboot.demo.mapper.SysRoleUserMapper;
import com.tony.springboot.demo.mapper.SysUserMapper;
import com.tony.springboot.demo.model.R;
import com.tony.springboot.demo.model.bo.UserBO;
import com.tony.springboot.demo.model.dto.user.SysUserPageDTO;
import com.tony.springboot.demo.model.vo.user.SysUserEditVO;
import com.tony.springboot.demo.model.vo.user.SysUserPageVO;
import com.tony.springboot.demo.model.vo.user.SysUserStatusVO;
import com.tony.springboot.demo.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Resource
    private SysRoleUserMapper sysRoleUserMapper;
    @Resource
    private SysRoleResourceMapper sysRoleResourceMapper;

    @Override
    public R userPage(SysUserPageVO query) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        String username = query.getUsername();
        if (StrUtil.isNotEmpty(username)) {
            queryWrapper.lambda().likeRight(SysUser::getUsername, username);
        }
        IPage<SysUser> listPage =super.page(new Page<>(query.getNumber(), query.getSize()), queryWrapper);
        List<SysUser> records = listPage.getRecords();
        Map<Long, String> userNameMap = buildUserNameMap(records.stream().flatMap(e -> Stream.of(e.getCreateUser(), e.getUpdateUser())).collect(Collectors.toList()));
        return R.ok(records.stream().map(e -> new SysUserPageDTO(e, userNameMap)).collect(Collectors.toList()));
    }

    private Map<Long, String> buildUserNameMap(Collection<Long> userIds) {
        if (CollUtil.isEmpty(userIds)) {
            return Collections.emptyMap();
        }
        return super.list(new QueryWrapper<SysUser>().lambda().in(SysUser::getId, userIds))
                .stream().collect(Collectors.toMap(SysUser::getId, SysUser::getUsername));
    }

    @Override
    public R userEdit(SysUserEditVO edit) {
        UserBO user = UserConext.getUser();
        SysUser entity = getById(edit.getId());
        if (entity == null) {
            entity = new SysUser();
            BeanUtil.copyProperties(edit, entity);
            entity.setStatus(StatusEnum.ENABLE.getVal());
            entity.setCreateUser(user.getUserId());
            entity.setUpdateUser(user.getUserId());
            long timeMillis = System.currentTimeMillis();
            entity.setCreateTime(timeMillis);
            entity.setUpdateTime(timeMillis);
            save(entity);
            return R.ok(entity.getId());
        }

        BeanUtil.copyProperties(edit, entity, CopyOptions.create().setIgnoreNullValue(true));
        entity.setUpdateUser(user.getUserId());
        long timeMillis = System.currentTimeMillis();
        entity.setUpdateTime(timeMillis);
        updateById(entity);
        return R.ok(entity.getId());
    }

    @Override
    public R userStatus(SysUserStatusVO statusVO) {
        UserBO user = UserConext.getUser();
        SysUser entity = new SysUser();
        entity.setId(statusVO.getId());
        entity.setStatus(statusVO.getStatus());
        entity.setUpdateUser(user.getUserId());
        long timeMillis = System.currentTimeMillis();
        entity.setUpdateTime(timeMillis);
        updateById(entity);
        return R.ok(entity.getId());
    }

    @Override
    public Set<String> queryUserApis(Long userId) {
        List<Long> roleIds = sysRoleUserMapper.queryRoleIdsByUserId(userId);
        if (CollUtil.isEmpty(roleIds)) {
            return Collections.emptySet();
        }
        return sysRoleResourceMapper.queryUserApis(roleIds);
    }

    @Override
    public SysUser queryByUsername(String username) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysUser::getUsername, username);
        List<SysUser> users = list(queryWrapper);
        if (CollUtil.isEmpty(users) || users.get(0) == null) {
            return null;
        }
        return users.get(0);
    }

}




