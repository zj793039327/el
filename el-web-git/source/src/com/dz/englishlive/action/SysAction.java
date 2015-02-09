package com.dz.englishlive.action;

import org.springframework.stereotype.Controller;

import com.dz.englishlive.support.normalcode.NCodeWraper;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright (c) 2007<br>
 * Company: 北京紫光华宇软件股份有限公司<br>
 * 
 * @author zj
 * @version 1.0
 * @date 2013-8-5
 */
@Controller
public class SysAction extends ElAction
{
    private static final long serialVersionUID = -1514989813959286403L;
    private Integer code;

    private String NCodeRresult;
    
    public Integer getCode()
    {
        return code;
    }

    public void setCode(Integer code)
    {
        this.code = code;
    }

    
    public String getNCodeRresult()
    {
        return NCodeRresult;
    }

    public void setNCodeRresult(String nCodeRresult)
    {
        NCodeRresult = nCodeRresult;
    }

    public String getNormalCode()
    {
        setNCodeRresult(NCodeWraper.getNCodesJSON(getCode()));
        return SUCCESS;
    }
}
