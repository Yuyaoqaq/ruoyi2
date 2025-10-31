package com.ruoyi.bookSys.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 借阅信息查询对象 loan_records
 * 
 * @author ruoyi
 * @date 2025-09-16
 */
public class LoanRecords extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 借书记录的唯一ID */
    private Long recordId;

    /** 被借的图书ID */
    @Excel(name = "被借的图书ID")
    private Long bookId;

    /** 借书的用户ID */
    @Excel(name = "借书的用户ID")
    private Long userId;

    /** 借书日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "借书日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date loanDate;

    /** 应还日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "应还日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dueDate;

    /** 是否已还书 */
    @Excel(name = "是否已还书")
    private Integer returned;

    public void setRecordId(Long recordId) 
    {
        this.recordId = recordId;
    }

    public Long getRecordId() 
    {
        return recordId;
    }
    public void setBookId(Long bookId) 
    {
        this.bookId = bookId;
    }

    public Long getBookId() 
    {
        return bookId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setLoanDate(Date loanDate) 
    {
        this.loanDate = loanDate;
    }

    public Date getLoanDate() 
    {
        return loanDate;
    }
    public void setDueDate(Date dueDate) 
    {
        this.dueDate = dueDate;
    }

    public Date getDueDate() 
    {
        return dueDate;
    }
    public void setReturned(Integer returned) 
    {
        this.returned = returned;
    }

    public Integer getReturned() 
    {
        return returned;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("recordId", getRecordId())
            .append("bookId", getBookId())
            .append("userId", getUserId())
            .append("loanDate", getLoanDate())
            .append("dueDate", getDueDate())
            .append("returned", getReturned())
            .toString();
    }
}
