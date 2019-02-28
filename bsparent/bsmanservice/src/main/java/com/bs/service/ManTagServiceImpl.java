package com.bs.service;

import com.bs.dao.ManTagMapper;
import com.bs.dto.DataGridDTO;
import com.bs.dto.ManTagDto;
import com.bs.dto.TreeDTO;
import com.bs.pojo.ManTag;
import com.bs.pojo.ManTagExample;
import com.bs.utils.DateUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tangfan
 * @Date:2019/2/26 16:35
 * @Description:
 */
@Service
public class ManTagServiceImpl implements ManTagService {
    @Autowired
    private ManTagMapper manTagMapper;

    @Override
    public List<TreeDTO> findTreeTag(Integer tagId) {

        ManTagExample example = new ManTagExample();
        ManTagExample.Criteria criteria = example.createCriteria();
        criteria.andPidEqualTo(tagId);

        TreeDTO treeDTO1 = new TreeDTO();
        treeDTO1.setId(1);
        //"open":菜单  closed:目录
        treeDTO1.setState("closed");
        treeDTO1.setText("根目录");

        List<TreeDTO> list = new ArrayList<>();
        List<ManTag> manTags = manTagMapper.selectByExample(example);
        list.add(treeDTO1);
        for (ManTag tag : manTags) {
            TreeDTO treeDTO = new TreeDTO();
            treeDTO.setId(tag.getTagId());
            //"open":菜单  closed:目录
            treeDTO.setState(tag.getIsParent() != null && tag.getIsParent() == 1 ? "closed" : "open");
            treeDTO.setText(tag.getName());
            list.add(treeDTO);
        }
        return list;
    }


    @Override
    public List<TreeDTO> findTreeTagBookTag(Integer tagId) {

        ManTagExample example = new ManTagExample();
        ManTagExample.Criteria criteria = example.createCriteria();
        criteria.andPidEqualTo(tagId);
        List<TreeDTO> list = new ArrayList<>();
        List<ManTag> manTags = manTagMapper.selectByExample(example);
        for (ManTag tag : manTags) {
            TreeDTO treeDTO = new TreeDTO();
            treeDTO.setId(tag.getTagId());
            //"open":菜单  closed:目录
            treeDTO.setState(tag.getIsParent() != null && tag.getIsParent() == 1 ? "closed" : "open");
            treeDTO.setText(tag.getName());
            list.add(treeDTO);
        }
        return list;
    }

    @Override
    public int addTag(ManTagDto manTagDto) throws ParseException {
        ManTag manTag = new ManTag();
        BeanUtils.copyProperties(manTagDto, manTag);
        manTag.setCtime(DateUtils.stringToDate(manTagDto.getCtime()));
        return manTagMapper.insert(manTag);
    }

    @Override
    public void updateTag(ManTagDto manTagDto) throws ParseException {
        ManTag manTag = new ManTag();
        BeanUtils.copyProperties(manTagDto, manTag);
        manTag.setCtime(DateUtils.stringToDate(manTagDto.getCtime()));
        manTagMapper.updateByPrimaryKeySelective(manTag);
    }

    @Override
    public int deleTag(Integer tagId) {
        return manTagMapper.deleteByPrimaryKey(tagId);
    }


    @Override
    public DataGridDTO findTag(int page, int rows) {
        DataGridDTO dataGridDTO = new DataGridDTO();
        PageHelper.startPage(page, rows);
        ManTagExample example = new ManTagExample();
        List<ManTag> tags = manTagMapper.selectByExample(example);
        PageInfo<ManTag> info = new PageInfo<>(tags);
        long total = info.getTotal();
        dataGridDTO.setTotal(total);
        dataGridDTO.setRows(tags);
        return dataGridDTO;
    }
}
