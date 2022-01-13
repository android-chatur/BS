package com.example.barayihsalem.UI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VisionspinnerPojo {
    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public List<Row> getRow() {
        return row;
    }

    public void setRow(List<Row> row) {
        this.row = row;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "VisionspinnerPojo{" +
                "success=" + success +
                ", row=" + row +
                ", message='" + message + '\'' +
                '}';
    }

    @SerializedName("success")
    @Expose
    public Integer success;
    @SerializedName("row")
    @Expose
    public List<Row> row = null;
    @SerializedName("Message")
    @Expose
    public String message;


}
