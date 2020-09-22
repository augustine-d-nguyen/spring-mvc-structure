/**
* @author augustine.d.nguyen
* @email augustine.d.nguyen@outlook.com
* @year 2015
*
*/
package com.ttv.shop.backend.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Order entity maps with 
 *      <code>tbl_order</code> table.

 *
 */
@Entity
@Table(name = "tbl_order", schema = "probation_test")
public class Order extends BaseEntity {

    /**
     * Serial version number.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Mapping with column <code>coupon_id</code>
     * Foreign constraint with Coupon.
     */
    @ManyToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;
    
    /**
     * Mapping with column <code>user_id</code>
     * Foreign constraint with User.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    /**
     * Mapping with column <code>discount</code>
     */
    @Column(nullable = false)
    private Float discount;
    
    /**
     * Mapping with column <code>address_info</code>
     */
    @Column(name = "address_info", nullable = false)
    private String addressInfo;
    
    /**
     * Mapping with column <code>total_payment</code>
     */
    @Column(name = "total_payment", nullable = false)
    private Double totalPayment;

    /**
     * Detail of this order.
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<OrderDetail> orderDetails;
    /**
     * @return the coupon
     */
    public final Coupon getCoupon() {
        return coupon;
    }

    /**
     * @param coupon the coupon to set
     */
    public final void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    /**
     * @return the user
     */
    public final User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public final void setUser(User user) {
        this.user = user;
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

    /**
     * @return the addressInfo
     */
    public final String getAddressInfo() {
        return addressInfo;
    }

    /**
     * @param addressInfo the addressInfo to set
     */
    public final void setAddressInfo(String addressInfo) {
        this.addressInfo = addressInfo;
    }

    /**
     * @return the totalPayment
     */
    public final Double getTotalPayment() {
        return totalPayment;
    }

    /**
     * @param totalPayment the totalPayment to set
     */
    public final void setTotalPayment(Double totalPayment) {
        this.totalPayment = totalPayment;
    }

    /**
     * @return the orderDetails
     */
    public final List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    /**
     * @param orderDetails the orderDetails to set
     */
    public final void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
