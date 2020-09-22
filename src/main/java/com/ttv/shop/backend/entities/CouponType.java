/**
* @author augustine.d.nguyen
* @email augustine.d.nguyen@outlook.com
* @year 2015
*
*/
package com.ttv.shop.backend.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * CouponType entity maps with 
 *      <code>tbl_coupon_type</code> table.

 *
 */
@Entity
@Table(name = "tbl_coupon_type", schema = "probation_test")
public class CouponType extends BaseEntity {

    /**
     * Serial version number.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Mapping with <code>name</code> column.
     */
    @Column
    private String name;
    
    /**
     * Mapping with <code>discount</code> column.
     */
    @Column
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
