/**
* @author augustine.d.nguyen
* @email augustine.d.nguyen@outlook.com
* @year 2015
*
*/
package com.ttv.shop.backend.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.ttv.shop.backend.daos.OrderDAO;
import com.ttv.shop.backend.dtos.CouponDTO;
import com.ttv.shop.backend.dtos.CouponTypeDTO;
import com.ttv.shop.backend.dtos.OrderDTO;
import com.ttv.shop.backend.dtos.OrderDetailDTO;
import com.ttv.shop.backend.dtos.ProductDTO;
import com.ttv.shop.backend.entities.Order;
import com.ttv.shop.backend.models.ShoppingCart;
import com.ttv.shop.backend.services.impl.OrderServiceImpl;
import com.ttv.shop.common.exceptions.ServiceLayerException;

/**
 * This is unit test for OrderService

 * To do list
 * + Method buildOrderFromCart
 * - Should throw an exception when cart is null.
 * - Should throw an exception when cart is empty.
 * - Should throw an exception when product not found in db.
 * - Should return an order when coupon is applied.
 * - Should return an order when coupon is not applied.
 * 
 * + Method registerOrder
 * - Should return null when order is null.
 * - Should throw an exception when reducing stock error.
 * - Should throw an exception when saving coupon error. (Change coupon to used)
 * - Should return order when saving successfully.
 */
public class OrderServiceTest {
    
    private static final Long ID_ERROR = 123L;
    private static final Long ID_SUCCESS = 124L;
    private static final Double PRICE = 1000D;
    private static final Integer QUANTITY = 1;
    private static final float DISCOUNT = 10L;
    
    private OrderService service;
    
    @Before
    public void setUp() throws ServiceLayerException {
        // - Create service
        service = new OrderServiceImpl();
        
        // - Mock service, dao.
        CouponService cpSrv = mock(CouponService.class);
        when(cpSrv.save(Mockito.any(CouponDTO.class))).then(new Answer<CouponDTO>() {

            @Override
            public CouponDTO answer(InvocationOnMock invocation) throws Throwable {
                CouponDTO input = invocation.getArgumentAt(0, CouponDTO.class);
                if (ID_ERROR == input.getId()) {
                    throw new ServiceLayerException();
                } else if (ID_SUCCESS == input.getId()) {
                    return input; 
                }
                return null;
            }
        });
        ProductService pdSrv = mock(ProductService.class);
        when(pdSrv.reduceProductStock(Mockito.anyLong(), Mockito.anyInt()))
        .then(new Answer<Boolean>() {

            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                Long id = invocation.getArgumentAt(0, Long.class);
                if (ID_ERROR == id) {
                    return false;
                } else {
                    return true;
                }
            }
        });
        when(pdSrv.findOne(Mockito.anyLong())).then(new Answer<ProductDTO>() {

            @Override
            public ProductDTO answer(InvocationOnMock invocation) throws Throwable {
                Long id = invocation.getArgumentAt(0, Long.class);
                if (ID_ERROR == id) {
                    return null;
                } else {
                    ProductDTO result = new ProductDTO();
                    result.setSalePrice(PRICE);
                    return result;
                }
            }
        });
        OrderDAO odDao = mock(OrderDAO.class);
        when(odDao.save(Mockito.any(Order.class))).then(new Answer<Order>() {

            @Override
            public Order answer(InvocationOnMock invocation) throws Throwable {
                return invocation.getArgumentAt(0, Order.class);
            }
        });
        
