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
	public final static double unigramN = 3175482; // value obtained from wordcount.exe
	public final static double bigramN = 3009662;
	public final static double trigramN = 2984332;
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
	
	public static void Puni(String str) {
		// P(w1) x P(w2) x P(w3)
		String input[] = str.split(" ");
		double uniProbability = 1.0;
		for(String temp : input)
		{
			uniProbability *= Double.parseDouble(unigramMap.get(temp))/unigramN ;
		}
		System.out.println("Puni(str) : "+ uniProbability);
	}
	public static void Pbi(String str) {
		// P(w1) x P(w2 | w1) x P(w3 | w2)
		String input[] = str.split(" ");
		double biProbability = 1.0;
		double w1 = Double.parseDouble(unigramMap.get(input[0]))/unigramN;
		double w1w2 = Double.parseDouble(bigramMap.get(input[0]+" "+input[1]))/bigramN;
		double w2 = Double.parseDouble(unigramMap.get(input[1]))/unigramN;
		double w2w3 = Double.parseDouble(bigramMap.get(input[1]+" "+input[2]))/bigramN;
		biProbability *= (w1) * (w1w2/w1) * (w2w3/w2);
		System.out.println("Pbi(str) : "+ biProbability);

	}
	public static void Ptri(String str) {
		// P(w1) x P(w2 | w1) x P(w3 | w1w2)
		String input[] = str.split(" ");
		double triProbability = 1.0;
		
		double w1 = Double.parseDouble(unigramMap.get(input[0]))/unigramN;
		double w1w2 = Double.parseDouble(bigramMap.get(input[0]+" "+input[1]))/bigramN;
		double w2 = Double.parseDouble(unigramMap.get(input[1]))/unigramN;
		double w1w2w3 = Double.parseDouble(trigramMap.get(input[0]+" "+input[1]+" "+input[2]))/trigramN;
		triProbability *= (w1) * (w1w2/w1) * (w1w2w3/w1w2);
		System.out.println("Ptri(str) : "+ triProbability);

	}
	
	public static void main(String[] args) throws IOException
	{
		setMap("uniCount.txt", unigramMap);
		setMap("biCount.txt",bigramMap);
		setMap("triCount.txt",trigramMap);
		
		String str = "말하는 경우가 많다";
		//System.out.println(unigramMap.get(str));
		Puni(str);
		Pbi(str);
		Ptri(str);
	}
}
