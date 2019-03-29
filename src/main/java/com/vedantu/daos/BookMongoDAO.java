package com.vedantu.daos;

import java.util.Collection;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.vedantu.models.BookMongo;
import com.vedantu.models.CustomerMongo;
import com.vedantu.utils.LogFactory;

@Service
public class BookMongoDAO extends AbstractMongoDAO{
	 @Autowired
	    private LogFactory logFactory;

	    @SuppressWarnings("static-access")
	    private Logger logger = logFactory.getLogger(BookMongoDAO.class);

	    @Autowired
	    private MongoClientFactory mongoClientFactory;

	    @Override
	    protected MongoOperations getMongoOperations() {
	        return mongoClientFactory.getMongoOperations();
	    }

	    public BookMongoDAO() {
	        super();
	    }

	    public void create(BookMongo bookMongo) {
	        try {
	        	logger.info("customer "+bookMongo);
	            saveEntity(bookMongo);
	        } catch (Exception ex) {
	            throw new RuntimeException(
	                    "ContentInfoUpdateError : Error updating the content info " + bookMongo, ex);
	        }
	    }

	    public BookMongo getById(String id) {
	    	BookMongo challenge = null;
	        try {
	            challenge = (BookMongo) getEntityById(id, BookMongo.class);
	        } catch (Exception ex) {
	            throw new RuntimeException("ContentInfoFetchError : Error fetch the content info with id " + id, ex);
	        }
	        return challenge;
	    }

	    public List<CustomerMongo> getStudyEntryItems(List<String> cuid) {
	        Query query;
	        query = new Query();
	        query.addCriteria(Criteria.where("cuid").in(cuid));
	        return runQuery(query, CustomerMongo.class);
	    }

	    public CustomerMongo getStudyEntryItem(String cuid) {
	        Query query = new Query();
	        query.addCriteria(Criteria.where(cuid).is(cuid));
	        logger.info(query);
	        return findOne(query, CustomerMongo.class);
	    }
	    
	    public void update(BookMongo p ,String callingUserId) {
      	  if (p != null) {
          logger.info("update: " + p.toString());
          saveEntity(p, callingUserId);
      	  }
      	  
	    }
	    
	    public void delete(CustomerMongo p,String callingUserId) {
	      	  if (p != null) {
	          logger.info("delete: " + p.toString());
	          deleteEntityById(p.getId(),CustomerMongo.class);
	      	  }
	      	  
		    }
	    
	    //custom
	    public Collection<BookMongo> getAll() {
	    	return getAllEntities(BookMongo.class);
	  
		    }
	    
}
