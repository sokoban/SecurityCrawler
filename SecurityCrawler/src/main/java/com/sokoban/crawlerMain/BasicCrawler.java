package com.sokoban.crawlerMain;

/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.sokoban.Webemul.GetPageByHtmlUnit;
import com.sokoban.crawlerUtil.UrlDownLoader;
import com.sokoban.model.CrawlResult;


import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class BasicCrawler extends WebCrawler {

	UrlDownLoader udl = new UrlDownLoader();
	
	GetPageByHtmlUnit gpbhu = new GetPageByHtmlUnit();
	
    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css" 
            + "|png|tiff?|mid|mp2|mp3|mp4"
            + "|wav|avi|mov|mpeg|ram|m4v|pdf" 
            + "|rm|smil|wmv|swf|wma|zip|rar|gz))$");
    
    private final static Pattern FILTERS3 = Pattern.compile(".*(\\.(css|ico" 
            + "|png|tiff?|mid|mp2|mp3|mp4"
            + "|wav|avi|mov|mpeg|ram|m4v|pdf" 
            + "|rm|smil|wmv|swf|wma|zip|rar|gz))$");
    
    private final static Pattern FILTERS2 = Pattern.compile(".*(\\.(jpe?g))$");

        private String[] myCrawlDomains;

        @Override
        public void onStart() {
                myCrawlDomains = (String[]) myController.getCustomData();
        }

        @Override
        public boolean shouldVisit(WebURL url)  {
                String href = url.getURL().toLowerCase();
                
                try {
					if(!FILTERS3.matcher(href).matches()){
						gpbhu.getPagefileinfo(href);
						udl.fileDownload(href, "/home/tempsite", "normal");
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                if (FILTERS.matcher(href).matches()) {
                        return false;
                }
                //for (String crawlDomain : myCrawlDomains) {
                        //if (href.startsWith(crawlDomain)) {
                //                return true;
                        //}
                //}
                return true;
        }

        @Override
        public void visit(Page page) {
        	
        	List<CrawlResult> lcr = new ArrayList<CrawlResult>();
                int docid = page.getWebURL().getDocid();
                String url = page.getWebURL().getURL();
                int parentDocid = page.getWebURL().getParentDocid();
                
                //System.out.println(page.getContentType());
                //try {
					//System.out.println(new String(page.getContentData(),"UTF-8"));
				//} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
				//	e.printStackTrace();
				//}
                /*
                System.out.println(page.getWebURL().getAnchor());
                System.out.println(page.getWebURL().getDocid());
                System.out.println(page.getWebURL().getDomain());
                System.out.println(page.getWebURL().getParentDocid());
                System.out.println(page.getWebURL().getParentUrl());
                System.out.println(page.getWebURL().getPath());
                System.out.println(page.getWebURL().getSubDomain());
                System.out.println(page.getWebURL().getURL());
                System.out.println(page.getWebURL().getDepth());
				*/
                CrawlResult cr = new CrawlResult();
                cr.setAnchor(page.getWebURL().getAnchor());
                cr.setDocid(page.getWebURL().getDocid());
                cr.setDomain(page.getWebURL().getDomain());
                cr.setParentdocid(page.getWebURL().getParentDocid());
                cr.setParenturl(page.getWebURL().getParentUrl());
                cr.setPath(page.getWebURL().getPath());
                cr.setSubdomain(page.getWebURL().getSubDomain());
                cr.setUrl(page.getWebURL().getURL());
                cr.setDepth(page.getWebURL().getDepth());
                
                if (page.getParseData() instanceof HtmlParseData) {
                	HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
                    try {
						cr.setParsedata(new String(htmlParseData.getText().getBytes(),"UTF-8") );
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    String html = htmlParseData.getHtml();
                	cr.setContents(html);
                	//lcr.add(cr);
                }
                
                lcr.add(cr);
                cr = null;
                

                if (page.getParseData() instanceof HtmlParseData) {
                        HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
                        String text = htmlParseData.getText();
                        String html = htmlParseData.getHtml();
                        List<WebURL> links = htmlParseData.getOutgoingUrls();


                        //System.out.println(text.);

                        for(int ab = 0;ab<links.size();ab++){
                        	
                            CrawlResult cr2 = new CrawlResult();
                            cr2.setAnchor(links.get(ab).getAnchor());
                            cr2.setDocid(links.get(ab).getDocid());
                            cr2.setDomain(links.get(ab).getDomain());
                            cr2.setParentdocid(links.get(ab).getParentDocid());
                            cr2.setParenturl(links.get(ab).getParentUrl());
                            cr2.setPath(links.get(ab).getPath());
                            cr2.setSubdomain(links.get(ab).getSubDomain());
                            cr2.setUrl(links.get(ab).getURL());
                            cr2.setDepth(links.get(ab).getDepth());
                            
                            //cr2.setContents(links.get(ab).get);
                            lcr.add(cr2);
                            cr2 = null;
                        	/*
                        	System.out.println("anchor : " + links.get(ab).getAnchor());
                        	System.out.println("depth : " + links.get(ab).getDepth());
                        	System.out.println("Docid : " + links.get(ab).getDocid());
                        	System.out.println("Domain" + links.get(ab).getDomain());
                        	System.out.println("parentDocid : " + links.get(ab).getParentDocid());
                        	System.out.println("parentUrl : " + links.get(ab).getParentUrl());
                        	System.out.println("getPath : " + links.get(ab).getPath());
                        	System.out.println(links.get(ab).getSubDomain());
                        	System.out.println(links.get(ab).getURL());
                        	*/
                        	
                        }
						
                        //System.out.println("Text length: " + text.length());
                        //System.out.println("Html length: " + html.length());
                        //System.out.println("Number of outgoing links: " + links.size());
                }

                System.out.println("=============");
                
                //try {
                	//BasicCrawlerBo basicCrawlerBo = new BasicCrawlerBoImpl();
					//basicCrawlerBo.InsertCrawlResult(sqlMap,lcr);
					//basicCrawlerBo = null;
					
				//} catch (SQLException e) {
					// TODO Auto-generated catch block
				//	e.printStackTrace();
				//}
                
                
                //return lcr;
        }
}
