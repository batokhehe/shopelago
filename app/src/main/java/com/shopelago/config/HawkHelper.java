package com.shopelago.config;

import com.orhanobut.hawk.Hawk;
import com.shopelago.models.ForgotPassword;
import com.shopelago.models.User;

public class HawkHelper {

    public static void SetUser(User user){
        Hawk.put(Config.USER_KEY, user);
    }

    public static User GetUser(){
        return Hawk.get(Config.USER_KEY);
    }

    public static void DeleteUser(){
        Hawk.deleteAll();
    }

    public static void SetForgotPassword(ForgotPassword forgotPassword){Hawk.put(Config.RESET_PASS_KEY, forgotPassword);}

    public static ForgotPassword GetForgotPassword(){return Hawk.get(Config.RESET_PASS_KEY);}

}
