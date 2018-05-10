package com.company.cs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.lang.StringUtils;

public class TextDataSplit 
{
	public static int totalWords; // total number of words
	public static int N; // unigram, bigram, trigram ..
	public static String[] input;
	
	public static File inFile; // input text
	public static File outFile; // output text
	
	public static BufferedReader br;
	public static BufferedWriter out;
	public static StringBuilder writing;
	public static void writingTxt(String str) throws IOException
	{
		try {
			out.write(str);
			//out.newLine();
			out.flush();
			out.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void solve(int index, int check, int len)
	{
		if(check == N) return; // base case
		if(index >= 0 && index < len) {
			writing.append(input[index]+" ");
			solve(index+1, check+1, len);
		}
		else if(index < 0 ) {
			writing.append("^");
			solve(index+1,check+1,len);
		}
		else if(index >= len) {
			writing.append("&");
			solve(index+1,check+1,len);
		}
	}
    
	public static void main( String[] args )
    {
    	
    	//inFile = new File("test1.txt");
    	outFile = new File("trigramOutput.txt");
    	
    	writing = new StringBuilder();
    	
    	N =3; // test bigram
    	
    	String dir = "test2.txt";
    	File dirFile = new File(dir);
    	
    	try {
    		/*
    		List<String> readList = FileUtils.readLines(dirFile,"euc-kr");
    		for(int i=0;i< readList.size(); i++)
    		{
    			System.out.println(readList.get(i));
    		}*/
    		String readFile = FileUtils.readFileToString(dirFile,"utf-8");
    		System.out.println("read success! ");
    		readFile.trim();
    		long start = System.currentTimeMillis();
    		//input = StringUtils.split(readFile);
    		readFile= readFile.replaceAll("[[:]\\\\/?[*]]", "");
    		
    		//readFile = readFile.replaceAll("!\"#[$]%&\\(\\)\\{\\}@`[*]:[+];-.<>,\\^~|'\\[\\]", ""); 
    		//readFile= readFile.replaceAll("/[\\{\\}\\[\\]\\/?.,;:|\\)*~`!^\\-_+<>@\\#$%&\\\\\\=\\(\\'\\\"]/gi", "");
    		//input = readFile.split("^[ㄱ-ㅎ가-힣0-9]*$");
    		long end = System.currentTimeMillis();
    		System.out.println("split 실행시간 : " +(end-start)/1000.0+"초 ");
    		
    		//System.out.println(input.length);
    		
    		long startWrite = System.currentTimeMillis();
    		
    		Collection lines = new ArrayList<>();
    		System.out.println(readFile);
    		for(int i=0; i< input.length ; i++)  // add 2 in length to confirm text to the end
    		{
    			solve(i-(N-1), 0, input.length);
    		
    			FileUtils.writeStringToFile(outFile,writing.toString()+"\n",true);
    			writing.setLength(0);
    		}
    		long endWrite = System.currentTimeMillis();
    		System.out.println("write 실행시간 : "+(endWrite-startWrite)/1000.0+"초 ");
    		System.out.println("for each success");
    		
    		//FileUtils.writeLines(outFile, lines);
    		//FileUtils.writeStringToFile(outFile, writing.toString());
    		
    	} catch( Throwable e) {
    		e.printStackTrace();
    	}
    	
    	
    	/*
    	 *  read text
    	 
    	try {
    		br = new BufferedReader(new InputStreamReader(new FileInputStream(inFile),"utf-8"));
    		// 파일 형식에 따라 euc-kr , utf-8 
    		out= new BufferedWriter(new FileWriter(outFile, true)); // true : subsequent writing
    		
    		String line;
    		
    		
    		// too slow to append total lines !
    		while( (line=br.readLine() ) != null) // read by line 
    		{
    			
    			//line.trim(); //  
    			line = line.replaceAll("(^\\p{Z}+|\\p{Z}+$)", "");
    			
    			//sb.append(line); // join lines
        		//System.out.println(line);
    			//sb.trimToSize();
    			
        		//StringTokenizer token = new StringTokenizer(line); // split by space 
        		//totalWords =  token.countTokens();
        		//System.out.println("total number of words : "+totalWords);
        		
        		input = line.split(" "); // easier to access with array than tokenizer
        		
        		for(int i=0; i< input.length + 2 ; i++)  // add 2 in length to confirm text to the end
        		{
        			solve(i-(N-1), 0, input.length);
        			writing.append("\n");
        		}
        		writing.trimToSize();
    		}
    		
    		System.out.println("complete Ngram classification!");
    		//writingTxt(writing.toString());
    		
    	} catch ( FileNotFoundException e) {
    		e.printStackTrace();
    	} catch ( IOException e) {
    		e.printStackTrace();
    	} finally {
			if(br != null ) try {br.close(); } catch (IOException e) {}
		}
    	*/
    }
}
