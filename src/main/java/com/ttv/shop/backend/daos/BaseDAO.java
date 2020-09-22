/**
* @author augustine.d.nguyen
* @email augustine.d.nguyen@outlook.com
* @year 2015
*
*/
package com.ttv.shop.backend.daos;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Base Data Access Object.

 *
 */
@NoRepositoryBean
public interface BaseDAO<T, ID extends Serializable> 
    extends JpaRepository<T, ID>, QueryDslPredicateExecutor<T> {

}
