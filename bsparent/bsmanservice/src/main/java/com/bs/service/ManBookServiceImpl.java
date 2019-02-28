package com.bs.service;

import com.bs.dao.ManBookMapper;
import com.bs.dto.DataGridDTO;
import com.bs.dto.ManBookDto;
import com.bs.pojo.ManBook;
import com.bs.pojo.ManBookExample;
import com.bs.utils.DateUtils;
import com.bs.utils.MathUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

/**
 * @Author: tangfan
 * @Date:2019/2/26 16:54
 * @Description:
 */
@Service
public class ManBookServiceImpl implements ManBookService {

    @Autowired
    private ManBookMapper manBookMapper;

    /*添加图书之后返回主键，相册表会用
     * */
    @Override
    public int addBook(ManBookDto manBookDto) throws ParseException {
        ManBook manBook = new ManBook();
        BeanUtils.copyProperties(manBookDto, manBook);
        manBook.setCreatedAt(DateUtils.stringToDate(manBookDto.getCreatedAt()));
        manBook.setUpdatedAt(DateUtils.stringToDate(manBookDto.getUpdatedAt()));
        manBook.setoPrice(MathUtil.bigDecimal(manBookDto.getoPrice()));
        manBook.setdPrice(MathUtil.bigDecimal(manBookDto.getdPrice()));
        int insert = manBookMapper.insert(manBook);
        //主键在实体类中
        return manBook.getId();

    }

    @Override
    public int delBook(Integer id) {
        return manBookMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateBook(ManBookDto manBookDto) throws ParseException {
       //动态更新
        ManBook manBook = new ManBook();
        BeanUtils.copyProperties(manBookDto, manBook);
        manBook.setCreatedAt(DateUtils.stringToDate(manBookDto.getCreatedAt()));
        manBook.setUpdatedAt(DateUtils.stringToDate(manBookDto.getUpdatedAt()));
        manBook.setoPrice(MathUtil.bigDecimal(manBookDto.getoPrice()));
        manBook.setdPrice(MathUtil.bigDecimal(manBookDto.getdPrice()));
        manBookMapper.updateByPrimaryKeySelective(manBook);
    }

    @Override
    public DataGridDTO findBook(int page, int rows) {
        DataGridDTO dataGridDTO = new DataGridDTO();
        PageHelper.startPage(page, rows);
        ManBookExample example = new ManBookExample();
        List<ManBook> manBookList = manBookMapper.selectByExample(example);
        PageInfo<ManBook> pageInfo = new PageInfo<>(manBookList);
        long total = pageInfo.getTotal();//总记录数
        dataGridDTO.setTotal(total);
        dataGridDTO.setRows(manBookList);
        return dataGridDTO;
    }
}
