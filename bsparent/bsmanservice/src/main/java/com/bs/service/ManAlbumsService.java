package com.bs.service;

import com.bs.dto.ManAlbumsDto;

import java.text.ParseException;

/**
 * @Author: tangfan
 * @Date:2019/2/27 14:52
 * @Description:
 */
public interface ManAlbumsService {
    public void addAlum(ManAlbumsDto manAlbumsDto) throws ParseException;
}
