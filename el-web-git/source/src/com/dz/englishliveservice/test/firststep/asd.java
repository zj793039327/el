package com.dz.englishliveservice.test.firststep;



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
public class asd
{
    private String title;
    private String description;
    private Integer duringMinutes;

    
    public asd()
    {
        super();
    }

    public asd(String title, String description, Integer duringMinutes)
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

}
