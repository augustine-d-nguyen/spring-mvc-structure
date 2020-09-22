/**
* @author augustine.d.nguyen
* @email augustine.d.nguyen@outlook.com
* @year 2015
*
*/
package com.ttv.shop.web.validators;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ttv.shop.backend.dtos.CouponDTO;
import com.ttv.shop.backend.services.CouponService;

/**
 * Validator validates input coupon code from user.

 *
 */
public class CouponValidator implements Validator {
    
    /**
     * Coupon service.
     */
    @Autowired
    private CouponService service;
    
    /* (non-Javadoc)
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return CouponDTO.class.equals(clazz);
    }

    /* (non-Javadoc)
     * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
     */
    @Override
    public void validate(Object target, Errors errors) {
        CouponDTO dto = (CouponDTO)target;
        String couponCode = dto.getCode();
        if (isBlank(couponCode)) {
            errors.rejectValue("code", "error.coupon.empty");
        } else {
            CouponDTO coupon = service.findByCode(couponCode.trim());
            if (null == coupon) {
                errors.rejectValue("code", "error.coupon.not.exist");
            } else {
                if (isUsed(coupon)) {
                    errors.rejectValue("code", "error.coupon.is.used");
                } else {
                    if (isExpired(coupon)) {
                        errors.rejectValue("code", "error.coupon.is.expired");
                    }
                }
            }
        }
    }
    /**
     * Check this coupon has already used or not.
     * @param coupon to check.
     * @return true if used
     */
    private boolean isUsed(final CouponDTO coupon) {
        return 0 < coupon.getStatus();
    }
    /**
     * Check expired date of this coupon.
     * @param coupon to check
     * @return true if expired, else false.
     */
    private boolean isExpired(final CouponDTO coupon) {
        boolean result = false;
        Calendar now = Calendar.getInstance();
        Date nowDate = now.getTime();
        Date couponExpiredDate = coupon.getExpiredDate();
        if (nowDate.after(couponExpiredDate)) {
            result = true;
        }
        return result;
    }

}
