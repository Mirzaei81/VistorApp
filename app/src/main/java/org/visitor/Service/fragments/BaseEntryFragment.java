package org.visitor.Service.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.RelativeLayout;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.alarmamir.R;
import org.visitor.Service.view.MainKalaActivity;
import org.visitor.Service.view.MainMoshtariActivity;

public class  BaseEntryFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.entery_data_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RelativeLayout mahsolat =  view.findViewById(R.id.btnFac);
        RelativeLayout persons = view.findViewById(R.id.btnPro);
        mahsolat.setOnClickListener(view1 ->{
                    Activity context =(Activity) view1.getContext();
                    Intent intent = new Intent(context, MainKalaActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
        );
        persons.setOnClickListener(view1 ->{
                    Activity context =(Activity) view1.getContext();
                    Intent intent = new Intent(context, MainMoshtariActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
        );
    }
}
