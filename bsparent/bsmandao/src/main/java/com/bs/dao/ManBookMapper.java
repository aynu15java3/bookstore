package com.bs.dao;

import com.bs.pojo.ManBook;
import com.bs.pojo.ManBookExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManBookMapper {
    long countByExample(ManBookExample example);

    int deleteByExample(ManBookExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ManBook record);

    int insertSelective(ManBook record);

    List<ManBook> selectByExampleWithBLOBs(ManBookExample example);

    List<ManBook> selectByExample(ManBookExample example);

    ManBook selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ManBook record, @Param("example") ManBookExample example);

    int updateByExampleWithBLOBs(@Param("record") ManBook record, @Param("example") ManBookExample example);

    int updateByExample(@Param("record") ManBook record, @Param("example") ManBookExample example);

    int updateByPrimaryKeySelective(ManBook record);

    int updateByPrimaryKeyWithBLOBs(ManBook record);

    int updateByPrimaryKey(ManBook record);
}