/**
* @author augustine.d.nguyen
* @email augustine.d.nguyen@outlook.com
* @year 2015
*
*/
package com.ttv.shop.backend.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * User entity maps with
 *      <code>tbl_user</code> table.

 *
 */
@Entity
@Table(name = "tbl_user", schema = "probation_test")
public class User extends BaseEntity {

    /**
     * Serial version number.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Mapping with column <code>user_name</code>.
     */
    @Column(name = "user_name", nullable = false)
    private String userName;
    
    /**
     * Mapping with column <code>password</code>.
     */
    @Column(nullable = false)
    private String password;
    
    /**
     * Mapping with column <code>first_name</code>.
     */
    @Column(name = "first_name", nullable = false)
    private String firstName;
    
    /**
     * Mapping with column <code>last_name</code>.
     */
    @Column(name = "last_name", nullable = false)
    private String lastName;
    
    /**
     * Mapping with column <code>isactive</code>.
     */
    @Column(name = "isactive", nullable = false)
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
     * @return the firstName
     */
    public final String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public final void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public final String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public final void setLastName(String lastName) {
        this.lastName = lastName;
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
