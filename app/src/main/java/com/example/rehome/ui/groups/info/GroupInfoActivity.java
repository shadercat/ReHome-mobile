package com.example.rehome.ui.groups.info;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rehome.R;
import com.example.rehome.models.Device;
import com.example.rehome.models.ResourceGroup;
import com.example.rehome.ui.devices.DeviceListAdapter;
import com.example.rehome.ui.devices.info.DeviceInfoActivity;

import java.util.ArrayList;
import java.util.List;

public class GroupInfoActivity extends AppCompatActivity {
    public final static String RESOURCE_GROUP_ID = "resGroupId";

    private GroupInfoViewModel infoViewModel;
    private ImageView backArrow;
    private TextView name;
    private TextView description;
    private TextView createdAt;
    private TextView resId;
    private RecyclerView recyclerView;
    private Animation rotation;
    private Animation shaking;
    private List<Device> deviceList = new ArrayList<>();
    private DeviceListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_info);

        backArrow = findViewById(R.id.group_info_back_arrow);
        name = findViewById(R.id.group_info_name);
        description = findViewById(R.id.group_info_description);
        createdAt = findViewById(R.id.group_info_createdAt);
        resId = findViewById(R.id.group_info_id);
        recyclerView = findViewById(R.id.group_info_devices_list);

        rotation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        shaking = AnimationUtils.loadAnimation(this, R.anim.shake);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        String resourceId = "";
        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            resourceId = (String) arguments.getSerializable(RESOURCE_GROUP_ID);
        }

        loadingStartFeedback();
        infoViewModel = new ViewModelProvider(this).get(GroupInfoViewModel.class);
        infoViewModel.getGroupData(resourceId).observe(this, new Observer<ResourceGroup>() {
            @Override
            public void onChanged(ResourceGroup resourceGroup) {
                name.setText(resourceGroup.getName());
                description.setText(getString(R.string.group_info_description_p, resourceGroup.getDescription()));
                createdAt.setText(getString(R.string.group_info_createdAt_p, resourceGroup.getCreatedAt()));
                resId.setText(getString(R.string.id_p, resourceGroup.getId()));
                loadingStopFeedback();
            }
        });

        listAdapter = new DeviceListAdapter(this, deviceList);
        recyclerView.setAdapter(listAdapter);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        infoViewModel.getDevicesList(resourceId).observe(this, new Observer<List<Device>>() {
            @Override
            public void onChanged(List<Device> devices) {
                deviceList = devices;
                listAdapter.setDevicesList(deviceList);
            }
        });

        listAdapter.setOnClickHandler(new DeviceListAdapter.ItemActionHandler() {
            @Override
            public void onItemClick(int position) {
                Intent deviceInfoActivity = new Intent(getApplicationContext(), DeviceInfoActivity.class);
                deviceInfoActivity.putExtra(DeviceInfoActivity.DEVICE_CODE, deviceList.get(position).getCode());
                startActivity(deviceInfoActivity);
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
