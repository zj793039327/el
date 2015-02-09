package com.dz.englishlive.pojo;


/**
 * TSysUser entity. @author MyEclipse Persistence Tools
 */

public class TSysUser implements java.io.Serializable
{

    // Fields

    private static final long serialVersionUID = -5332652452946428863L;
    private long NId;
    private String CLoginid;
    private String CPassowrd;
    private String CEmail;
    private Integer NValid;
    private Integer NType;
    private long NInfoid;

    // Constructors

    /** default constructor */
    public TSysUser()
    {
    }

    /** minimal constructor */
    public TSysUser(long NId, String CLoginid)
    {
        this.NId = NId;
        this.CLoginid = CLoginid;
    }

    /** full constructor */
    public TSysUser(long NId, String CLoginid, String CPassowrd, String CEmail,
            Integer NValid, Integer NType, long NInfoid)
    {
        this.NId = NId;
        this.CLoginid = CLoginid;
        this.CPassowrd = CPassowrd;
        this.CEmail = CEmail;
        this.NValid = NValid;
        this.NType = NType;
        this.NInfoid = NInfoid;
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

    public String getCLoginid()
    {
        return this.CLoginid;
    }

    public void setCLoginid(String CLoginid)
    {
        this.CLoginid = CLoginid;
    }

    public String getCPassowrd()
    {
        return this.CPassowrd;
    }

    public void setCPassowrd(String CPassowrd)
    {
        this.CPassowrd = CPassowrd;
    }

    public String getCEmail()
    {
        return this.CEmail;
    }

    public void setCEmail(String CEmail)
    {
        this.CEmail = CEmail;
    }

    public Integer getNValid()
    {
        return this.NValid;
    }

    public void setNValid(Integer NValid)
    {
        this.NValid = NValid;
    }

    public Integer getNType()
    {
        return this.NType;
    }

    public void setNType(Integer NType)
    {
        this.NType = NType;
    }

    public long getNInfoid()
    {
        return this.NInfoid;
    }

    public void setNInfoid(long NInfoid)
    {
        this.NInfoid = NInfoid;
    }

}