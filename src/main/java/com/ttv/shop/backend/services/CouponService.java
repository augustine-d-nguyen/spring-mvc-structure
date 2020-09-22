/**
* @author augustine.d.nguyen
* @email augustine.d.nguyen@outlook.com
* @year 2015
*
*/
package com.ttv.shop.backend.services;

import com.ttv.shop.backend.dtos.CouponDTO;
import com.ttv.shop.common.exceptions.ServiceLayerException;

/**
 * Service provides methods to check coupon.

 *
 */
public interface CouponService extends BaseService<CouponDTO, Long> {
    /**
     * Fidn a coupon by its code.
     * @param code of a coupon
     * @return CouponDTO contain the code, null if not found.
     */
    CouponDTO findByCode(String code);
    /**
     * Save coupon to db.
     * @param coupon
     * @return coupon dto
     */
    CouponDTO save(CouponDTO coupon) throws ServiceLayerException;
}
