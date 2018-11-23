package com.coohua;

import io.appium.java_client.android.AndroidDriver;

public class BussinessLib extends AndroidDriverLib {
    //新设备跳过用户
    public void AnonymousUser(){
        super.newClik(ObjectStore.Button_Unregistered);
        super.newClik(ObjectStore.Button_GiveUp);
        CommonLib.waitTime(5);
    }

    //绑定手机
    public void tiePhone(String p_phoneNumber,String p_password, String p_code){
        super.newClik(ObjectStore.SignIn_MinePage);
        if (super.newIsElementPresent(ObjectStore.Popups_MSelfStarting)){
            super.newClik(ObjectStore.Button_X);
        }
        super.newClik(ObjectStore.Button_CollarRedEnvelope);
        CommonLib.waitTime(2);
        super.newType(ObjectStore.RegisterBox_User,p_phoneNumber);
        super.newClik(ObjectStore.Button_Next);
        CommonLib.waitTime(3);
        super.newType(ObjectStore.Login_VerCode,p_code);
        super.newType(ObjectStore.LoginBox_Pass,p_password);
        super.newClik(ObjectStore.Button_Register);

    }
    // 不填写邀请码注册
    public void sinUp(String p_phoneNumber,String p_password, String p_code){
        if(super.newIsElementPresent(ObjectStore.SignIn_WecChat)){
            super.newClik(ObjectStore.Button_ReceiveReward);
            CommonLib.waitTime(3);
        }else {
            super.newClik(ObjectStore.SignIn_register);
        }
        super.newType(ObjectStore.RegisterBox_User,p_phoneNumber);
        super.newClik(ObjectStore.Button_Next);
        CommonLib.waitTime(3);
        super.newType(ObjectStore.Login_VerCode,p_code);
        super.newType(ObjectStore.LoginBox_Pass,p_password);
        super.newClik(ObjectStore.Button_Register);

    }
//    //填写邀请码注册
    public void InvitationcodeSinUp(String p_phoneNumber,String p_password, String p_code,String p_Invitationcode){

        if(super.newIsElementPresent(ObjectStore.SignIn_WecChat)){
            super.newClik(ObjectStore.Button_ReceiveReward);
            CommonLib.waitTime(3);
        }else {
            super.newClik(ObjectStore.SignIn_register);
        }
        super.newType(ObjectStore.RegisterBox_User,p_phoneNumber);
        super.newClik(ObjectStore.Button_Next);
        CommonLib.waitTime(3);
        super.newType(ObjectStore.Login_VerCode,p_code);
        super.newType(ObjectStore.LoginBox_Pass,p_password);
        super.newType(ObjectStore.RegisterBox_InviteCode,p_Invitationcode);
        super.newClik(ObjectStore.Button_Register);

    }

  //用户登录
    public void Login(String p_userid,String p_password,String p_Code) {
        //super.newClik(ObjectStore.SignIn_Login);
        newType(ObjectStore.LoginBox_User,p_userid);
        newType(ObjectStore.LoginBox_Pass,p_password);
        newClik(ObjectStore.Button_Login);
        //判断是否需要切换设备
        if(newIsElementPresent(ObjectStore.Popups_SwitchingDevice)){
            newClik(ObjectStore.Button_Confirm);
            CommonLib.waitTime(3);
            newClik(ObjectStore.Button_Confirm);
            CommonLib.waitTime(5);
            newType(ObjectStore.Login_VerCode,p_Code);
            newClik(ObjectStore.Button_Next);
        }


    }
    //退出登录0
    public void LogOut(){
        AndroidDriver driver = null;
        super.newClik(ObjectStore.SignIn_MinePage);
        driver.scrollToExact("设置").click();

    }
//    //登录同盾规则验证（同一个设备一天最多只能登录两个账号）
//    public void AnticheatingLogin(){
//        int time=0;
//        do {
//            Login("1237","2334","2323");
//        }
//        while (!super.newIsElementPresent(ObjectStore.Button_CollarRedEnvelope));
//        System.out.println(time);
//    }



}
