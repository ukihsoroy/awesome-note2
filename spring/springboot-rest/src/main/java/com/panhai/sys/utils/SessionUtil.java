package com.panhai.sys.utils;

public class SessionUtil {

    public static UserInfoBo getUser() {
        return new UserInfoBo();
    }

    public static class UserInfoBo {

        private String userId;

        public static UserInfoBo newInstance () {
            return new UserInfoBo();
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
