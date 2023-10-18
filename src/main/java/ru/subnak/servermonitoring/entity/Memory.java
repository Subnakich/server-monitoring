package ru.subnak.servermonitoring.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Memory {
    @JsonProperty("total")
    private long total;
    @JsonProperty("free")
    private long free;

    @JsonProperty("used")
    private long used;

    public long getTotal() {
        return total;
    }

    public long getFree() {
        return free;
    }

    public long getUsed() {
        return used;
    }


    @Override
    public String toString() {
        return "Total: " + total + ", Free: " + free+ ", Used: " + used;
    }
}
