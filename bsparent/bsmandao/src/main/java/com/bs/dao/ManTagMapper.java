package com.bs.dao;

import com.bs.pojo.ManTag;
import com.bs.pojo.ManTagExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManTagMapper {
    long countByExample(ManTagExample example);

    int deleteByExample(ManTagExample example);

    int deleteByPrimaryKey(Integer tagId);

    int insert(ManTag record);

    int insertSelective(ManTag record);

    List<ManTag> selectByExample(ManTagExample example);

    ManTag selectByPrimaryKey(Integer tagId);

    int updateByExampleSelective(@Param("record") ManTag record, @Param("example") ManTagExample example);

    int updateByExample(@Param("record") ManTag record, @Param("example") ManTagExample example);

    int updateByPrimaryKeySelective(ManTag record);

    int updateByPrimaryKey(ManTag record);
}