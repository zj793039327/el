package com.dz.englishliveservice.test.utils.args;

import java.text.ParseException;

import org.junit.Test;

import com.dz.englishlive.utils.arg.Args;
import static org.junit.Assert.assertEquals;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright (c) 2007<br>
 * Company: 北京紫光华宇软件股份有限公司<br>
 * 
 * @author zj
 * @version 1.0
 * @date 2014-6-4
 */
public class TestInput
{
    @Test
    public void TestInputBoolean() throws ParseException
    {
        String[] args = new String[]
        { "-l", "true", "-p", "1", "-d", "asdasdasd" };
        Args arg = new Args("l,p#,d*", args);
        boolean logging = arg.getBoolean('l');
        String a = arg.getString('d');
        int port = arg.getInt('p');

        assertEquals(true, logging);
        assertEquals("asdasdasd", a);
        assertEquals(1, port);
    }
}
