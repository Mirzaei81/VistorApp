package org.visitor.Service.fragments;

import static android.content.ContentValues.TAG;
import com.google.android.material.textfield.TextInputLayout;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import org.alarmamir.R;
import org.visitor.Api;
import org.visitor.Service.presenter.ResultAnbarPresenter;
import org.visitor.Service.presenter.ResultMoshtariPresenter;
import org.visitor.Service.presenter.model.Anbar;
import org.visitor.Service.presenter.model.Moshtari;
import org.visitor.Service.presenter.model.MoshtariResponse;
import org.visitor.Tools.Databace.DataSaver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SubmitFactorFragment extends Fragment {
    private Snackbar snackbar;
    private Api api;
    private Activity  activity;
    private  AutoCompleteTextView moshtaries;
    private  AutoCompleteTextView anbars;
    private Integer selectedMoshtari;
    private int selectedAnbar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activity = getActivity();
        return inflater.inflate(R.layout.submit_factor_fragment,container,false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextInputLayout moshtari = view.findViewById(R.id.moshtari);
        TextInputLayout anbar = view.findViewById(R.id.anbar);
        DataSaver datasaver = new DataSaver(getContext());
        snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),"",100000);
        this.moshtaries =(AutoCompleteTextView)  moshtari.getEditText();
        this.anbars =(AutoCompleteTextView)  anbar.getEditText();
        api = new Api(getContext(),datasaver);
        api.getAnbars(resultAnbarPresenter);
        api.getMoshtaris(resultMoshtariPresenter);
    }


    private final ResultMoshtariPresenter  resultMoshtariPresenter = new ResultMoshtariPresenter() {
        @Override
        public void onErrorServer(String e) {
            activity.runOnUiThread(() -> {
                snackbar.setText(e);
                snackbar.show();
            });
        }
        @Override
        public void onErrorInternetConnection() {
            activity.runOnUiThread(() -> {
                snackbar.setText("please check you're internet connection");
                snackbar.show();
            });
        }
        @Override
        public void onSuccessResultSearch(MoshtariResponse response) {
            activity.runOnUiThread(() -> {
                Log.i(TAG, "success");

                Context context =  getContext();
                if(context!=null){
                    if (!response.getMoshtaris().isEmpty()){
                        List<String> moshtryNames = response.getMoshtaris()
                                .stream()
                                .map(Moshtari::getmName).collect(Collectors.toList());
                        moshtaries.setAdapter(new ArrayAdapter<String>(getContext(),R.layout.item,moshtryNames));

                    }else
                        moshtaries.setAdapter(new ArrayAdapter<String>(getContext(),R.layout.item,new ArrayList<>()));

                }

                moshtaries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        Moshtari selected =  response.getMoshtaris().stream().filter(
                                a->a.getmName().equals(adapterView.getSelectedItem().toString())).findFirst().orElse(null);
                        assert  selected !=null;
                        selectedAnbar =selected.getmCode();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {}
                });
            });
        }
    };
    private final ResultAnbarPresenter resultAnbarPresenter= new ResultAnbarPresenter() {
        @Override
        public void onErrorServer(String e) {
            activity.runOnUiThread(() -> {
                snackbar.setText(e);
                snackbar.show();
            });
        }
        @Override
        public void onErrorInternetConnection() {
            activity.runOnUiThread(() -> {
                snackbar.setText("please check you're internet connection");
                snackbar.show();
            });
        }
        @Override
        public void onFinish(ArrayList<Anbar> response) {
            activity.runOnUiThread(() -> {
                if (!response.isEmpty()){
                    List<String> moshtryNames = response
                            .stream()
                            .map(a ->a.aName).collect(Collectors.toList());
                    anbars.setAdapter(new ArrayAdapter<String>(getContext(),R.layout.item,moshtryNames));
                }else
                    anbars.setAdapter(new ArrayAdapter<String>(getContext(),R.layout.item,new ArrayList<>()));
                anbars.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        Anbar selected =  response.stream().filter(
                                a->a.aName.equals(adapterView.getSelectedItem().toString())).findFirst().orElse(null);
                        assert  selected !=null;
                       selectedAnbar =selected.aCode;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {}
                });
            });

        }
    };
}