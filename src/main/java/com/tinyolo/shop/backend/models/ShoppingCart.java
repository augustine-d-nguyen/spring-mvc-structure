/**
* @author augustine.d.nguyen
* @email augustine.d.nguyen@outlook.com
* @year 2015
*
*/
package com.ttv.shop.backend.models;

import java.util.HashMap;
import java.util.Map;

import com.ttv.shop.backend.dtos.CouponDTO;

/**
 * Cart contains product order.

 *
 */
public class ShoppingCart {
    /**
     * Container contains product order of a user.
     */
    private Map<Long, Integer> orders;
    
    /**
     * Coupon used for this cart.
     */
    private CouponDTO coupon;
    /**
     * Constructor non parameter.
     */
    public ShoppingCart() {
        this.orders = new HashMap<Long, Integer>();
    };
    /**
     * Add a order to container.
     * @param <code>productId</code> id of product
     * @param <code>quantity</code> quantity of product
     * @return true if adding successfully, else false.
     */
    public boolean addOrder(final Long productId, final Integer quantity) {
        // - Result of adding order
        boolean result = true;
        
        // - Ignore if productId or quantity is null
        if (null == productId || null == quantity) {
            result = false;
        }
        
        // - Check container is initialized or not, create new container if not.
        checkInitContainter();
        
        // - Increase quantity of the product is added before
        Integer addedQuantity = getQuantityOf(productId) + quantity;
        
        // - Put order to container.
        getOrdersContainer().put(productId, addedQuantity);
        
        return result;
    }
    /**
     * Add a order to container.
     * @param <code>productCart</code> product cart to add.
     * @return true if adding successfully, else false.
     */
    public final boolean addOrder(final ProductCart productCart) {
        // - Result of adding cart
        boolean result = true;
        try {
            addOrder(productCart.getProductId(), 
                    Integer.valueOf(productCart.getQuantity()));
        } catch (NumberFormatException e) {
            result = false;
        }
        return result;
    }
    
    /**
     * Get orders of this shopping cart.
     * @return Map contains orders.
     */
    public Map<Long, Integer> getOrdersContainer() {
        return this.orders;
    }
    /**
     * Get quantity of a product by its id in container.
     * @param productId
     * @return 0 if product not exist, else return its quantity.
     */
    public final Integer getQuantityOf(final Long productId) {
        // - Default result.
        Integer result = 0;
        
        // - Check container is initialized or not
        checkInitContainter();
        
        // - Case 1: productId not null and the product exists in container.
        if (null != productId && null != orders.get(productId)) {
            result += orders.get(productId);
        }
        // - Case 2: Else - return 0
        
        // - Return result
        return result;
    }
    /**
     * Initialize container if not exist.
     */
    private void checkInitContainter() {
        if (null == this.orders) {
            this.orders = new HashMap<Long, Integer>();
        }
    }
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
    
}
