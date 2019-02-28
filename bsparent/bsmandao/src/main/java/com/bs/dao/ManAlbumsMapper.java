package com.bs.dao;

import com.bs.pojo.ManAlbums;
import com.bs.pojo.ManAlbumsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManAlbumsMapper {
    long countByExample(ManAlbumsExample example);

    int deleteByExample(ManAlbumsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ManAlbums record);

    int insertSelective(ManAlbums record);

    List<ManAlbums> selectByExample(ManAlbumsExample example);

    ManAlbums selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ManAlbums record, @Param("example") ManAlbumsExample example);

    int updateByExample(@Param("record") ManAlbums record, @Param("example") ManAlbumsExample example);

    int updateByPrimaryKeySelective(ManAlbums record);

    int updateByPrimaryKey(ManAlbums record);
}