package me.edward.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "transaction")
public class Transaction extends AbstractEntity {
    @Column(name = "merchant_request_id", nullable = false)
    private String merchantRequestId;
    @Column(name = "checkout_request_id", nullable = false)
    private String checkoutRequestId;
    @Column(name = "result_code", nullable = false)
    private int resultCode;
    @Column(name = "result_desc", nullable = false)
    private String resultDesc;
    @Column(name = "amount")
    private double amount;
    @Column(name = "mpesa_receipt_number")
    private String mpesaReceiptNumber;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "transaction_date")
    private Date transactionDate;
    @Column(name = "phone_number")
    private long phoneNumber;

    public Transaction() {
    }

    @PrePersist
    @PreUpdate
    public void update() {
        setCreatedDate(new Date());
        setActive(true);
        setDeleted(false);
        if (super.getRef() == null) {
            setRef(UUID.randomUUID().toString());
        }
    }

    public String getMerchantRequestId() {
        return merchantRequestId;
    }

    public void setMerchantRequestId(String merchantRequestId) {
        this.merchantRequestId = merchantRequestId;
    }

    public String getCheckoutRequestId() {
        return checkoutRequestId;
    }

    public void setCheckoutRequestId(String checkoutRequestId) {
        this.checkoutRequestId = checkoutRequestId;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMpesaReceiptNumber() {
        return mpesaReceiptNumber;
    }

    public void setMpesaReceiptNumber(String mpesaReceiptNumber) {
        this.mpesaReceiptNumber = mpesaReceiptNumber;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Transaction{" + ", merchantRequestId='" + merchantRequestId + '\'' + ", checkoutRequestId='"
                + checkoutRequestId + '\'' + ", resultCode=" + resultCode + ", resultDesc='" + resultDesc + '\''
                + ", amount=" + amount + ", mpesaReceiptNumber='" + mpesaReceiptNumber + '\'' + ", transactionDate="
                + transactionDate + ", phoneNumber=" + phoneNumber + '}';
    }
}

