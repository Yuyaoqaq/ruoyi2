package com.ruoyi.student.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.student.mapper.StudentMapper;
import com.ruoyi.student.domain.Student;
import com.ruoyi.student.service.IStudentService;

/**
 * 学生信息Service业务层处理
 * 
 * @author cy
 * @date 2025-10-31
 */
@Service
public class StudentServiceImpl implements IStudentService 
{
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 查询学生信息
     * 
     * @param sId 学生信息主键
     * @return 学生信息
     */
    @Override
    public Student selectStudentBySId(Long sId)
    {
        return studentMapper.selectStudentBySId(sId);
    }

    /**
     * 查询学生信息列表
     * 
     * @param student 学生信息
     * @return 学生信息
     */
    @Override
    public List<Student> selectStudentList(Student student)
    {
        return studentMapper.selectStudentList(student);
    }

    /**
     * 新增学生信息
     * 
     * @param student 学生信息
     * @return 结果
     */
    @Override
    public int insertStudent(Student student)
    {
        return studentMapper.insertStudent(student);
    }

    /**
     * 修改学生信息
     * 
     * @param student 学生信息
     * @return 结果
     */
    @Override
    public int updateStudent(Student student)
    {
        return studentMapper.updateStudent(student);
    }

    /**
     * 批量删除学生信息
     * 
     * @param sIds 需要删除的学生信息主键
     * @return 结果
     */
    @Override
    public int deleteStudentBySIds(Long[] sIds)
    {
        return studentMapper.deleteStudentBySIds(sIds);
    }

    /**
     * 删除学生信息信息
     * 
     * @param sId 学生信息主键
     * @return 结果
     */
    @Override
    public int deleteStudentBySId(Long sId)
    {
        return studentMapper.deleteStudentBySId(sId);
    }
}
