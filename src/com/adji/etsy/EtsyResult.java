package com.adji.etsy;


import java.util.Iterator;


/**
 * Generic Etsy result type.
 * It's a Java wrapper for the Etsy JSON result (http://developer.etsy.com/docs#api_responses).
 * Accepts a generic type parameter R that corresponds to the array "results" parameter of the response.
 */
public class EtsyResult<R> {
	private Integer count;
	private String type;
	private R [] results;
	
	public int getCount() {
		return (count==null?0:count);
	}
	public String getType() {
		return type;
	}
	public R getResult(int index) {
		if ((results == null) || (results.length <= index)) return null;
		else return results[index];
	}
	
	public static class EtsyResultsIterator<R> implements Iterator<R> {
		private R [] results;
		private int index = 0;
		private EtsyResultsIterator(R [] results) {
			this.results = results;
			this.index = 0;
		}
		
		@Override
		public boolean hasNext() {
			if ((results == null) || (results.length <= index)) return false;
			else return true;
		}
		@Override
		public R next() {
			if ((results == null) || (results.length <= index)) return null;
			else {
				index++;
				return results[index-1];
			}
		}
		@Override
		public void remove() {}  // not implemented
	}
	
	public EtsyResultsIterator<R> getIterator() {
		return new EtsyResultsIterator<R>(results);
	}
}


