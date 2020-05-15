package com.example.rehome.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.rehome.ConstantUrls;
import com.example.rehome.R;
import com.example.rehome.connection.HttpClient;
import com.example.rehome.connection.ResponseHandler;
import com.example.rehome.models.User;
import com.example.rehome.ui.splash.EmptyActivity;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class UserFragment extends Fragment {

    private UserViewModel mViewModel;
    private TextView nameView;
    private TextView emailView;
    private TextView idView;
    private TextView dateView;
    private Button logoutBtn;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        nameView = view.findViewById(R.id.user_name);
        emailView = view.findViewById(R.id.user_email);
        idView = view.findViewById(R.id.user_id);
        dateView = view.findViewById(R.id.user_date);
        logoutBtn = view.findViewById(R.id.user_logout_btn);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(getActivity()).get(UserViewModel.class);
        mViewModel.getData().observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                nameView.setText(user.getName());
                emailView.setText(getString(R.string.email_p, user.getEmail()));
                idView.setText(getString(R.string.id_p, user.getId()));
                dateView.setText(getString(R.string.creation_date_p, user.getDate()));
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpClient.post(ConstantUrls.logout, null, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        if (ResponseHandler.IsSuccessed(response)) {
                            Intent startupActivity = new Intent(getContext(), EmptyActivity.class);
                            startActivity(startupActivity);
                            getActivity().finish();
                        }
                    }
                });
            }
        });
    }
}
