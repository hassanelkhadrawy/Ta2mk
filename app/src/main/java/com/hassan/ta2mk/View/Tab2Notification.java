package com.hassan.ta2mk.View;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hassan.ta2mk.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tab2Notification extends Fragment {


    public Tab2Notification() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab2_notification, container, false);
    }

}