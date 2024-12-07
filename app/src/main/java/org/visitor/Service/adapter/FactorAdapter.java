package org.visitor.Service.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.alarmamir.R;
import org.visitor.Service.presenter.SelectItemList;
import org.visitor.Service.presenter.model.AccHsbPrsnsKoliResponse;
import org.visitor.Service.presenter.model.HsbPrsnsKoli;
import org.visitor.Service.presenter.model.Moshtari;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class FactorAdapter extends RecyclerView.Adapter<FactorAdapter.MyViewHolder> {
    private List<HsbPrsnsKoli> listItemTemp=new ArrayList<>();
    private List<HsbPrsnsKoli> listItem;
    private Context context;
    private SelectItemList<HsbPrsnsKoli> selectItemGroupDb;
    //-----------------------------------------------
    public FactorAdapter(Context context,
                         List<HsbPrsnsKoli> listItem, SelectItemList<HsbPrsnsKoli> selectItemGroupDb) {
        this.listItem = listItem;
        this.listItemTemp.addAll(listItem);
        this.selectItemGroupDb = selectItemGroupDb;
        this.context = context;
    }

    @Override
    public FactorAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_acc_hsbprsns_koli, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FactorAdapter.MyViewHolder viewHolder, final int position) {
        HsbPrsnsKoli hsbPrsnsKoli = listItem.get(position);
        viewHolder.txtBrws.setText(hsbPrsnsKoli.getBrws()+"");
        viewHolder.txtDate.setText(hsbPrsnsKoli.getDate());
        viewHolder.txtNo.setText(hsbPrsnsKoli.getNo()+"");
        viewHolder.txtKind.setText(hsbPrsnsKoli.getKind());
        viewHolder.txtSharh.setText(hsbPrsnsKoli.getSharh());
        viewHolder.txtBed.setText(hsbPrsnsKoli.getBed()+"");
        viewHolder.txtBes.setText(hsbPrsnsKoli.getBes()+"");
        viewHolder.txtMande.setText(hsbPrsnsKoli.getMande()+"");
     //   viewHolder.txtStatus.setText(group.getStatus());

    }

    public void addItems(List<HsbPrsnsKoli> listItem) {
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
        public TextView txtBrws,txtDate,txtNo,txtKind,txtSharh,txtBed,txtBes,txtMande;

        public MyViewHolder(final View itemLayoutView) {
            super(itemLayoutView);
        //    UtilFonts.overrideFonts(context, itemLayoutView, UtilFonts.IRAN_SANS_NORMAL);

            txtBrws = itemLayoutView.findViewById(R.id.txtBrws);
            txtDate = itemLayoutView.findViewById(R.id.txtDate);
            txtNo = itemLayoutView.findViewById(R.id.txtNo);
            txtKind = itemLayoutView.findViewById(R.id.txtKind);
            txtSharh = itemLayoutView.findViewById(R.id.txtSharh);
            txtBed = itemLayoutView.findViewById(R.id.txtBed);
            txtBes = itemLayoutView.findViewById(R.id.txtBes);
            txtMande = itemLayoutView.findViewById(R.id.txtMande);


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