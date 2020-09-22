/**
* @author augustine.d.nguyen
* @email augustine.d.nguyen@outlook.com
* @year 2015
*
*/
package com.ttv.shop.backend.dtos;

import java.util.Date;


/**
 * Data Transfer Object of Product entity.

 *
 */
public class ProductDTO extends BaseDTO {
    /**
     * Name of this product.
     */
    private String name;
    
    /**
     * Code of this product.
     */
    private String code;
    
    /**
     * Stock quantity of this product.
     */
    private Integer stockQuantity;
    
    /**
     * Price of this product.
     */
    private Double salePrice;
    
    /**
     * Last updating date of this product.
     */
    private Date lastUpdate;

    /**
     * @return the name
     */
    public final String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public final void setName(final String name) {
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
    public final void setCode(final String code) {
        this.code = code;
    }

    /**
     * @return the stockQuantity
     */
    public final Integer getStockQuantity() {
        return stockQuantity;
    }

    /**
     * @param stockQuantity the stockQuantity to set
     */
    public final void setStockQuantity(final Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    /**
     * @return the salePrice
     */
    public final Double getSalePrice() {
        return salePrice;
    }

    /**
     * @param salePrice the salePrice to set
     */
    public final void setSalePrice(final Double salePrice) {
        this.salePrice = salePrice;
    }

    /**
     * @return the lastUpdate
     */
    public final Date getLastUpdate() {
        return lastUpdate;
    }

    /**
     * @param lastUpdate the lastUpdate to set
     */
    public final void setLastUpdate(final Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
