package com.cqupt.ssm.dao;

import com.cqupt.ssm.domain.Member;
import org.apache.ibatis.annotations.Select;

/**
 * Created by Lisheng in 10:20 2019/9/5
 */
public interface IMemberDao {

    @Select("select * from member where id = #{id}")
    Member findById(Integer id) throws Exception;
}
