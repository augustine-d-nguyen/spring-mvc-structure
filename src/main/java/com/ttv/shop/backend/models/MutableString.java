/**
* @author augustine.d.nguyen
* @email augustine.d.nguyen@outlook.com
* @year 2015
*
*/
package com.ttv.shop.backend.models;

/**
 * Mutable string.

 *
 */
public class MutableString {

    /**
     * Content value.
     */
    private String content;

    /**
     * @return the content
     */
    public final String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public final void setContent(final String content) {
        this.content = content;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public final boolean equals(final Object obj) {
        if (obj instanceof MutableString) {
            return content.equals(((MutableString) obj).getContent());
        }
        if (obj instanceof String) {
            return content.equals(obj);
        }
        return false;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public final int hashCode() {
        return content.hashCode();
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public final String toString() {
        return content;
    }
}
