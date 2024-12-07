package com.bite.book.controller;

import com.bite.book.constant.Constants;
import com.bite.book.enums.ResultStatus;
import com.bite.book.model.*;
import com.bite.book.service.BookService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Book;
import java.util.List;

@Slf4j
@RequestMapping("/book")
@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    /**
     * 添加图书
     */
    @RequestMapping(value = "/addBook",produces = "application/json")
    public String addBook(BookInfo bookInfo){
        //校验参数
        log.info("添加图书, 接收到参数bookInfo: {}", bookInfo);

        if (!StringUtils.hasLength(bookInfo.getBookName())
                || !StringUtils.hasLength(bookInfo.getAuthor())
                || bookInfo.getCount()==null
                || bookInfo.getPrice()==null
                || !StringUtils.hasLength(bookInfo.getPublish())
                || bookInfo.getStatus()==null){
            return "输入参数不合法";
        }
        //添加图书
        try{
            Integer result = bookService.insertBook(bookInfo);
            if (result >0){
                return "";
            }
        }catch (Exception e){
            log.error("添加图书异常, e: ", e);
        }
        return "添加失败";
    }

    /**
     * 查询图书列表
     */
    @RequestMapping("/getBookListByPage")
    public Result<PageResult<BookInfo>> getBookListByPage(PageRequest pageRequest, HttpSession session){
        log.info("查询图书列表, 请求参数pageRequest: {}", pageRequest);
//        //从session获取用户信息
//        //如果用户信息为空, 说明用户未登录
//        UserInfo loginUserInfo = (UserInfo) session.getAttribute(Constants.USER_SESSION_KEY);
//        if (loginUserInfo==null || loginUserInfo.getId()<=0){
//            return Result.nologin();
//        }
        //参数校验, 自己补充
        PageResult<BookInfo> bookList = bookService.getBookListByPage(pageRequest);
        return Result.success(bookList);
    }

    /**
     * 查询图书信息
     * @param bookId
     * @return
     */
    @RequestMapping("/queryBookById")
    public BookInfo queryBookById(Integer bookId){
        log.info("根据ID查询图书信息, id:"+bookId);
        long start = System.currentTimeMillis();
        BookInfo bookInfo = bookService.queryBookById(bookId);
        log.info("queryBookById 耗时: "+ (System.currentTimeMillis()-start) + "ms");
        return bookInfo;
    }
    /**
     * 更新图书
     */
    @RequestMapping(value = "/updateBook", produces = "application/json")
    public String updateBook(BookInfo bookInfo){
        log.info("更新图书, bookInfo: {}", bookInfo);
        try{
            Integer result = bookService.updateBookById(bookInfo);
            if (result>0){
                return "";
            }
            return "内部错误, 请联系管理员";
        }catch (Exception e){
            log.error("更新图书失败, e:", e);
            return "内部错误, 请联系管理员";
        }
    }

    /**
     * 删除图书
     */
    @RequestMapping("/deleteBook")
    public Boolean deleteBook(Integer bookId) {
        log.info("删除图书, bookId: {}", bookId);
        Integer result = bookService.deleteBook(bookId);
        if (result>0){
            return true;
        }
        return false;
    }

    /**
     * 批量删除图书
     * @param ids
     * @return
     */
    @RequestMapping(value = "/batchDeleteBook", produces = "application/json")
    public String batchDelete(@RequestParam List<Integer> ids){
        log.info("批量删除图书, ids: {}", ids);
        Integer result = bookService.batchDeleteBookByIds(ids);
        if (result >0){
            return "";
        }
        return "删除失败, 请联系管理员";
    }

}
