package com.example.globaldorm.model;

import lombok.Data;

@Data
public class Temp2M {
    private long max;
    private long min;

    public Temp2M() {}

    public Temp2M(long max, long min) {
        this.max = max;
        this.min = min;
    }

    public long getMax() {
        return max;
    }
    public void setMax(long max) {
        this.max = max;
    }
    public long getMin() {
        return min;
    }
    public void setMin(long min) {
        this.min = min;
    }
}
