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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * OrderDetail entity maps with 
 *      <code>tbl_orderdetail</code> table.

 *
 */
@Entity
@Table(name = "tbl_orderdetail", schema = "probation_test")
public class OrderDetail extends BaseEntity {

    /**
     * Serial version number.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Mapping with column <code>quantity</code>
     */
    @Column(nullable = false)
    private Integer quantity;
    
    /**
     * Mapping with column <code>order_id</code>
     * Foreign constraint with Order.
     */
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    
    /**
     * Mapping with column <code>product_id</code>
     * Foreign constraint with Product.
     */
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    
    /**
     * Mapping with column <code>creation_date</code>
     */
    @Column(name = "creation_date", nullable =false)
    private Date creationDate;

    /**
     * @return the quantity
     */
    public final Integer getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public final void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the order
     */
    public final Order getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public final void setOrder(Order order) {
        this.order = order;
    }

    /**
     * @return the product
     */
    public final Product getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public final void setProduct(Product product) {
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
    public final void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

}
