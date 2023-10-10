package me.edward.dto.mpesaCallback;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Value")
    private String value;

    public Item() {
    }

    public Item(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public Item(String name, double value) {
        this.name = name;
        this.value = String.valueOf(value);
    }

    public Item(String name, int value) {
        this.name = name;
        this.value = String.valueOf(value);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
