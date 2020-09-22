/**
* @author augustine.d.nguyen
* @email augustine.d.nguyen@outlook.com
* @year 2015
*
*/
package com.ttv.shop.backend.services.impl;

import static com.ttv.shop.common.utilities.DataMapper.doMap;
import static org.apache.commons.lang3.StringUtils.isBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttv.shop.backend.daos.UserDAO;
import com.ttv.shop.backend.dtos.UserDTO;
import com.ttv.shop.backend.entities.QUser;
import com.ttv.shop.backend.services.UserService;

/**
 * The implementation of UserService.

 *
 */
@Service("UserService")
public class UserServiceImpl extends BaseServiceImpl<UserDTO, Long> implements UserService {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @Autowired
    private UserDAO userDAO;

    /* (non-Javadoc)
     * @see com.ttv.shop.backend.services.UserService#findByUserName(java.lang.String)
     */
    @Override
    public UserDTO findByUserName(String username) {
        UserDTO result = null;
        if (!isBlank(username)) {
            QUser qUser = QUser.user;
            result =  doMap(userDAO.findOne(qUser.userName.eq(username)), UserDTO.class);
        }
        return result;
    }

}
