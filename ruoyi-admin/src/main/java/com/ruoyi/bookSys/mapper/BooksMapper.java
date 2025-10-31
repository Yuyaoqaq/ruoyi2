package com.ruoyi.bookSys.mapper;

import java.util.List;
import com.ruoyi.bookSys.domain.Books;
import com.ruoyi.bookSys.domain.LoanRecords;

/**
 * 图书信息Mapper接口
 * 
 * @author ruoyi
 * @date 2025-09-16
 */
public interface BooksMapper 
{
    /**
     * 查询图书信息
     * 
     * @param bookId 图书信息主键
     * @return 图书信息
     */
    public Books selectBooksByBookId(Long bookId);

    /**
     * 查询图书信息列表
     * 
     * @param books 图书信息
     * @return 图书信息集合
     */
    public List<Books> selectBooksList(Books books);

    /**
     * 新增图书信息
     * 
     * @param books 图书信息
     * @return 结果
     */
    public int insertBooks(Books books);

    /**
     * 修改图书信息
     * 
     * @param books 图书信息
     * @return 结果
     */
    public int updateBooks(Books books);

    /**
     * 删除图书信息
     * 
     * @param bookId 图书信息主键
     * @return 结果
     */
    public int deleteBooksByBookId(Long bookId);

    /**
     * 批量删除图书信息
     * 
     * @param bookIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBooksByBookIds(Long[] bookIds);

    /**
     * 批量删除借阅信息查询
     * 
     * @param bookIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLoanRecordsByRecordIds(Long[] bookIds);
    
    /**
     * 批量新增借阅信息查询
     * 
     * @param loanRecordsList 借阅信息查询列表
     * @return 结果
     */
    public int batchLoanRecords(List<LoanRecords> loanRecordsList);
    

    /**
     * 通过图书信息主键删除借阅信息查询信息
     * 
     * @param bookId 图书信息ID
     * @return 结果
     */
    public int deleteLoanRecordsByRecordId(Long bookId);
}
