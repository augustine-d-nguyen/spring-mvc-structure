/**
* @author augustine.d.nguyen
* @email augustine.d.nguyen@outlook.com
* @year 2015
*
*/
package com.ttv.shop.backend.services.impl;

import static com.ttv.shop.common.utilities.DataMapper.doMap;
import static org.apache.commons.lang3.StringUtils.isBlank;

import org.dozer.Mapper;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ttv.shop.backend.daos.CouponDAO;
import com.ttv.shop.backend.dtos.CouponDTO;
import com.ttv.shop.backend.entities.Coupon;
import com.ttv.shop.backend.entities.QCoupon;
import com.ttv.shop.backend.services.CouponService;
import com.ttv.shop.common.exceptions.ServiceLayerException;
import com.ttv.shop.common.utilities.DataMapper;

/**
 * A implementation of CouponSerivce.

 *
 */
@Service("CouponService")
public class CouponServiceImpl extends BaseServiceImpl<CouponDTO, Long> implements CouponService {

    /**
     * Serial number.
     */
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private CouponDAO dao;
    
    /* (non-Javadoc)
     * @see com.ttv.shop.backend.services.CouponService#findByCode(java.lang.String)
     */
    @Override
    public CouponDTO findByCode(String code) {
        
        QCoupon qCounpon = QCoupon.coupon;
        
        CouponDTO result = null;
        
        if (!isBlank(code)) {
            Coupon cp = dao.findOne(qCounpon.code.eq(code));
            result = doMap(cp, CouponDTO.class);
        }
        return result;
    }
    /* (non-Javadoc)
     * @see com.ttv.shop.backend.services.CouponService#save(com.ttv.shop.backend.dtos.CouponDTO)
     */
    @Override
    public CouponDTO save(CouponDTO coupon) throws ServiceLayerException {
        CouponDTO result = null;
        // - Check null
        if (null == coupon) {
            return result;
        }
        // - Try saving
        try {
            Mapper mp = DataMapper.createMapper();
            // - Map DTO to Entity.
            Coupon beSaved = mp.map(coupon, Coupon.class);
            // - Saving
            result =  mp.map(dao.save(beSaved), CouponDTO.class);
        } catch (MappingException e) {
            throw new ServiceLayerException(
                    "Cannot map data between DTO and Entity", e);
        } catch (DataAccessException dae) {
            throw new ServiceLayerException("Has error when save data.", dae);
        }
        return result;
    }

}
