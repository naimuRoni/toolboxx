package com.parkingkoi.service.toolbox.model.numbervalidate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NumValidation {

    @SerializedName("post_mobile")
    @Expose
    String postMobile;

    public NumValidation(String number){
        this.postMobile=number;
    }

    public String getPostMobile() {
        return postMobile;
    }

    public void setPostMobile(String postMobile) {
        this.postMobile = postMobile;
    }

}
