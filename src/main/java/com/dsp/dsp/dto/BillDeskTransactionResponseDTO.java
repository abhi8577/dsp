package com.dsp.dsp.dto;


public class BillDeskTransactionResponseDTO {
	private String transactionid;
	private String auth_status;
	private String transaction_date;
	private String payment_method_type;
	private String charge_amount;
	private String bank_ref_no;
	private String transaction_error_code;
	private String transaction_error_desc;
	public String getTransactionid() {
		return transactionid;
	}
	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}
	public String getAuth_status() {
		return auth_status;
	}
	public void setAuth_status(String auth_status) {
		this.auth_status = auth_status;
	}
	public String getTransaction_date() {
		return transaction_date;
	}
	public void setTransaction_date(String transaction_date) {
		this.transaction_date = transaction_date;
	}
	public String getPayment_method_type() {
		return payment_method_type;
	}
	public void setPayment_method_type(String payment_method_type) {
		this.payment_method_type = payment_method_type;
	}
	public String getCharge_amount() {
		return charge_amount;
	}
	public void setCharge_amount(String charge_amount) {
		this.charge_amount = charge_amount;
	}
	public String getBank_ref_no() {
		return bank_ref_no;
	}
	public void setBank_ref_no(String bank_ref_no) {
		this.bank_ref_no = bank_ref_no;
	}
	public String getTransaction_error_code() {
		return transaction_error_code;
	}
	public void setTransaction_error_code(String transaction_error_code) {
		this.transaction_error_code = transaction_error_code;
	}
	public String getTransaction_error_desc() {
		return transaction_error_desc;
	}
	public void setTransaction_error_desc(String transaction_error_desc) {
		this.transaction_error_desc = transaction_error_desc;
	}
	
	
	
}
