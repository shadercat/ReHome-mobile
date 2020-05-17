package com.example.rehome.ui.groups;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rehome.R;
import com.example.rehome.models.ResourceGroup;
import com.example.rehome.models.adapters.GroupListAdapter;
import com.example.rehome.ui.groups.info.GroupInfoActivity;

import java.util.ArrayList;
import java.util.List;

public class GroupsFragment extends Fragment {

    private GroupsViewModel groupsViewModel;
    private List<ResourceGroup> resourceGroupsList = new ArrayList<>();
    private RecyclerView groupRecyclerView;
    private GroupListAdapter groupListAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        groupsViewModel = new ViewModelProvider(getActivity()).get(GroupsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_groups, container, false);
        groupRecyclerView = root.findViewById(R.id.group_recycler_view);

        groupListAdapter = new GroupListAdapter(getContext(), resourceGroupsList);
        groupRecyclerView.setAdapter(groupListAdapter);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        groupRecyclerView.addItemDecoration(itemDecoration);

        groupsViewModel.getData().observe(getViewLifecycleOwner(), new Observer<List<ResourceGroup>>() {
            @Override
            public void onChanged(List<ResourceGroup> resourceGroups) {
                resourceGroupsList = resourceGroups;
                groupListAdapter.setDeviceList(resourceGroupsList);
            }
        });

        groupListAdapter.setOnClickHandler(new GroupListAdapter.ItemActionHandler() {
            @Override
            public void OnItemClick(int position) {
                Intent groupInfoActivity = new Intent(getContext(), GroupInfoActivity.class);
                groupInfoActivity.putExtra(GroupInfoActivity.RESOURCE_GROUP_ID, resourceGroupsList.get(position).getId());
                startActivity(groupInfoActivity);
            }
        });

        return root;
    }
}
