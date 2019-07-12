package dns_resolver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import data_structures.Hash;
import data_structures.HashI;
import time_data_structures.FileFormatException;

import java.io.FileReader;

/**
 * The LoadInternetAddresses class should take a filename as a string, uses BufferedReader
 * to read the file, split the lines into URLs and IPAddresses, and create the appropriate
 * objects. It should add those objects to a hash, and finally, after reading the whole file
 * it should return the instance of the hash.
 * 
 * If there is an error with the file format, you should throw a new FileFormatException error
 * with an appropriate message.
 *  
 * @author 
 *
 */
public class LoadInternetAddresses {

	
	
	/**
	 * @param filename
	 * @return
	 * @throws FileFormatException
	 */
	public HashI<URL, IPAddress> load_addresses(String filename) throws FileFormatException {
		Hash<URL,IPAddress> hashTable = new Hash<URL,IPAddress>(3000);
		BufferedReader obj = null;
		try{
			obj = new BufferedReader(new FileReader(filename));
		}catch(FileNotFoundException e) { System.out.println("File not found");}
		String line = null;
		
		try{
			while((line = obj.readLine()) != null) {
				String [] dataSplit = line.split("\t");
				if(dataSplit.length != 2)
					throw new FileFormatException();
				else {
					IPAddress ip = new IPAddress(dataSplit[0]);
					URL url = new URL(dataSplit[1]);
					hashTable.add(url,ip);
				}
					
			}
		} catch(IOException e) {System.out.println("IOException");}
			
		
		return hashTable;
		
	}
}
