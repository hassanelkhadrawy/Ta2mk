package com.hassan.ta2mk.View;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.hassan.ta2mk.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tab3Account extends Fragment {


    private View MainView;
    private RelativeLayout fragmentProfile;
    private RelativeLayout background;
    private LinearLayout liner;
    private CircleImageView imgProfile;
    private TextView Name;
    private TextView Email;
    private TextView Phone;
    private TextView dashboard;
    private TextView Setting;
    private TextView Posts;
    private TextView Messages;
    private TextView Help;

    public Tab3Account() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        MainView = inflater.inflate(R.layout.fragment_tab3_account, container, false);
        initView();
        Action();
        return MainView;
    }

    private void initView() {
        fragmentProfile = (RelativeLayout) MainView.findViewById(R.id.fragmentProfile);
        background = (RelativeLayout) MainView.findViewById(R.id.background);
        liner = (LinearLayout)MainView. findViewById(R.id.liner);
        imgProfile = (CircleImageView)MainView. findViewById(R.id.img_profile);
        Name = (TextView) MainView.findViewById(R.id._name);
        Email = (TextView) MainView.findViewById(R.id._email);
        Phone = (TextView) MainView.findViewById(R.id._phone);
        dashboard = (TextView)MainView. findViewById(R.id._dashboard);
        Setting = (TextView)MainView. findViewById(R.id._setting);
        Posts = (TextView)MainView. findViewById(R.id._posts);
        Messages = (TextView)MainView. findViewById(R.id._messages);
        Help = (TextView) MainView.findViewById(R.id._help);
    }
    private void Action(){
        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Dashboard.class));
            }
        });
    }
}
