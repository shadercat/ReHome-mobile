package com.example.rehome.ui.devices.info;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.rehome.R;
import com.example.rehome.models.DeviceInfo;

public class DeviceInfoActivity extends AppCompatActivity {
    public final static String DEVICE_CODE = "deviceCode";

    private DeviceInfoViewModel infoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_info);

        String deviceCode = "";
        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            deviceCode = (String) arguments.getSerializable(DEVICE_CODE);
        }

        infoViewModel = new ViewModelProvider(this).get(DeviceInfoViewModel.class);
        infoViewModel.getData(deviceCode).observe(this, new Observer<DeviceInfo>() {
            @Override
            public void onChanged(DeviceInfo deviceInfo) {

            }
        });
    }
}
