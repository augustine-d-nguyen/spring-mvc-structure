/**
* @author augustine.d.nguyen
* @email augustine.d.nguyen@outlook.com
* @year 2015
*
*/
package com.ttv.shop.backend.services;

import com.ttv.shop.backend.dtos.UserDTO;

/**
 * Service supplies ways to interact with User.

 *
 */
public interface UserService extends BaseService<UserDTO, Long>{
    
    /**
     * Find a user with its username.
     * @param <code>username</code> username of a user.
     * @return UserDTO entity mapping with input username. 
     * <code>NULL</code> value return if not found.
     */
    UserDTO findByUserName(final String username);

}
