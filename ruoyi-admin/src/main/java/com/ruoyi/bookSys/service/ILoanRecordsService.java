package com.ruoyi.bookSys.service;

import java.util.List;
import com.ruoyi.bookSys.domain.LoanRecords;

/**
 * 借阅信息查询Service接口
 * 
 * @author ruoyi
 * @date 2025-09-16
 */
public interface ILoanRecordsService 
{
    /**
     * 查询借阅信息查询
     * 
     * @param recordId 借阅信息查询主键
     * @return 借阅信息查询
     */
    public LoanRecords selectLoanRecordsByRecordId(Long recordId);

    /**
     * 查询借阅信息查询列表
     * 
     * @param loanRecords 借阅信息查询
     * @return 借阅信息查询集合
     */
    public List<LoanRecords> selectLoanRecordsList(LoanRecords loanRecords);

    /**
     * 新增借阅信息查询
     * 
     * @param loanRecords 借阅信息查询
     * @return 结果
     */
    public int insertLoanRecords(LoanRecords loanRecords);

    /**
     * 修改借阅信息查询
     * 
     * @param loanRecords 借阅信息查询
     * @return 结果
     */
    public int updateLoanRecords(LoanRecords loanRecords);

    /**
     * 批量删除借阅信息查询
     * 
     * @param recordIds 需要删除的借阅信息查询主键集合
     * @return 结果
     */
    public int deleteLoanRecordsByRecordIds(Long[] recordIds);

    /**
     * 删除借阅信息查询信息
     * 
     * @param recordId 借阅信息查询主键
     * @return 结果
     */
    public int deleteLoanRecordsByRecordId(Long recordId);
}
