package time_data_structures;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import data_structures.AVLTree;
import data_structures.Hash;
import dns_resolver.IPAddress;
import dns_resolver.URL;

import java.util.HashMap;

import java.util.TreeMap;
public class TimeHashAVLTree {

	public static void main(String args[]) {
		/**
		 * Time to load AVL Tree
		 */

		long start = System.currentTimeMillis();
		AVLTree<URL,IPAddress> avlTree = new AVLTree<URL,IPAddress>();
		BufferedReader obj = null;
		try{
			obj = new BufferedReader(new FileReader("ips.txt"));
		}catch(FileNotFoundException e) { System.out.println("File not found");}
		String line = null;

		try{
			while((line = obj.readLine()) != null) {
				String [] dataSplit = line.split("\t");


				IPAddress ip = new IPAddress(dataSplit[0]);
				URL url = new URL(dataSplit[1]);
				avlTree.add(url,ip);


			}
			long stop = System.currentTimeMillis();
			System.out.println("Time to load AVL Tree: " + Long.toString(stop - start) + " ms");
		} catch(IOException e) {System.out.println("IOException");} 

		/**
		 * Time to search AVL Tree
		 */
		start = System.currentTimeMillis();
		obj = null;
		try{
			obj = new BufferedReader(new FileReader("ips.txt"));
		}catch(FileNotFoundException e) { System.out.println("File not found");}
		line = null;

		try{
			while((line = obj.readLine()) != null) {
				String [] dataSplit = line.split("\t");


				IPAddress ip = new IPAddress(dataSplit[0]);
				URL url = new URL(dataSplit[1]);
				avlTree.getValue(url);


			}
			long stop = System.currentTimeMillis();
			System.out.println("Time to search AVL Tree: " + Long.toString(stop - start) + " ms");
		} catch(IOException e) {System.out.println("IOException");} 
		avlTree = null;

		/**
		 * Time to load Hash
		 */
		start = System.currentTimeMillis();
		Hash<URL,IPAddress> hash = new Hash<URL,IPAddress>(4);
		obj = null;
		try{
			obj = new BufferedReader(new FileReader("ips.txt"));
		}catch(FileNotFoundException e) { System.out.println("File not found");}
		line = null;

		try{
			while((line = obj.readLine()) != null) {
				String [] dataSplit = line.split("\t");


				IPAddress ip = new IPAddress(dataSplit[0]);
				URL url = new URL(dataSplit[1]);
				hash.add(url,ip);


			}
			long stop = System.currentTimeMillis();
			System.out.println("Time to load Hash: " + Long.toString(stop - start) + " ms");
		} catch(IOException e) {System.out.println("IOException");} 



		/**
		 * Time to search Hash
		 */
		start = System.currentTimeMillis();
		obj = null;
		try{
			obj = new BufferedReader(new FileReader("ips.txt"));
		}catch(FileNotFoundException e) { System.out.println("File not found");}
		line = null;

		try{
			while((line = obj.readLine()) != null) {
				String [] dataSplit = line.split("\t");


				IPAddress ip = new IPAddress(dataSplit[0]);
				URL url = new URL(dataSplit[1]);
				hash.getValue(url);


			}
			long stop = System.currentTimeMillis();
			System.out.println("Time to search Hash: " + Long.toString(stop - start) + " ms");
		} catch(IOException e) {System.out.println("IOException");} 
		hash = null;
		/**
		 * Time to load Java API Hash
		 */
		start = System.currentTimeMillis();
		HashMap<URL,IPAddress> newHash = new HashMap<URL,IPAddress>();
		obj = null;
		try{
			obj = new BufferedReader(new FileReader("ips.txt"));
		}catch(FileNotFoundException e) { System.out.println("File not found");}
		line = null;

		try{
			while((line = obj.readLine()) != null) {
				String [] dataSplit = line.split("\t");


				IPAddress ip = new IPAddress(dataSplit[0]);
				URL url = new URL(dataSplit[1]);
				newHash.put(url,ip);


			}
			long stop = System.currentTimeMillis();
			System.out.println("Time to load Java API Hash: " + Long.toString(stop - start) + " ms");
		} catch(IOException e) {System.out.println("IOException");} 

		/**
		 * Time to search Java API Hash
		 */

		start = System.currentTimeMillis();
		obj = null;
		try{
			obj = new BufferedReader(new FileReader("ips.txt"));
		}catch(FileNotFoundException e) { System.out.println("File not found");}
		line = null;

		try{
			while((line = obj.readLine()) != null) {
				String [] dataSplit = line.split("\t");


				IPAddress ip = new IPAddress(dataSplit[0]);
				URL url = new URL(dataSplit[1]);
				newHash.get(url);


			}
			long stop = System.currentTimeMillis();
			System.out.println("Time to search Java API Hash: " + Long.toString(stop - start) + " ms");
		} catch(IOException e) {System.out.println("IOException");} 
		newHash = null;
		/**
		 * Time to load Java API Tree
		 */
		start = System.currentTimeMillis();
		TreeMap<URL,IPAddress> tree = new TreeMap<URL,IPAddress>();
		obj = null;
		try{
			obj = new BufferedReader(new FileReader("ips.txt"));
		}catch(FileNotFoundException e) { System.out.println("File not found");}
		line = null;

		try{
			while((line = obj.readLine()) != null) {
				String [] dataSplit = line.split("\t");


				IPAddress ip = new IPAddress(dataSplit[0]);
				URL url = new URL(dataSplit[1]);
				tree.put(url,ip);


			}
			long stop = System.currentTimeMillis();
			System.out.println("Time to load Java API Tree: " + Long.toString(stop - start) + " ms");
		} catch(IOException e) {System.out.println("IOException");} 

		/**
		 * Time to search Java API Tree
		 */
		start = System.currentTimeMillis();
		obj = null;
		try{
			obj = new BufferedReader(new FileReader("ips.txt"));
		}catch(FileNotFoundException e) { System.out.println("File not found");}
		line = null;

		try{
			while((line = obj.readLine()) != null) {
				String [] dataSplit = line.split("\t");


				IPAddress ip = new IPAddress(dataSplit[0]);
				URL url = new URL(dataSplit[1]);
				tree.get(url);


			}
			long stop = System.currentTimeMillis();
			System.out.println("Time to search Java API Tree: " + Long.toString(stop - start) + " ms");
		} catch(IOException e) {System.out.println("IOException");} 
		tree = null;
	} 


}