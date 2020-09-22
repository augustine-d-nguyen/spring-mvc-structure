/**
 * @author augustine.d.nguyen
* @email augustine.d.nguyen@outlook.com
* @year 2015
 *
 */
package com.ttv.shop.backend.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ttv.shop.backend.daos.OrderDAO;
import com.ttv.shop.backend.dtos.CouponDTO;
import com.ttv.shop.backend.dtos.OrderDTO;
import com.ttv.shop.backend.dtos.OrderDetailDTO;
import com.ttv.shop.backend.dtos.ProductDTO;
import com.ttv.shop.backend.entities.Order;
import com.ttv.shop.backend.models.ShoppingCart;
import com.ttv.shop.backend.services.CouponService;
import com.ttv.shop.backend.services.OrderService;
import com.ttv.shop.backend.services.ProductService;
import com.ttv.shop.common.exceptions.ServiceLayerException;
import com.ttv.shop.common.utilities.DataMapper;

/**
 * Implementation of OrderService.
 * 

 *
 */
@Service("OrderService")
public class OrderServiceImpl extends BaseServiceImpl<OrderDTO, Long> implements OrderService {
    
    /**
     * Percent.
     */
    private static final double PERCENT = 100.0D;

    /**
     * Serial number.
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private ProductService productService;

    @Autowired
    private CouponService couponService;

    /* (non-Javadoc)
     * @see com.ttv.shop.backend.services.OrderService#buildOrderFromCart(com.ttv.shop.backend.models.ShoppingCart)
     */
    @Override
    public OrderDTO buildOrderFromCart(ShoppingCart shoppingCart) throws ServiceLayerException {
     // - Check input
        if (null == shoppingCart || null == shoppingCart.getOrdersContainer()
                || 0 == shoppingCart.getOrdersContainer().size()) {
            throw new ServiceLayerException("Shopping Cart is empty.");
        }
        
        // - Create new order
        OrderDTO newOrder = new OrderDTO();
        newOrder.setOrderDetails(new ArrayList<OrderDetailDTO>());

        // - Total payment of this order
        Double totalPayment = 0.0D;

        // - Travel around map
        Iterator<Entry<Long, Integer>> iter = shoppingCart.getOrdersContainer().entrySet()
                .iterator();
        while (iter.hasNext()) {
            Entry<Long, Integer> cartEntry = iter.next();
            // - Ignore null
            if (null == cartEntry) {
                continue;
            }

            // - Get cart value
            Long productId = cartEntry.getKey();
            Integer quantity = cartEntry.getValue();

            // - Ignore invalid value
            if (null == productId || null == quantity) {
                continue;
            }


            // - Find a product for this cart entry
            ProductDTO product = productService.findOne(productId);

            // - Check product
            if (null == product) {
                throw new ServiceLayerException("Product not found, id = " 
                                                    + productId);
            }
            
            // - New order detail
            OrderDetailDTO detail = new OrderDetailDTO();

            // - Set detail order
            detail.setProduct(product);
            detail.setQuantity(quantity);
            detail.setTotalPrice(quantity * product.getSalePrice());
            detail.setOrder(newOrder);
            detail.setCreationDate(new Date());

            // - Set order detail to order
            newOrder.getOrderDetails().add(detail);

            // - Increase total payment
            totalPayment += detail.getTotalPrice();

        }

        float discount = 0.0F;
        if (null != shoppingCart.getCoupon()) {
            CouponDTO coupon = shoppingCart.getCoupon();
            if (null != coupon.getCouponType()) {
                discount = coupon.getCouponType().getDiscount();
                totalPayment = totalPayment 
                        - ((totalPayment * discount) / PERCENT);
            }
            newOrder.setCoupon(coupon);
        }

        newOrder.setDiscount(discount);
        // - Set total payment
        newOrder.setTotalPayment(totalPayment);

        return newOrder;
    }

    /* (non-Javadoc)
     * @see com.ttv.shop.backend.services.OrderService#registerOrder(com.ttv.shop.backend.dtos.OrderDTO)
     */
    @Override
    @Transactional(rollbackFor = ServiceLayerException.class)
    public OrderDTO registerOrder(OrderDTO order) throws ServiceLayerException {
        
        if (null == order) {
            return null;
        }
        Mapper mp = DataMapper.createMapper();
        // - Map DTO to Entity
        Order beSaved = mp.map(order, Order.class);
        // - Save order and map return order entity to DTO
        OrderDTO saved = mp.map(orderDAO.save(beSaved), OrderDTO.class);
        
        if (null != saved) {
            // - Reduce quantity
            List<OrderDetailDTO> details = saved.getOrderDetails();
            
            for (OrderDetailDTO orderDetailDTO : details) {
                if (!productService.reduceProductStock(orderDetailDTO.getProduct().getId(),
                        orderDetailDTO.getQuantity())) {
                    saved = null;
                    throw new ServiceLayerException("Cannot reduce quantity for product id = " 
                        + orderDetailDTO.getProduct().getId());
                }
            }
            // - Set coupon is used
            CouponDTO coupon = saved.getCoupon();
            if (null != coupon) {
                coupon.setStatus(1);
                couponService.save(coupon);
            }
        }
        return saved;
    }

}
