package com.dz.englishlive.support.cache;

import net.sf.ehcache.Cache;


/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright (c) 2007<br>
 * Company: �����Ϲ⻪������ɷ����޹�˾<br>
 * 
 * @author zj
 * @version 1.0
 * @date 2013-8-5
 */

public class ElCacheManager
{
    /**
     * ��ȡ����
     * @param sName
     * @return
     */
    public static Cache getCache(String sName)
    {
        return net.sf.ehcache.CacheManager.getInstance().getCache(sName);
    }

}
