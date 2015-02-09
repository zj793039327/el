package com.dz.englishlive.pojo;

/**
 * TSysCodetype entity. @author MyEclipse Persistence Tools
 */

public class TSysCodetype implements java.io.Serializable
{

    // Fields

    private static final long serialVersionUID = 7567790550701034029L;
    private Integer NId;
    private String CName;
    private String CDescription;

    // Constructors

    /** default constructor */
    public TSysCodetype()
    {
    }

    /** minimal constructor */
    public TSysCodetype(Integer NId)
    {
        this.NId = NId;
    }

    /** full constructor */
    public TSysCodetype(Integer NId, String CName, String CDescription)
    {
        this.NId = NId;
        this.CName = CName;
        this.CDescription = CDescription;
    }

    // Property accessors

    public Integer getNId()
    {
        return this.NId;
    }

    public void setNId(Integer NId)
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

}