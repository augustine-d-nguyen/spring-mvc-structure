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
public class OrderDetailDTO extends BaseDTO {
    /**
     * Quantity of Product.
     */
    private Integer quantity;
    
    /**
     * Order of this detail.
     */
    private OrderDTO order;
    
    /**
     * Product of this detail.
     */
    private ProductDTO product;
    
    /**
     * Creation date of this detail.
     */
    private Date creationDate;
    
    /**
     * Total price of this order detail.
     */
    private Double totalPrice;

    /**
     * @return the quantity
     */
    public final Integer getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public final void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the order
     */
    public final OrderDTO getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public final void setOrder(final OrderDTO order) {
        this.order = order;
    }

    /**
     * @return the product
     */
    public final ProductDTO getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public final void setProduct(final ProductDTO product) {
        this.product = product;
    }

    /**
     * @return the creationDate
     */
    public final Date getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate the creationDate to set
     */
    public final void setCreationDate(final Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return the totalPrice
     */
    public final Double getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice the totalPrice to set
     */
    public final void setTotalPrice(final Double totalPrice) {
        this.totalPrice = totalPrice;
    }

}
