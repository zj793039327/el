package com.dz.englishlive.pojo;

/**
 * TSysRole entity. @author MyEclipse Persistence Tools
 */

public class TSysRole implements java.io.Serializable
{

    // Fields

    private static final long serialVersionUID = -3713749198572086642L;
    private long NId;
    private String CName;
    private String CDescription;
    private Integer NOrder;

    // Constructors

    /** default constructor */
    public TSysRole()
    {
    }

    /** minimal constructor */
    public TSysRole(long NId)
    {
        this.NId = NId;
    }

    /** full constructor */
    public TSysRole(long NId, String CName, String CDescription, Integer NOrder)
    {
        this.NId = NId;
        this.CName = CName;
        this.CDescription = CDescription;
        this.NOrder = NOrder;
    }

    // Property accessors

    public long getNId()
    {
        return this.NId;
    }

    public void setNId(long NId)
    {
        this.NId = NId;
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