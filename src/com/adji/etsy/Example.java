package com.adji.etsy;


import com.adji.etsy.EtsyResult.EtsyResultsIterator;
import com.google.gson.reflect.TypeToken;


/**
 * Example shows how to use the EtsyApi and EtsyResult classes to call the Etsy api V2.
 * The example calls the getUser method and the findAllFeaturedListing calls.
 * 
 * 
 * All other methods can be called in the same way:
 * 
 * 1. Create a result class in which include all the fields from the response that are interesting
 * 2. Write a wrapper call that will invoke the makeCall method from EtsyApi class
 * 
 */
public class Example {
	
	
	// ********************************************************************************************************
	// getUser
	
	/**
	 * Result for get users call.
	 * http://developer.etsy.com/docs/resource_user#getuser
	 * 
	 * Included are only the parameters that are interesting for the caller.
	 * The names of the fields must correspond to the names in the JSON reply.
	 */
	public static class ResultGetUser {
		private Long user_id;
		private String login_name;
		
		public Long getUser_id() {
			return user_id;
		}
		public String getLogin_name() {
			return login_name;
		}
	}
	
	/**
	 * getUser call wrapper
	 */
	private static ResultGetUser getUser(String id) {
		EtsyResult<ResultGetUser> etsyResult = 
			new EtsyApi(EtsyApiParamsSimple.getInstance()).makeCall(new TypeToken<EtsyResult<ResultGetUser>>() {}, "users/" + id);
		if ((etsyResult == null) || (etsyResult.getCount() == 0)) return null;
		else return etsyResult.getResult(0);
	}
	
	// ********************************************************************************************************
	// findAllFeaturedListing (shows example of additional call parameters and also iterating an array of results)
	
	/**
	 * Result of findAllFeaturedListing
	 * http://developer.etsy.com/docs/resource_featuredlisting#findallfeaturedlisting
	 */
	public static class ResultFindAllFeaturedListing {
		private long listing_id;
		
		public long getListing_id() {
			return listing_id;
		}
	}
	
	/**
	 * findAllFeaturedListing call wrapper
	 */
	private static EtsyResultsIterator<ResultFindAllFeaturedListing> findAllFeaturedListing(int offset, int limit) {
		EtsyResult<ResultFindAllFeaturedListing> r = 
			new EtsyApi(EtsyApiParamsSimple.getInstance()).makeCall(new TypeToken<EtsyResult<ResultFindAllFeaturedListing>>() {},
						  											"homepages/listings/", 
						  											"offset=" + offset, "limit=" + limit);  // additional parameters
		if (r == null) return null;
		else return r.getIterator();
	}
	
	// ********************************************************************************************************
	
	/**
	 * Example main, calls getUser and displays the user id then calls findAllFeaturedListing and displays the ids of the listings
	 * 
	 * ********************************************************************************************************
	 * BEFORE RUNNING THE EXAMPLE EDIT THE getEtsyApiKey() METHOD IN EtsyApiParamsSimple.java WITH YOUR API KEY
	 * ********************************************************************************************************
	 * 
	 */
	public static void main(String [] args) {
		
		// call getUser
		ResultGetUser user = getUser("runinventory");
		if (user != null) {
			System.out.println("User id is: " + user.getUser_id());
		}
		
		// call getFeaturedListings
		EtsyResultsIterator<ResultFindAllFeaturedListing> iterator = findAllFeaturedListing(0, 10);
		if (iterator != null) {
			while(iterator.hasNext()) {
				System.out.println("Listing id is: " + iterator.next().getListing_id());
			}
		}
	}
}


