/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.depaul.cdm.se.inventory.service;

import edu.depaul.cdm.se.inventory.exception.ProductExistsException;
import edu.depaul.cdm.se.inventory.exception.ProductNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author sebonzo
 */
@Remote
public interface ProductServiceRemote {
    

    public Integer newProduct(String name, String description, BigDecimal price, Integer available, Integer manufacturerId) throws ProductExistsException;

    public Integer updateProduct(Integer productId,String name, String description, BigDecimal price, Integer available, Integer manufacturerId)
            throws ProductNotFoundException;
    
    public Integer deleteProduct(Integer productId)
            throws ProductNotFoundException; 
    
//    public Integer remove(Integer productId, Integer amount)
//            throws NoProductsLeftException, ProductNotFoundException;
//
//    public Integer add(Integer productId, Integer amount)
//            throws ProductNotFoundException;
    
    public List getAllProducts();

}
