/**
* @author augustine.d.nguyen
* @email augustine.d.nguyen@outlook.com
* @year 2015
*
*/
package com.ttv.shop.backend.services;

import java.util.List;

import com.ttv.shop.backend.dtos.ProductDTO;

/**
 * Service supplies ways to interact with Product.

 *
 */
public interface ProductService extends BaseService<ProductDTO, Long> {
    
    
    /**
     * Find a product from db.
     * @param id Id of the product be found.
     * @return ProductDTO product entity if found, NULL if not found.
     */
    ProductDTO findOne(Long id);
    
    /**Find all product from db.
     * @return List<ProductDTO> list of all product entity in db.
     */
    List<ProductDTO> findAll();
    
    /**
     * Check quantity of product in stock.
     * @param productId product id
     * @param quantity of order.
     * @return <code>false</code> if out of stock, 
     *      else <code>true</code> will be return.
     */
    boolean validateStock(Long productId, Integer quantity);
    
    /**Reduce quantity of product in stock.
     * @param productId reduce quantity of this product id.
     * @param quantity reducing number
     * @return true if stock >= quantity, else false.
     */
    boolean reduceProductStock(Long productId, Integer quantity);
}
