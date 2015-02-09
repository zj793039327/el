package com.dz.englishlive.pojo;

/**
 * TSysCode entity. @author MyEclipse Persistence Tools
 */

public class TSysCode implements java.io.Serializable
{

    // Fields

    private static final long serialVersionUID = -1109774346721983327L;
    
    private TSysCodeId id;
    private String CValue;
    private Integer NOrder;
    private String CDescription;
    private String CInputcode;

    // Constructors

    /** default constructor */
    public TSysCode()
    {
    }

    /** minimal constructor */
    public TSysCode(TSysCodeId id)
    {
        this.id = id;
    }

    /** full constructor */
    public TSysCode(TSysCodeId id, String CValue, Integer NOrder,
            String CDescription, String CInputcode)
    {
        this.id = id;
        this.CValue = CValue;
        this.NOrder = NOrder;
        this.CDescription = CDescription;
        this.CInputcode = CInputcode;
    }

    // Property accessors

    public TSysCodeId getId()
    {
        return this.id;
    }

    public void setId(TSysCodeId id)
    {
        this.id = id;
    }

    public String getCValue()
    {
        return this.CValue;
    }

    public void setCValue(String CValue)
    {
        this.CValue = CValue;
    }

    public Integer getNOrder()
    {
        return this.NOrder;
    }

    public void setNOrder(Integer NOrder)
    {
        this.NOrder = NOrder;
    }

    public String getCDescription()
    {
        return this.CDescription;
    }

    public void setCDescription(String CDescription)
    {
        this.CDescription = CDescription;
    }

    public String getCInputcode()
    {
        return this.CInputcode;
    }

    public void setCInputcode(String CInputcode)
    {
        this.CInputcode = CInputcode;
    }

}