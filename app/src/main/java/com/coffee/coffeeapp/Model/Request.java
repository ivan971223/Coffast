package com.coffee.coffeeapp.Model;

public class Request {
    private String address;
    private String comment;
    private String paymentState;
    private String formatAmount;

    public Request(String address, String comment, String paymentState, String formatAmount) {
        this.address = address;
        this.comment = comment;
        this.paymentState = paymentState;
        this.formatAmount = formatAmount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPaymentState() {
        return paymentState;
    }

    public void setPaymentState(String paymentState) {
        this.paymentState = paymentState;
    }

    public String getFormatAmount() {
        return formatAmount;
    }

    public void setFormatAmount(String formatAmount) {
        this.formatAmount = formatAmount;
    }
}
