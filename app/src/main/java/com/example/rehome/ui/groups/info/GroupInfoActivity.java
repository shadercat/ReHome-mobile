package com.example.rehome.ui.groups.info;

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
import com.example.rehome.models.ResourceGroup;

public class GroupInfoActivity extends AppCompatActivity {
    public final static String RESOURCE_GROUP_ID = "resGroupId";

    private GroupInfoViewModel infoViewModel;
    private ImageView backArrow;
    private TextView name;
    private TextView description;
    private TextView createdAt;
    private TextView resId;
    private Animation rotation;
    private Animation shaking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_info);

        backArrow = findViewById(R.id.group_info_back_arrow);
        name = findViewById(R.id.group_info_name);
        description = findViewById(R.id.group_info_description);
        createdAt = findViewById(R.id.group_info_createdAt);
        resId = findViewById(R.id.group_info_id);

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
