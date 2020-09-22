/**
* @author augustine.d.nguyen
* @email augustine.d.nguyen@outlook.com
* @year 2015
*
*/
package com.ttv.shop.common.utilities;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

/**
 * Mapper map data from source object to destination.

 *
 */
public class DataMapper {
    
    /**
     * Default constructor.
     */
    private DataMapper() { };
    /**
     * Do map from a source object to a destination object.
     * @param source object
     * @param destinationType type of destination.
     * @return destination object.
     */
    public static final <D, S> D doMap(S source, Class<D> destinationType) {
        if (null == source) {
            return null;
        }
        return new DozerBeanMapper().map(source, destinationType);
    }
    
    /**
     * Do map from a list of source object to a list of destination object.
     * @param sources
     * @param destinationType
     * @return
     */
    public static final <D, S> List<D> doMap(final List<S> sources, Class<D> destinationType) {
        if (null == sources) {
            return null;
        }
        Mapper mapper = new DozerBeanMapper();
        List<D> destList = new ArrayList<D>();
        for(S source : sources) {
            destList.add(mapper.map(source, destinationType));
        }
        return destList;
    }
    /**
     * Create a instance of DozerBeanMapper.
     * @return Mapper instance
     */
    public static final Mapper createMapper() {
        return new DozerBeanMapper();
    }
}
