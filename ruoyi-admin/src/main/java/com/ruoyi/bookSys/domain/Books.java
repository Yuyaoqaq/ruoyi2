package com.ruoyi.bookSys.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 图书信息对象 books
 * 
 * @author ruoyi
 * @date 2025-09-16
 */
public class Books extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 图书的唯一ID */
    private Long bookId;

    /** 书名 */
    @Excel(name = "书名")
    private String title;

    /** 作者名称 */
    @Excel(name = "作者名称")
    private String author;

    /** 出版日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出版日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date publishDate;

    /** 国际标准书号 */
    @Excel(name = "国际标准书号")
    private String isbn;

    /** 图书是否可借 */
    @Excel(name = "图书是否可借")
    private Integer available;

    /** 借阅信息查询信息 */
    private List<LoanRecords> loanRecordsList;

    public void setBookId(Long bookId) 
    {
        this.bookId = bookId;
    }

    public Long getBookId() 
    {
        return bookId;
    }

    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }

    public void setAuthor(String author) 
    {
        this.author = author;
    }

    public String getAuthor() 
    {
        return author;
    }

    public void setPublishDate(Date publishDate) 
    {
        this.publishDate = publishDate;
    }

    public Date getPublishDate() 
    {
        return publishDate;
    }

    public void setIsbn(String isbn) 
    {
        this.isbn = isbn;
    }

    public String getIsbn() 
    {
        return isbn;
    }

    public void setAvailable(Integer available) 
    {
        this.available = available;
    }

    public Integer getAvailable() 
    {
        return available;
    }

    public List<LoanRecords> getLoanRecordsList()
    {
        return loanRecordsList;
    }

    public void setLoanRecordsList(List<LoanRecords> loanRecordsList)
    {
        this.loanRecordsList = loanRecordsList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("bookId", getBookId())
            .append("title", getTitle())
            .append("author", getAuthor())
            .append("publishDate", getPublishDate())
            .append("isbn", getIsbn())
            .append("available", getAvailable())
            .append("loanRecordsList", getLoanRecordsList())
            .toString();
    }
}
