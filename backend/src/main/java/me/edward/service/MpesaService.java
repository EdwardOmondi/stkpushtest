package me.edward.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.edward.dto.AuthorizeResponse;
import me.edward.dto.StkPushResponse;
import me.edward.dto.mpesaCallback.CallbackUrlBody;
import me.edward.dto.mpesaCallback.StkCallback;
import me.edward.model.Transaction;
import me.edward.repository.ConfigurationRepository;
import me.edward.repository.TransactionRepository;
import okhttp3.*;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

@Service

public class MpesaService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final TransactionRepository transactionRepository;
    private final ConfigurationRepository configurationRepository;
    @Value("${mpesa.oauth.url}")
    private String mpesaOauthUrl;
    @Value("${mpesa.stkpush.url}")
    private String mpesaStkpushUrl;
    @Value("${mpesa.consumer.key}")
    private String consumerKey;
    @Value("${mpesa.consumer.secret}")
    private String consumerSecret;
    @Value("${mpesa.passkey}")
    private String passKey;
    @Value("${mpesa.callback.url}")
    private String callbackUrl;
    @Value("${mpesa.paybill}")
    private String businessShortcode;

    public MpesaService(TransactionRepository transactionRepository, ConfigurationRepository configurationRepository) {
        this.transactionRepository = transactionRepository;
        this.configurationRepository = configurationRepository;
    }

    private static StkCallback getStkCallback(CallbackUrlBody body) {
        return body.getBody().getStkCallback();
    }

    private static void populateTransaction(CallbackUrlBody body, Transaction transaction) {
        transaction.setMerchantRequestId(getStkCallback(body).getMerchantRequestId());
        transaction.setCheckoutRequestId(getStkCallback(body).getCheckoutRequestId());
        transaction.setResultCode(getStkCallback(body).getResultCode());
        transaction.setResultDesc(getStkCallback(body).getResultDesc());
    }

    private String getTimestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date currentDate = new Date();
        return dateFormat.format(currentDate);
    }

    private Date getDate(String date) {
        try {
            return new SimpleDateFormat("yyyyMMddHHmmss").parse(date);
        } catch (ParseException error) {
            logger.error("getDate:\nerror => " + error);
            throw new RuntimeException(error);
        }
    }

    public StkPushResponse mpesaExpress(String phoneNumber, String passedCallback) {
        String timestamp = getTimestamp();
        final String rawPassword = businessShortcode + passKey + timestamp;
        String encodedPassword = Base64.getEncoder().encodeToString(rawPassword.getBytes());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("BusinessShortCode", businessShortcode);
        jsonObject.put("Password", encodedPassword);
        jsonObject.put("Timestamp", timestamp);
        jsonObject.put("TransactionType", "CustomerPayBillOnline");
        jsonObject.put("Amount", 1);
        jsonObject.put("PartyA", phoneNumber);
        jsonObject.put("PartyB", businessShortcode);
        jsonObject.put("PhoneNumber", phoneNumber);
        if (passedCallback.length() == 0) {
            jsonObject.put("CallBackURL", callbackUrl);
        } else {
            logger.info("mpesaExpress:\npassedCallback => " + passedCallback);
            jsonObject.put("CallBackURL", passedCallback);
        }
        jsonObject.put("AccountReference", "Edward Omondi Test");
        jsonObject.put("TransactionDesc", "Stk Push Implementation");
        logger.info("mpesaExpress:\njsonObject => " + jsonObject);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), String.valueOf(jsonObject));
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder().url(mpesaStkpushUrl).method("POST", body)
                .addHeader("Content-Type", "application/json; utf-8")
                .addHeader("Authorization", "Bearer " + authorizeRequest().getAccessToken()).build();
        try (Response response = client.newCall(request).execute()) {
            ObjectMapper objectMapper = new ObjectMapper();
            assert response.body() != null;
            StkPushResponse stkPushResponse = objectMapper.readValue(response.body().string(), StkPushResponse.class);
            logger.info("mpesaExpress:\nstkPushResponse => " + stkPushResponse);
            return stkPushResponse;
        } catch (IOException error) {
            logger.error("mpesaExpress:\nerror.getMessage() => " + error.getMessage());
            throw new RuntimeException(error);
        }
    }

    public AuthorizeResponse authorizeRequest() {
        String combinedKeySecret = String.format("%s:%s", consumerKey, consumerSecret);
        String base64EncodedString = Base64.getEncoder()
                .encodeToString(combinedKeySecret.getBytes(StandardCharsets.ISO_8859_1));
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder().url(mpesaOauthUrl).method("GET", null)
                .addHeader("Authorization", "Basic " + base64EncodedString).build();

        try (Response response = client.newCall(request).execute()) {
            ObjectMapper objectMapper = new ObjectMapper();
            assert response.body() != null;
            AuthorizeResponse authorizeResponse = objectMapper.readValue(response.body().string(),
                    AuthorizeResponse.class);
            logger.info("authorizeRequest:\nauthorizeResponse => " + authorizeResponse);
            return authorizeResponse;
        } catch (IOException error) {
            logger.error("mpesaExpress:\nerror.getMessage() => " + error.getMessage());
            throw new RuntimeException(error);
        }
    }

    public Transaction saveCallback(CallbackUrlBody body) {
        Transaction transaction = new Transaction();

        if (getStkCallback(body).getResultCode() == 0) {
            populateTransaction(body, transaction);
            transaction.setAmount(Double.parseDouble(getStkCallback(body).getCallbackMetadata().findValue("Amount")));
            transaction
                    .setMpesaReceiptNumber(getStkCallback(body).getCallbackMetadata().findValue("MpesaReceiptNumber"));
            transaction.setTransactionDate(getDate(
                    getStkCallback(body).getCallbackMetadata().findValue("TransactionDate")));
            transaction.setPhoneNumber(
                    Long.parseLong(getStkCallback(body).getCallbackMetadata().findValue("PhoneNumber")));
        } else {
            populateTransaction(body, transaction);
        }
        try {
            Transaction saveResult = transactionRepository.save(transaction);
            logger.info("saveCallback:\nsaveResult => " + saveResult);
        } catch (Exception error) {
            logger.error("saveCallback:\nerror.getMessage() => " + error.getMessage());
            throw new RuntimeException(error);
        }
        return transaction;
    }

    public Page<Transaction> getTransactions(Pageable pageable) {
        Page<Transaction> result = transactionRepository.findAll(pageable);
        logger.info("getTransactions:\nresult => " + result.getContent());
        return result;
    }
}
