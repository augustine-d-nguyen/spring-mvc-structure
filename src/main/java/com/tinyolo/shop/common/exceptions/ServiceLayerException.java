/**
* @author augustine.d.nguyen
* @email augustine.d.nguyen@outlook.com
* @year 2015
*
*/
package com.ttv.shop.common.exceptions;

/**
 * Exception for service layer.
 * This exception will wrap all exceptions occurs in service layer.

 *
 */
public class ServiceLayerException extends Exception {
    
    /**
     * Serial number.
     */
    private static final long serialVersionUID = 1L;

    
    /**
     * Default constructor.
     */
    public ServiceLayerException() {
        
    }
    
    /**
     * Constructor with message.
     * 
     * @param message of this.
     */
    public ServiceLayerException(String message) {
        super(message);
    }
    
    /**
     * Constructor with parent exception.
     * @param parent of this exception.
     */
    public ServiceLayerException(Exception parent) {
        super(parent);
    }
    
    /**
     * Constructor with message and parent exception.
     * @param message of this.
     * @param parent of this.
     */
    public ServiceLayerException(String message, Exception parent) {
        super(message, parent);
    }
    
}
