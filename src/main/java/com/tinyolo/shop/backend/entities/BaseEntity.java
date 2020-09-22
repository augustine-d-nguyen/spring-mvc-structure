/**
* @author augustine.d.nguyen
* @email augustine.d.nguyen@outlook.com
* @year 2015
*
*/
package com.ttv.shop.backend.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


/**
 * Base mapping entity.

 *
 */
@MappedSuperclass
public class BaseEntity implements Serializable{

    /**
     * Serialize version.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Id column.
     */
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Getter of id.
     * @return Long id of row
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter of id.
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

}
