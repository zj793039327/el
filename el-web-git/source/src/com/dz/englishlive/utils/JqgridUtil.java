package com.dz.englishlive.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

/**
 * Title: 用于将数据进行针对Jqgrid类型的格式化所用<br>
 * Description: <br>
 * Copyright: Copyright (c) 2007<br>
 * Company: 北京紫光华宇软件股份有限公司<br>
 * 
 * @author zj
 * @version 1.0
 * @date 2013-7-12
 */
public class JqgridUtil
{
    /**
     * 简单格式化lstData，没有分页一说
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
