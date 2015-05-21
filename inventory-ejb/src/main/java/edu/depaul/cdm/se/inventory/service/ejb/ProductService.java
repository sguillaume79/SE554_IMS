package edu.depaul.cdm.se.inventory.service.ejb;


import edu.depaul.cdm.se.inventory.exception.ProductExistsException;
import edu.depaul.cdm.se.inventory.exception.ProductNotFoundException;
import edu.depaul.cdm.se.inventory.persistence.Manufacturer;
import edu.depaul.cdm.se.inventory.persistence.Products;
import edu.depaul.cdm.se.inventory.service.ProductServiceRemote;
import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sebonzo
 */
@Stateless
public class ProductService implements ProductServiceRemote {
    
    @PersistenceContext(unitName = "inventoryPU")
    private EntityManager entityManager;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Integer newProduct(String name, String desc, BigDecimal price, Integer available, Integer manufacturerId) throws ProductExistsException {
       Products product = new Products();
       
       Manufacturer mObj = entityManager.find(Manufacturer.class, manufacturerId);
            
       product.setAvailable(available);
       product.setManufacturerId(mObj);
       product.setProductDesc(desc);
       product.setProductPrice(price);
       product.setProductName(name);
       
       entityManager.persist(product);
       
       return product.getProductId();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
     public Integer updateProduct(Integer productId, String name, String desc, BigDecimal price, Integer available, Integer manufacturerId) throws ProductNotFoundException {
        Products product = entityManager.find(Products.class, productId);
        Manufacturer mObj = entityManager.find(Manufacturer.class, manufacturerId);
            
        product.setAvailable(available);
        product.setManufacturerId(mObj);
        product.setProductDesc(desc);
        product.setProductPrice(price);
        product.setProductName(name);
           
        entityManager.merge(product);
        
        return product.getProductId();
        
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
     public Integer deleteProduct(Integer productId) throws ProductNotFoundException {
      Products product = entityManager.find(Products.class, productId);
      
       entityManager.remove(product);
       
       return  productId;
    
    }
    
    @Override
    public List getAllProducts() {
             return entityManager.createQuery("SELECT p FROM Products p").getResultList();
  
    }
    
}
