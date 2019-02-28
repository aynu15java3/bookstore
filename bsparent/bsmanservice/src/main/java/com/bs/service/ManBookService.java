package com.bs.service;

import com.bs.dto.DataGridDTO;
import com.bs.dto.ManBookDto;

import java.text.ParseException;

/**
 * @Author: tangfan
 * @Date:2019/2/26 16:30
 * @Description:
 */
public interface ManBookService {
    public int addBook(ManBookDto manBookDto) throws ParseException;
    public int delBook(Integer id);
    public void updateBook(ManBookDto manBookDto) throws ParseException;
    public DataGridDTO findBook(int page, int rows);
}