        // - Inject service, dao.
        setField(service, "productService", pdSrv);
        setField(service, "couponService", cpSrv);
        setField(service, "orderDAO", odDao);
    }

    @Test
    public void registerOrder_shouldReturnNullWhenOrderIsNull() {
        // - Given
        // - When
        try {
            OrderDTO actual = service.registerOrder(null);
            // - Then
            assertNull(actual);
        } catch (ServiceLayerException e) {
            fail();
        }
        
    }
    @Test(expected = ServiceLayerException.class)
    public void registerOrder_shouldThrowAnExceptionWhenReducingStockError() throws ServiceLayerException {
        // - Given
        OrderDTO inputOrder = new OrderDTO();
        inputOrder.setOrderDetails(new ArrayList<OrderDetailDTO>());
        
        OrderDetailDTO detailOrder = new OrderDetailDTO();
        
        ProductDTO product = new ProductDTO();
        product.setId(ID_ERROR);
        
        detailOrder.setProduct(product);
        
        inputOrder.getOrderDetails().add(detailOrder);
        
        // - When
        OrderDTO actual = service.registerOrder(inputOrder);
        // - Then
        assertNull(actual);
    }
    @Test(expected = ServiceLayerException.class)
    public void registerOrder_shouldThrowAnExceptionWhenSavingCouponError() throws ServiceLayerException {
        // - Given
        OrderDTO inputOrder = new OrderDTO();
        
        inputOrder.setOrderDetails(new ArrayList<OrderDetailDTO>());
        
        OrderDetailDTO detailOrder = new OrderDetailDTO();
        
        ProductDTO product = new ProductDTO();
        product.setId(ID_SUCCESS);
        
        detailOrder.setProduct(product);
        
        inputOrder.getOrderDetails().add(detailOrder);
        
        CouponDTO coupon = new CouponDTO();
        coupon.setId(ID_ERROR);
        
        inputOrder.setCoupon(coupon);
        // - When
        OrderDTO actual = service.registerOrder(inputOrder);
        // - Then
        assertNull(actual);
    }
    @Test
    public void registerOrder_shouldReturnOrderWhenSavingSuccessfully() {
     // - Given
        OrderDTO inputOrder = new OrderDTO();
        
        inputOrder.setOrderDetails(new ArrayList<OrderDetailDTO>());
        
        OrderDetailDTO detailOrder = new OrderDetailDTO();
        
        ProductDTO product = new ProductDTO();
        product.setId(ID_SUCCESS);
        
        detailOrder.setProduct(product);
        
        inputOrder.getOrderDetails().add(detailOrder);
        
        CouponDTO coupon = new CouponDTO();
        coupon.setId(ID_SUCCESS);
        
        inputOrder.setCoupon(coupon);
        // - When
        try {
            OrderDTO actual = service.registerOrder(inputOrder);
            // - Then
            assertNotNull(actual);
        } catch (ServiceLayerException e) {
            fail();
        }
    }
    
    @Test(expected = ServiceLayerException.class)
    public void buildOrderFromCart_shouldThrowAnExceptionWhenCartIsNull() throws ServiceLayerException {
        // - Given
        ShoppingCart cart = null;
        // - When
        OrderDTO actual = service.buildOrderFromCart(cart);
        // - Then
        assertNull(actual);
    }
    @Test(expected = ServiceLayerException.class)
    public void buildOrderFromCart_shouldThrowAnExceptionWhenCartIsEmpty() throws ServiceLayerException {
        // - Given
        ShoppingCart cart = new ShoppingCart();
        // - When
        OrderDTO actual = service.buildOrderFromCart(cart);
        // - Then
        assertNull(actual);
    }
    @Test(expected = ServiceLayerException.class)
    public void buildOrderFromCart_shouldThrowAnExceptionWhenProductNotFound() throws ServiceLayerException {
        // - Given
        ShoppingCart cart = new ShoppingCart();
        cart.addOrder(ID_ERROR, 1);
        // - When
        OrderDTO actual = service.buildOrderFromCart(cart);
        // - Then
        assertNull(actual);
    }
    @Test
    public void buildOrderFromCart_shouldReturnOrderWhenCouponIsApplied() {
        // - Given
        Double totalPay = PRICE * QUANTITY;
        Double expectedPrice = totalPay - (totalPay * DISCOUNT) / 100.0;
        
        ShoppingCart cart = new ShoppingCart();
        cart.addOrder(ID_SUCCESS, QUANTITY);
        
        CouponTypeDTO type = new CouponTypeDTO();
        type.setDiscount(DISCOUNT);
        
        CouponDTO coupon = new CouponDTO();
        coupon.setCouponType(type);
        
        cart.setCoupon(coupon);
        
        // - When
        try {
            OrderDTO actual = service.buildOrderFromCart(cart);
            // - Then
            assertNotNull(actual);
            assertEquals(expectedPrice, actual.getTotalPayment());
        } catch (ServiceLayerException e) {
            fail();
        }
        
    }
    @Test
    public void buildOrderFromCart_shouldReturnOrderWhenCouponIsNotApplied() {
     // - Given
        Double totalPay = PRICE * QUANTITY;
        
        ShoppingCart cart = new ShoppingCart();
        cart.addOrder(ID_SUCCESS, QUANTITY);
        
        cart.setCoupon(null);
        
        // - When
        try {
            OrderDTO actual = service.buildOrderFromCart(cart);
            // - Then
            assertNotNull(actual);
            assertEquals(totalPay, actual.getTotalPayment());
        } catch (ServiceLayerException e) {
            fail();
        }
        
    }
}
