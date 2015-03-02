package com.dz.englishlive.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright (c) 2007<br>
 * Company: 北京紫光华宇软件股份有限公司<br>
 * @author zj
 * @version 1.0 
 * @date 2013-7-7
 */

public abstract class ElDao extends HibernateDaoSupport
{
    @Resource
    public void setSessionFacotry(SessionFactory sessionFacotry)
    {
        super.setSessionFactory(sessionFacotry);
    }
}
