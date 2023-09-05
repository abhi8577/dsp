package com.dsp.dsp.dto;

import java.util.List;

public class BillDeskOrderResponseDTO {
	private String objectid;
	private String orderid;
	private String bdorderid;
	private String mercid;
	private String order_date;
	private String amount;
	private String currency;
	private String ru;

	private BillDeskOrderAdditionalInfo additional_info;
	private String itemcode;
	private String createdon;
	private String next_step;
	private List<BillDeskOrderLinksDTO> links;
	private String status;
	public String getObjectid() {
		return objectid;
	}
	public void setObjectid(String objectid) {
		this.objectid = objectid;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getBdorderid() {
		return bdorderid;
	}
	public void setBdorderid(String bdorderid) {
		this.bdorderid = bdorderid;
	}
	public String getMercid() {
		return mercid;
	}
	public void setMercid(String mercid) {
		this.mercid = mercid;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getRu() {
		return ru;
	}
	public void setRu(String ru) {
		this.ru = ru;
	}
	public BillDeskOrderAdditionalInfo getAdditional_info() {
		return additional_info;
	}
	public void setAdditional_info(BillDeskOrderAdditionalInfo additional_info) {
		this.additional_info = additional_info;
	}
	public String getItemcode() {
		return itemcode;
	}
	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}
	public String getCreatedon() {
		return createdon;
	}
	public void setCreatedon(String createdon) {
		this.createdon = createdon;
	}
	public String getNext_step() {
		return next_step;
	}
	public void setNext_step(String next_step) {
		this.next_step = next_step;
	}
	public List<BillDeskOrderLinksDTO> getLinks() {
		return links;
	}
	public void setLinks(List<BillDeskOrderLinksDTO> links) {
		this.links = links;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}