/**
* @author augustine.d.nguyen
* @email augustine.d.nguyen@outlook.com
* @year 2015
*
*/
package com.ttv.shop.backend.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Product entity maps with
 *      <code>tbl_product</code> table.

 *
 */
@Entity
@Table(name = "tbl_product", schema = "probation_test")
public class Product extends BaseEntity {

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
     * Mapping with column <code>stock_quantity</code>
     */
    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity;
    
    /**
     * Mapping with column <code>sale_price</code>
     */
    @Column(name = "sale_price", nullable = false)
    private Double salePrice;
    
    /**
     * Mapping with column <code>last_update</code>
     */
    @Column(name = "last_update", nullable = false)
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
     * @return the stockQuantity
     */
    public final Integer getStockQuantity() {
        return stockQuantity;
    }

    /**
     * @param stockQuantity the stockQuantity to set
     */
    public final void setStockQuantity(Integer stockQuantity) {
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
    public final void setSalePrice(Double salePrice) {
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
    public final void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

}
