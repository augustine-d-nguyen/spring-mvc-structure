/**
* @author augustine.d.nguyen
* @email augustine.d.nguyen@outlook.com
* @year 2015
*
*/
package com.ttv.shop.backend.services.impl;
import static com.ttv.shop.common.utilities.DataMapper.doMap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.query.BooleanBuilder;
import com.ttv.shop.backend.daos.ProductDAO;
import com.ttv.shop.backend.dtos.ProductDTO;
import com.ttv.shop.backend.entities.Product;
import com.ttv.shop.backend.entities.QProduct;
import com.ttv.shop.backend.services.ProductService;

/**
 * A implementation of ProductService

 *
 */
@Service("ProductService")
public class ProductServiceImpl implements ProductService {

    /**
     * Serial number.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Data access object for Product table.
     */
    @Autowired
    private ProductDAO productDAO;
    
    /* (non-Javadoc)
     * @see com.ttv.shop.backend.services.ProductService#findOne(java.lang.Long)
     */
    @Override
    public ProductDTO findOne(Long id) {
        ProductDTO result = null;
        if (null != id) {
            result = doMap(productDAO.findOne(id), ProductDTO.class);
        }
        return result;
    }

    /* (non-Javadoc)
     * @see com.ttv.shop.backend.services.ProductService#findAll()
     */
    @Override
    public List<ProductDTO> findAll() {
        return doMap(productDAO.findAll(), ProductDTO.class);
    }

    /* (non-Javadoc)
     * @see com.ttv.shop.backend.services.ProductService#validateStock(java.lang.Long, java.lang.Integer)
     */
    @Override
    @Transactional
    public boolean validateStock(Long productId, Integer quantity) {
        
        // - Check input parameter
        if (null == productId || null == quantity) {
            return false;
        }
        
        // - QueryDsl
        QProduct qProduct = QProduct.product;
        BooleanBuilder conditions = new BooleanBuilder();
        
        // - Build conditions
        if (null != productId) {
            conditions.and(qProduct.id.eq(productId));
        }
        if (null != quantity) {
            conditions.and(qProduct.stockQuantity.goe(quantity));
        }
        // - 
        return (null != productDAO.findOne(conditions));
    }
    
    /* (non-Javadoc)
     * @see com.ttv.shop.backend.services.ProductService#reduceProductStock(java.lang.Long, java.lang.Integer)
     */
    @Override
    @Transactional
    public boolean reduceProductStock(Long productId, Integer quantity) {
        if (validateStock(productId, quantity)) {
            Product p = productDAO.findOne(productId);
            p.setStockQuantity(p.getStockQuantity() - quantity);
            productDAO.save(p);
            return true;
        }
        return false;
    }

}
