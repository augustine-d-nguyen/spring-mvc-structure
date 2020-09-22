/**
* @author augustine.d.nguyen
* @email augustine.d.nguyen@outlook.com
* @year 2015
*
*/
package com.ttv.shop.common.utilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;

import com.ttv.shop.backend.dtos.CouponDTO;
import com.ttv.shop.backend.entities.Coupon;
import com.ttv.shop.backend.entities.CouponType;
import com.ttv.shop.common.utilities.DataMapper;


/**
 * Unit test for DataMapper.

 * 
 * To do list:
 * + Method createMapper
 * - Should return a DozerBeanMapper when call method.
 * 
 * + Method doMap(Object, Class type)
 * - Should return null when a source object is null.
 * - Should return a destination object when mapping a not null source object.
 * 
 * + Method doMap(List, Class type)
 * - Should return null when a list of source object is null.
 * - Should return a empty list when a list of source object is empty.
 * - Should return list of destination object when mapping a list of source object.
 *
 */
public class DataMapperTest {
    
    private Coupon givenObj;
    
    private List<Coupon> givenListObj;
    
    @Before
    public void setUp() {
        createCoupon();
        givenListObj = new ArrayList<Coupon>();
        givenListObj.add(givenObj);
    }

    /**
     * 
     */
    private void createCoupon() {
        givenObj = new Coupon();
        givenObj.setCode("TEST00001");
        
        CouponType type = new CouponType();
        type.setId(123L);
        type.setName("Diamon");
        type.setDiscount(10F);
        
        givenObj.setCouponType(type);
        givenObj.setExpiredDate(new Date());
        givenObj.setId(132L);
        givenObj.setName("CP0001");
        givenObj.setStartDate(new Date());
        givenObj.setStatus(1);
    }
    
    @Test
    public void shouldReturnADozerBeanMapperWhenCallMethod() {
        // - Given
        String expected = DozerBeanMapper.class.getName();
        // - When
        Mapper actual = DataMapper.createMapper();
        // - Then
        assertNotNull(actual);
        assertEquals(expected, actual.getClass().getName());
    }
    @Test
    public void shouldReturnNullWhenASourceObjectIsNull() {
        // - Given
        Coupon givenObj = null;
        // - When
        CouponDTO actual = DataMapper.doMap(givenObj, CouponDTO.class);
        // - Then
        assertNull(actual);
    }
    @Test
    public void shouldReturnDestinationWhenSourceNotNull() {
        // - Given
        // - When
        CouponDTO actual = DataMapper.doMap(givenObj, CouponDTO.class);
        // - Then
        assertNotNull(actual);
        assertEquals(givenObj.getCode(), actual.getCode());
        assertEquals(givenObj.getCouponType().getId(), actual.getCouponType().getId());
        assertEquals(givenObj.getExpiredDate(), actual.getExpiredDate());
        assertEquals(givenObj.getId(), actual.getId());
        assertEquals(givenObj.getName(), actual.getName());
        assertEquals(givenObj.getStartDate(), actual.getStartDate());
        assertEquals(givenObj.getStatus(), actual.getStatus());
    }
    @Test
    public void shouldReturnNullWhenSourceListIsNull() {
        // - Given
        givenListObj = null;
        // - When
        List<CouponDTO> actual = DataMapper.doMap(givenListObj, CouponDTO.class);
        // - Then
        assertNull(actual);
    }
    @Test
    public void shouldReturnAEmptyListWhenSourceListIsEmpty() {
        // - Given
        givenListObj = new ArrayList<Coupon>();
        // - When
        List<CouponDTO> actual = DataMapper.doMap(givenListObj, CouponDTO.class);
        // - Then
        assertNotNull(actual);
        assertTrue(actual.isEmpty());
    }
    @Test
    public void shouldReturnAListOfDestinationWhenSourceListIsNotEmpty() {
        // - Given
        int expectedSize = 1;
        // - When
        List<CouponDTO> actual = DataMapper.doMap(givenListObj, CouponDTO.class);
        // - Then
        assertNotNull(actual);
        assertFalse(actual.isEmpty());
        assertEquals(expectedSize, actual.size());
        
        CouponDTO target = actual.get(0);
        
        assertEquals(givenObj.getCode(), target.getCode());
        assertEquals(givenObj.getCouponType().getId(), target.getCouponType().getId());
        assertEquals(givenObj.getExpiredDate(), target.getExpiredDate());
        assertEquals(givenObj.getId(), target.getId());
        assertEquals(givenObj.getName(), target.getName());
        assertEquals(givenObj.getStartDate(), target.getStartDate());
        assertEquals(givenObj.getStatus(), target.getStatus());
    }
}
