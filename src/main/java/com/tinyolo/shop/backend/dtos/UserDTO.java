/**
* @author augustine.d.nguyen
* @email augustine.d.nguyen@outlook.com
* @year 2015
*
*/
package com.ttv.shop.backend.dtos;



/**
 * Data Transfer Object of User entity.

 *
 */
public class UserDTO extends BaseDTO {
    /**
     * User name of this user.
     */
    private String userName;
    
    /**
     * Password of this user.
     */
    private String password;
    
    /**
     * Status of this user.
     */
    private Integer isActive;

    /**
     * @return the userName
     */
    public final String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public final void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public final String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public final void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the isActive
     */
    public final Integer getIsActive() {
        return isActive;
    }

    /**
     * @param isActive the isActive to set
     */
    public final void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }
}
