package com.bs.service;

import com.bs.dto.DataGridDTO;
import com.bs.dto.ManTagDto;
import com.bs.dto.TreeDTO;

import java.text.ParseException;
import java.util.List;

/**
 * @Author: tangfan
 * @Date:2019/2/26 16:35
 * @Description:
 */
public interface ManTagService {
    List<TreeDTO> findTreeTag(Integer tagId);

    List<TreeDTO> findTreeTagBookTag(Integer tagId);

    int addTag(ManTagDto manTagDto) throws ParseException;

    int deleTag(Integer tagId);

    void updateTag(ManTagDto manTagDto) throws ParseException;

    DataGridDTO findTag(int page, int rows);

}
