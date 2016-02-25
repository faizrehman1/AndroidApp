package com.example.adnan.panachatfragment.UTils;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

/**
 * Created by Adnan on 1/8/2016.
 */
public class Utils {
    private static Cloudinary cloudinary;



    public static Cloudinary cloudinary() {
        if (cloudinary == null) {
            cloudinary = new Cloudinary(ObjectUtils.asMap(
                    "cloud_name", "adnan-ahmed",
                    "api_key", "371584418575433",
                    "api_secret", "BQ9mpYeuRtHoPkW6f2MEeGONDXg"));
        }
        return cloudinary;
    }
}