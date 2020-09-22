/**
* @author augustine.d.nguyen
* @email augustine.d.nguyen@outlook.com
* @year 2015
*
*/
package com.ttv.shop.backend.dtos;


/**
 * Data Transfer Object of CouponType entity.

 *
 */
public class CouponTypeDTO extends BaseDTO {
    /**
     * Name of this coupon type.
     */
    private String name;
    
    /**
     * Discount of this coupon type.
     */
    private Float discount;

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
     * @return the discount
     */
    public final Float getDiscount() {
        return discount;
    }

    /**
     * @param discount the discount to set
     */
    public final void setDiscount(Float discount) {
        this.discount = discount;
    }
}
