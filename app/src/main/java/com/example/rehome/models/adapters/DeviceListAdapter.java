package com.example.rehome.models.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rehome.R;
import com.example.rehome.models.Device;

import java.util.List;


public class DeviceListAdapter extends RecyclerView.Adapter<DeviceListAdapter.DeviceViewHolder> {
    private LayoutInflater inflater;
    private List<Device> devices;
    private ItemActionHandler onClickHandler;
    private Context context;

    public DeviceListAdapter(Context context, List<Device> devices) {
        this.devices = devices;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }


    @NonNull
    @Override
    public DeviceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_device, parent, false);
        return new DeviceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceViewHolder holder, int position) {
        if (position != devices.size()) {
            Device device = devices.get(holder.getAdapterPosition());
            holder.name.setText(device.getName());
            holder.code.setText(context.getString(R.string.device_code_p, device.getCode()));
            holder.state.setText(device.getState());
            holder.status.setText(device.getStatus());
        }
    }

    @Override
    public int getItemCount() {
        return devices == null ? 0 : devices.size();
    }

    public void setOnClickHandler(ItemActionHandler handler) {
        this.onClickHandler = handler;
    }

    public void setDevicesList(List<Device> devices) {
        this.devices = devices;
        notifyDataSetChanged();
    }

    public interface ItemActionHandler {
        void onItemClick(int position);
    }

    public class DeviceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView name, code, state, status;

        public DeviceViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            name = itemView.findViewById(R.id.device_name);
            code = itemView.findViewById(R.id.device_code);
            state = itemView.findViewById(R.id.device_state);
            status = itemView.findViewById(R.id.device_status);
        }

        @Override
        public void onClick(View v) {
            if (onClickHandler != null) {
                onClickHandler.onItemClick(getLayoutPosition());
            }
        }
    }
}
