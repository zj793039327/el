package com.dz.englishlive.support.normalcode;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dz.englishlive.dao.ElDao;
import com.dz.englishlive.pojo.TSysCode;
import com.dz.englishlive.pojo.TSysCodetype;

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
@Repository("nCodeDao")
public class NCodeDao extends ElDao
{
    @SuppressWarnings("unchecked")
    public List<TSysCodetype> queryAllCodeType()
    {
        StringBuilder hql = new StringBuilder();
        hql.append("from TSysCodetype");
        return getHibernateTemplate().find(hql.toString());
    }
    
    @SuppressWarnings("unchecked")
    public List<TSysCode> queryAllCode()
    {
        StringBuilder hql = new StringBuilder();
        hql.append("from TSysCode order by id,NOrder");
        return getHibernateTemplate().find(hql.toString());
    }
}
