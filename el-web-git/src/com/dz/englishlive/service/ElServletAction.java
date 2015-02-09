package com.dz.englishlive.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.dz.englishlive.action.ElAction;

/**
 * Title: ���Ի�ȡ req��Action����Ҫ ��ȡreq�Լ�response��action ���Լ̳���һ�� <br>
 * Description: <br>
 * Copyright: Copyright (c) 2007<br>
 * Company: �����Ϲ⻪������ɷ����޹�˾<br>
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
