package org.visitor.Service.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.alarmamir.R;
import org.visitor.Service.presenter.model.SpinnerItem;

import java.util.List;

public class MoshtariDropDown extends ArrayAdapter<SpinnerItem> {

    public MoshtariDropDown(Context context, List<SpinnerItem> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.moshtri_dropdown, parent, false);


        SpinnerItem item = getItem(position);

        TextView left = view.findViewById(R.id.text_left);
        TextView right = view.findViewById(R.id.text_right);

        left.setText(item.left);
        right.setText(item.right);

        return view;
    }
}

