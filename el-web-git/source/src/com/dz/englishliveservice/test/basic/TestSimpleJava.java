package com.dz.englishliveservice.test.basic;

import java.io.File;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright (c) 2007<br>
 * Company: 北京紫光华宇软件股份有限公司<br>
 * 
 * @author zj
 * @version 1.0
 * @date 2014-3-10
 */
public class TestSimpleJava
{
    public static void main(String[] args)
    {
        int a = 0, c = 0;
        do
        {
            --c;
            a = a - 1;
        }
        while (a > 0);
        System.out.println(a + "," + c);

        int tmp = 0;
        int n = 5;
        for (int j = 1; j <= n; j++)
        {
            for (int i = 1; i <= n; i++)
            {
                tmp = j;
                if (j - i >= 0)
                {
                    System.out.print("*");
                }
                while (tmp > 0)
                {
                    System.out.print(".");
                    tmp--;
                }
            }
            System.out.println();
        }

        for (int i = 1; i <= n; i++)
        {
            for (int j = 1; j <= i; j++)
            {
                int k = i;
                System.out.print("*");
                while (k > 0)
                {
                    System.out.print(".");
                    k--;
                }
            }
            System.out.println();
        }

        File file = new File("C:\\Program Files");
        getDirs3(file, "|");

    }

    public static void getDirs(String path)
    {
        File file = new File(path);
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++)
        {
            if (files[i].isDirectory())
            {
                System.out.println("--" + files[i].getPath());
                getDirs(files[i].getPath());
            }
        }
        for (int i = 0; i < files.length; i++)
        {
            if (files[i].isFile())
            {
                System.out.println("--" + files[i].getPath());
            }
        }
    }

    public static void getDirs2(File file)
    {
        if (file == null)
        {
            return;
        }
        if (file.isDirectory())
        {
            System.out.println(file.getPath());
            for (File f : file.listFiles())
            {
                System.out.print("--");
                getDirs2(f);
            }
        }

    }

    public static void getDirs3(File file, String writeSpace)
    {
        if (file == null)
        {
            return;
        }
        System.out.println(file.getPath());
        for (File f : file.listFiles())
        {
            if (f.isDirectory())
            {
                for (int i = 0; i < writeSpace.length(); i++)
                {
                    System.out.print("  ");
                }
                System.out.print(writeSpace + "--");
                getDirs3(f, writeSpace + "--");
            }
        }

    }
}
