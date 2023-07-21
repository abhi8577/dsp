package com.dsp.dsp.dto;

public class BillDeskOrderLinksDTO {

	private String href;
	private String rel;
	private String method;
	private BillDeskOrderParameters parameters;
	private String valid_date;
	private BillDeskOrderHeaders headers;
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getRel() {
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public BillDeskOrderParameters getParameters() {
		return parameters;
	}
	public void setParameters(BillDeskOrderParameters parameters) {
		this.parameters = parameters;
	}
	public String getValid_date() {
		return valid_date;
	}
	public void setValid_date(String valid_date) {
		this.valid_date = valid_date;
	}
	public BillDeskOrderHeaders getHeaders() {
		return headers;
	}
	public void setHeaders(BillDeskOrderHeaders headers) {
		this.headers = headers;
	}
	
	
	

}
