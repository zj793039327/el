package com.dz.englishliveservice.test.other;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright (c) 2007<br>
 * Company: 北京紫光华宇软件股份有限公司<br>
 * 
 * @author zj
 * @version 1.0
 * @date 2014-6-16
 */
public class TestWeakHashMap
{
    public static void main(String[] args)
    {
        String a = new String("a");
        String b = new String("b");
        Map weakmap = new WeakHashMap();
        Map map = new HashMap();
        map.put(a, "aaa");
        map.put(b, "bbb");

        weakmap.put(a, "aaa");
        weakmap.put(b, "bbb");

        map.remove(a);

        a = null;
        b = null;

        System.gc();
        Iterator i = map.entrySet().iterator();
        while (i.hasNext())
        {
            Map.Entry en = (Map.Entry) i.next();
            System.out.println("map:" + en.getKey() + ":" + en.getValue());
        }

        Iterator j = weakmap.entrySet().iterator();
        while (j.hasNext())
        {
            Map.Entry en = (Map.Entry) j.next();
            System.out.println("weakmap:" + en.getKey() + ":" + en.getValue());

        }

        System.out.println(new String("4") == "4");
        System.out.println(new String("4").intern() == "4");

        
        WeakHashMap<String, String> map2 = new WeakHashMap<String, String>();
        map2.put(new String("1"), "1");
        map2.put("2", "2");
        String s = new String("3");
        map2.put(s, "3");
        s = null;
        while (map2.size() > 0)
        {
            try
            {
                Thread.sleep(500);
            }
            catch (InterruptedException ignored)
            {
            }
            System.out.println("Map Size:" + map2.size());
            System.out.println(map2.get("1"));
            System.out.println(map2.get("2"));
            System.out.println(map2.get("3"));
            System.gc();
        }
    }
}
