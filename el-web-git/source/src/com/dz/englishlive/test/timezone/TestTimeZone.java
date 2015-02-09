package com.dz.englishlive.test.timezone;

import java.util.TimeZone;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright (c) 2007<br>
 * Company: 北京紫光华宇软件股份有限公司<br>
 * @author zj
 * @version 1.0 
 * @date 2013-8-3
 */
public class TestTimeZone
{
    public static void main(String[] args)
    {
        TimeZone tz = TimeZone.getDefault();
        String[] asd = TimeZone.getAvailableIDs();
        System.out.println();
    }
}
