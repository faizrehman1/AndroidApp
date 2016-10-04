package com.example.faiz.cloudinary_testing;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

public class Utils {
  private static Cloudinary cloudinary;

    public static Cloudinary cloudinary() {
        if (cloudinary == null) {
            cloudinary = new Cloudinary(ObjectUtils.asMap(
                    "cloud_name", "fkcs14",
                    "api_key", "527495965545816",
                    "api_secret", "RI0k_mpmGjDa0TVkZABkVQwutf0"));
        }
        return cloudinary;
    }

}
