package com.ruoyi.bookSys.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bookSys.mapper.LoanRecordsMapper;
import com.ruoyi.bookSys.domain.LoanRecords;
import com.ruoyi.bookSys.service.ILoanRecordsService;

/**
 * 借阅信息查询Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-09-16
 */
@Service
public class LoanRecordsServiceImpl implements ILoanRecordsService 
{
    @Autowired
    private LoanRecordsMapper loanRecordsMapper;

    /**
     * 查询借阅信息查询
     * 
     * @param recordId 借阅信息查询主键
     * @return 借阅信息查询
     */
    @Override
    public LoanRecords selectLoanRecordsByRecordId(Long recordId)
    {
        return loanRecordsMapper.selectLoanRecordsByRecordId(recordId);
    }

    /**
     * 查询借阅信息查询列表
     * 
     * @param loanRecords 借阅信息查询
     * @return 借阅信息查询
     */
    @Override
    public List<LoanRecords> selectLoanRecordsList(LoanRecords loanRecords)
    {
        return loanRecordsMapper.selectLoanRecordsList(loanRecords);
    }

    /**
     * 新增借阅信息查询
     * 
     * @param loanRecords 借阅信息查询
     * @return 结果
     */
    @Override
    public int insertLoanRecords(LoanRecords loanRecords)
    {
        return loanRecordsMapper.insertLoanRecords(loanRecords);
    }

    /**
     * 修改借阅信息查询
     * 
     * @param loanRecords 借阅信息查询
     * @return 结果
     */
    @Override
    public int updateLoanRecords(LoanRecords loanRecords)
    {
        return loanRecordsMapper.updateLoanRecords(loanRecords);
    }

    /**
     * 批量删除借阅信息查询
     * 
     * @param recordIds 需要删除的借阅信息查询主键
     * @return 结果
     */
    @Override
    public int deleteLoanRecordsByRecordIds(Long[] recordIds)
    {
        return loanRecordsMapper.deleteLoanRecordsByRecordIds(recordIds);
    }

    /**
     * 删除借阅信息查询信息
     * 
     * @param recordId 借阅信息查询主键
     * @return 结果
     */
    @Override
    public int deleteLoanRecordsByRecordId(Long recordId)
    {
        return loanRecordsMapper.deleteLoanRecordsByRecordId(recordId);
    }
}
