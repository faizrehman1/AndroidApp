package com.example.adnan.panachatfragment.UTils;

import com.example.adnan.panachatfragment.Activities.AfterLoginScreen;
import com.firebase.client.Firebase;

/**
 * Created by Adnan on 1/19/2016.
 */
public class FireBaseHandler {
    public static FireBaseHandler innstance;

    public static FireBaseHandler getInstance() {
        if (innstance == null) {
            innstance = new FireBaseHandler();
        }

        return innstance;
    }

    public FireBaseHandler() {

        Firebase.setAndroidContext(AfterLoginScreen.context);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
    }
}
