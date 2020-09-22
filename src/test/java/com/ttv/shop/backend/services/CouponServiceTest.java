/**
* @author augustine.d.nguyen
* @email augustine.d.nguyen@outlook.com
* @year 2015
*
*/
package com.ttv.shop.backend.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;

import com.ttv.shop.backend.daos.CouponDAO;
import com.ttv.shop.backend.dtos.CouponDTO;
import com.ttv.shop.backend.entities.Coupon;
import com.ttv.shop.backend.services.impl.CouponServiceImpl;
import com.ttv.shop.common.exceptions.ServiceLayerException;

/**
 * This is unit test class for CouponService.

 *To do list:
 *
 * + Method save.
 * - Should return null when input is null.
 * - Should throw exception when a error occurs.
 * - Should return saved Coupon when saving successfully.
 * + Method findByCode
 * - Should return null when code is null.
 * - Should return null when code is empty.
 * - Should return null when code is a space character.
 * - Should return null when coupon is not found.
 * - Should return CouponDTO when found.
 */
public class CouponServiceTest {
    
    private static Long ID = 10000L;
    
    private static String COUPON_NAME = "CP001";
    
    private static String NOT_FOUND_COUPON_CODE = "CP0012";
    
    private static String FOUND_COUPON_CODE = "CP00123";
    
    /**
     * Target service of this testing.
     */
    private CouponService service;
    
    @Before
    public void setUp() {
        // - Create service.
        service = new CouponServiceImpl();
        // - Mock dao.
        CouponDAO dao = createMockCouponDAO();
        // - Inject dao.
        setField(service, "dao", dao);
    }
    
    /**
     * Mock a CouponDAO, define behavior.
     * @return CouponDAO
     */
    private CouponDAO createMockCouponDAO() {
        
        Coupon savedCoupon = new Coupon();
        savedCoupon.setId(ID);
        
        CouponDAO mockDAO = mock(CouponDAO.class);
        
        // - Create behavior for save method.
        when(mockDAO.save(Mockito.any(Coupon.class))).then(new Answer<Coupon>() {

            @Override
            public Coupon answer(InvocationOnMock invocation) throws Throwable {
                Coupon input = invocation.getArgumentAt(0, Coupon.class);
                if (null == input || null == input.getName()) {
                    throw new DataIntegrityViolationException("Invalid");
                }
                return input;
            }
        });
        
        return mockDAO;
    }
    
    
    @Test
    public void save_shouldReturnNullWhenInputIsNull() {
        // - Given
        // - When
        try {
            CouponDTO actual = service.save(null);
            // - Then
            assertNull(actual);
        } catch(ServiceLayerException e) {
            fail();
        }
    }
    
    @Test(expected = ServiceLayerException.class)
    public void save_shouldThrowAnExceptionWhenHasErrors() throws ServiceLayerException {
        // - Given
        CouponDTO input = new CouponDTO();
        input.setName(null);
        // - When
        CouponDTO actual = service.save(input);
        // - Then
        assertNull(actual);
    }
    
    @Test
    public void save_shouldReturnSavedCouponWhenSuccessfully() {
        // - Given
        CouponDTO input = new CouponDTO();
        input.setId(ID);
        input.setName(COUPON_NAME);
        // - When
        try {
            CouponDTO actual = service.save(input);
            // - Then
            assertNotNull(actual);
            assertEquals(ID, actual.getId());
            assertEquals(COUPON_NAME, actual.getName());
        } catch(ServiceLayerException e) {
            fail();
        }
    }
    
    @Test
    public void findByCode_shouldReturnNullWhenCodeIsNull() {
        // - Given
        // - When
        CouponDTO actual = service.findByCode(null);
        // - Then
        assertNull(actual);
    }
    @Test
    public void findByCode_shouldReturnNullWhenCodeIsEmpty() {
        // - Given
        // - When
        CouponDTO actual = service.findByCode("");
        // - Then
        assertNull(actual);
    }
    @Test
    public void findByCode_shouldReturnNullWhenCodeIsASpace() {
        // - Given
        // - When
        CouponDTO actual = service.findByCode(" ");
        // - Then
        assertNull(actual);
    }
    @Test
    public void findByCode_shouldReturnNullWhenCodeNotFound() {
        // - Test in integration test because dao using Spring JPA data
        // - , so difficult to create a mock obj
    }
    @Test
    public void findByCode_shouldReturnCouponWhenFound() {
        // - Test in integration test because dao using Spring JPA data
        // - , so difficult to create a mock obj
    }
}
