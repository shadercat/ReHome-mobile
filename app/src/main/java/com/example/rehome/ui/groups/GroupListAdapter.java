package com.example.rehome.ui.groups;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rehome.R;
import com.example.rehome.models.ResourceGroup;

import java.util.List;

public class GroupListAdapter extends RecyclerView.Adapter<GroupListAdapter.GroupViewHolder> {
    private LayoutInflater inflater;
    private List<ResourceGroup> resourceGroups;
    private ItemActionHandler onClickHandler;
    private Context context;

    GroupListAdapter(Context context, List<ResourceGroup> resourceGroups) {
        this.resourceGroups = resourceGroups;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.group_item, parent, false);
        return new GroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {
        ResourceGroup resGroup = resourceGroups.get(holder.getAdapterPosition());
        holder.name.setText(resGroup.getName());
        holder.id.setText(context.getString(R.string.id_p, resGroup.getId()));
    }

    @Override
    public int getItemCount() {
        return resourceGroups == null ? 0 : resourceGroups.size();
    }

    public void setOnClickHandler(ItemActionHandler handler) {
        this.onClickHandler = handler;
    }

    public void setDeviceList(List<ResourceGroup> resourceGroups) {
        this.resourceGroups = resourceGroups;
        notifyDataSetChanged();
    }

    interface ItemActionHandler {
        void OnItemClick(int position);
    }

    public class GroupViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView name, id;

        public GroupViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            name = itemView.findViewById(R.id.res_group_name);
            id = itemView.findViewById(R.id.res_group_id);
        }

        @Override
        public void onClick(View v) {
            if (onClickHandler != null) {
                onClickHandler.OnItemClick(getLayoutPosition());
            }
        }
    }
}
