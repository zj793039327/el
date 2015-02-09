package com.dz.englishlive.pojo;

import java.util.Date;

/**
 * TInfoStudent entity. @author MyEclipse Persistence Tools
 */

public class TInfoStudent implements java.io.Serializable
{

    // Fields

    private static final long serialVersionUID = -143621894002421435L;
    private long NId;
    private Integer NSex;
    private String CName;
    private String CFirstlanguage;
    private String CAddress;
    private Integer NTimezone;
    private long NContactId;
    private long CAgencyId;
    private String CEnglishlevel;
    private String CAim;
    private String CComment;
    private Integer NValid;
    private Date dtCreatetime;

    // Constructors

    /** default constructor */
    public TInfoStudent()
    {
    }

    /** minimal constructor */
    public TInfoStudent(long NId)
    {
        this.NId = NId;
    }

    /** full constructor */
    public TInfoStudent(long NId, Integer NSex, String CName,
            String CFirstlanguage, String CAddress, Integer NTimezone,
            long NContactId, long CAgencyId, String CEnglishlevel, String CAim,
            String CComment, Integer NValid, Date dtCreatetime )
    {
        this.NId = NId;
        this.NSex = NSex;
        this.CName = CName;
        this.CFirstlanguage = CFirstlanguage;
        this.CAddress = CAddress;
        this.NTimezone = NTimezone;
        this.NContactId = NContactId;
        this.CAgencyId = CAgencyId;
        this.CEnglishlevel = CEnglishlevel;
        this.CAim = CAim;
        this.CComment = CComment;
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

    public Integer getNSex()
    {
        return this.NSex;
    }

    public void setNSex(Integer NSex)
    {
        this.NSex = NSex;
    }

    public String getCName()
    {
        return this.CName;
    }

    public void setCName(String CName)
    {
        this.CName = CName;
    }

    public String getCFirstlanguage()
    {
        return this.CFirstlanguage;
    }

    public void setCFirstlanguage(String CFirstlanguage)
    {
        this.CFirstlanguage = CFirstlanguage;
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

    public long getNContactId()
    {
        return this.NContactId;
    }

    public void setNContactId(long NContactId)
    {
        this.NContactId = NContactId;
    }

    public long getCAgencyId()
    {
        return this.CAgencyId;
    }

    public void setCAgencyId(long CAgencyId)
    {
        this.CAgencyId = CAgencyId;
    }

    public String getCEnglishlevel()
    {
        return this.CEnglishlevel;
    }

    public void setCEnglishlevel(String CEnglishlevel)
    {
        this.CEnglishlevel = CEnglishlevel;
    }

    public String getCAim()
    {
        return this.CAim;
    }

    public void setCAim(String CAim)
    {
        this.CAim = CAim;
    }

    public String getCComment()
    {
        return this.CComment;
    }

    public void setCComment(String CComment)
    {
        this.CComment = CComment;
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