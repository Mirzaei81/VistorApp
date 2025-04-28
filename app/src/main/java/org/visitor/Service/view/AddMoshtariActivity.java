package org.visitor.Service.view;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import org.alarmamir.R;
import org.visitor.Api;
import org.visitor.Room.MyRoomDatabase;
import org.visitor.Service.fragments.listeners.FormDialogListener;
import org.visitor.Service.presenter.model.CreateMoshtariDTO;
import org.visitor.Service.presenter.model.GorohM;
import org.visitor.Service.presenter.model.Groups;

import java.util.ArrayList;
import java.util.List;

public class AddMoshtariActivity  extends DialogFragment {
    private FormDialogListener<CreateMoshtariDTO> listener;
    private List<GorohM>  mgrohs ;
    private int mGorohSelected = 0;
    private EditText   etMName,  etMMobil, etMAddress, etMMeli,
             etMPost,
            etMCity, etMOstan;
    Spinner etMHmkar;
    ArrayAdapter<String> adapter;
    private Api busApi;
    private Button btnSubmit;
    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null && getDialog().getWindow() != null) {

            Window window = getDialog().getWindow();
            // Remove background dim if you want transparent background
            // window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

            // Set transparent background if needed
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            // Set the dialog to match parent width and height (full screen)
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.MATCH_PARENT);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.activity_form, container, false);
        mgrohs = MyRoomDatabase.getAppDatabase(getContext()).moshtariDao().getAllGorohMs();
        etMName = view.findViewById(R.id.etMName);
        etMMobil = view.findViewById(R.id.etMMobil);
        etMAddress = view.findViewById(R.id.etMAddress);
        etMMeli = view.findViewById(R.id.etMMeli);
        etMHmkar = view.findViewById(R.id.etMHmkar);
        etMPost = view.findViewById(R.id.etMPost);
        etMCity = view.findViewById(R.id.etMCity);
        etMOstan = view.findViewById(R.id.etMOstan);
        List<String> mgrohsName = new ArrayList<String>();
        for(GorohM mgrohs: mgrohs){
            mgrohsName.add(mgrohs.getGmName());
        }
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item,mgrohsName );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        etMHmkar.setAdapter(adapter);

        btnSubmit = view.findViewById(org.alarmamir.R.id.btnSubmit);
        btnSubmit.setOnClickListener(v -> {
            CreateMoshtariDTO data = collectFormData();
            this.listener.onFormSubmited( data);
            dismiss();
        });
        etMHmkar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String selectedItem = (String) parent.getItemAtPosition(position);
            for(GorohM mgroh:mgrohs){
                if(mgroh.getGmName()==selectedItem){
                    mGorohSelected = mgroh.getGmCode();
                    break;
                }
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            // Handle no selection
        }
    });
        return view;
    }

    // 2. Set listener through method
    public void setFormDialogListener(FormDialogListener listener) {
        this.listener = listener;
    }


    private CreateMoshtariDTO collectFormData() {
        CreateMoshtariDTO data = new CreateMoshtariDTO();

        try {
            data.mName = etMName.getText().toString();
            data.mMobil = etMMobil.getText().toString();
            data.mAddress = etMAddress.getText().toString();
            data.mMeli = etMMeli.getText().toString();
            data.mHmkar = mGorohSelected;
            data.mPost = etMPost.getText().toString();
            data.mCity = etMCity.getText().toString();
            data.mOstan = etMOstan.getText().toString();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            // Handle number parsing errors
        }

        return data;
    }
}
