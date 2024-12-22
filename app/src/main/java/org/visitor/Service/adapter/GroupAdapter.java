package org.visitor.Service.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import org.alarmamir.R;
import org.visitor.Service.presenter.SelectItemList;
import org.visitor.Service.presenter.model.Groups;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.MyViewHolder> {
    private List<Groups> listItemTemp=new ArrayList<>();
    private List<Groups> listItem;
    private List<Groups> listRoom;
    private Context context;
    private SelectItemList<Groups> selectItemGroupDb;
    //-----------------------------------------------
    public GroupAdapter(Context context,
                        List<Groups> listItem, List<Groups> listRoom, SelectItemList<Groups> selectItemGroupDb) {
        this.listItem = listItem;
        this.listRoom = listRoom;
        this.listItemTemp.addAll(listItem);
        this.selectItemGroupDb = selectItemGroupDb;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_group, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final GroupAdapter.MyViewHolder viewHolder, final int position) {
        Groups kala = listItem.get(position);
        if (listRoom!=null) {
            for (Groups mkala : listRoom) {
                if (kala == mkala) {
//                    viewHolder.i.setVisibility(View.VISIBLE);
//                    viewHolder.txtCart.setVisibility(View.GONE);
//                    viewHolder.txtNumber.setText(String.valueOf(kala.()));
                }

            }
        }


    }

    public void addItems(List<Groups> listItem) {
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
        public TextView groupName;
        public MyViewHolder(final View itemLayoutView) {
            super(itemLayoutView);
            groupName=itemLayoutView.findViewById(android.R.id.text1);
//            selectItemGroupDb.onSelectItemForCats(listItem.get(getAdapterPosition()), getAdapterPosition(), txtName, txtNumber,true);
        }
//            itemLayoutView.setOnClickListener(v -> selectItemGroupDb.onSelectItem(listItem.get(getAdapterPosition()), getAdapterPosition(), txtName, null));
    }
    public void setFilter(String name) {
        this.listItem.clear();
        this.listItem.addAll(listItemTemp);
        List<Groups> listItem1=new ArrayList<>();
        for (Groups group:listItem){
            if (group.name.contains(name))
                listItem1.add(group);
        }
        this.listItem.clear();
        this.listItem.addAll(listItem1);
        notifyDataSetChanged();
    }
    public List<Groups> getList() {
        return listItem;
    }
    @Override
    public int getItemCount() {
        return listItem.size();
    }

}
