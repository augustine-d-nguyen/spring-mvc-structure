/**
* @author augustine.d.nguyen
* @email augustine.d.nguyen@outlook.com
* @year 2015
*
*/
package com.ttv.shop.backend.models;

/**
 * Cart contains order of product.

 *
 */
public class ProductCart {
    
    /**
     * Id of product.
     */
    private Long productId;
    /**
     * Quantity of product order;
     */
    private String quantity;
    /**
     * @return the productId
     */
    public final Long getProductId() {
        return productId;
    }
    /**
     * @param productId the productId to set
     */
    public final void setProductId(Long productId) {
        this.productId = productId;
    }
    /**
     * @return the quantity
     */
    public final String getQuantity() {
        return quantity;
    }
    /**
     * @param quantity the quantity to set
     */
    public final void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
