package com.dz.englishlive.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

/**
 * Title: ���ڽ����ݽ������Jqgrid���͵ĸ�ʽ������<br>
 * Description: <br>
 * Copyright: Copyright (c) 2007<br>
 * Company: �����Ϲ⻪������ɷ����޹�˾<br>
 * 
 * @author zj
 * @version 1.0
 * @date 2013-7-12
 */
public class JqgridUtil
{
    /**
     * �򵥸�ʽ��lstData��û�з�ҳһ˵
     * 
     * @param lstData
     * @return
     */

    public static String formatLstToJSON(List<?> lstData)
    {

        Map<String, Object> dataMap = new HashMap<String, Object>();

        dataMap.put("totalsize", lstData.size());
        dataMap.put("rows", lstData);
        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("data", dataMap);
        returnMap.putAll(getNormalStatusMsg());
        JSONObject json = JSONObject.fromObject(returnMap);
        return json.toString();
    }

    public static Map<String, Object> getNormalStatusMsg()
    {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("status", "200");
        returnMap.put("msg", "ok");
        return returnMap;
    }

    public static Map<String, Object> getBadStatusMsg()
    {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("status", "500");
        returnMap.put("msg", "ok");
        returnMap.put("errorMsg", "");
        return returnMap;
    }
}
