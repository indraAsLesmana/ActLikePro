package com.tutor93.indraaguslesmana.actlikepro.api;

/**
 * Created by rifqi on Apr 22, 2016.
 */
public class AuthRequest {
    // regular authentication
    public String email;
    public String password;

    // check for facebook authentication
    public String facebook_id;
    public String facebook_token;

    // sign up
    public String name;
    public String birthday;
    public String phone;
    public String secondary_phone;
    public String promo_code;

    // switch account
    public String target_role;

    // update FCM reg token
    public String fcm_reg_token;

    // update password
    public String current_password;
    public String new_password;
    public String confirm_new_password;
}
