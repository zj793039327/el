package com.dz.englishlive.support.normalcode.bean;

import java.io.Serializable;
import java.util.List;

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
public class NCodeType extends NCode implements Serializable
{

    private static final long serialVersionUID = -202763415974289978L;

    private List<NCode> lstNcodes;

    public List<NCode> getLstNcodes()
    {
        return lstNcodes;
    }

    public void setLstNcodes(List<NCode> lstNcodes)
    {
        this.lstNcodes = lstNcodes;
    }

}
