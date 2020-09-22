/**
* @author augustine.d.nguyen
* @email augustine.d.nguyen@outlook.com
* @year 2015
*
*/
package com.ttv.shop.web.controllers;

import static org.apache.commons.lang3.StringUtils.isBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.ttv.shop.backend.dtos.CouponDTO;
import com.ttv.shop.backend.dtos.OrderDTO;
import com.ttv.shop.backend.dtos.UserDTO;
import com.ttv.shop.backend.models.MutableString;
import com.ttv.shop.backend.models.ShoppingCart;
import com.ttv.shop.backend.services.CouponService;
import com.ttv.shop.backend.services.OrderService;
import com.ttv.shop.backend.services.UserService;
import com.ttv.shop.common.exceptions.ServiceLayerException;

/**
 * Controller controls request to cart page

 *
 */
@Controller
@SessionAttributes("shoppingCart")
public class CartController extends BaseController {
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private CouponService couponService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    @Qualifier("couponValidator")
    private Validator validator;
    
    @InitBinder("coupon")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }
    
    /**
     * Create a session model for containing orders.
     * @return ShoppingCart model.
     */
    @ModelAttribute("shoppingCart")
    private ShoppingCart createShoppingCart() {
        return new ShoppingCart();
    }
    
    @ModelAttribute("coupon")
    private CouponDTO createCouponDTO() {
        return new CouponDTO();
    }
    @ModelAttribute("address")
    private MutableString createAddress() {
        return new MutableString();
    }
    
    /**
     * Calculate order from shopping cart.
     * @param shoppingCart to calculate order
     * @param errors when calculate
     * @param model
     */
    private OrderDTO calculateOrder(ShoppingCart shoppingCart, 
            BindingResult errors,
            ModelAndView model) {
        
        OrderDTO order = null;
        try {
            // - Get order from shoppingCart
            order = orderService.buildOrderFromCart(shoppingCart);
        } catch (ServiceLayerException e) {
            order = new OrderDTO();
            errors.reject("error.building.order", 
                    new Object[]{e.getMessage()}, 
                    "Has error when build order.");
        }
        
        // - Set data
        model.addObject("order", order);
        
        // - Set view name
        model.setViewName("cart");
        
        return order;
    }
    /**
     * Get all orders in shopping cart to present.
     * @param shoppingCart contains orders
     * @return model
     */
    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public ModelAndView getAllOrders(@ModelAttribute("shoppingCart") @Validated ShoppingCart shoppingCart, BindingResult errors) {
        // - Model
        ModelAndView model = new ModelAndView();
        
        calculateOrder(shoppingCart, errors, model);
        
        // - Return model
        return model;
    }

    /**
     * Handle when user input coupon.
     * @param coupon
     * @param shoppingCart
     * @param errors
     * @return
     */
    @RequestMapping(value = "/cart", method = RequestMethod.POST, params = "checkCoupon")
    public ModelAndView useCoupon(@ModelAttribute("coupon") @Validated CouponDTO coupon, BindingResult bindingResult, 
            @ModelAttribute("shoppingCart") ShoppingCart shoppingCart) {
        // - Model
        ModelAndView model = new ModelAndView();
        // - Not errors
        if (!bindingResult.hasErrors()) {
            CouponDTO couponDTO = couponService.findByCode(coupon.getCode());
            shoppingCart.setCoupon(couponDTO);
        } else {
            shoppingCart.setCoupon(null);
        }
        calculateOrder(shoppingCart, bindingResult, model);
        
        // - Return model
        return model;
    }
    
    /**
     * Register order to db
     * @param order
     * @return
     */
    @RequestMapping(value = "/cart", method = RequestMethod.POST, params = "register")
    public ModelAndView registerOrder(@ModelAttribute("address") @Validated MutableString address, BindingResult errors,
            @ModelAttribute("shoppingCart") ShoppingCart shoppingCart, SessionStatus status) {
        // - Model
        ModelAndView model = new ModelAndView();
        // - Recalculate
        OrderDTO calOrder = calculateOrder(shoppingCart, errors, model);
        // - Validate
        if (null == address || isBlank(address.getContent())) {
                errors.rejectValue("content", "error.address.empty");
        } else {
            UserDTO user = userService.findByUserName(getCurrentUsername());
            calOrder.setUser(user);
            calOrder.setAddressInfo(address.getContent());
            try {
                orderService.registerOrder(calOrder);
            } catch (ServiceLayerException e) {
                errors.reject("error.saving.order");
            }
            if (!errors.hasErrors()) {
                status.setComplete();
                model.setViewName("redirect:products");
            }
        }
        // - Return model
        return model;
    }
}
