package dns_resolver;

/**
 * A URL Object is a representation of the URL that we have been giving. 
 * It knows how to compare URLs!
 *
 * Note: The templates for some methods have been provided, but you should consider which additional
 * methods to add to this class.
 * 
 * @author nathanazoulay
 *
 */
public class URL implements Comparable<URL> {
	private String url;
	
	/**
	 * @return int hashCode
	 */	
	@Override
	public int hashCode() {
		return url.hashCode();
	}
	
	/** Constructor
	 * @param obj
	 */
	public URL(String obj) {
		url = obj;
	}
	
	/**
	 * @return String url
	 */
	public String getURL() {
		return url;
	}
	
	/** Compares urls
	 * @param obj
	 * @return int
	 */
	@Override
	public int compareTo(URL obj) {
		return url.compareTo(obj.getURL());
	}
	
	public String toString() {
		return url;
	}
}
