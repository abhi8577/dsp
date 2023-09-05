package com.dsp.dsp.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DSP_PAYMENT_HISTORY")
public class DspPaymentHistory implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
    private String orderId;

    @Column(name = "BD_ORDER_ID")
    private String bdOrderId;

    @Column(name = "PAYMENT_STATUS")
    private Integer paymentStatus;

    @Column(name = "PAYMENT_TYPE")
    private String paymentType;

    @Column(name = "TOTAL_AMOUNT")
    private BigDecimal totalAmount;
    
    @Column(name = "CGST")
    private BigDecimal cgst;
    
    @Column(name = "SGST")
    private BigDecimal sgst;
    
    @Column(name = "IGST")
    private BigDecimal igst;

    @Column(name = "PAYMENT_SOURCE")
    private String paymentSource;

    @Column(name = "ORDER_DATE")
    private String orderDate;

    @Column(name = "UPDATED_DATE")
    private String updatedDate;

    @Column(name = "TOKEN")
    private String token;

    @Column(name = "IP_ADDRESS")
    private String ipAddress;
    
    @Column(name = "REMARK")
    private String remark;

	public DspPaymentHistory() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getBdOrderId() {
		return bdOrderId;
	}

	public void setBdOrderId(String bdOrderId) {
		this.bdOrderId = bdOrderId;
	}

	public Integer getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(Integer paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getPaymentSource() {
		return paymentSource;
	}

	public void setPaymentSource(String paymentSource) {
		this.paymentSource = paymentSource;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getCgst() {
		return cgst;
	}

	public void setCgst(BigDecimal cgst) {
		this.cgst = cgst;
	}

	public BigDecimal getSgst() {
		return sgst;
	}

	public void setSgst(BigDecimal sgst) {
		this.sgst = sgst;
	}

	public BigDecimal getIgst() {
		return igst;
	}

	public void setIgst(BigDecimal igst) {
		this.igst = igst;
	}

}
