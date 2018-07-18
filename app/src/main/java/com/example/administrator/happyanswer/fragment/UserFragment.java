package com.example.administrator.happyanswer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.administrator.happyanswer.R;
import com.example.administrator.happyanswer.activity.UserSettingActivity;

public class UserFragment extends Fragment implements View.OnClickListener{
    private LinearLayout setting;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView(){
        setting= (LinearLayout) getActivity().findViewById(R.id.user_setting);
        setting.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.user_setting:
                Intent intent = new Intent(getActivity(),UserSettingActivity.class);
                getActivity().startActivity(intent);
                break;
        }
    }
}
