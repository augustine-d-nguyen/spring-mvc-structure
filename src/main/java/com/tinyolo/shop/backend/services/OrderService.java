/**
* @author augustine.d.nguyen
* @email augustine.d.nguyen@outlook.com
* @year 2015
*
*/
package com.ttv.shop.backend.services;

import com.ttv.shop.backend.dtos.OrderDTO;
import com.ttv.shop.backend.models.ShoppingCart;
import com.ttv.shop.common.exceptions.ServiceLayerException;

/**
 * Service supplies ways to interact with Order.

 *
 */
public interface OrderService extends BaseService<OrderDTO, Long> {
    
    /**
     * Build a order from shopping cart.
     * @param shoppingCart to build order
     * @return OrderDTO object presents a order.
     * @throws ServiceLayerException will throw if errors.
     */
    OrderDTO buildOrderFromCart(ShoppingCart shoppingCart) 
            throws ServiceLayerException;
    
    /**
     * Save order to db.
     * Reduce product quantity if order save successfully.
     * @param order to save
     * @return saved object
     */
    OrderDTO registerOrder(OrderDTO order) throws ServiceLayerException;
}
