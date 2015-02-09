package com.dz.englishlive.pojo;

import java.util.Date;

/**
 * TInfoCourse entity. @author MyEclipse Persistence Tools
 */

public class TInfoCourse implements java.io.Serializable
{

    // Fields

    private static final long serialVersionUID = -2305305712476742252L;
    private long NId;
    private String CTopic;
    private Date dtStarttime;
    private Date dtEndtime;
    private Integer NProgress;
    private Integer NPaymentStatus;
    private float FCharge;
    private long NTeacherid;
    private long NStudentid;
    private Integer NBookStatus;
    private Integer NValid;
    private Date dtCreatetime;

    // Constructors

    /** default constructor */
    public TInfoCourse()
    {
    }

    /** minimal constructor */
    public TInfoCourse(long NId)
    {
        this.NId = NId;
    }

    /** full constructor */
    public TInfoCourse(long NId, String CTopic, Date dtStarttime,
            Date dtEndtime, Integer NProgress, Integer NPaymentStatus,
            float FCharge, long NTeacherid, long NStudentid,
            Integer NBookStatus, Integer NValid, Date dtCreatetime
            )
    {
        this.NId = NId;
        this.CTopic = CTopic;
        this.dtStarttime = dtStarttime;
        this.dtEndtime = dtEndtime;
        this.NProgress = NProgress;
        this.NPaymentStatus = NPaymentStatus;
        this.FCharge = FCharge;
        this.NTeacherid = NTeacherid;
        this.NStudentid = NStudentid;
        this.NBookStatus = NBookStatus;
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

    public String getCTopic()
    {
        return this.CTopic;
    }

    public void setCTopic(String CTopic)
    {
        this.CTopic = CTopic;
    }

    public Date getDtStarttime()
    {
        return this.dtStarttime;
    }

    public void setDtStarttime(Date dtStarttime)
    {
        this.dtStarttime = dtStarttime;
    }

    public Date getDtEndtime()
    {
        return this.dtEndtime;
    }

    public void setDtEndtime(Date dtEndtime)
    {
        this.dtEndtime = dtEndtime;
    }

    public Integer getNProgress()
    {
        return this.NProgress;
    }

    public void setNProgress(Integer NProgress)
    {
        this.NProgress = NProgress;
    }

    public Integer getNPaymentStatus()
    {
        return this.NPaymentStatus;
    }

    public void setNPaymentStatus(Integer NPaymentStatus)
    {
        this.NPaymentStatus = NPaymentStatus;
    }

    public float getFCharge()
    {
        return this.FCharge;
    }

    public void setFCharge(float FCharge)
    {
        this.FCharge = FCharge;
    }

    public long getNTeacherid()
    {
        return this.NTeacherid;
    }

    public void setNTeacherid(long NTeacherid)
    {
        this.NTeacherid = NTeacherid;
    }

    public long getNStudentid()
    {
        return this.NStudentid;
    }

    public void setNStudentid(long NStudentid)
    {
        this.NStudentid = NStudentid;
    }

    public Integer getNBookStatus()
    {
        return this.NBookStatus;
    }

    public void setNBookStatus(Integer NBookStatus)
    {
        this.NBookStatus = NBookStatus;
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