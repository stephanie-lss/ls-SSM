package com.cqupt.ssm.service;

import com.cqupt.ssm.domain.SysLog;

import java.util.List;

/**
 * @author LiSheng
 * @date 2019/9/6 22:53
 */
public interface ISysLogService {
    /**
     * 保存日志信息
     * @param sysLog
     */
    void saveSysLog(SysLog sysLog) throws Exception;

    /**
     * 查询所有日志信息
     * @return
     */
    List<SysLog> findAll() throws Exception;
}
