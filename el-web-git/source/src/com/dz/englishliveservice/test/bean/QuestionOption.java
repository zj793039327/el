package com.dz.englishliveservice.test.bean;

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
public class QuestionOption
{
    private String optionNum;
    private String optionDes;

    
    public QuestionOption(String optionNum, String optionDes)
    {
        super();
        this.optionNum = optionNum;
        this.optionDes = optionDes;
    }

    public String getOptionNum()
    {
        return optionNum;
    }

    public void setOptionNum(String optionNum)
    {
        this.optionNum = optionNum;
    }

    public String getOptionDes()
    {
        return optionDes;
    }

    public void setOptionDes(String optionDes)
    {
        this.optionDes = optionDes;
    }

}
