package com.dz.englishlive.pojo;

/**
 * TSysCountry entity. @author MyEclipse Persistence Tools
 */

public class TSysCountry implements java.io.Serializable
{

    // Fields

    private static final long serialVersionUID = 3284473079001123443L;
    private Integer NId;
    private String CName;
    private String CShortname;
    private String CDescription;
    private String CPhonenum;
    private Integer NStates;
    private Integer NOrder;

    // Constructors

    /** default constructor */
    public TSysCountry()
    {
    }

    /** minimal constructor */
    public TSysCountry(Integer NId, String CName)
    {
        this.NId = NId;
        this.CName = CName;
    }

    /** full constructor */
    public TSysCountry(Integer NId, String CName, String CShortname,
            String CDescription, String CPhonenum, Integer NStates,
            Integer NOrder)
    {
        this.NId = NId;
        this.CName = CName;
        this.CShortname = CShortname;
        this.CDescription = CDescription;
        this.CPhonenum = CPhonenum;
        this.NStates = NStates;
        this.NOrder = NOrder;
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

    public String getCShortname()
    {
        return this.CShortname;
    }

    public void setCShortname(String CShortname)
    {
        this.CShortname = CShortname;
    }

    public String getCDescription()
    {
        return this.CDescription;
    }

    public void setCDescription(String CDescription)
    {
        this.CDescription = CDescription;
    }

    public String getCPhonenum()
    {
        return this.CPhonenum;
    }

    public void setCPhonenum(String CPhonenum)
    {
        this.CPhonenum = CPhonenum;
    }

    public Integer getNStates()
    {
        return this.NStates;
    }

    public void setNStates(Integer NStates)
    {
        this.NStates = NStates;
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