
package org.visitor.Service.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.alarmamir.R;
import org.visitor.Service.presenter.model.FactorDetail;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
public class FactorDetailAdapter extends RecyclerView.Adapter<FactorDetailAdapter.FactorDetailViewHolder>{
    private final List<FactorDetail> listItem;
        //-----------------------------------------------
        public FactorDetailAdapter(
                             List<FactorDetail> listItem) {
            this.listItem = listItem;
        }

        @NonNull
        @Override
        public FactorDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.factor_detail_fragment, parent, false);
            return new FactorDetailViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final FactorDetailViewHolder viewHolder, final int position) {
            FactorDetail  factorDetail= listItem.get(position);
            if(factorDetail.fK_Num==0){
                viewHolder.view.setBackgroundColor(Color.parseColor("#CCCCCC"));
            }
            viewHolder.txtNO.setText(String.format(Locale.getDefault(),"%d",factorDetail.fK_Num ));
            viewHolder.txtCode.setText(factorDetail.k_Code);
            viewHolder.txtName.setText(factorDetail.k_Name);
            viewHolder.txtCount.setText(String.format(Locale.getDefault(),"%d",factorDetail.fK_koli));
            viewHolder.txtperPeckage.setText(String.format(Locale.getDefault(),"%d",factorDetail.k_zarib));
            viewHolder.txtPrice.setText(String.format(Locale.getDefault(), "%d",factorDetail.fK_Mab));
            viewHolder.txtTotal.setText(String.format(Locale.getDefault(), "%d",factorDetail.fK_Mab_koli));
            //   viewHolder.txtStatus.setText(group.getStatus());

        }

        public void addItems(List<FactorDetail> listItem) {
                Collections.reverse(listItem);
                this.listItem.clear();
                this.listItem.addAll(listItem);
                notifyItemInserted(1);
        }

        public static class FactorDetailViewHolder extends RecyclerView.ViewHolder {
            public TextView txtNO,txtCode,txtName,txtCount,txtperPeckage,txtPrice,txtTotal;
            public View view;

            public FactorDetailViewHolder(final View itemLayoutView) {
                super(itemLayoutView);
                //    UtilFonts.overrideFonts(context, itemLayoutView, UtilFonts.IRAN_SANS_NORMAL);
                view = itemLayoutView;
                txtNO = itemLayoutView.findViewById(R.id.No);
                txtCode = itemLayoutView.findViewById(R.id.code);
                txtName = itemLayoutView.findViewById(R.id.name);
                txtCount = itemLayoutView.findViewById(R.id.count);
                txtperPeckage = itemLayoutView.findViewById(R.id.perPeckage);
                txtPrice = itemLayoutView.findViewById(R.id.price);
                txtTotal = itemLayoutView.findViewById(R.id.total);
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
        public List<FactorDetail> getList() {
            return listItem;
        }
        @Override
        public int getItemCount() {
            return listItem.size();
        }
}
