package com.tony.springboot.demo.service;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.tony.springboot.demo.constant.Constants;
import com.tony.springboot.demo.constant.RedisKeyPrefix;
import com.tony.springboot.demo.entity.SysUser;
import com.tony.springboot.demo.enums.StatusEnum;
import com.tony.springboot.demo.model.R;
import com.tony.springboot.demo.model.bo.UserBO;
import com.tony.springboot.demo.model.vo.login.LoginVO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class LoginService {
    @Resource
    private SysUserService sysUserService;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private static final Integer MAX_EXPIRE_SECONDS = 3600;

    public R login(LoginVO loginVO) {
        SysUser sysUser = sysUserService.queryByUsername(loginVO.getUsername());
        if (sysUser == null || !Objects.equals(sysUser.getPassword(), loginVO.getPassword())) {
            return R.error("用户名或者密码不正确");
        }
        if(Objects.equals(sysUser.getStatus(), StatusEnum.DISABLE.getVal())) {
            return R.error("用户已禁用");
        }
        UserBO userBO = UserBO.builder()
                .userId(sysUser.getId())
                .username(sysUser.getUsername())
                .email(sysUser.getEmail())
                .build();
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        redisTemplate.opsForValue().set(RedisKeyPrefix.TOKEN_PREFIX + token, userBO, MAX_EXPIRE_SECONDS, TimeUnit.SECONDS);
        return R.ok(token);
    }

    public UserBO getCurrentUser(HttpServletRequest request) {
        String token = request.getHeader(Constants.TOKEN);
        if (StrUtil.isBlank(token)) {
            return null;
        }
        return (UserBO) redisTemplate.opsForValue().get(RedisKeyPrefix.TOKEN_PREFIX + token);
    }
}
