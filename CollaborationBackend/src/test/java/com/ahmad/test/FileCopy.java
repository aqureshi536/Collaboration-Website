package com.ahmad.test;

import java.io.FileReader;
import java.io.FileWriter;

public class FileCopy {

	
	public static void main(String[] args){
		
		char [] c = new char[128];
		int cLen  = c.length;
		try(FileReader fr = new FileReader("E:\\");
				FileWriter fw = new FileWriter("E:\\")){
			int count  = 0;
			int read = 0;
			while((read = fr.read(c))!=1){_
				
			}
		}
	}
}
