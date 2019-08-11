package com.linqi.userservice.service;

import com.google.common.base.Preconditions;
import com.linqi.userservice.common.beans.PageQuery;
import com.linqi.userservice.common.beans.PageResult;
import com.linqi.userservice.common.enums.UserResultEnum;
import com.linqi.userservice.common.exception.ParamException;
import com.linqi.userservice.common.utils.IpUtil;
import com.linqi.userservice.common.utils.MD5Util;
import com.linqi.userservice.common.utils.PasswordUtil;
import com.linqi.userservice.common.utils.RequestHolder;
import com.linqi.userservice.dao.SysUserMapper;
import com.linqi.userservice.model.SysUser;
import com.linqi.userservice.param.UserParam;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysLogService sysLogService;

    public void save(UserParam param) {
        if(checkTelephoneExist(param.getTelephone(), param.getId())) {
            throw new ParamException(UserResultEnum.TELEPHONE_EXIST);
        }
        if(checkEmailExist(param.getMail(), param.getId())) {
            throw new ParamException(UserResultEnum.EMAIL_EXIST);
        }
        String password = PasswordUtil.randomPassword();
        // TODO:
        password = "12345678";
        String encryptedPassword = MD5Util.encrypt(password);
        SysUser user = SysUser.builder().username(param.getUsername()).telephone(param.getTelephone()).mail(param.getMail())
                .password(encryptedPassword).deptId(param.getDeptId()).status(param.getStatus()).remark(param.getRemark()).build();
        user.setOperator(RequestHolder.getCurrentUser().getUsername());
        user.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        user.setOperateTime(new Date());

        // TODO: sendEmail

        sysUserMapper.insertSelective(user);
        sysLogService.saveUserLog(null, user);
    }

    public void update(UserParam param) {
        if(checkTelephoneExist(param.getTelephone(), param.getId())) {
            throw new ParamException(UserResultEnum.TELEPHONE_EXIST);
        }
        if(checkEmailExist(param.getMail(), param.getId())) {
            throw new ParamException(UserResultEnum.EMAIL_EXIST);
        }
        SysUser before = sysUserMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before, "待更新的用户不存在");
        SysUser after = SysUser.builder().id(param.getId()).username(param.getUsername()).telephone(param.getTelephone()).mail(param.getMail())
                .deptId(param.getDeptId()).status(param.getStatus()).remark(param.getRemark()).build();
        after.setOperator(RequestHolder.getCurrentUser().getUsername());
        after.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        after.setOperateTime(new Date());
        sysUserMapper.updateByPrimaryKeySelective(after);
        sysLogService.saveUserLog(before, after);
    }

    public boolean checkEmailExist(String mail, Integer userId) {
        return sysUserMapper.countByMail(mail, userId) > 0;
    }

    public boolean checkTelephoneExist(String telephone, Integer userId) {
        return sysUserMapper.countByTelephone(telephone, userId) > 0;
    }

    public SysUser findByKeyword(String keyword) {
        return sysUserMapper.findByKeyword(keyword);
    }

    public PageResult<SysUser> getPageByDeptId(int deptId, PageQuery page) {
        int count = sysUserMapper.countByDeptId(deptId);
        if (count > 0) {
            List<SysUser> list = sysUserMapper.getPageByDeptId(deptId, page);
            return PageResult.<SysUser>builder().total(count).data(list).build();
        }
        return PageResult.<SysUser>builder().build();
    }

    public List<SysUser> getAll() {
        return sysUserMapper.getAll();
    }
}
