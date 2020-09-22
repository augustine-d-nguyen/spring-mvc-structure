/**
* @author augustine.d.nguyen
* @email augustine.d.nguyen@outlook.com
* @year 2015
*
*/
package com.ttv.shop.common.utilities;

import static org.apache.commons.lang3.StringUtils.isNumeric;

/**
 * Numeric utilities.

 *
 */
public final class NumberUtil {
    
    /**
     * One number.
     */
    private static final double ONE = 1.0D;
    
    /**
     * Default constructor.
     */
    private NumberUtil() { };
    /**
     * Check a number in string form.
     * @param number to check
     * @return true if the string is a number, else false
     */
    public static boolean isNumber(final String number) {
        return isNumeric(number);
    }
    /**
     * Check a number in string form is greater than zero.
     * @param number to check
     * @return true if a string is greater than zero, else false
     */
    public static boolean isGreaterZero(final String number) {
        boolean result = false;
        try {
            result = (ONE == Math.signum(Double.parseDouble(number)));
        } catch (NullPointerException ne) {
            result = false;
        } catch (NumberFormatException nfe) {
            result = false;
        }
        return result;
    }
}
