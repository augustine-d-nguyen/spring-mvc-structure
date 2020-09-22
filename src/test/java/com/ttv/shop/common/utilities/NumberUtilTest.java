/**
* @author augustine.d.nguyen
* @email augustine.d.nguyen@outlook.com
* @year 2015
*
*/
package com.ttv.shop.common.utilities;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This is unit test for class NumberUtil

 * 
 * To do list:
 * 
 * + Method isNumber
 * - should return false when input string is null.
 * - should return false when input string is empty.
 * - should return false when input string is space.
 * - should return false when input string is alphabet character.
 * - should return false when input string is a decimal number.
 * - should return false when input string is a signed number.
 * - should return true when input string is a natural number.
 * 
 * + Method isGreaterZero
 * - should return false when input string is null.
 * - should return false when input string is empty.
 * - should return false when input string is space.
 * - should return false when input string is alphabet character.
 * - should return false when input string is a negative number.
 * - should return false when input string is zero number.
 * - should return true when input string is a number be greater than zero.
 *
 */
public class NumberUtilTest {
    
    @Test
    public void isNumber_shouldReturnFalseWhenInputStringIsNull() {
        // - Given
        String input = null;
        // - When
        boolean actual = NumberUtil.isNumber(input);
        // - Then
        assertFalse(actual);
    }
    @Test
    public void isNumber_shouldReturnFalseWhenInputStringIsEmpty() {
        // - Given
        String input = "";
        // - When
        boolean actual = NumberUtil.isNumber(input);
        // - Then
        assertFalse(actual);
    }
    @Test
    public void isNumber_shouldReturnFalseWhenInputStringIsSpace() {
        // - Given
        String input = " ";
        // - When
        boolean actual = NumberUtil.isNumber(input);
        // - Then
        assertFalse(actual);
    }
    @Test
    public void isNumber_shouldReturnFalseWhenInputStringIsAlphabet() {
        // - Given
        String input = "acb";
        // - When
        boolean actual = NumberUtil.isNumber(input);
        // - Then
        assertFalse(actual);
    }
    @Test
    public void isNumber_shouldReturnFalseWhenInputStringIsDecimalNumber() {
        // - Given
        String input = "1.1";
        // - When
        boolean actual = NumberUtil.isNumber(input);
        // - Then
        assertFalse(actual);
    }
    @Test
    public void isNumber_shouldReturnFalseWhenInputStringIsSignedNumber() {
        // - Given
        String negativeInput = "-1";
        String positiveInput = "+1";
        // - When
        boolean actual1 = NumberUtil.isNumber(negativeInput);
        boolean actual2 = NumberUtil.isNumber(positiveInput);
        // - Then
        assertFalse(actual1);
        assertFalse(actual2);
    }
    @Test
    public void isNumber_shouldReturnTrueWhenInputStringIsNaturalNumber() {
        // - Given
        String input = "123";
        // - When
        boolean actual = NumberUtil.isNumber(input);
        // - Then
        assertTrue(actual);
    }
    
    @Test
    public void isGreaterZero_shouldReturnFalseWhenInputStringIsNull() {
        // - Given
        String input = null;
        // - When
        boolean actual = NumberUtil.isGreaterZero(input);
        // - Then
        assertFalse(actual);
    }
    @Test
    public void isGreaterZero_shouldReturnFalseWhenInputStringIsEmpty() {
        // - Given
        String input = "";
        // - When
        boolean actual = NumberUtil.isGreaterZero(input);
        // - Then
        assertFalse(actual);
    }
    @Test
    public void isGreaterZero_shouldReturnFalseWhenInputStringIsSpace() {
        // - Given
        String input = " ";
        // - When
        boolean actual = NumberUtil.isGreaterZero(input);
        // - Then
        assertFalse(actual);
    }
    @Test
    public void isGreaterZero_shouldReturnFalseWhenInputStringIsAlphabet() {
        // - Given
        String input = "acb";
        // - When
        boolean actual = NumberUtil.isGreaterZero(input);
        // - Then
        assertFalse(actual);
    }
    @Test
    public void isGreaterZero_shouldReturnFalseWhenInputStringIsANegativeNumber() {
     // - Given
        String input = "-1";
        // - When
        boolean actual = NumberUtil.isGreaterZero(input);
        // - Then
        assertFalse(actual);
    }
    @Test
    public void isGreaterZero_shouldReturnFalseWhenInputStringIsZeroNumber() {
     // - Given
        String input = "0";
        // - When
        boolean actual = NumberUtil.isGreaterZero(input);
        // - Then
        assertFalse(actual);
    }
    @Test
    public void isGreaterZero_shouldReturnTrueWhenInputStringIsAPositiveNumber() {
     // - Given
        String input = "1";
        // - When
        boolean actual = NumberUtil.isGreaterZero(input);
        // - Then
        assertTrue(actual);
    }
    
}
