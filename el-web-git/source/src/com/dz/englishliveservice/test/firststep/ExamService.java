package com.dz.englishliveservice.test.firststep;

import com.dz.englishliveservice.test.bean.ExamPaper;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright (c) 2007<br>
 * Company: 北京紫光华宇软件股份有限公司<br>
 * 
 * @author zj
 * @version 1.0
 * @date 2013-9-6
 */
public class ExamService
{
    public ExamPaper[] loadMutiPapers()
    {
        return (ExamPaper[]) DataUtil.getMutiPaper().toArray();
    }

    public ExamPaper loadOnePaper()
    {
         return DataUtil.getOnePaper();
    }

    public static void main(String[] args)
    {
        System.out.println(new ExamService().loadMutiPapers());
    }
}
