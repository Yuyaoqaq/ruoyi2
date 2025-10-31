package com.ruoyi.student.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学生信息对象 student
 * 
 * @author cy
 * @date 2025-10-31
 */
public class Student extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 学生ID */
    private Long sId;

    /** 学生姓名 */
    @Excel(name = "学生姓名")
    private String sName;

    /** 学生班级 */
    @Excel(name = "学生班级")
    private String sClass;

    /** 学生学校 */
    @Excel(name = "学生学校")
    private String sSchool;

    /** 学生学号 */
    @Excel(name = "学生学号")
    private Long sNum;

    /** 学生邮箱 */
    @Excel(name = "学生邮箱")
    private String sEmail;

    public void setsId(Long sId) 
    {
        this.sId = sId;
    }

    public Long getsId() 
    {
        return sId;
    }

    public void setsName(String sName) 
    {
        this.sName = sName;
    }

    public String getsName() 
    {
        return sName;
    }

    public void setsClass(String sClass) 
    {
        this.sClass = sClass;
    }

    public String getsClass() 
    {
        return sClass;
    }

    public void setsSchool(String sSchool) 
    {
        this.sSchool = sSchool;
    }

    public String getsSchool() 
    {
        return sSchool;
    }

    public void setsNum(Long sNum) 
    {
        this.sNum = sNum;
    }

    public Long getsNum() 
    {
        return sNum;
    }

    public void setsEmail(String sEmail) 
    {
        this.sEmail = sEmail;
    }

    public String getsEmail() 
    {
        return sEmail;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sId", getsId())
            .append("sName", getsName())
            .append("sClass", getsClass())
            .append("sSchool", getsSchool())
            .append("sNum", getsNum())
            .append("sEmail", getsEmail())
            .toString();
    }
}
