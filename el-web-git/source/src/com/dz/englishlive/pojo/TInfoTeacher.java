package com.dz.englishlive.pojo;

import java.util.Date;

/**
 * TInfoTeacher entity. @author MyEclipse Persistence Tools
 */

public class TInfoTeacher implements java.io.Serializable
{

    // Fields

    private static final long serialVersionUID = -3104163694683892761L;
    private long NId;
    private String CName;
    private Integer NSex;
    private Integer NFirstlanguage;
    private String CAddress;
    private Integer NTimezone;
    private Integer NValid;
    private Date dtCreatetime;

    // Constructors

    /** default constructor */
    public TInfoTeacher()
    {
    }

    /** minimal constructor */
    public TInfoTeacher(long NId)
    {
        this.NId = NId;
    }

    /** full constructor */
    public TInfoTeacher(long NId, String CName, Integer NSex,
            Integer NFirstlanguage, String CAddress, Integer NTimezone,
            Integer NValid, Date dtCreatetime)
    {
        this.NId = NId;
        this.CName = CName;
        this.NSex = NSex;
        this.NFirstlanguage = NFirstlanguage;
        this.CAddress = CAddress;
        this.NTimezone = NTimezone;
        this.NValid = NValid;
        this.dtCreatetime = dtCreatetime;
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

    public Integer getNSex()
    {
        return this.NSex;
    }

    public void setNSex(Integer NSex)
    {
        this.NSex = NSex;
    }

    public Integer getNFirstlanguage()
    {
        return this.NFirstlanguage;
    }

    public void setNFirstlanguage(Integer NFirstlanguage)
    {
        this.NFirstlanguage = NFirstlanguage;
    }

    public String getCAddress()
    {
        return this.CAddress;
    }

    public void setCAddress(String CAddress)
    {
        this.CAddress = CAddress;
    }

    public Integer getNTimezone()
    {
        return this.NTimezone;
    }

    public void setNTimezone(Integer NTimezone)
    {
        this.NTimezone = NTimezone;
    }

    public Integer getNValid()
    {
        return this.NValid;
    }

    public void setNValid(Integer NValid)
    {
        this.NValid = NValid;
    }

    public Date getDtCreatetime()
    {
        return this.dtCreatetime;
    }

    public void setDtCreatetime(Date dtCreatetime)
    {
        this.dtCreatetime = dtCreatetime;
    }

}