package com.example.rehome.ui.devices;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rehome.R;
import com.example.rehome.models.Device;
import com.example.rehome.ui.devices.info.DeviceInfoActivity;

import java.util.ArrayList;
import java.util.List;

public class DevicesFragment extends Fragment {

    private DevicesViewModel devicesViewModel;
    private List<Device> deviceList = new ArrayList<>();
    private RecyclerView deviceRecyclerView;
    private DeviceListAdapter listAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        devicesViewModel = new ViewModelProvider(getActivity()).get(DevicesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_devices, container, false);

        deviceRecyclerView = root.findViewById(R.id.device_recycler_view);

        listAdapter = new DeviceListAdapter(getContext(), deviceList);
        deviceRecyclerView.setAdapter(listAdapter);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        deviceRecyclerView.addItemDecoration(itemDecoration);

        devicesViewModel.getData().observe(getViewLifecycleOwner(), new Observer<List<Device>>() {
            @Override
            public void onChanged(List<Device> devices) {
                deviceList = devices;
                listAdapter.setDevicesList(deviceList);
            }
        });

        listAdapter.setOnClickHandler(new DeviceListAdapter.ItemActionHandler() {
            @Override
            public void onItemClick(int position) {
                Intent deviceInfoActivity = new Intent(getContext(), DeviceInfoActivity.class);
                deviceInfoActivity.putExtra(DeviceInfoActivity.DEVICE_CODE, deviceList.get(position).getCode());
                startActivity(deviceInfoActivity);
            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
