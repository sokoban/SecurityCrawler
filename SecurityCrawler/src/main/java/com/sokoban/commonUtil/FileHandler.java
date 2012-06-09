package com.sokoban.commonUtil;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;

public class FileHandler {

	private static final Logger LOG = Logger.getLogger(FileHandler.class);
	
	public void WriteLogInLocal(String log, String Loca) throws IOException{
	
		Loca = Loca.replace(" ", "");
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(Loca,true));
		bw.write(log);
		bw.newLine();
		bw.close();
	}
	
	public String ReadLogInLocal(String log, String Src){
		String result = null;
		
		
		return result;		
	}
}
