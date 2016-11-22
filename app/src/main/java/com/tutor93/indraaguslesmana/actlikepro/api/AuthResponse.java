package com.tutor93.indraaguslesmana.actlikepro.api;

import java.io.Serializable;

/**
 * Created by rifqi on Apr 20, 2016.
 */
public class AuthResponse {

    public static class FacebookUser implements Serializable {
        public String facebook_id;
        public String name;
        public String email;
        public String birthday;
        public String gender;
    }
}
