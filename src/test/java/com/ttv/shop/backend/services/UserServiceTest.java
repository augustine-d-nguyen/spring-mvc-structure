/**
* @author augustine.d.nguyen
* @email augustine.d.nguyen@outlook.com
* @year 2015
*
*/
package com.ttv.shop.backend.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.mysema.query.types.Predicate;
import com.ttv.shop.backend.daos.UserDAO;
import com.ttv.shop.backend.dtos.UserDTO;
import com.ttv.shop.backend.entities.User;
import com.ttv.shop.backend.services.impl.UserServiceImpl;

/**
 * This is unit test for UserService.

 * To do list:
 * + Method findByUserName
 * - Should return null when input is null.
 * - Should return null when input is empty.
 * - Should return null when input is a space.
 * - Should return null when input is not found.
 * - Should return User when found by username.
 *
 */
public class UserServiceTest {
    
    private UserService service;
    
    @Before
    public void setUp() {
        // - Create service.
        service = new UserServiceImpl();
        // - Mock dao.
        UserDAO dao = createMockUserDAO();
        // - Inject dao.
        setField(service, "userDAO", dao);
    }
    
    private UserDAO createMockUserDAO() {
        UserDAO mock = mock(UserDAO.class);
        when(mock.findOne(Mockito.any(Predicate.class))).then(new Answer<User>() {

            @Override
            public User answer(InvocationOnMock invocation) throws Throwable {
                Predicate pre = invocation.getArgumentAt(0, Predicate.class);
                if (null == pre) {
                    return null;
                } else {
                    return new User();
                }
            }
        });
        return mock;
    }

    @Test
    public void shouldReturnNullWhenInputIsNull() {
        // - Given
        // - When
        UserDTO actual = service.findByUserName(null);
        // - Then
        assertNull(actual);
    }
    @Test
    public void shouldReturnNullWhenInputIsEnpty() {
        // - Given
        // - When
        UserDTO actual = service.findByUserName("");
        // - Then
        assertNull(actual);
    }
    @Test
    public void shouldReturnNullWhenInputIsASpace() {
        // - Given
        // - When
        UserDTO actual = service.findByUserName(" ");
        // - Then
        assertNull(actual);
    }
    @Test
    public void shouldReturnNullWhenInputIsNotFound() {
        // - Test in integration test because dao using Spring JPA data
        // - , so difficult to create a mock obj
    }
    @Test
    public void shouldReturnUserWhenInputIsFound() {
        // - Given
        // - When
        UserDTO actual = service.findByUserName("CODE");
        // - Then
        assertNotNull(actual);
    }
}
