package com.dz.englishlive.pojo;

/**
 * TSysRight entity. @author MyEclipse Persistence Tools
 */

public class TSysRight implements java.io.Serializable
{

    // Fields

    private static final long serialVersionUID = -3344963478569587911L;
    private String CKey;
    private String CName;
    private String CDescription;
    private Integer NOrder;

    // Constructors

    /** default constructor */
    public TSysRight()
    {
    }

    /** minimal constructor */
    public TSysRight(String CKey, String CName)
    {
        this.CKey = CKey;
        this.CName = CName;
    }

    /** full constructor */
    public TSysRight(String CKey, String CName, String CDescription,
            Integer NOrder)
    {
        this.CKey = CKey;
        this.CName = CName;
        this.CDescription = CDescription;
        this.NOrder = NOrder;
    }

    // Property accessors

    public String getCKey()
    {
        return this.CKey;
    }

    public void setCKey(String CKey)
    {
        this.CKey = CKey;
    }

    public String getCName()
    {
        return this.CName;
    }

    public void setCName(String CName)
    {
        this.CName = CName;
    }

    public String getCDescription()
    {
        return this.CDescription;
    }

    public void setCDescription(String CDescription)
    {
        this.CDescription = CDescription;
    }

    public Integer getNOrder()
    {
        return this.NOrder;
    }

    public void setNOrder(Integer NOrder)
    {
        this.NOrder = NOrder;
    }

}