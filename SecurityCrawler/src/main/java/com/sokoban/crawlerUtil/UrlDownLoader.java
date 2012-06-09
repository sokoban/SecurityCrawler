package com.sokoban.crawlerUtil;

import java.io.*;
import java.net.*;

import org.bson.types.ObjectId;

public class UrlDownLoader {

	private String crawldir = "/crawldata";
	public String tempdir = crawldir + "/temp";
	public String dstdir = crawldir + "/data";
	public String dstdirphoto = crawldir + "/image";
	
	final static int size=1024;

	public void fileUrl(String fAddress, String localFileName
			, String destinationDir, Boolean filter, String mode) {

		// Filter type 
		// 1. File Size Filter
		// 2. 
		
		OutputStream outStream = null;
		URLConnection  uCon = null;	
		ObjectId id = new ObjectId();
		int filtersize = 1;
				
		if(mode.equals("photo")){
			filtersize = 90000;  // over 90kbyte
		}
		
		InputStream is = null;
		try {
			URL Url;
			byte[] buf;
			int ByteRead,ByteWritten=0;
			Url= new URL(fAddress);
			
			String dstfilename = fAddress.replace("/", "");
			dstfilename = dstfilename.replace(" ", "");
			
			
			if(filter == true){
				outStream = new BufferedOutputStream(new
						FileOutputStream( tempdir + "/"+ id + localFileName));
			}else{
				outStream = new BufferedOutputStream(new
						FileOutputStream( destinationDir + "/" + dstfilename ));
			}

			uCon = Url.openConnection();
			is = uCon.getInputStream();
			buf = new byte[size];
			
			while ((ByteRead = is.read(buf)) != -1) {
				outStream.write(buf, 0, ByteRead);
				ByteWritten += ByteRead;
			}

			//System.out.println("Downloaded Successfully.");
			//System.out.println("File name:\""+localFileName+ "\"\nNo ofbytes :" + ByteWritten);
		
		
			try {
				is.close();
				outStream.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
			
			if(filter == true){
				ByteRead = 0;
				if(ByteWritten > filtersize){
					outStream = new BufferedOutputStream(new
					FileOutputStream(destinationDir+"/"+ localFileName));
					
					is = new BufferedInputStream(new FileInputStream( tempdir + "/"+ id + localFileName));
					
					while((ByteRead = is.read(buf)) != -1){
						outStream.write(buf, 0, ByteRead);
						ByteWritten += ByteRead;
					}
					
					is.close();
					outStream.close();
					
				}
				
				try{
					File f = new File(destinationDir + "/" + id + localFileName);
					f.delete();
					f = null;
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {

		
		}
		
	}
	
	public void fileDownload(String fAddress, String destinationDir, String mode)
	{
		 
		int slashIndex =fAddress.lastIndexOf('/');
		int periodIndex =fAddress.lastIndexOf('.');
		
		String fileName=fAddress.substring(slashIndex + 1);
		
		if(mode.equals("normal")){
			makesavedir(tempdir,dstdir);
		}else{
			makesavedir(tempdir,dstdirphoto);
		}
		
		
		if (periodIndex >=1 &&  slashIndex >= 0 
		&& slashIndex < fAddress.length()-1)
		{
			if(mode.equals("normal")){
				fileUrl(fAddress,fileName,dstdir,false, mode);
			}else{
				fileUrl(fAddress,fileName,dstdirphoto,true, mode);
			}

		}else{
			System.err.println("path or file name Invalid.");
		}
	}
	
	public int makesavedir(String ttdir, String dtdir){
		int result = 0;
		
		try{
			File tedir = new File(ttdir);
			if(!tedir.isDirectory()){
				tedir.mkdir();
			}
			tedir = null;
			
			File dsdir = new File(dtdir);
			if(!dsdir.isDirectory()){
				dsdir.mkdir();
			}
		}catch(Exception e){
			result = -1;
			e.printStackTrace();
		}
		
		return result;
	}

}