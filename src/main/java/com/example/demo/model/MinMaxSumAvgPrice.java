package com.example.demo.model;

public class MinMaxSumAvgPrice {
    private int min;
    private int max;
    private int sum;
    private int avg;

    public MinMaxSumAvgPrice() {
    }

    public MinMaxSumAvgPrice(int min, int max, int sum, int avg) {
        this.min = min;
        this.max = max;
        this.sum = sum;
        this.avg = avg;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getAvg() {
        return avg;
    }

    public void setAvg(int avg) {
        this.avg = avg;
    }

    @Override
    public String toString() {
        return "MinMaxSumAvgPrice{" +
                "min=" + min +
                ", max=" + max +
                ", sum=" + sum +
                ", avg=" + avg +
                '}';
    }
}
