package org.visitor.Service.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.alarmamir.R;
import org.visitor.Service.presenter.SelectItemList;
import org.visitor.Service.presenter.model.Groups;
import org.visitor.Service.view.FactorDetailActivity;
import org.visitor.Service.view.MainKalaActivity;

import java.util.ArrayList;
import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.GroupViewHolder> {
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
    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_group, parent, false);
        return new GroupViewHolder(view);
    }
    private View.OnClickListener onItemClick(int position){
        Groups group = listItem.get(position);
        return view -> {
            Intent intent = new Intent(context, MainKalaActivity.class);
            intent.putExtra(MainKalaActivity.class.getName(),group.id);
            context.startActivity(intent);
        };
    }
    @Override
    public void onBindViewHolder(@NonNull final GroupViewHolder viewHolder, final int position) {
        Groups group = listItem.get(position);
        viewHolder.groupName.setText(group.name);
        viewHolder.groupView.setOnClickListener(onItemClick(position));
        }
    public void addItems(List<Groups> listItem) {
            this.listItem.clear();
            this.listItem.addAll(listItem);
            notifyItemInserted(listItem.size());
    }
    public static class GroupViewHolder extends RecyclerView.ViewHolder {
        public TextView groupName;
        public View groupView;
        public GroupViewHolder(final View itemLayoutView) {
            super(itemLayoutView);
            groupName=itemLayoutView.findViewById(R.id.group_txt);
            groupView = itemLayoutView;

        }
    }

}
