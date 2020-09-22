/**
* @author augustine.d.nguyen
* @email augustine.d.nguyen@outlook.com
* @year 2015
*
*/
package com.ttv.shop.backend.dtos;

import java.util.Date;


/**
 * Data Transfer Object of Coupon entity.

 *
 */
public class CouponDTO extends BaseDTO {
    /**
     * Name of this coupon.
     */
    private String name;
    
    /**
     * Code of this coupon
     */
    private String code;
    
    /**
     * Start date of this coupon.
     */
    private Date startDate;
    
    /**
     * End date of this coupon.
     */
    private Date expiredDate;
    
    /**
     * Type of this coupon.
     */
    private CouponTypeDTO couponType;
    
    /**
     * Status of this coupon.
     */
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
    public final CouponTypeDTO getCouponType() {
        return couponType;
    }

    /**
     * @param couponType the couponType to set
     */
    public final void setCouponType(CouponTypeDTO couponType) {
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
