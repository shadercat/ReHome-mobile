package com.example.rehome.models.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rehome.R;
import com.example.rehome.models.Trigger;

import java.util.List;

public class TriggerListAdapter extends RecyclerView.Adapter<TriggerListAdapter.TriggerViewHolder> {
    private LayoutInflater inflater;
    private List<Trigger> triggers;
    private Context context;
    private ItemActionHandler actionHandler;

    public TriggerListAdapter(Context context, List<Trigger> triggers) {
        inflater = LayoutInflater.from(context);
        this.triggers = triggers;
        this.context = context;
    }

    @NonNull
    @Override
    public TriggerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_trigger, parent, false);
        return new TriggerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TriggerViewHolder holder, int position) {
        Trigger trigger = triggers.get(holder.getAdapterPosition());
        holder.name.setText(trigger.getName());
    }

    public void setActionHandler(ItemActionHandler actionHandler) {
        this.actionHandler = actionHandler;
    }

    public void setTriggersList(List<Trigger> triggers) {
        this.triggers = triggers;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return triggers == null ? 0 : triggers.size();
    }

    public interface ItemActionHandler {
        void OnItemClick(int position);
    }

    public class TriggerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView name;

        public TriggerViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            name = itemView.findViewById(R.id.trigger_name);
        }

        @Override
        public void onClick(View v) {
            if (actionHandler != null) {
                actionHandler.OnItemClick(getLayoutPosition());
            }
        }
    }
}
