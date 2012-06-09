package com.sokoban.Webemul;

import java.io.File;
import java.io.IOException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ScriptPreProcessor;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.sokoban.commonUtil.FileHandler;
import com.sokoban.crawlerUtil.UrlDownLoader;


public class GetPageByHtmlUnit  {

	final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_3_6 );
	UrlDownLoader udl = new UrlDownLoader();
	FileHandler fh = new FileHandler();
	
	public void getPagefileinfo(String siteurl) throws IOException{

		try{
			webClient.setScriptPreProcessor( new ScriptPreProcessor() {
	             public String preProcess(final HtmlPage htmlPage, final String sourceCode, String sourceName, final int lineNumber, final HtmlElement htmlElement) {
	                if (sourceCode.indexOf("unimplementedFunction" ) > -1) {
	                     return "" ;
	                 }
	                     
	                     try {
	                    	 sourceName = sourceName.replace("/", "");
							fh.WriteLogInLocal(sourceCode,udl.dstdir + "/" + sourceName);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                     
	                     //udl.fileDownload(sourceName, "/home/tempsite");
	                     //System. out.println(htmlPage.asXml());
	               return sourceCode;
	             }

	       });

	       HtmlPage page = webClient.getPage(siteurl);

	       final String pageAsXml = page.asXml();
	       //System. out.println(pageAsXml);


	       final String pageAsXml2 = page.asXml();
	       //System. out.println(pageAsXml2);
	      
	       // Home Page Save without Script
	     //File saveFile = new File("saved.html" );
	     //if(saveFile.exists()){
	     //    saveFile.delete();
	     //    saveFile = new File("saved.html" );
	    // }
	     //page.save(saveFile);

	     webClient.closeAllWindows();
		}catch(Exception e){
			e.printStackTrace();
		}
       

	}
	
	  public void login(String URL, String USERNAME, String PASSWORD) throws IOException
	  {
	    HtmlPage page = (HtmlPage) webClient.getPage(URL);
	    HtmlForm form = page.getFormByName("formLogin");

	    String user = USERNAME;
	    String password = PASSWORD;

	    // Enter login and password
	    form.getInputByName("LoginSteps$UserName").setValueAttribute(user);
	    form.getInputByName("LoginSteps$Password").setValueAttribute(password);

	    // Click Login Button
	    page = (HtmlPage) form.getInputByName("LoginSteps$LoginButton").click();

	    webClient.waitForBackgroundJavaScript(3000);

	    // Click on Campa area
	    HtmlAnchor link = (HtmlAnchor) page.getElementById("ctl00_linkCampaNoiH");
	    page = (HtmlPage) link.click();

	    webClient.waitForBackgroundJavaScript(3000);
	    System.out.println(page.asText());
	  }

}
