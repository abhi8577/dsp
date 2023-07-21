package com.dsp.dsp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "DSP_INVOICE_HISTORY")
public class DspInvoiceHistory {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
    private Long id;

    @Column(name = "APPLICATION_NUMBER")
    private String applicationNumber;

    @Column(name = "CONSUMER_MOBILE")
    private String consumerMobile;

    @Column(name = "CONSUMER_NAME")
    private String consumerName;

    @Column(name = "INVOICE_ID")
    private String invoiceId;

    @Column(name = "ORDER_ID")
    private String orderid;

    @Column(name = "BANK_ID")
    private String bankid;

    @Column(name = "PAYMENT_METHOD_TYPE")
    private String paymentType;

    @Column(name = "TXN_PROCESS_TYPE")
    private String txn_process_type;

    @Column(name = "TRANSACTION_ID")
    private String transactionid;

    @Column(name = "TRANSACTION_DATE")
    private String transaction_date;

    @Column(name = "SURCHARGE")
    private String surcharge;

    @Column(name = "DISCOUNT")
    private String discount;

    @Column(name = "CHARGE_AMOUNT")
    private String charge_amount;

    @Column(name = "ITEM_CODE")
    private String itemcode;

    @Column(name = "AUTH_STATUS")
    private String auth_status;

    @Column(name = "RESPONSE_DATE")
    private String responseDate;

    @Column(name = "TRANSACTION_ERROR_CODE")
    @JsonProperty("transaction_error_code")
    private String transaction_error_code;

    @Column(name = "TRANSACTION_ERROR_TYPE")
    @JsonProperty("transaction_error_type")
    private String transaction_error_type;

    @Column(name = "TRANSACTION_ERROR_DESC")
    @JsonProperty("transaction_error_desc")
    private String transaction_error_desc;

    // Refund
    @Column(name = "REFUND_ID")
    @JsonProperty("refundid")
    private String refundId;

    @Column(name = "REFUND_AMOUNT")
    @JsonProperty("refund_amount")
    private String refundAmount;

    @Column(name = "TXN_AMOUNT")
    @JsonProperty("txn_amount")
    private String txnAmount;

    @Column(name = "REFUND_DATE")
    @JsonProperty("refund_date")
    private String refundDate;

    @Column(name = "MERC_REFUND_REF_NO")
    @JsonProperty("merc_refund_ref_no")
    private String mercRefundRefNo;

    @Column(name = "REFUND_STATUS")
    @JsonProperty("refund_status")
    private String refundStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApplicationNumber() {
		return applicationNumber;
	}

	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	public String getConsumerMobile() {
		return consumerMobile;
	}

	public void setConsumerMobile(String consumerMobile) {
		this.consumerMobile = consumerMobile;
	}

	public String getConsumerName() {
		return consumerName;
	}

	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getBankid() {
		return bankid;
	}

	public void setBankid(String bankid) {
		this.bankid = bankid;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getTxn_process_type() {
		return txn_process_type;
	}

	public void setTxn_process_type(String txn_process_type) {
		this.txn_process_type = txn_process_type;
	}

	public String getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}

	public String getTransaction_date() {
		return transaction_date;
	}

	public void setTransaction_date(String transaction_date) {
		this.transaction_date = transaction_date;
	}

	public String getSurcharge() {
		return surcharge;
	}

	public void setSurcharge(String surcharge) {
		this.surcharge = surcharge;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getCharge_amount() {
		return charge_amount;
	}

	public void setCharge_amount(String charge_amount) {
		this.charge_amount = charge_amount;
	}

	public String getItemcode() {
		return itemcode;
	}

	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}

	public String getAuth_status() {
		return auth_status;
	}

	public void setAuth_status(String auth_status) {
		this.auth_status = auth_status;
	}

	public String getTransaction_error_code() {
		return transaction_error_code;
	}

	public void setTransaction_error_code(String transaction_error_code) {
		this.transaction_error_code = transaction_error_code;
	}

	public String getTransaction_error_type() {
		return transaction_error_type;
	}

	public void setTransaction_error_type(String transaction_error_type) {
		this.transaction_error_type = transaction_error_type;
	}

	public String getTransaction_error_desc() {
		return transaction_error_desc;
	}

	public void setTransaction_error_desc(String transaction_error_desc) {
		this.transaction_error_desc = transaction_error_desc;
	}

	public String getRefundId() {
		return refundId;
	}

	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}

	public String getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(String refundAmount) {
		this.refundAmount = refundAmount;
	}

	public String getTxnAmount() {
		return txnAmount;
	}

	public void setTxnAmount(String txnAmount) {
		this.txnAmount = txnAmount;
	}

	public String getRefundDate() {
		return refundDate;
	}

	public void setRefundDate(String refundDate) {
		this.refundDate = refundDate;
	}

	public String getMercRefundRefNo() {
		return mercRefundRefNo;
	}

	public void setMercRefundRefNo(String mercRefundRefNo) {
		this.mercRefundRefNo = mercRefundRefNo;
	}

	public String getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}

	public String getResponseDate() {
		return responseDate;
	}

	public void setResponseDate(String responseDate) {
		this.responseDate = responseDate;
	}

}
