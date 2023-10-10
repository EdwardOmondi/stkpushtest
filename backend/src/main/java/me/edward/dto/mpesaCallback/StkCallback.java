package me.edward.dto.mpesaCallback;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StkCallback {
    @JsonProperty("MerchantRequestID")
    private String merchantRequestId;
    @JsonProperty("CheckoutRequestID")
    private String checkoutRequestId;
    @JsonProperty("ResultCode")
    private int resultCode;
    @JsonProperty("ResultDesc")
    private String resultDesc;
    @JsonProperty("CallbackMetadata")
    private CallbackMetadata callbackMetadata;

    public StkCallback() {
    }

    public StkCallback(String merchantRequestId, String checkoutRequestId, int resultCode, String resultDesc, CallbackMetadata callbackMetadata) {
        this.merchantRequestId = merchantRequestId;
        this.checkoutRequestId = checkoutRequestId;
        this.resultCode = resultCode;
        this.resultDesc = resultDesc;
        this.callbackMetadata = callbackMetadata;
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

    public int getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultDesc() {
        return this.resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public CallbackMetadata getCallbackMetadata() {
        return this.callbackMetadata;
    }

    public void setCallbackMetadata(CallbackMetadata callbackMetadata) {
        this.callbackMetadata = callbackMetadata;
    }

    @Override
    public String toString() {
        return "StkCallback{" +
                "merchantRequestId='" + merchantRequestId + '\'' +
                ", checkoutRequestId='" + checkoutRequestId + '\'' +
                ", resultCode=" + resultCode +
                ", resultDesc='" + resultDesc + '\'' +
                ", callbackMetadata=" + callbackMetadata +
                '}';
    }
}
