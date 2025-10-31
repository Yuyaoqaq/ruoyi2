package com.ruoyi.bookSys.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.bookSys.domain.LoanRecords;
import com.ruoyi.bookSys.mapper.BooksMapper;
import com.ruoyi.bookSys.domain.Books;
import com.ruoyi.bookSys.service.IBooksService;

/**
 * 图书信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-09-16
 */
@Service
public class BooksServiceImpl implements IBooksService 
{
    @Autowired
    private BooksMapper booksMapper;

    /**
     * 查询图书信息
     * 
     * @param bookId 图书信息主键
     * @return 图书信息
     */
    @Override
    public Books selectBooksByBookId(Long bookId)
    {
        return booksMapper.selectBooksByBookId(bookId);
    }

    /**
     * 查询图书信息列表
     * 
     * @param books 图书信息
     * @return 图书信息
     */
    @Override
    public List<Books> selectBooksList(Books books)
    {
        return booksMapper.selectBooksList(books);
    }

    /**
     * 新增图书信息
     * 
     * @param books 图书信息
     * @return 结果
     */
    @Transactional
    @Override
    public int insertBooks(Books books)
    {
        int rows = booksMapper.insertBooks(books);
        insertLoanRecords(books);
        return rows;
    }

    /**
     * 修改图书信息
     * 
     * @param books 图书信息
     * @return 结果
     */
    @Transactional
    @Override
    public int updateBooks(Books books)
    {
        booksMapper.deleteLoanRecordsByRecordId(books.getBookId());
        insertLoanRecords(books);
        return booksMapper.updateBooks(books);
    }

    /**
     * 批量删除图书信息
     * 
     * @param bookIds 需要删除的图书信息主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteBooksByBookIds(Long[] bookIds)
    {
        booksMapper.deleteLoanRecordsByRecordIds(bookIds);
        return booksMapper.deleteBooksByBookIds(bookIds);
    }

    /**
     * 删除图书信息信息
     * 
     * @param bookId 图书信息主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteBooksByBookId(Long bookId)
    {
        booksMapper.deleteLoanRecordsByRecordId(bookId);
        return booksMapper.deleteBooksByBookId(bookId);
    }

    /**
     * 新增借阅信息查询信息
     * 
     * @param books 图书信息对象
     */
    public void insertLoanRecords(Books books)
    {
        List<LoanRecords> loanRecordsList = books.getLoanRecordsList();
        Long bookId = books.getBookId();
        if (StringUtils.isNotNull(loanRecordsList))
        {
            List<LoanRecords> list = new ArrayList<LoanRecords>();
            for (LoanRecords loanRecords : loanRecordsList)
            {
                loanRecords.setRecordId(bookId);
                list.add(loanRecords);
            }
            if (list.size() > 0)
            {
                booksMapper.batchLoanRecords(list);
            }
        }
    }
}
