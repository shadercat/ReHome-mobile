package com.example.rehome.ui.devices.info;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.rehome.R;
import com.example.rehome.models.DeviceInfo;

public class DeviceInfoActivity extends AppCompatActivity {
    public final static String DEVICE_CODE = "deviceCode";

    private DeviceInfoViewModel infoViewModel;
    private ImageView backArrow;
    private TextView name;
    private TextView code;
    private TextView status;
    private TextView state;
    private TextView type;
    private TextView createdAt;
    private Animation rotation;
    private Animation shaking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_info);

        backArrow = findViewById(R.id.device_info_back_arrow);
        name = findViewById(R.id.device_info_name);
        code = findViewById(R.id.device_info_device_code);
        status = findViewById(R.id.device_info_status);
        state = findViewById(R.id.device_info_state);
        type = findViewById(R.id.device_info_type);
        createdAt = findViewById(R.id.device_info_createdAt);

        rotation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        shaking = AnimationUtils.loadAnimation(this, R.anim.shake);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        loadingStartFeedback();

        String deviceCode = "";
        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            deviceCode = (String) arguments.getSerializable(DEVICE_CODE);
        }

        infoViewModel = new ViewModelProvider(this).get(DeviceInfoViewModel.class);
        infoViewModel.getData(deviceCode).observe(this, new Observer<DeviceInfo>() {
            @Override
            public void onChanged(DeviceInfo deviceInfo) {
                name.setText(deviceInfo.getName());
                code.setText(getString(R.string.device_info_code_p, deviceInfo.getCode()));
                status.setText(getString(R.string.device_info_status_p, deviceInfo.getStatus()));
                state.setText(getString(R.string.device_info_state_p, deviceInfo.getState()));
                type.setText(getString(R.string.device_info_type_p, deviceInfo.getType()));
                createdAt.setText(getString(R.string.device_info_regDate_p, deviceInfo.getCreatedAt()));

                loadingStopFeedback();
            }
        });
    }

    private void loadingStartFeedback() {
        backArrow.setImageResource(R.drawable.ic_cached_black_24dp);
        backArrow.startAnimation(rotation);
    }

    private void loadingStopFeedback() {
        backArrow.setImageResource(R.drawable.ic_arrow_back_black_24dp);
        backArrow.startAnimation(shaking);
    }

}
