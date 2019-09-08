package com.cqupt.ssm.dao;

import com.cqupt.ssm.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author LiSheng
 * @date 2019/9/6 22:55
 */
public interface ISysLogDao {

    /**
     * 保存日志信息到数据库中
     *
     * @param sysLog
     */
    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void saveSysLog(SysLog sysLog) throws Exception;

    /**
     * 查询所有日志信息
     *
     * @return
     * @throws Exception
     */
    @Select("select * from syslog")
    List<SysLog> findAll() throws Exception;
}
