package com.dz.englishlive.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

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
@Repository
public class StudentDao extends ElDao
{
    @SuppressWarnings("unchecked")
    public List<TInfoStudent> queryAllStudents()
    {
        StringBuilder sql = new StringBuilder();
        sql.append("from TInfoStudent");
        return this.getHibernateTemplate().find(sql.toString());
    }
}
