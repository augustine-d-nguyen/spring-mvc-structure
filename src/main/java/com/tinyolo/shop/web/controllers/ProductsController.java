/**
* @author augustine.d.nguyen
* @email augustine.d.nguyen@outlook.com
* @year 2015
*
*/
package com.ttv.shop.web.controllers;

import java.util.List;

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
import org.springframework.web.servlet.ModelAndView;

import com.ttv.shop.backend.dtos.ProductDTO;
import com.ttv.shop.backend.models.ProductCart;
import com.ttv.shop.backend.models.ShoppingCart;
import com.ttv.shop.backend.services.ProductService;

/**
 * Controller handles actions on product page.

 *
 */
@Controller
@SessionAttributes("shoppingCart")
public class ProductsController extends BaseController {
    @Autowired
    private ProductService service;
    
    @Autowired
    @Qualifier("quantityValidator")
    private Validator validator;
    
    @InitBinder("productCart")
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
    
    /**
     * Load all products and put to model.
     * @param model to put data
     */
    private void loadProductsPage(ModelAndView model) {
        // - Load all products
        List<ProductDTO> products = service.findAll();
        // - Set data
        model.addObject("products", products);
        // - Set view
        model.setViewName("products");
    }
    /**
     * List all products to view.
     * Prepare Orders model.
     * @return model.
     */
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ModelAndView loadAllProducts() {
        // - Model
        ModelAndView model = new ModelAndView();
        loadProductsPage(model);
        return model;
    }
    
    /**
     * Add products to cart.
     * @param productId product id
     * @param quantity of order
     * @return model
     */
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ModelAndView addToCart(@ModelAttribute("productCart") @Validated ProductCart productCart, 
            BindingResult bindingResult, @ModelAttribute("shoppingCart") ShoppingCart shoppingCart) {
        // - Model
        ModelAndView model = new ModelAndView();
        // - Validate data from submit form
        if (bindingResult.hasErrors()) {
            loadProductsPage(model);
        } else {
            // - Set order of product to shopping cart.
            shoppingCart.addOrder(productCart);
            // - Set view name
            model.setViewName("redirect:cart");
        }
        
        return model;
    }
}
