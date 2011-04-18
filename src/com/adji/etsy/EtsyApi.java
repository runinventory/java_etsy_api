package com.adji.etsy;


import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;



/**
 * Class used to make calls to the Etsy api.
 * 
 * Two external libraries are used:
 * 
 * 1. To make JSON calls the Jersey package is used (http://jersey.java.net/).
 * 2. To parse the JSON replies and convert them to Java objects, Google GSON package is used (http://code.google.com/p/google-gson/).
 * 
 */
public class EtsyApi {
	
	private IEtsyApiParams etsyParams = null;
	
	public EtsyApi(IEtsyApiParams etsyParams) {
		this.etsyParams = etsyParams;
	}
	
	/**
	 * Call the Etsy api.
	 * 
	 * @param <T> the type of the result that corresponds to the call
	 * @param typeToken used to let GSON know how to parse the result
	 * @param callUrl the url to call
	 * @param args call parameters
	 * @return
	 */
	public <T> EtsyResult<T> makeCall(TypeToken<EtsyResult<T>> typeToken, String callUrl, String... args) {
		try {
			if (etsyParams == null) return null;
			
			String url = etsyParams.getEtsyApiPublicEntry() + callUrl + "?api_key=" + etsyParams.getEtsyApiKey();
			if (args != null) for(String arg : args) url += "&" + arg;  // call parameters
			
			// create Jersey client
			Client client = Client.create();
			WebResource entry = client.resource(url);
			
			// make Jersey call
			ClientResponse response = entry.type(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class);
			if ((response != null) && (Response.Status.OK.getStatusCode() == response.getStatus())) {
				// call was ok
				String jsonStr = response.getEntity(String.class);
				if (jsonStr != null) {
					// parse JSON with GSON
					return new Gson().fromJson(jsonStr, typeToken.getType());
				}
				else {
					// log.severe("JSON reply null");
					return null;
				}
			}
			else {
				// log.severe("HTML error on call, code: " + (response==null?"response null":""+response.getStatus()));
				return null;
			}
		}
		catch (Exception ex) {
			// log.severe("Exception: " + ex.getMessage());
			return null;
		}
	}
}


