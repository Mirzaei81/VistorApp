package org.visitor.Service.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.alarmamir.R;
import org.visitor.Service.presenter.SelectItemList;
import org.visitor.Service.presenter.model.AccHsbPrsnsKoliResponse;
import org.visitor.Service.presenter.model.HsbPrsnsKoli;
import org.visitor.Service.presenter.model.Moshtari;
import org.visitor.Service.view.FactorDetailActivity;
import org.visitor.Service.view.MainFactorActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


public class FactorAdapter extends RecyclerView.Adapter<FactorAdapter.MyViewHolder> {
    private final List<HsbPrsnsKoli> listItemTemp=new ArrayList<>();
    private final List<HsbPrsnsKoli> listItem;
    private final Context context;

    //-----------------------------------------------
    public FactorAdapter(Context context,List<HsbPrsnsKoli> listItem) {
        this.context = context;
        this.listItem = listItem;
        this.listItemTemp.addAll(listItem);
    }
    private View.OnClickListener onItemClick(int position){
        return view -> {
            Intent intent=new Intent(context, FactorDetailActivity.class);
            intent.putExtra(FactorDetailActivity.class.getName(),listItem.get(position).getNo());
            context.startActivity(intent);
        };
    }
    @NonNull
    @Override
    public FactorAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_acc_hsbprsns_koli, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final FactorAdapter.MyViewHolder viewHolder, final int position) {
        HsbPrsnsKoli hsbPrsnsKoli = listItem.get(position);
        if(hsbPrsnsKoli.getNo()==null){
            viewHolder.view.setBackgroundColor(Color.parseColor("#CCCCCC"));
        }
        if(Objects.equals(hsbPrsnsKoli.getKindFactor(), "F")){
            viewHolder.detailView.setOnClickListener(this.onItemClick(position));
            viewHolder.detailView.setImageResource(R.drawable.clipboard);
        }
        viewHolder.txtBrws.setText(String.format(Locale.forLanguageTag("en-US"),"%d", hsbPrsnsKoli.getBrws()));
        viewHolder.txtDate.setText(hsbPrsnsKoli.getDate());
        viewHolder.txtNo.setText(String.format(Locale.forLanguageTag("en-US"),"%d", hsbPrsnsKoli.getNo()));
        viewHolder.txtKind.setText(hsbPrsnsKoli.getKind());
        viewHolder.txtSharh.setText(hsbPrsnsKoli.getSharh());
        viewHolder.txtBed.setText(String.format("%s", hsbPrsnsKoli.getBed()));
        viewHolder.txtBes.setText(String.format("%s", hsbPrsnsKoli.getBes()));
        viewHolder.txtMande.setText(String.format(Locale.forLanguageTag("en-US"),"%d", hsbPrsnsKoli.getMande()));
     //   viewHolder.txtStatus.setText(group.getStatus());

    }

    public void addItems(List<HsbPrsnsKoli> listItem) {
        Collections.reverse(listItem);
        this.listItem.clear();
        this.listItem.addAll(listItem);
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtBrws,txtDate,txtNo,txtKind,txtSharh,txtBed,txtBes,txtMande;
        public View view;
        public ImageView detailView;

        public MyViewHolder(final View itemLayoutView) {
            super(itemLayoutView);
        //    UtilFonts.overrideFonts(context, itemLayoutView, UtilFonts.IRAN_SANS_NORMAL);
            view = itemLayoutView;
            txtBrws = itemLayoutView.findViewById(R.id.txtBrws);
            txtDate = itemLayoutView.findViewById(R.id.txtDate);
            txtNo = itemLayoutView.findViewById(R.id.txtNo);
            txtKind = itemLayoutView.findViewById(R.id.txtKind);
            txtSharh = itemLayoutView.findViewById(R.id.txtSharh);
            txtBed = itemLayoutView.findViewById(R.id.txtBed);
            txtBes = itemLayoutView.findViewById(R.id.txtBes);
            txtMande = itemLayoutView.findViewById(R.id.txtMande);
            detailView = itemLayoutView.findViewById(R.id.detail);
            /*itemLayoutView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectItemGroupDb.onSelectItem(listItem.get(getAdapterPosition()), getAdapterPosition(), txtName, null);
                }
            });*/
        }
    }
    /*public void setFilter(String name) {
        this.listItem.clear();
        this.listItem.addAll(listItemTemp);
        List<AccHsbPrsnsKoliResponse> listItem1=new ArrayList<>();
        for (AccHsbPrsnsKoliResponse accHsbPrsnsKoliResponse:listItem){
            if (moshtari.getmName().contains(name))
                listItem1.add(moshtari);
        }
        this.listItem.clear();
        this.listItem.addAll(listItem1);
        notifyDataSetChanged();
    }*/
    public List<HsbPrsnsKoli> getList() {
        return listItem;
    }
    @Override
    public int getItemCount() {
        return listItem.size();
    }

}