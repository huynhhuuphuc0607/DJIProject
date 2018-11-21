package huynhhuuphuc.azncoder.com.djiproject;

import android.app.Application;
import android.content.Context;

import com.secneo.sdk.Helper;

public class MApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        //Developer needs to invoke this method before using any SDK functionality.
        //Failing to do so will result in unexpected crashes.
        Helper.install(MApplication.this);
    }
}
