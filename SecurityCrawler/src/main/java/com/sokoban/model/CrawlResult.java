package com.sokoban.model;

public class CrawlResult {

	private String _id;
	private String anchor;
	private short depth;
	private int docid;
	private String domain;
	private int parentdocid;
	private String parenturl;
	private String path;
	private String subdomain;
	private String url;
	private String contents;
	private String parsedata;
	
	public String getAnchor() {
		return anchor;
	}
	
	public void setAnchor(String anchor) {
		this.anchor = anchor;
	}

	public short getDepth() {
		return depth;
	}

	public void setDepth(short depth) {
		this.depth = depth;
	}

	public int getDocid() {
		return docid;
	}

	public void setDocid(int docid) {
		this.docid = docid;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public int getParentdocid() {
		return parentdocid;
	}

	public void setParentdocid(int parentdocid) {
		this.parentdocid = parentdocid;
	}

	public String getParenturl() {
		return parenturl;
	}

	public void setParenturl(String parenturl) {
		this.parenturl = parenturl;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getSubdomain() {
		return subdomain;
	}

	public void setSubdomain(String subdomain) {
		this.subdomain = subdomain;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getParsedata() {
		return parsedata;
	}

	public void setParsedata(String parsedata) {
		this.parsedata = parsedata;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}
	
	
}
