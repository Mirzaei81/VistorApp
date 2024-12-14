package org.visitor.Service.adapter;

import android.content.Context;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import org.alarmamir.R;
import org.visitor.Service.presenter.SelectItemList;
import org.visitor.Service.presenter.model.Kala;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class KalaAdapter extends RecyclerView.Adapter<KalaAdapter.MyViewHolder> {
    private List<Kala> listItemTemp=new ArrayList<>();
    private List<Kala> listItem;
    private List<Kala> listRoom;
    private Context context;
    private SelectItemList<Kala> selectItemGroupDb;
    //-----------------------------------------------
    public KalaAdapter(Context context,
                       List<Kala> listItem,List<Kala> listRoom, SelectItemList<Kala> selectItemGroupDb) {
        this.listItem = listItem;
        this.listRoom = listRoom;
        this.listItemTemp.addAll(listItem);
        this.selectItemGroupDb = selectItemGroupDb;
        this.context = context;
    }

    @Override
    public KalaAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_kala, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final KalaAdapter.MyViewHolder viewHolder, final int position) {
        Kala kala = listItem.get(position);
        viewHolder.txtName.setText(kala.getkName());
        viewHolder.txtMojodi.setText("");
        if (null!=kala.getkPic()) {
            final String pureBase64Encoded = kala.getkPic().substring(kala.getkPic().indexOf(",") + 1);
            final byte[] decodedBytes = Base64.decode(pureBase64Encoded, Base64.DEFAULT);
            Glide.with(context).load(decodedBytes).transform(new CenterCrop(),new RoundedCorners(25)).into(viewHolder.img);
        }
        if (null!=listRoom) {
            for (Kala mkala : listRoom) {
                if (kala == mkala) {
                    viewHolder.lytNumbric.setVisibility(View.VISIBLE);
                    viewHolder.txtCart.setVisibility(View.GONE);
//                    viewHolder.txtNumber.setText(String.valueOf(kala.()));
                }

            }
        }


    }

    public void addItems(List<Kala> listItem) {
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
        public TextView txtName,txtCart,txtNumber;
        public TextView txtMojodi;
        public ImageView img,imgAdd,imgRemove;
        public RelativeLayout lytNumbric;
        public MyViewHolder(final View itemLayoutView) {
            super(itemLayoutView);
        //    UtilFonts.overrideFonts(context, itemLayoutView, UtilFonts.IRAN_SANS_NORMAL);
            txtNumber=itemLayoutView.findViewById(R.id.txtNumber);
            txtMojodi=itemLayoutView.findViewById(R.id.txtMojodi);
            txtName = itemLayoutView.findViewById(R.id.txtName);
            txtCart=itemLayoutView.findViewById(R.id.txtCart);
            img=itemLayoutView.findViewById(R.id.img);
            imgAdd=itemLayoutView.findViewById(R.id.imgAdd);
            imgRemove=itemLayoutView.findViewById(R.id.imgRemove);
            lytNumbric=itemLayoutView.findViewById(R.id.lytNumbric);

            imgAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectItemGroupDb.onSelectItemForCats(listItem.get(getAdapterPosition()), getAdapterPosition(), txtName, txtNumber,true);
                }
            });
            imgRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectItemGroupDb.onSelectItemForCats(listItem.get(getAdapterPosition()), getAdapterPosition(), txtName, txtNumber,false);
                }
            });
            txtCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectItemGroupDb.onSelectItemForCats(listItem.get(getAdapterPosition()), getAdapterPosition(), txtName, txtNumber,true);
                    lytNumbric.setVisibility(View.VISIBLE);
                    txtCart.setVisibility(View.GONE);
                }
            });
            itemLayoutView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectItemGroupDb.onSelectItem(listItem.get(getAdapterPosition()), getAdapterPosition(), txtName, null);

                }
            });
        }
    }
    public void setFilter(String name) {
        this.listItem.clear();
        this.listItem.addAll(listItemTemp);
        List<Kala> listItem1=new ArrayList<>();
        for (Kala kala:listItem){
            if (kala.getkName().contains(name))
                listItem1.add(kala);
        }
        this.listItem.clear();
        this.listItem.addAll(listItem1);
        notifyDataSetChanged();
    }
    public List<Kala> getList() {
        return listItem;
    }
    @Override
    public int getItemCount() {
        return listItem.size();
    }

}