package dns_resolver;

/**
 * The IPAddress is using iIPv4 and has dotted-decimal notation, with the network, two subnets, 
 * and host separated by periods. For example, the IP address 130.191.226.146 has 
 * a network of 130, a subnet of 191, the second subnet is 226, and the host address is 146.
 * 
 * Your IPAddress class should accept a string of dotted-decimal IPAddresses in the constructor
 * and separate them into the components. 
 *
 * Note: The templates for some methods have been provided, but you should consider which additional
 * methods to add to this class.
 * 
 * @see <a href="https://en.wikipedia.org/wiki/IP_address#IPv4_addresses">Wikipedia IPv4 addresses</a>
 * @author CS310
 *
 */

public class IPAddress implements Comparable<IPAddress> {

	int network;
	int subnet;
	int subnet2;
	int host;
	String ip;

	/**
	 * The constructor for the IPAddress class
	 * 
	 * @param ip the dotted-decimal IP address
	 */
	 public IPAddress(String ip) {
		String dataIP[] = ip.split("\\.");
		
		this.ip = ip;
		network = Integer.parseInt(dataIP[0]);
		subnet = Integer.parseInt(dataIP[1]);
		subnet2 = Integer.parseInt(dataIP[2]);
		host = Integer.parseInt(dataIP[3]);
		
	}


	
	/** 
	 * Computes an integer based off of IP
	 * @return integer
	 */
	@Override
	public int hashCode() {
		return network + subnet + subnet2 + host;
	}

	
	/** Checks if strings are equal
	 * @param obj
	 * @return true or false
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj.toString().equals(this.toString()))
			return true;
		return false;
	}

	@Override
	public String toString() {
		return network + "." + subnet + "." + subnet2 + "." + host;
	}

	/** Compares the IP
	 * @param num
	 * @return integer -1,0,1
	 */
	@Override
	public int compareTo(IPAddress num) {
		
		return this.ip.compareTo(num.ip);
	}

} 
