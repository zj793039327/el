package com.dz.englishlive.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.dz.englishlive.action.ElAction;

/**
 * Title: 可以获取 req的Action，需要 获取req以及response的action 可以继承这一个 <br>
 * Description: <br>
 * Copyright: Copyright (c) 2007<br>
 * Company: 北京紫光华宇软件股份有限公司<br>
 * 
 * @author zj
 * @version 1.0
 * @date 2013-7-7
 */
public class ElServletAction extends ElAction implements ServletRequestAware,
        ServletResponseAware
{
    private static final long serialVersionUID = 5573663439630787102L;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public HttpServletRequest getRequest()
    {
        return request;
    }

    public void setRequest(HttpServletRequest request)
    {
        this.request = request;
    }

    public HttpServletResponse getResponse()
    {
        return response;
    }

    public void setResponse(HttpServletResponse response)
    {
        this.response = response;
    }

    @Override
    public void setServletRequest(HttpServletRequest hreq)
    {
        setRequest(hreq);
    }

    @Override
    public void setServletResponse(HttpServletResponse hres)
    {
        this.setResponse(hres);

    }
}
