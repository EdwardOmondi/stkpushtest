package me.edward.dto.mpesaCallback;

public class Body {
    private StkCallback stkCallback;

    public Body() {
    }

    public Body(StkCallback stkCallback) {
        this.stkCallback = stkCallback;
    }

    public StkCallback getStkCallback() {
        return this.stkCallback;
    }

    public void setStkCallback(StkCallback stkCallback) {
        this.stkCallback = stkCallback;
    }

    @Override
    public String toString() {
        return "Body{" +
                "stkCallback=" + stkCallback +
                '}';
    }
}
