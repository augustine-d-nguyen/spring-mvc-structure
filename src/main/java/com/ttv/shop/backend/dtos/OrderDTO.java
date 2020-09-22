/**
* @author augustine.d.nguyen
* @email augustine.d.nguyen@outlook.com
* @year 2015
*
*/
package com.ttv.shop.backend.dtos;

import java.util.List;


/**
 * Data Transfer Object of Order entity.

 *
 */
public class OrderDTO extends BaseDTO {
    /**
     * The coupon of this Order.
     * It will be NULL if user do not use coupon.
     */
    private CouponDTO coupon;
    
    /**
     * The user make this Order.
     */
    private UserDTO user;
    
    /**
     * Order discount.
     */
    private Float discount;
    
    /**
     * Deliver address.
     */
    private String addressInfo;
    
    /**
     * Payment.
     */
    private Double totalPayment;

    /**
     * Details of this order.
     */
    private List<OrderDetailDTO> orderDetails;
    /**
     * @return the coupon
     */
    public final CouponDTO getCoupon() {
        return coupon;
    }

    /**
     * @param coupon the coupon to set
     */
    public final void setCoupon(final CouponDTO coupon) {
        this.coupon = coupon;
    }

    /**
     * @return the user
     */
    public final UserDTO getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public final void setUser(final UserDTO user) {
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
    public final void setDiscount(final Float discount) {
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
    public final void setAddressInfo(final String addressInfo) {
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
    public final void setTotalPayment(final Double totalPayment) {
        this.totalPayment = totalPayment;
    }

    /**
     * @return the orderDetails
     */
    public final List<OrderDetailDTO> getOrderDetails() {
        return orderDetails;
    }

    /**
     * @param orderDetails the orderDetails to set
     */
    public final void setOrderDetails(final List<OrderDetailDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
