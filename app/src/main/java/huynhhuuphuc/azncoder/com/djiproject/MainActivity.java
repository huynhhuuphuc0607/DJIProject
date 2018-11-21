package huynhhuuphuc.azncoder.com.djiproject;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import dji.sdk.base.BaseProduct;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    public static final String FLAG_CONNECTION_CHANGE = "dji_sdk_connection_change";
    private static BaseProduct mProduct;
    private static Handler mHandler;

    private static final String[] REQUIRED_PERMISSION_LIST = new String[]{
            Manifest.permission.VIBRATE,
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.WAKE_LOCK,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.CHANGE_WIFI_STATE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.BLUETOOTH,
            Manifest.permission.BLUETOOTH_ADMIN,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,
    };
    private List<String> missingPermission = new ArrayList<>();
    private AtomicBoolean isRegistrationInProgress = new AtomicBoolean(false);
    private static final int REQUEST_PERMISSION_CODE = 12345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            checkAndRequestPermissions();

        setContentView(R.layout.activity_main);

        //Initialize DJI SDK Manager
        mHandler = new Handler(Looper.getMainLooper());
    }

    private void checkAndRequestPermissions() {
        for(String eachPermission: REQUIRED_PERMISSION_LIST)
            if(ContextCompat.checkSelfPermission(this,eachPermission) != PackageManager.PERMISSION_GRANTED)
                missingPermission.add(eachPermission);

        //TODO: need to implement startSDKRegistration()
        if(missingPermission.isEmpty()) {
            //startSDKRegistration();
        }
        else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M)
        {
            showToast("Please grant the required permission(s)");
            ActivityCompat.requestPermissions(this,
                    missingPermission.toArray(new String[missingPermission.size()])
                    ,REQUEST_PERMISSION_CODE);
        }
    }

    private void showToast(final String s) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
