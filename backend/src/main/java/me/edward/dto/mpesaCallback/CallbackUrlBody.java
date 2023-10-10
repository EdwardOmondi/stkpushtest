package me.edward.dto.mpesaCallback;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CallbackUrlBody {
    @JsonProperty("Body")
    private Body body;

    public CallbackUrlBody() {
    }

    public CallbackUrlBody(Body body) {
        this.body = body;
    }

    public Body getBody() {
        return this.body;
    }

    public void setBody(Body body) {
        this.body = body;
    }
}

