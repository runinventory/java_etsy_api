package com.adji.etsy;



/**
 * An example implementation of the IEtsyApiParams interface that simply returns the strings.
 * Typically this class will be implemented as a singleton that reads the parameters from the
 * database or a configuration file when instantiated the first time and then stores the results
 * in static variables.
 */
public class EtsyApiParamsSimple implements IEtsyApiParams {
	
	// the singleton instance
	private final static EtsyApiParamsSimple SingleInstance = new EtsyApiParamsSimple();
	
	/**
	 * Private constructor - force to use the getInstance method - in most cases 
	 * this class will be singleton!
	 */
	private EtsyApiParamsSimple() {}
	
	public static EtsyApiParamsSimple getInstance() {
		return SingleInstance;
	}
	
	// ************************************************************************
	// INTERFACE IMPLEMENTATION
	
	@Override
	public String getEtsyApiPublicEntry() {
		return "http://openapi.etsy.com/v2/sandbox/public/";
	}
	
	@Override
	public String getEtsyApiPrivateEntry() {
		return "http://openapi.etsy.com/v2/sandbox/private/";
	}
	
	@Override
	public String getEtsyApiKey() {
		return "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
	}
	
	@Override
	public String getEtsySharedSecret() {
		return "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
	}
}


