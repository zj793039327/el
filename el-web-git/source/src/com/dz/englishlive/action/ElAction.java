package com.dz.englishlive.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright (c) 2007<br>
 * Company: 北京紫光华宇软件股份有限公司<br>
 * 
 * @author zj
 * @version 1.0
 * @date 2013-7-7
 */
public abstract class ElAction extends ActionSupport
{
    private static final long serialVersionUID = -4301182274293447082L;
    protected Log logger = LogFactory.getLog(this.getClass());
}
