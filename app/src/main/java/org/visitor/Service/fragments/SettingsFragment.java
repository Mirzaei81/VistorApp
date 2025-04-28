package org.visitor.Service.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.alarmamir.R;
import org.visitor.Service.view.GroupActivity;
import org.visitor.Service.view.LoginActivity;
import org.visitor.Service.view.MainActivity;
import org.visitor.Service.view.MainMoshtariActivity;
import org.visitor.Tools.Databace.DataSaver;

import java.util.Objects;

public class SettingsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.settings_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RelativeLayout logout=  view.findViewById(R.id.btnFac);
        DataSaver dataSaver = new DataSaver(getContext());

        logout.setOnClickListener((l)->{
            dataSaver.rmConfig();
            startActivity(new Intent(getContext(), LoginActivity.class));
            requireActivity().finish();
        });

    }

}
