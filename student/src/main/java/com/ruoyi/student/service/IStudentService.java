package com.ruoyi.student.service;

import java.util.List;
import com.ruoyi.student.domain.Student;

/**
 * 学生信息Service接口
 * 
 * @author cy
 * @date 2025-10-31
 */
public interface IStudentService 
{
    /**
     * 查询学生信息
     * 
     * @param sId 学生信息主键
     * @return 学生信息
     */
    public Student selectStudentBySId(Long sId);

    /**
     * 查询学生信息列表
     * 
     * @param student 学生信息
     * @return 学生信息集合
     */
    public List<Student> selectStudentList(Student student);

    /**
     * 新增学生信息
     * 
     * @param student 学生信息
     * @return 结果
     */
    public int insertStudent(Student student);

    /**
     * 修改学生信息
     * 
     * @param student 学生信息
     * @return 结果
     */
    public int updateStudent(Student student);

    /**
     * 批量删除学生信息
     * 
     * @param sIds 需要删除的学生信息主键集合
     * @return 结果
     */
    public int deleteStudentBySIds(Long[] sIds);

    /**
     * 删除学生信息信息
     * 
     * @param sId 学生信息主键
     * @return 结果
     */
    public int deleteStudentBySId(Long sId);
}
