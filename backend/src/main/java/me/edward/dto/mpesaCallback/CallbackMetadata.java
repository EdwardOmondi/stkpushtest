package me.edward.dto.mpesaCallback;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class CallbackMetadata {
    @JsonProperty("Item")
    private List<Item> itemList;

    public CallbackMetadata() {
    }

    public CallbackMetadata(List<Item> itemList) {
        this.itemList = itemList;
    }

    public List<Item> getItemList() {
        return this.itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    @Override
    public String toString() {
        return "CallbackMetadata{" +
                "item=" + itemList +
                '}';
    }

    public String findValue(String value){
        return Objects.requireNonNull(itemList.stream().filter(item -> Objects.equals(item.getName(), value)).findAny().orElse(null)).getValue();
    }
}
