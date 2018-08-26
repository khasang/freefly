package io.khasang.freefly.service.impl;

import io.khasang.freefly.dao.SecurityLogDao;
import io.khasang.freefly.entity.SecurityLog;
import io.khasang.freefly.service.SecurityLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("securityLogService")
public class SecurityLogServiceImpl implements SecurityLogService {

    @Autowired
    private SecurityLogDao securityLogDao;

    @Override
    public SecurityLog addSecurityLog(SecurityLog securityLog) {
        return securityLogDao.create(securityLog);
    }

    @Override
    public SecurityLog getSecurityLogById(long id) {
        return securityLogDao.getById(id);
    }

    @Override
    public void deleteSecurityLogById(long id) {
    }
}
