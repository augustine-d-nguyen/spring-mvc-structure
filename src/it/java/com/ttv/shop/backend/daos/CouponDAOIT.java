/**
* @author augustine.d.nguyen
* @email augustine.d.nguyen@outlook.com
* @year 2015
*
*/
package com.ttv.shop.backend.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ttv.shop.backend.entities.Coupon;
import com.ttv.shop.backend.entities.CouponType;

/**
 * Integration test for CouponDAO.

 * To do list:
 * - Should throw an exception when coupon name is null;
 * - Should throw an exception when coupon code is null;
 * - Should return nothing when selecting a non-existed coupon;
 * - Should return a Coupon entity when selecting a existed coupon;
 * - Should return a Coupon entity when saving it successfully;
 * - Should return nothing when deleting a not existed coupon;
 * - Should return nothing when deleting a existed coupon;
 * - Should return a new entity when updating a non-existed coupon;
 * - Should return a updated entity when updating coupon;
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:backend-jpa-context.xml")
@Transactional
public class CouponDAOIT {
    
    @Autowired
    private CouponDAO dao;
    
    private Coupon expected;
    
    @Before
    public void setUp() {
        Coupon cp = new Coupon();
        cp.setCode("CP000123IT");
        cp.setName("CPABC0001IT");
        cp.setExpiredDate(new Date());
        cp.setStartDate(new Date());
        cp.setStatus(1);
        
        CouponType type = new CouponType();
        type.setId(1L);
        
        cp.setCouponType(type);

        expected = dao.save(cp);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void shouldThrowAnExceptionWhenSavingACouponWithNameIsNull() {
        // - Given
        CouponType type = new CouponType();
        type.setId(1L);
        
        Coupon entity = new Coupon();
        entity.setName(null);
        entity.setCode("ABC12222");
        entity.setCouponType(type);
        entity.setExpiredDate(new Date());
        entity.setStartDate(new Date());
        entity.setStatus(1);
        // - When
        Coupon actual = dao.save(entity);
        // - Then
        assertNull(actual);
    }
    
    @Test(expected = DataIntegrityViolationException.class)
    public void shouldThrowAnExceptionWhenSavingACouponWithCodeIsNull() {
        // - Given
        CouponType type = new CouponType();
        type.setId(1L);
        
        Coupon entity = new Coupon();
        entity.setName("CP009833");
        entity.setCode(null);
        entity.setCouponType(type);
        entity.setExpiredDate(new Date());
        entity.setStartDate(new Date());
        entity.setStatus(1);
        // - When
        Coupon actual = dao.save(entity);
        // - Then
        assertNull(actual);
    }
    
    @Test
    public void shouldReturnNothingWhenSelectingNonExistedCoupon() {
        // - Given
        Long nonExistedCouponId = 12254L;
        // - When
        Coupon actual = dao.findOne(nonExistedCouponId);
        // - Then
        assertNull(actual);
    }
    
    @Test
    public void shouldReturnACouponEntityWhenSelectingExistedCoupon() {
        // - Given
        assertNotNull(expected);
        assertNotNull(expected.getId());
        
        Long nonExistedCouponId = expected.getId();
        // - When
        Coupon actual = dao.findOne(nonExistedCouponId);
        // - Then
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.getId(), actual.getId());
    }
}
