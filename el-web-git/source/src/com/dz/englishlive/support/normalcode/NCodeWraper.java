package com.dz.englishlive.support.normalcode;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import net.sf.json.JSONArray;

import com.dz.englishlive.consts.Elconsts;
import com.dz.englishlive.support.cache.ElCacheManager;
import com.dz.englishlive.support.normalcode.bean.NCodeType;

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
public class NCodeWraper
{

    // private static NCodeService getNCodeService()
    // {
    // ApplicationContext ac = WebApplicationContextUtils
    // .getWebApplicationContext(ServletActionContext
    // .getServletContext());
    // return ac.getBean(NCodeService.class);
    // }

    public static NCodeType getNCodes(Integer type)
    {
        if (type == null)
        {
            return null;
        }
        Cache c = ElCacheManager.getCache(Elconsts.CACHE_NCODE);
        Element e = c.get(type);
        return (NCodeType) e.getObjectValue();
    }

    /**
     * 获取JSON串
     * 
     * @param type
     * @return
     */
    public static String getNCodesJSON(Integer type)
    {
        NCodeType nt = getNCodes(type);
        if (nt == null || nt.getLstNcodes() == null)
        {
            return null;
        }
        String value = JSONArray.fromObject(nt.getLstNcodes()).toString();
        return value;
    }

}
