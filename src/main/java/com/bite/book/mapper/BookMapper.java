package com.bite.book.mapper;

import com.bite.book.model.BookInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookMapper {
    /**
     * 插入图书
     */
    @Insert("insert into book_info (book_name, author, count, price, publish, `status`) " +
            "values (#{bookName}, #{author}, #{count}, #{price}, #{publish}, #{status})")
    Integer insertBook(BookInfo bookInfo);

    @Select("select * from book_info where status !=0 order by id desc limit #{offset}, #{limit}")
    List<BookInfo> queryBookByPage(Integer offset, Integer limit);

    @Select("select count(1) from book_info where status != 0")
    Integer count();

    @Select("select * from book_info where id = #{bookId}")
    BookInfo queryBookById(Integer bookId);

    Integer updateBookById(BookInfo bookInfo);

    Integer batchDeleteBookByIds(List<Integer> ids);
}
