package com.dz.englishlive.test.jqgrid;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.dz.englishlive.action.ElAction;
import com.dz.englishlive.pojo.TInfoStudent;
import com.dz.englishlive.service.StudentService;
import com.dz.englishlive.utils.JqgridUtil;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright (c) 2007<br>
 * Company: 北京紫光华宇软件股份有限公司<br>
 * 
 * @author zj
 * @version 1.0
 * @date 2013-7-12
 */
@Controller
public class TestJqgridAction extends ElAction
{
    private static final long serialVersionUID = 7324696365289348238L;
    @Autowired
    private StudentService service;
    private List<TInfoStudent> lstStu;
    private InputStream stuJsonStream;

    public List<TInfoStudent> getLstStu()
    {
        return lstStu;
    }

    public void setLstStu(List<TInfoStudent> lstStu)
    {
        this.lstStu = lstStu;
    }

    public InputStream getStuJsonStream()
    {
        return stuJsonStream;
    }

    public void setStuJsonStream(InputStream stuJsonStream)
    {
        this.stuJsonStream = stuJsonStream;
    }

    @Override
    public String execute()
    {
        lstStu = service.doQueryAllStudents();
        stuJsonStream = new ByteArrayInputStream(JqgridUtil.formatLstToJSON(
                lstStu).getBytes());
        return SUCCESS;
    }

}
