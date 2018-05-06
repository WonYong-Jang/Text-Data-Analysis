package com.company.anlysis;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

public class TextAnalysis {
	public static Map<String, String> map;
	
	public static void setMap(String filename) throws IOException {
		String dir = filename;
    	File dirFile = new File(dir);
		map = new HashMap<String, String>();
		
		long start = System.currentTimeMillis();
		List<String> readList = FileUtils.readLines(dirFile, "UTF-8");
		long end = System.currentTimeMillis();
		System.out.println("Excution time : "+ (end-start)/1000.0+ "second");
		
		long startFor = System.currentTimeMillis();
		String[] input;
		for(int i=0; i< readList.size(); i++)
		{
			// remove front-end space
			String result = readList.get(i).replaceAll("(^\\p{Z}+|\\p{Z}+$)", "");
			
			input = result.split("\t");
			
			if(input.length == 2) map.put(input[1], input[0]);
			
		}
		long endFor = System.currentTimeMillis();
		System.out.println("Excution time : "+ (endFor-startFor)/1000.0+ "second");
		
	}
	public static void main(String[] args) throws IOException
	{
		setMap("trigramCount.txt");
	}
}
