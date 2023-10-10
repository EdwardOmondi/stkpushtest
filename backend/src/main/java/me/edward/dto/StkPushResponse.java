package me.edward.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StkPushResponse {
    @JsonProperty("MerchantRequestID")
    private String merchantRequestId;
    @JsonProperty("CheckoutRequestID")
    private String checkoutRequestId;
    @JsonProperty("ResponseCode")
    private String responseCode;
    @JsonProperty("ResponseDescription")
    private String responseDescription;
    @JsonProperty("CustomerMessage")
    private String customerMessage;
    @JsonProperty("requestId")
    private String requestId;
    @JsonProperty("errorCode")
    private String errorCode;
    @JsonProperty("errorMessage")
    private String errorMessage;

    public StkPushResponse() {
    }

    public StkPushResponse(String merchantRequestId, String checkoutRequestId, String responseCode, String responseDescription, String customerMessage) {
        this.merchantRequestId = merchantRequestId;
        this.checkoutRequestId = checkoutRequestId;
        this.responseCode = responseCode;
        this.responseDescription = responseDescription;
        this.customerMessage = customerMessage;
    }

    public String getMerchantRequestId() {
        return this.merchantRequestId;
    }

    public void setMerchantRequestId(String merchantRequestId) {
        this.merchantRequestId = merchantRequestId;
    }

    public String getCheckoutRequestId() {
        return this.checkoutRequestId;
    }

    public void setCheckoutRequestId(String checkoutRequestId) {
        this.checkoutRequestId = checkoutRequestId;
    }

    public String getResponseCode() {
        return this.responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDescription() {
        return this.responseDescription;
    }

    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }

    public String getCustomerMessage() {
        return this.customerMessage;
    }

    public void setCustomerMessage(String customerMessage) {
        this.customerMessage = customerMessage;
    }

    @Override
    public String toString() {
        return "StkPushResponse{" +
                "merchantRequestId='" + merchantRequestId + '\'' +
                ", checkoutRequestId='" + checkoutRequestId + '\'' +
                ", responseCode='" + responseCode + '\'' +
                ", responseDescription='" + responseDescription + '\'' +
                ", customerMessage='" + customerMessage + '\'' +
                ", requestId='" + requestId + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}

