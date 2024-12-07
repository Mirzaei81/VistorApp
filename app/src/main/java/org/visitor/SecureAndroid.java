package org.visitor;

import android.content.Context;
import android.provider.Settings;

/**
 * Created by renjer on 2017-12-26.
 */

public class SecureAndroid {
    public static String getSecureId(Context context) {
        try {
            return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        } catch (Exception e) {
            return "";
        }
    }
    //-----------------------------------------------

}
