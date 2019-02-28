package com.bs.service;

import com.bs.dao.ManAlbumsMapper;
import com.bs.dto.ManAlbumsDto;
import com.bs.pojo.ManAlbums;
import com.bs.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;

/**
 * @Author: tangfan
 * @Date:2019/2/27 14:54
 * @Description:
 */
@Service
public class ManAlbumsServiceImpl implements ManAlbumsService {
    @Autowired
    private ManAlbumsMapper manAlbumsMapper;
    @Override
    public void addAlum(ManAlbumsDto manAlbumsDto) throws ParseException {
        ManAlbums manAlbums = new ManAlbums();
        BeanUtils.copyProperties(manAlbumsDto,manAlbums);
        manAlbums.setCreatedAt(DateUtils.stringToDate(manAlbumsDto.getCreatedAt()));
        manAlbums.setUpdatedAt(DateUtils.stringToDate(manAlbumsDto.getUpdatedAt()));
        manAlbumsMapper.insert(manAlbums);
    }
}
