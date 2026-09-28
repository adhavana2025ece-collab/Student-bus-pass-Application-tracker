package com.buspass.studentbuspass.dto;

import jakarta.validation.constraints.NotBlank;

public class RejectionRequest {

    @NotBlank(message = "Rejection remark is required")
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}