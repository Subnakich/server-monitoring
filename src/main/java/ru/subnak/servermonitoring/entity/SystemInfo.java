package ru.subnak.servermonitoring.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;

public class SystemInfo {

    @JsonProperty("memory")
    private Memory memory;

    @JsonProperty("cpuusage")
    private double cpuusage;

    public Memory getMemory() {
        return memory;
    }

    public double getCPUUsage() {
        return cpuusage;
    }

    public static SystemInfo fromJson(String json) {
        var gson = new Gson();
        return gson.fromJson(json, SystemInfo.class);
    }

    @Override
    public String toString() {
        return "Memory: " + memory + ", CPUUsage: " + cpuusage;
    }

}
