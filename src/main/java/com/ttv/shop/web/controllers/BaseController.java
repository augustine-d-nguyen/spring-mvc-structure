/**
* @author augustine.d.nguyen
* @email augustine.d.nguyen@outlook.com
* @year 2015
*
*/
package com.ttv.shop.web.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Base controller

 *
 */
public abstract class BaseController {
    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}
