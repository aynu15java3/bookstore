package com.bs.action;

import com.bs.dto.*;
import com.bs.service.ManAlbumsService;
import com.bs.service.ManBookService;
import com.bs.service.ManTagService;
import com.bs.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @Author: tangfan
 * @Date:2019/2/26 17:54
 * @Description:
 */
@RestController
public class BookAction {
    //依赖服务层
    @Autowired
    private ManBookService manBookService;
    @Autowired
    private ManTagService manTagService;
    @Autowired
    private ManAlbumsService manAlbumsService;

    @RequestMapping("/tag/tagtree")
    public List<TreeDTO> showTree(Integer tagId) {
        return manTagService.findTreeTag(tagId);
    }


    @RequestMapping("/tag/tagList")
    public List<TreeDTO> showTreeBookTag(Integer tagId) {
        return manTagService.findTreeTagBookTag(tagId);
    }

    @RequestMapping(" /tag/tagedit")
    public ResultDTO editTag(HttpServletRequest request) throws ParseException {
        String tagId = request.getParameter("tagId");
        String pid = request.getParameter("pid");
        String name = request.getParameter("name");
        String icon = request.getParameter("icon");
        String alias = request.getParameter("alias");
        ManTagDto manTagDto = new ManTagDto();
        manTagDto.setTagId(Integer.parseInt(tagId));
        manTagDto.setPid(Integer.parseInt(pid));
        manTagDto.setName(name);
        manTagDto.setIcon(icon);
        manTagDto.setAlias(alias);
        manTagDto.setIsParent(Integer.parseInt(pid)==1?1:0);
        Date date = new Date();
        manTagDto.setCtime(DateUtils.dateToString(date));
        manTagService.updateTag(manTagDto);
        return ResultDTO.ok();
    }

    @RequestMapping("/tag/tagsave")
    public ResultDTO addTag(HttpServletRequest request) throws ParseException {
        String pid = request.getParameter("pid");
        String name = request.getParameter("name");
        String icon = request.getParameter("icon");
        String alias = request.getParameter("alias");
        ManTagDto manTagDto = new ManTagDto();
        manTagDto.setPid(Integer.parseInt(pid));
        manTagDto.setName(name);
        manTagDto.setIcon(icon);
        manTagDto.setAlias(alias);
        manTagDto.setIsParent(Integer.parseInt(pid)==1?1:0);
        Date date = new Date();
        manTagDto.setCtime(DateUtils.dateToString(date));
        manTagService.addTag(manTagDto);
        return ResultDTO.ok();
    }

    @RequestMapping("/tag/tagdelete")
    public ResultDTO deleTag(HttpServletRequest request) {
        String[] ids = request.getParameterValues("ids");
        //13,23,34
        String idsStr = ids[0];
        String[] split = idsStr.split(",");
        for (String s : split) {
            manTagService.deleTag(Integer.parseInt(s));
        }
        return ResultDTO.ok();
    }

    @RequestMapping("/tag/tags")
    public DataGridDTO findTag(Integer page,Integer rows){
        DataGridDTO dataGridDTO = manTagService.findTag(page, rows);
        return dataGridDTO;
    }

    @RequestMapping("/item/booklist")
    public DataGridDTO findBook(Integer page, Integer rows) {
        DataGridDTO dataGridDTO = manBookService.findBook(page, rows);
        return dataGridDTO;
    }

    @RequestMapping("/item/booksave")
    public ResultDTO addBook(HttpServletRequest request) throws ParseException {
        String tagId = request.getParameter("tagId");
        String name = request.getParameter("name");
        String author = request.getParameter("author");
        String publishing = request.getParameter("publishing");
        String oprice = request.getParameter("oPrice");
        String dprice = request.getParameter("dPrice");
        String amount = request.getParameter("amount");
        String image = request.getParameter("image");
        String description = request.getParameter("description");
        //添加到图书表，立即获得主键
        ManBookDto manBookDto = new ManBookDto();
        manBookDto.setTagId(Integer.parseInt(tagId));
        manBookDto.setName(name);
        manBookDto.setAuthor(author);
        manBookDto.setAmount(Integer.parseInt(amount));
        manBookDto.setPublishing(publishing);
        manBookDto.setoPrice(oprice);
        manBookDto.setdPrice(dprice);
        manBookDto.setDescription(description);
        Date date = new Date();
        manBookDto.setCreatedAt(DateUtils.dateToString(date));
        manBookDto.setUpdatedAt(DateUtils.dateToString(date));
        int bookId = manBookService.addBook(manBookDto);

        //添加地址到相册表

        if (!StringUtils.isEmpty(image)) {
            ManAlbumsDto manAlbumsDto = new ManAlbumsDto();
            manAlbumsDto.setBookId(bookId);
            manAlbumsDto.setName(name);
            manAlbumsDto.setPictureUrl(image);
            manAlbumsDto.setCreatedAt(DateUtils.dateToString(date));
            manAlbumsDto.setUpdatedAt(DateUtils.dateToString(date));
            manAlbumsService.addAlum(manAlbumsDto);
        }
        return ResultDTO.ok();
    }


    @RequestMapping("/item/bookedit")
    public ResultDTO editBook(HttpServletRequest request) throws ParseException {
        String id = request.getParameter("id");
        String tagId = request.getParameter("tagId");
        String name = request.getParameter("name");
        String author = request.getParameter("author");
        String publishing = request.getParameter("publishing");
        String oprice = request.getParameter("oPrice");
        String dprice = request.getParameter("dPrice");
        String amount = request.getParameter("amount");
        String image = request.getParameter("image");
        String description = request.getParameter("description");
        //修改到图书表
        ManBookDto manBookDto = new ManBookDto();
        manBookDto.setId(Integer.parseInt(id));
        manBookDto.setTagId(Integer.parseInt(tagId));
        manBookDto.setName(name);
        manBookDto.setAuthor(author);
        manBookDto.setPublishing(publishing);
        manBookDto.setoPrice(oprice);
        manBookDto.setdPrice(dprice);
        manBookDto.setAmount(Integer.parseInt(amount));
        manBookDto.setDescription(description);
        Date date = new Date();
        manBookDto.setCreatedAt(DateUtils.dateToString(date));
        manBookDto.setUpdatedAt(DateUtils.dateToString(date));
        manBookService.updateBook(manBookDto);
        return ResultDTO.ok();
    }

    @RequestMapping("/item/bookdelete")
    public ResultDTO deleBook(HttpServletRequest request) {
        String[] ids = request.getParameterValues("ids");
        //13,23,34
        String idsStr = ids[0];
        String[] split = idsStr.split(",");
        for (String s : split) {
            manBookService.delBook(Integer.parseInt(s));
        }
        return ResultDTO.ok();
    }
}
