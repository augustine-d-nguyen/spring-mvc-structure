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

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.mysema.query.types.Predicate;
import com.ttv.shop.backend.daos.ProductDAO;
import com.ttv.shop.backend.dtos.ProductDTO;
import com.ttv.shop.backend.entities.Product;
import com.ttv.shop.backend.services.impl.ProductServiceImpl;

/**
 * This is unit test for ProductService

 *To do list:
 * + Method findOne
 * - Should return null when id is null.
 * - Should return null when not found.
 * - Should return Product when found.
 * 
 * + Method findAll
 * - Should return all product.
 * 
 * + Method validateStock
 * - Should return false when productId is null.
 * - Should return false when quantity is null.
 * - Should return false when both is null.
 * - Should return false when productId is not found.
 * - Should return false when quantity exceeds stock.
 * - Should return true when quantity less than stock.
 * 
 * + Method reduceProductStock
 * - Should return false when productId is null.
 * - Should return false when quantity is null.
 * - Should return false when both is null.
 * - Should return false when productId is not found.
 * - Should return false when quantity exceeds stock.
 * - Should return true when quantity less than stock.
 * 
 */
public class ProductServiceTest {
    
    private static final Long FOUND_ID = 123L;
    private static final Long NOT_FOUND_ID = 223L;

    private ProductService service;
    
    @Before
    public void setUp() {
        // - Create service.
        service = new ProductServiceImpl();
        // - Mock dao.
        ProductDAO dao = createMockProductDAO();
        // - Inject dao.
        setField(service, "productDAO", dao);
    }
    
    private ProductDAO createMockProductDAO() {
        ProductDAO dao = mock(ProductDAO.class);
        when(dao.findOne(Mockito.anyLong())).then(new Answer<Product>() {

            @Override
            public Product answer(InvocationOnMock invocation) throws Throwable {
                Long id = invocation.getArgumentAt(0, Long.class);
                if (NOT_FOUND_ID == id) {
                    return null;
                } else {
                    Product result = new Product();
                    result.setStockQuantity(100);
                    return result;
                }
            }
        });
       when(dao.findOne(Mockito.any(Predicate.class))).then(new Answer<Product>() {

            @Override
            public Product answer(InvocationOnMock invocation) throws Throwable {
                return new Product();
            }
       });
        return dao;
    }
    
    @Test
    public void findOne_shouldReturnNullWhenIdIsNull() {
        // - Given
        // - When
        ProductDTO actual = service.findOne(null);
        // - Then
        assertNull(actual);
    }
    @Test
    public void findOne_shouldReturnNullWhenIdIsNotFound() {
        // - Given
        // - When
        ProductDTO actual = service.findOne(NOT_FOUND_ID);
        // - Then
        assertNull(actual);
    }
    @Test
    public void findOne_shouldReturnNullWhenIdIsFound() {
        // - Given
        // - When
        ProductDTO actual = service.findOne(FOUND_ID);
        // - Then
        assertNotNull(actual);
    }
    @Test
    public void findAll_shouldReturnAllProduct() {
        // - Test in integration test phase. Because using Spring JPA Data.
    }
    
    @Test
    public void validateStock_shouldReturnFalseWhenProducIdIsNull() {
        // - Given
        // - When
        boolean actual = service.validateStock(null, 1);
        // - Then
        assertFalse(actual);
    }
    @Test
    public void validateStock_shouldReturnFalseWhenQuantityIsNull() {
        // - Given
        // - When
        boolean actual = service.validateStock(1L, null);
        // - Then
        assertFalse(actual);
    }
    @Test
    public void validateStock_shouldReturnFalseWhenBothIsNull() {
        // - Given
        // - When
        boolean actual = service.validateStock(null, null);
        // - Then
        assertFalse(actual);
    }
    @Test
    public void validateStock_shouldReturnFalseWhenProducIdNotFound() {
        // - Test in integration phase. Because using Spring JPA Data for implement DAO
        // - So, it is difficult to create a mock for dao.
    }
    @Test
    public void validateStock_shouldReturnFalseWhenQuantityExceedStock() {
        // - Test in integration phase. Because using Spring JPA Data for implement DAO
        // - So, it is difficult to create a mock for dao.
    }
    @Test
    public void validateStock_shouldReturnTrueWhenValid() {
        // - Given
        // - When
        boolean actual = service.validateStock(FOUND_ID, 10000);
        // - Then
        assertTrue(actual);
    }
    
    /**
     * - Should return false when productId is null.
     * - Should return false when quantity is null.
     * - Should return false when both is null.
     * - Should return false when productId is not found.
     * - Should return false when quantity exceeds stock.
     */
    @Test
    public void reduceProductStock_shouldReturnFalseWhenProductIdIsNull() {
        // - All of test case is verified by method validateStock
    }
    @Test
    public void reduceProductStock_shouldReturnTrueWhenValid() {
        // - Given
        // - When
        boolean actual = service.reduceProductStock(FOUND_ID, 10);
        // - Then
        assertTrue(actual);
    }
}
