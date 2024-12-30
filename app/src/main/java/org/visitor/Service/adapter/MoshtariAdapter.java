package org.visitor.Service.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.alarmamir.R;
import org.visitor.Service.presenter.SelectItemList;
import org.visitor.Service.presenter.model.Moshtari;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;


public class MoshtariAdapter extends RecyclerView.Adapter<MoshtariAdapter.MyViewHolder> {
    private List<Moshtari> listItemTemp=new ArrayList<>();
    private List<Moshtari> listItem;
    private Context context;
    private SelectItemList<Moshtari> selectItemGroupDb;
    //-----------------------------------------------
    public MoshtariAdapter(Context context,
                           List<Moshtari> listItem, SelectItemList<Moshtari> selectItemGroupDb) {
        this.listItem = listItem;
        this.listItemTemp.addAll(listItem);
        this.selectItemGroupDb = selectItemGroupDb;
        this.context = context;
    }

    @NonNull
    @Override
    public MoshtariAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_moshtari, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MoshtariAdapter.MyViewHolder viewHolder, final int position) {
        Moshtari group = listItem.get(position);
        viewHolder.txtName.setText(group.getmName());
        viewHolder.txtCode.setText(String.format(Locale.getDefault(),"%d",group.getmCode()));

    }

    public void addItems(List<Moshtari> listItem) {
        try {
            Collections.reverse(listItem);
            this.listItem.clear();
            this.listItem.addAll(listItem);
            notifyDataSetChanged();
        }
        catch (Exception e){

        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName;
        public TextView txtCode;
        public MyViewHolder(final View itemLayoutView) {
            super(itemLayoutView);
            txtName = itemLayoutView.findViewById(R.id.txtName);
            txtCode = itemLayoutView.findViewById(R.id.txtCode);
            itemLayoutView.setOnClickListener(v -> selectItemGroupDb.onSelectItem(listItem.get(getAdapterPosition()), getAdapterPosition(), txtName, null));
        }
    }
    public void setFilter(String name) {
        this.listItem.clear();
        this.listItem.addAll(listItemTemp);
        List<Moshtari> listItem1=new ArrayList<>();
        for (Moshtari moshtari:listItem){
            if (moshtari.getmName().contains(name))
                listItem1.add(moshtari);
        }
        this.listItem.clear();
        this.listItem.addAll(listItem1);
        notifyDataSetChanged();
    }
    public List<Moshtari> getList() {
        return listItem;
    }
    @Override
    public int getItemCount() {
        return listItem.size();
    }

}