package com.dz.englishliveservice.test.firststep;

import java.util.ArrayList;
import java.util.List;

import com.dz.englishliveservice.test.bean.ExamPaper;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright (c) 2007<br>
 * Company: �����Ϲ⻪������ɷ����޹�˾<br>
 * 
 * @author zj
 * @version 1.0
 * @date 2013-9-6
 */
public class DataUtil
{
    public static List getMutiPaper()
    {
        List ans = new ArrayList();
        for (int i = 0; i < 5; i++)
        {
            ExamPaper paper = new ExamPaper("�Ծ�" + i, "", 120);
            List questions = new ArrayList();
           // paper.setQuestions(questions);
            // for (int j = 0; j < 5; j++)
            // {
            // ExamQuestion ques = new ExamQuestion("����" + i + "" + j, "", 1);
            // List options = new ArrayList();
            // ques.setOptions(options);
            // for (int l = 0; l < 4; l++)
            // {
            // QuestionOption op = new QuestionOption(l + "", "ѡ��" + i
            // + "" + j + "" + l);
            // options.add(op);
            // }
            // questions.add(ques);
            // }
            ans.add(paper);
        }
        return ans;
    }

    public static ExamPaper getOnePaper()
    {
        return new ExamPaper();
    }
}
