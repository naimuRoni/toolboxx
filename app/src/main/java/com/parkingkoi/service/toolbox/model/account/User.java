package com.parkingkoi.service.toolbox.model.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



    public class User {

        @SerializedName("post_mobile")
        @Expose
        private String userNumber;


        @SerializedName("post_pass")
        @Expose
        private String userPass;

        public User(String userNumber, String userPass) {
            this.userNumber = userNumber;
            this.userPass = userPass;
        }

        public String getUserNumber() {
            return userNumber;
        }

        public void setUserNumber(String userNumber) {
            this.userNumber = userNumber;
        }

        public String getUserPass() {
            return userPass;
        }

        public void setUserPass(String userPass) {
            this.userPass = userPass;
        }


    }

