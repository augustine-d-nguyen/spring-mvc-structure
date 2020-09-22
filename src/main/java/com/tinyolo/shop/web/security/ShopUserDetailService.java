/**
* @author augustine.d.nguyen
* @email augustine.d.nguyen@outlook.com
* @year 2015
*
*/
package com.ttv.shop.web.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ttv.shop.backend.dtos.UserDTO;
import com.ttv.shop.backend.services.UserService;

/**
 * The service provide authentication with hashing password for Spring security.

 *
 */
public class ShopUserDetailService  implements UserDetailsService, Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @Autowired
    private UserService userService;
    /* (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // - Get user from DB by its username.
        final UserDTO user = userService.findByUserName(username);
        // - Case : the user is not found.
        if (null == user) {
            //TODO:need move message to properties.
            throw new UsernameNotFoundException("Username " + username + " not found.");
        }
        // - Case : user is found.
        return createUserDetail(user);
    }
    
    /**
     * Convert User entity to UserDetail entity.
     * @param user 
     * @return UserDetail entity.
     */
    private UserDetails createUserDetail(final UserDTO user) {
        return new UserDetails() {
            
            /**
             * 
             */
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isEnabled() {
                return 0 < user.getIsActive();
            }
            
            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }
            
            @Override
            public boolean isAccountNonLocked() {
                return true;
            }
            
            @Override
            public boolean isAccountNonExpired() {
                return true;
            }
            
            @Override
            public String getUsername() {
                return user.getUserName();
            }
            
            @Override
            public String getPassword() {
                return user.getPassword();
            }
            
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
                //TODO : Get role from DB or properties
                authorities.add(new SimpleGrantedAuthority("CUSTOMER"));
                return authorities;
            }
        };
    }

}
