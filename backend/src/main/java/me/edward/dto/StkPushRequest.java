package me.edward.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class StkPushRequest{
    @JsonProperty("TransactionType")
    private String transactionType;

    @JsonProperty("Amount")
    private int amount;

    @JsonProperty("CallBackURL")
    private String callBackURL;

    @JsonProperty("PhoneNumber")
    private String phoneNumber;

    @JsonProperty("PartyA")
    private String partyA;

    @JsonProperty("PartyB")
    private int partyB;

    @JsonProperty("AccountReference")
    private String accountReference;

    @JsonProperty("TransactionDesc")
    private String transactionDesc;

    @JsonProperty("BusinessShortCode")
    private String businessShortCode;

    @JsonProperty("Timestamp")
    private String timestamp;

    @JsonProperty("Password")
    private String password;

    public StkPushRequest() {
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCallBackURL() {
        return callBackURL;
    }

    public void setCallBackURL(String callBackURL) {
        this.callBackURL = callBackURL;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPartyA() {
        return partyA;
    }

    public void setPartyA(String partyA) {
        this.partyA = partyA;
    }

    public int getPartyB() {
        return partyB;
    }

    public void setPartyB(int partyB) {
        this.partyB = partyB;
    }

    public String getAccountReference() {
        return accountReference;
    }

    public void setAccountReference(String accountReference) {
        this.accountReference = accountReference;
    }

    public String getTransactionDesc() {
        return transactionDesc;
    }

    public void setTransactionDesc(String transactionDesc) {
        this.transactionDesc = transactionDesc;
    }

    public String getBusinessShortCode() {
        return businessShortCode;
    }

    public void setBusinessShortCode(String businessShortCode) {
        this.businessShortCode = businessShortCode;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "StkPushRequest{" +
                "transactionType='" + transactionType + '\'' +
                ", amount=" + amount +
                ", callBackURL='" + callBackURL + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", partyA='" + partyA + '\'' +
                ", partyB=" + partyB +
                ", accountReference='" + accountReference + '\'' +
                ", transactionDesc='" + transactionDesc + '\'' +
                ", businessShortCode='" + businessShortCode + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
