package com.dz.englishlive.pojo;

/**
 * TSysRoleRight entity. @author MyEclipse Persistence Tools
 */

public class TSysRoleRight implements java.io.Serializable
{

    // Fields

    private static final long serialVersionUID = -2960698696308640126L;
    private long NId;
    private long NRoleid;
    private String CRightkey;

    // Constructors

    /** default constructor */
    public TSysRoleRight()
    {
    }

    /** full constructor */
    public TSysRoleRight(long NId, long NRoleid, String CRightkey)
    {
        this.NId = NId;
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