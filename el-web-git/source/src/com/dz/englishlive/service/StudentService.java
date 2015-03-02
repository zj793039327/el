package com.dz.englishlive.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dz.englishlive.dao.StudentDao;
import com.dz.englishlive.dao.TeacherDao;
import com.dz.englishlive.pojo.TInfoStudent;

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
@Service
public class StudentService extends ElService
{
    private static final long serialVersionUID = 7554031876981733877L;
    @Autowired
    private StudentDao stuDao;

    public List<TInfoStudent> doQueryAllStudents()
    {
        stuDao.getHibernateTemplate().save(new TInfoStudent());
        return stuDao.queryAllStudents();
    }
}
