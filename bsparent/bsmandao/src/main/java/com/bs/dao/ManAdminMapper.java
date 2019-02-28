package com.bs.dao;

import com.bs.pojo.ManAdmin;
import com.bs.pojo.ManAdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManAdminMapper {
    long countByExample(ManAdminExample example);

    int deleteByExample(ManAdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ManAdmin record);

    int insertSelective(ManAdmin record);

    List<ManAdmin> selectByExample(ManAdminExample example);

    ManAdmin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ManAdmin record, @Param("example") ManAdminExample example);

    int updateByExample(@Param("record") ManAdmin record, @Param("example") ManAdminExample example);

    int updateByPrimaryKeySelective(ManAdmin record);

    int updateByPrimaryKey(ManAdmin record);
}