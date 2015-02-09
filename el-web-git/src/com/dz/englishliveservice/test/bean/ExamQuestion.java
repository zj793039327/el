package com.dz.englishliveservice.test.bean;

import java.util.List;

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
public class ExamQuestion
{
    private String stem;
    private String number;
    private int mustAnswer;

    private List<QuestionOption> options;
    

    public ExamQuestion(String stem, String number, int mustAnswer)
    {
        super();
        this.stem = stem;
        this.number = number;
        this.mustAnswer = mustAnswer;
    }

    public List<QuestionOption> getOptions()
    {
        return options;
    }

    public void setOptions(List<QuestionOption> options)
    {
        this.options = options;
    }

    public String getStem()
    {
        return stem;
    }

    public void setStem(String stem)
    {
        this.stem = stem;
    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public int getMustAnswer()
    {
        return mustAnswer;
    }

    public void setMustAnswer(int mustAnswer)
    {
        this.mustAnswer = mustAnswer;
    }

}
