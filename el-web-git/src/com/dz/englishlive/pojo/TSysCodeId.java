package com.dz.englishlive.pojo;

/**
 * TSysCodeId entity. @author MyEclipse Persistence Tools
 */

public class TSysCodeId implements java.io.Serializable
{

    // Fields

    private static final long serialVersionUID = -29786758881123848L;
    private Integer NPid;
    private Integer NCode;

    // Constructors

    /** default constructor */
    public TSysCodeId()
    {
    }

    /** full constructor */
    public TSysCodeId(Integer NPid, Integer NCode)
    {
        this.NPid = NPid;
        this.NCode = NCode;
    }

    // Property accessors

    public Integer getNPid()
    {
        return this.NPid;
    }

    public void setNPid(Integer NPid)
    {
        this.NPid = NPid;
    }

    public Integer getNCode()
    {
        return this.NCode;
    }

    public void setNCode(Integer NCode)
    {
        this.NCode = NCode;
    }

    public boolean equals(Object other)
    {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof TSysCodeId))
            return false;
        TSysCodeId castOther = (TSysCodeId) other;

        return ((this.getNPid() == castOther.getNPid()) || (this.getNPid() != null
                && castOther.getNPid() != null && this.getNPid().equals(
                castOther.getNPid())))
                && ((this.getNCode() == castOther.getNCode()) || (this
                        .getNCode() != null && castOther.getNCode() != null && this
                        .getNCode().equals(castOther.getNCode())));
    }

    public int hashCode()
    {
        int result = 17;

        result = 37 * result
                + (getNPid() == null ? 0 : this.getNPid().hashCode());
        result = 37 * result
                + (getNCode() == null ? 0 : this.getNCode().hashCode());
        return result;
    }

}