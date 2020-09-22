package com.ttv.shop.backend.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Coupon entity maps with 
 *      <code>tbl_coupon</code> table.

 *
 */
@Entity
@Table(name = "tbl_coupon", schema = "probation_test")
public class Coupon extends BaseEntity {

    /**
     * Serial version number.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Mapping with column <code>name</code>
     */
    @Column(nullable = false)
    private String name;
    
    /**
     * Mapping with column <code>code</code>
     */
    @Column(nullable = false)
    private String code;
    
    /**
     * Mapping with column <code>start_date</code>
     */
    @Column(name = "start_date", nullable = false)
    private Date startDate;
    
    /**
     * Mapping with column <code>expired_date</code>
     */
    @Column(name = "expired_date", nullable = false)
    private Date expiredDate;
    
    /**
     * Mapping with column <code>coupon_type_id</code>
     * Foreign constraint with CouponType.
     */
    @ManyToOne
    @JoinColumn(name = "coupon_type_id", nullable = false)
    private CouponType couponType;
    
    /**
     * Mapping with column <code>status</code>
     */
    @Column(nullable = false)
    private Integer status;

    /**
     * @return the name
     */
    public final String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public final void setName(String name) {
        this.name = name;
    }

    /**
     * @return the code
     */
    public final String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public final void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the startDate
     */
    public final Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public final void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the expiredDate
     */
    public final Date getExpiredDate() {
        return expiredDate;
    }

    /**
     * @param expiredDate the expiredDate to set
     */
    public final void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    /**
     * @return the couponType
     */
    public final CouponType getCouponType() {
        return couponType;
    }

    /**
     * @param couponType the couponType to set
     */
    public final void setCouponType(CouponType couponType) {
        this.couponType = couponType;
    }

    /**
     * @return the status
     */
    public final Integer getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public final void setStatus(Integer status) {
        this.status = status;
    }
    
}
