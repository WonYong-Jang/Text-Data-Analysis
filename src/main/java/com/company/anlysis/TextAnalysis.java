package com.company.anlysis;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

public class TextAnalysis {
	public static Map<String, String> unigramMap = new HashMap<String, String>();
	public static Map<String, String> bigramMap = new HashMap<String, String>();
	public static Map<String, String> trigramMap = new HashMap<String, String>();
	
	/**
	 * function to access easily value about count and string
	 */
	public static void setMap(String filename, Map<String, String> map) throws IOException {
		String dir = filename;
    	File dirFile = new File(dir);
		
		long start = System.currentTimeMillis();
		List<String> readList = FileUtils.readLines(dirFile, "UTF-8");
		long end = System.currentTimeMillis();
		System.out.println(filename+" Time to read: "+ (end-start)/1000.0+ " second");
		
		long startFor = System.currentTimeMillis();
		String[] input;
		for(int i=0; i< readList.size(); i++)
		{
			// remove front-end space
			String result = readList.get(i).replaceAll("(^\\p{Z}+|\\p{Z}+$)", "");
			
			input = result.split("\t"); // split count number and string value
			
			if(input.length == 2) map.put(input[1], input[0]);
			
		}
		long endFor = System.currentTimeMillis();

		System.out.println("Time to make hashMap: "+ (endFor-startFor)/1000.0+ " second");
	}
	public static void main(String[] args) throws IOException
	{
		setMap("uniCount.txt", unigramMap);
		
		String text = "자기 유도 전류는 역선으로 표현된다";
		String str = "전류";
		System.out.println(unigramMap.get(str));
		
	}
}
