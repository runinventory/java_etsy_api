package com.adji.etsy;


/**
 * Defines the parameters needed by EtsyApi class to make the calls.
 */
public interface IEtsyApiParams {
	
	/**
	 * @return entry to the public Etsy Api calls
	 */
	public String getEtsyApiPublicEntry();
	
	/**
	 * @return entry to the private Etsy Api calls
	 */
	public String getEtsyApiPrivateEntry();
	
	/**
	 * @return Etsy Api Key
	 */
	public String getEtsyApiKey();
	
	/**
	 * @return Etsy Api shared secret
	 */
	public String getEtsySharedSecret();
}


