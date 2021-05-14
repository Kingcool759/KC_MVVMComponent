package com.kc.library.base.config;

/**
 * Created by goldze on 2018/6/21 0021.
 * 组件生命周期反射类名管理，在这里注册需要初始化的组件，通过反射动态调用各个组件的初始化方法
 * 注意：以下模块中初始化的Module类不能被混淆
 */

public class ModuleLifecycleReflexs {

    //基础模块
    private static final String BaseInit = "com.kc.base.base.BaseModuleInit";

    //主业务模块
    private static final String MainInit = "com.kc.main.MainModuleInit";

    //登录模块
    private static final String LoginInit = "com.kc.login.LoginModuleInit";
    //首页模块
    private static final String HomeInit = "com.kc.home.HomeModuleInit";
    //项目模块
    private static final String ProjectInit = "com.kc.project.ProjectModuleInit";
    //广场模块
    private static final String SquareInit = "com.kc.square.SquareModuleInit";
    //公众号模块
    private static final String WxInit = "com.kc.wx.WxModuleInit";
    //我的模块
    private static final String MyInit = "com.kc.My.MyModuleInit";

    public static String[] initModuleNames = {BaseInit, MainInit, LoginInit, HomeInit, WxInit, ProjectInit, SquareInit, MyInit};
}
