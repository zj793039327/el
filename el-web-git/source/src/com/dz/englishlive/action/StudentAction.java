package com.dz.englishlive.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.dz.englishlive.pojo.TInfoStudent;
import com.dz.englishlive.service.StudentService;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright (c) 2007<br>
 * Company: 北京紫光华宇软件股份有限公司<br>
 * 
 * @author zj
 * @version 1.0
 * @date 2013-8-4
 */
@Controller
public class StudentAction extends ElAction
{
    private static final long serialVersionUID = 7629145142782891179L;
    private TInfoStudent student;
    private List<TInfoStudent> stulst;

    public TInfoStudent getStudent()
    {
        return student;
    }

    public void setStudent(TInfoStudent student)
    {
        this.student = student;
    }

    public List<TInfoStudent> getStulst()
    {
        return stulst;
    }

    public void setStulst(List<TInfoStudent> stulst)
    {
        this.stulst = stulst;
    }

    @Autowired
    private StudentService stuService;

    public String queryStudent() throws Exception
    {
        List<TInfoStudent> returnList = stuService.doQueryAllStudents();
        setStulst(returnList);
        return SUCCESS;
    }

    /**
     * 新增学生
    * @return
    * @throws Exception
     */
    public String newStudent() throws Exception
    {
        logger.debug(getStudent().getCName());
        return null;
    }
}
