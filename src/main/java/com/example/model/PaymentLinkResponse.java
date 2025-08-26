package com.example.model;

public class PaymentLinkResponse {
    private String approvalUrl;

    public PaymentLinkResponse(String approvalUrl) {
        this.approvalUrl = approvalUrl;
    }

    public String getApprovalUrl() {
        return approvalUrl;
    }

    public void setApprovalUrl(String approvalUrl) {
        this.approvalUrl = approvalUrl;
    }
}
