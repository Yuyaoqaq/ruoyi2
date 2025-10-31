package com.ruoyi.bookSys.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.bookSys.domain.LoanRecords;
import com.ruoyi.bookSys.service.ILoanRecordsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 借阅信息查询Controller
 * 
 * @author ruoyi
 * @date 2025-09-16
 */
@RestController
@RequestMapping("/bookSys/records")
public class LoanRecordsController extends BaseController
{
    @Autowired
    private ILoanRecordsService loanRecordsService;

    /**
     * 查询借阅信息查询列表
     */
    @PreAuthorize("@ss.hasPermi('bookSys:records:list')")
    @GetMapping("/list")
    public TableDataInfo list(LoanRecords loanRecords)
    {
        startPage();
        List<LoanRecords> list = loanRecordsService.selectLoanRecordsList(loanRecords);
        return getDataTable(list);
    }

    /**
     * 导出借阅信息查询列表
     */
    @PreAuthorize("@ss.hasPermi('bookSys:records:export')")
    @Log(title = "借阅信息查询", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, LoanRecords loanRecords)
    {
        List<LoanRecords> list = loanRecordsService.selectLoanRecordsList(loanRecords);
        ExcelUtil<LoanRecords> util = new ExcelUtil<LoanRecords>(LoanRecords.class);
        util.exportExcel(response, list, "借阅信息查询数据");
    }

    /**
     * 获取借阅信息查询详细信息
     */
    @PreAuthorize("@ss.hasPermi('bookSys:records:query')")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return success(loanRecordsService.selectLoanRecordsByRecordId(recordId));
    }

    /**
     * 新增借阅信息查询
     */
    @PreAuthorize("@ss.hasPermi('bookSys:records:add')")
    @Log(title = "借阅信息查询", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LoanRecords loanRecords)
    {
        return toAjax(loanRecordsService.insertLoanRecords(loanRecords));
    }

    /**
     * 修改借阅信息查询
     */
    @PreAuthorize("@ss.hasPermi('bookSys:records:edit')")
    @Log(title = "借阅信息查询", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LoanRecords loanRecords)
    {
        return toAjax(loanRecordsService.updateLoanRecords(loanRecords));
    }

    /**
     * 删除借阅信息查询
     */
    @PreAuthorize("@ss.hasPermi('bookSys:records:remove')")
    @Log(title = "借阅信息查询", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(loanRecordsService.deleteLoanRecordsByRecordIds(recordIds));
    }
}
