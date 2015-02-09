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
public class ExamPaper
{
    private String title;
    private String description;
    private Integer duringMinutes;
    private List<ExamQuestion> questions;

    
    public ExamPaper()
    {
        super();
    }

    public ExamPaper(String title, String description, Integer duringMinutes)
    {
        super();
        this.title = title;
        this.description = description;
        this.duringMinutes = duringMinutes;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Integer getDuringMinutes()
    {
        return duringMinutes;
    }

    public void setDuringMinutes(Integer duringMinutes)
    {
        this.duringMinutes = duringMinutes;
    }

    public List<ExamQuestion> getQuestions()
    {
        return questions;
    }

    public void setQuestions(List<ExamQuestion> questions)
    {
        this.questions = questions;
    }

}
