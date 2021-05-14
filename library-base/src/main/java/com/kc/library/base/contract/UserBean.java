package com.kc.library.base.contract;

/**
 * 登录完成后，组件间通信的契约类
 * Created by goldze on 2018/6/21.
 */

public class UserBean {
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
