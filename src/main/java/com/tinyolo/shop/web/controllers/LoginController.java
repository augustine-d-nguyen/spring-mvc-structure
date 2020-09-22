/**
* @author augustine.d.nguyen
* @email augustine.d.nguyen@outlook.com
* @year 2015
*
*/
package com.ttv.shop.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller handles login request.

 *
 */
@Controller
public class LoginController extends BaseController {
    /**
     * Handle login request.
     * @param error input param when fail to authenticate
     * @param logout input param when user logout. 
     * @return ModelAndView to generate pages.
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error, 
                                @RequestParam(value = "logout", required = false) String logout, 
                                @RequestParam(value = "timeout", required = false) String timeout,
                                SessionStatus status) {
        ModelAndView model = new ModelAndView();
        // - Case 1: authentication fails.
        if (null != error) {
            model.addObject("error", "Invalid username and password!");
        }
        // - Case 2: user logs out.
        if (null != logout) {
            model.addObject("logout", "Log out successfully.");
            // - Clear session
            status.setComplete();
        }
        // - Case 3: session is timeout.
        if (null != timeout) {
            model.addObject("timeout", "Your session is time out!");
        }
        // - Case 3: authenticated.
        model.setViewName("login");
        return model;
    }
}
