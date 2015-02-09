package com.dz.englishlive.support.normalcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dz.englishlive.consts.Elconsts;
import com.dz.englishlive.pojo.TSysCode;
import com.dz.englishlive.pojo.TSysCodetype;
import com.dz.englishlive.service.ElService;
import com.dz.englishlive.support.cache.ElCacheManager;
import com.dz.englishlive.support.normalcode.bean.NCode;
import com.dz.englishlive.support.normalcode.bean.NCodeType;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright (c) 2007<br>
 * Company: �����Ϲ⻪������ɷ����޹�˾<br>
 * 
 * @author zj
 * @version 1.0
 * @date 2013-8-4
 */
@Service("nCodeService")
public class NCodeService extends ElService
{
    private static final long serialVersionUID = 490215504745011089L;
    @Autowired
    private NCodeDao dao;

    public NCodeService()
    {
    }

    /**
     * ��Bean�е�ĳ�������ϼ���@PostConstructע�⣬��÷���������Bean��ʼ��֮��Spring�������ã�
     * ���õ�ͬ��Spring�����ļ���Ϊbean��ǩָ��init-method����
     */
    @PostConstruct
    private synchronized void init()
    {
        Cache nCodeCache = ElCacheManager.getCache(Elconsts.CACHE_NCODE);
        nCodeCache.removeAll();
        List<TSysCodetype> lstCodeType = dao.queryAllCodeType();
        List<TSysCode> lstCode = dao.queryAllCode();

        for (Iterator<TSysCodetype> it = lstCodeType.iterator(); it.hasNext();)
        {
            TSysCodetype tct = it.next();
            NCodeType ct = new NCodeType();
            ct.setValue(tct.getNId());
            ct.setName(tct.getCName());

            List<NCode> lstNCodeType = new ArrayList<NCode>();

            for (Iterator<TSysCode> itC = lstCode.iterator(); itC.hasNext();)
            {
                TSysCode tc = (TSysCode) itC.next();
                if (tct.getNId().equals(tc.getId().getNPid()) )
                {
                    
                    NCode nc = new NCode();
                    nc.setValue(tc.getId().getNCode());
                    nc.setName(tc.getCValue());
                    nc.setDescription(tc.getCDescription());
                    lstNCodeType.add(nc);
                }
            }
            ct.setLstNcodes(lstNCodeType);
            nCodeCache.put(new Element(ct.getValue(), ct));
        }
    }

    public void reload()
    {
        init();
    }
}
