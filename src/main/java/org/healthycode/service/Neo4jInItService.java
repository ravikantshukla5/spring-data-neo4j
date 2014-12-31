package org.healthycode.service;

/**
 * @author Ravikant
 * Service for initializing Neo4J Database with sample data using you can use this service for 
 * initializing the resources
 */
 
public interface Neo4jInItService {
	/**
	 * This method is used to delete existing users and creating new sample users you can use this for 
	 * resource intialization also
	 */
	void init();

}
