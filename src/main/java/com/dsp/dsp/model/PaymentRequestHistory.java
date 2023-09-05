package com.dsp.dsp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PAYMENT_REQUEST")
public class PaymentRequestHistory{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PAYMENT_REQUEST_ID")
    private Long paymentRequestId;
    
    @Column(name = "BD_TIMESTAMP", length = 3000)
    private String bdTimestamp;
    
    @Column(name = "BD_TRACE_ID", length = 3000)
    private String bdTraceId;

    @Column(name = "MERC_ID", length = 3000)
    private String mercId;

    @Column(name = "ORDER_ID", length = 3000)
    private String orderId;

    @Column(name = "AMOUNT", length = 3000)
    private String amount;

    @Column(name = "ORDER_DATE", length = 3000)
    private String orderDate;

    @Column(name = "CURRENCY", length = 3000)
    private String currency;

    @Column(name = "ITEM_CODE", length = 3000)
    private String itemCode;

    @Column(name = "RU", length = 3000)
    private String ru;

    @Column(name = "ADDITIONAL_INFO1", length = 3000)
    private String additionalInfo1;

    @Column(name = "ADDITIONAL_INFO2", length = 3000)
    private String additionalInfo2;

    @Column(name = "INIT_CHANNEL", length = 3000)
    private String initChannel;

    @Column(name = "IP", length = 3000)
    private String ip;

    @Column(name = "MAC", length = 3000)
    private String mac;

    @Column(name = "IMEI", length = 3000)
    private String imei;

    @Column(name = "USER_AGENT", length = 3000)
    private String userAgent;

    @Column(name = "ACCEPT_HEADER", length = 3000)
    private String acceptHeader;

    @Column(name = "FINGERPRINT_ID", length = 3000)
    private String fingerprintId;

    @Column(name = "CUSTOMER_REF_ID", length = 3000)
    private String customerRefId;

    @Column(name = "SUBSCRIPTION_REF_ID", length = 3000)
    private String subscriptionRefId;

    @Column(name = "SUBSCRIPTION_DESC", length = 3000)
    private String subscriptionDesc;

    @Column(name = "START_DATE", length = 3000)
    private String startDate;

    @Column(name = "END_DATE", length = 3000)
    private String endDate;

    @Column(name = "FREQUENCY", length = 3000)
    private String frequency;

    @Column(name = "AMOUNT_TYPE", length = 3000)
    private String amountType;

    @Column(name = "RECURRENCE_RULE", length = 3000)
    private String recurrenceRule;

    @Column(name = "DEBIT_DAY", length = 3000)
    private String debitDay;

    @Column(name = "INVOICE_NUMBER", length = 3000)
    private String invoiceNumber;

    @Column(name = "INVOICE_DISPLAY_NUMBER", length = 3000)
    private String invoiceDisplayNumber;

    @Column(name = "CUSTOMER_NAME", length = 3000)
    private String customerName;

    @Column(name = "INVOICE_DATE", length = 3000)
    private String invoiceDate;

    @Column(name = "CGST", length = 3000)
    private String cgst;

    @Column(name = "SGST", length = 3000)
    private String sgst;

    @Column(name = "IGST", length = 3000)
    private String igst;

    @Column(name = "GST", length = 3000)
    private String gst;

    @Column(name = "CESS", length = 3000)
    private String cess;

    @Column(name = "GSTINCENTIVE", length = 3000)
    private String gstincentive;

    @Column(name = "GSTPCT", length = 3000)
    private String gstpct;

    @Column(name = "GSTIN", length = 3000)
    private String gstin;

    @Column(name="CREATED_TIME")
   	private String createdTime;

	public Long getPaymentRequestId() {
		return paymentRequestId;
	}

	public void setPaymentRequestId(Long paymentRequestId) {
		this.paymentRequestId = paymentRequestId;
	}

	public String getBdTimestamp() {
		return bdTimestamp;
	}

	public void setBdTimestamp(String bdTimestamp) {
		this.bdTimestamp = bdTimestamp;
	}

	public String getBdTraceId() {
		return bdTraceId;
	}

	public void setBdTraceId(String bdTraceId) {
		this.bdTraceId = bdTraceId;
	}

	public String getMercId() {
		return mercId;
	}

	public void setMercId(String mercId) {
		this.mercId = mercId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getRu() {
		return ru;
	}

	public void setRu(String ru) {
		this.ru = ru;
	}

	public String getAdditionalInfo1() {
		return additionalInfo1;
	}

	public void setAdditionalInfo1(String additionalInfo1) {
		this.additionalInfo1 = additionalInfo1;
	}

	public String getAdditionalInfo2() {
		return additionalInfo2;
	}

	public void setAdditionalInfo2(String additionalInfo2) {
		this.additionalInfo2 = additionalInfo2;
	}

	public String getInitChannel() {
		return initChannel;
	}

	public void setInitChannel(String initChannel) {
		this.initChannel = initChannel;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getAcceptHeader() {
		return acceptHeader;
	}

	public void setAcceptHeader(String acceptHeader) {
		this.acceptHeader = acceptHeader;
	}

	public String getFingerprintId() {
		return fingerprintId;
	}

	public void setFingerprintId(String fingerprintId) {
		this.fingerprintId = fingerprintId;
	}

	public String getCustomerRefId() {
		return customerRefId;
	}

	public void setCustomerRefId(String customerRefId) {
		this.customerRefId = customerRefId;
	}

	public String getSubscriptionRefId() {
		return subscriptionRefId;
	}

	public void setSubscriptionRefId(String subscriptionRefId) {
		this.subscriptionRefId = subscriptionRefId;
	}

	public String getSubscriptionDesc() {
		return subscriptionDesc;
	}

	public void setSubscriptionDesc(String subscriptionDesc) {
		this.subscriptionDesc = subscriptionDesc;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getAmountType() {
		return amountType;
	}

	public void setAmountType(String amountType) {
		this.amountType = amountType;
	}

	public String getRecurrenceRule() {
		return recurrenceRule;
	}

	public void setRecurrenceRule(String recurrenceRule) {
		this.recurrenceRule = recurrenceRule;
	}

	public String getDebitDay() {
		return debitDay;
	}

	public void setDebitDay(String debitDay) {
		this.debitDay = debitDay;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getInvoiceDisplayNumber() {
		return invoiceDisplayNumber;
	}

	public void setInvoiceDisplayNumber(String invoiceDisplayNumber) {
		this.invoiceDisplayNumber = invoiceDisplayNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getCgst() {
		return cgst;
	}

	public void setCgst(String cgst) {
		this.cgst = cgst;
	}

	public String getSgst() {
		return sgst;
	}

	public void setSgst(String sgst) {
		this.sgst = sgst;
	}

	public String getIgst() {
		return igst;
	}

	public void setIgst(String igst) {
		this.igst = igst;
	}

	public String getGst() {
		return gst;
	}

	public void setGst(String gst) {
		this.gst = gst;
	}

	public String getCess() {
		return cess;
	}

	public void setCess(String cess) {
		this.cess = cess;
	}

	public String getGstincentive() {
		return gstincentive;
	}

	public void setGstincentive(String gstincentive) {
		this.gstincentive = gstincentive;
	}

	public String getGstpct() {
		return gstpct;
	}

	public void setGstpct(String gstpct) {
		this.gstpct = gstpct;
	}

	public String getGstin() {
		return gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
}

