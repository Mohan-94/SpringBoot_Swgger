package com.app.emp.exception;

public class Links {

	private String ref;
	private String url;

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Links(String ref, String url) {
		super();
		this.ref = ref;
		this.url = url;
	}

}
