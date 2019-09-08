package com.cqupt.ssm.service.impl;

import com.cqupt.ssm.dao.ISysLogDao;
import com.cqupt.ssm.domain.SysLog;
import com.cqupt.ssm.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author LiSheng
 * @date 2019/9/6 22:54
 */
@Service("sysLogService")
@Transactional
public class SysLogServiceImpl implements ISysLogService {

    @Autowired
    private ISysLogDao sysLogDao;
    @Override
    public void saveSysLog(SysLog sysLog) throws Exception {
        sysLogDao.saveSysLog(sysLog);
    }

    @Override
    public List<SysLog> findAll() throws Exception {
        return sysLogDao.findAll();
    }
}
