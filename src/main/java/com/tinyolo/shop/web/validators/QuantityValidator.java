/**
* @author augustine.d.nguyen
* @email augustine.d.nguyen@outlook.com
* @year 2015
*
*/
package com.ttv.shop.web.validators;

import static com.ttv.shop.common.utilities.NumberUtil.isGreaterZero;
import static com.ttv.shop.common.utilities.NumberUtil.isNumber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ttv.shop.backend.dtos.ProductDTO;
import com.ttv.shop.backend.models.ProductCart;
import com.ttv.shop.backend.services.ProductService;

/**

 *
 */
public class QuantityValidator implements Validator {
    
    /**
     * Service provides method to validate quantity of product.
     */
    @Autowired
    private ProductService service;
    /* (non-Javadoc)
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return ProductCart.class.equals(clazz);
    }

    /* (non-Javadoc)
     * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
     */
    @Override
    public void validate(Object target, Errors errors) {
        
        ProductCart productCart = (ProductCart) target;
        String quantity = productCart.getQuantity();
        Long productId = productCart.getProductId();
        
        // - Case 1: Invalid number
        if (!isNumber(quantity)) {
            errors.reject("error.quantity.invalid.number");
        } else
        // - Case 2: Less than zero
        if (!isGreaterZero(quantity)) {
            errors.reject("error.quantity.zero.number");
        } else
        // - Case 3: Over stock
        if (!service.validateStock(productId, Integer.valueOf(quantity))) {
            ProductDTO product = service.findOne(productId);
            Object[] params = null;
            if (null != product) {
                params = new Object[]{String.valueOf(product.getName())};
            }
            errors.reject("error.quantity.out.of.stock", params, "Exceeds maximum of stock quantity");
        }
    }

}
