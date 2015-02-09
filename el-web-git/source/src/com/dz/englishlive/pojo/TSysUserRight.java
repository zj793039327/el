package com.dz.englishlive.pojo;

/**
 * TSysUserRight entity. @author MyEclipse Persistence Tools
 */

public class TSysUserRight implements java.io.Serializable
{

    // Fields

    private static final long serialVersionUID = 4770035140421417270L;
    private long NId;
    private Integer NTpye;
    private long NRoleid;
    private String CRightkey;

    // Constructors

    /** default constructor */
    public TSysUserRight()
    {
    }

    /** minimal constructor */
    public TSysUserRight(long NId)
    {
        this.NId = NId;
    }

    /** full constructor */
    public TSysUserRight(long NId, Integer NTpye, long NRoleid, String CRightkey)
    {
        this.NId = NId;
        this.NTpye = NTpye;
        this.NRoleid = NRoleid;
        this.CRightkey = CRightkey;
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

    public Integer getNTpye()
    {
        return this.NTpye;
    }

    public void setNTpye(Integer NTpye)
    {
        this.NTpye = NTpye;
    }

    public long getNRoleid()
    {
        return this.NRoleid;
    }

    public void setNRoleid(long NRoleid)
    {
        this.NRoleid = NRoleid;
    }

    public String getCRightkey()
    {
        return this.CRightkey;
    }

    public void setCRightkey(String CRightkey)
    {
        this.CRightkey = CRightkey;
    }

}