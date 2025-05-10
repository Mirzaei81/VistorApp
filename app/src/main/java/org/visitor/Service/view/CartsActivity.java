package org.visitor.Service.view;

import static android.view.View.VISIBLE;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import org.alarmamir.R;
import org.visitor.Api;
import org.visitor.Room.AppExecutors;
import org.visitor.Room.MyRoomDatabase;
import org.visitor.Service.adapter.CartAdapter;
import org.visitor.Service.adapter.MoshtariDropDown;
import org.visitor.Service.presenter.ResultFactorSubmitPresenter;
import org.visitor.Service.presenter.SelectItemList;
import org.visitor.Service.presenter.model.ConfigResponse;
import org.visitor.Service.presenter.model.FactorDetail;
import org.visitor.Service.presenter.model.FactorResponse;
import org.visitor.Service.presenter.model.Kala;
import org.visitor.Service.presenter.model.Moshtari;
import org.visitor.Service.presenter.model.SpinnerItem;
import org.visitor.Tools.Databace.DataSaver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class CartsActivity extends BaseActivity {
    private CartAdapter cartAdapter;
    private RecyclerView list;
    private Api busApi;
    private ProgressBar loading;
    private DataSaver dataSaver;
    public Snackbar snackbar;
    TextView txtTitle;
    ImageView imgBack;
    MyRoomDatabase myRoomDatabase;
    List<Kala>kalaList;
    private List<SpinnerItem>  items = new ArrayList<SpinnerItem>();
    private List<Moshtari> moshtaris;
    private TextView txtBuy;
    private Moshtari buyer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataSaver = new DataSaver(CartsActivity.this);
        if(!dataSaver.hasLogin()){
           startActivity(new Intent(this,LoginActivity.class));
           finish();
        }
        init();
    }

    private void showConfigMoshtariDialog(String mName) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ویزیتور خود را انتخاب کنید");
        ConfigResponse configResponse = dataSaver.getConfig();
        // Create Spinner programmatically
        Spinner spinner = new Spinner(this) ;

        MoshtariDropDown adapter = new MoshtariDropDown(this,items);
        spinner.setAdapter(adapter);

        // Add Spinner to dialog
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(spinner);
        builder.setView(layout);
        String  visitorName = items.get(spinner.getSelectedItemPosition()).left;
        int mCode = moshtaris.stream().filter(moshtari -> moshtari.getmName().equals(visitorName)).findFirst().map(moshtari -> moshtari.mCode).orElse(0);
        builder.setPositiveButton("ثبت", (dialog, which) -> {
            sendFactor(mName,mCode);
        });

        builder.setNegativeButton("انصراف", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void showConfirmationDialog(String mName) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation");
        ConfigResponse configResponse = dataSaver.getConfig();
        builder.setMessage(String.format(new Locale("fa", "IR")
                ,"ایا مطمئنید که میخواهید با کاربر %s فاکتور ثبت کنید",configResponse.visitorName));
        builder.setPositiveButton("OK", (dialog, which) -> {
            sendFactor(mName,configResponse.mCode);
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> {
            // Handle Cancel action here
            dialog.dismiss();
            showConfigMoshtariDialog(mName);
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_carts;
    }

    private void init(){

        LinearLayout view = findViewById(R.id.parentView);
        snackbar = Snackbar.make(view,"",dataSaver.getDuraiton());


        myRoomDatabase = MyRoomDatabase.getAppDatabase(this);
        moshtaris =  myRoomDatabase.moshtariDao().getAll();
        for (Moshtari m: moshtaris){
            String hamkarString = switch (m.getmHmkar()) {
                case 0 -> "مشتری";
                case 1 -> "همکار";
                case 2 -> "عمده";
                default -> "مشتری";
            };
            items.add(new SpinnerItem(m.getmName(),hamkarString));
        }

        busApi = new Api(CartsActivity.this,dataSaver);
        loading =findViewById(R.id.progressbar);
        loading.setVisibility(View.GONE);
        list =findViewById(R.id.list);
        txtTitle=findViewById(R.id.txtTitle);
        txtTitle.setText("سبد خرید");
        imgBack=findViewById(R.id.imgBack);
        imgBack.setVisibility(View.GONE);
        txtBuy=findViewById(R.id.txtBuy);
        txtBuy.setOnClickListener(onclickListener);
        kalaList = myRoomDatabase.kalaDao().getAllKalaList();
        setupRecycler();
    }

    View.OnClickListener onclickListener= view -> {
        if (view.getId()==R.id.txtBuy){
            showSpinnerDialogBasic();
        }
    };
    private void sendFactor(String mName,int mPorsant){
        txtBuy.setEnabled(false);
        loading.setVisibility(VISIBLE);
        ConfigResponse config =  dataSaver.getConfig();
        List<Kala> kalaList = cartAdapter.getList();
        List<FactorDetail> factorDetails = new ArrayList<>();
         buyer =  null;
        for (Moshtari m: moshtaris){
            if (m.getmName().equals(mName)){
                buyer = m ;
            }
        }
        if(buyer==null){
            snackbar.setText("مشکلی پیش امده کاربر مورد نظر یافت نشد");
            return;
        }

        for (Kala k : kalaList){
            switch (buyer.mHmkar){
                case (0):
                    factorDetails.add(new FactorDetail(k.getkCode(),k.getkName(),k.getkVahedKoli(),k.getkZarib(),k.getkVahed(),k.count,k.getkForoshM(),k.count*k.getkForoshM()));
                    break;
                case (1):
                    factorDetails.add(new FactorDetail(k.getkCode(),k.getkName(),k.getkVahedKoli(),k.getkZarib(),k.getkVahed(),k.count,k.getkForoshH(),k.count*k.getkForoshH()));
                    break;
                case(2):
                    factorDetails.add(new FactorDetail(k.getkCode(),k.getkName(),k.getkVahedKoli(),k.getkZarib(),k.getkVahed(),k.count,k.getkOmde(),k.count*k.getkOmde()));
                    break;
                default:
                    factorDetails.add(new FactorDetail(k.getkCode(),k.getkName(),k.getkVahedKoli(),k.getkZarib(),k.getkVahed(),k.count,k.getkForoshM(),k.count*k.getkForoshM()));
                    break;
            }

        }
        if (buyer.getmCode()==10001){
            AddMoshtariActivity dialog = new AddMoshtariActivity();
            dialog.setFormDialogListener((m)->{
                busApi.sendFactor(config.markaz,config.loginId,config.markaz,10001,mPorsant,factorDetails,m.toString(),presenter);
                txtBuy.setEnabled(true);
            });
            dialog.show(getSupportFragmentManager(), "form_dialog");

        }else {
            busApi.sendFactor(config.markaz,config.loginId,0,buyer.getmCode(),mPorsant,factorDetails,"",presenter);
        }
    }

    public ResultFactorSubmitPresenter presenter =  new ResultFactorSubmitPresenter() {
        @Override
        public void onErrorServer(String e) {
            runOnUiThread(() ->{
                loading.setVisibility(View.GONE);
                snackbar.setText(e);
                snackbar.show();
            });
        }
        @Override
        public void onErrorInternetConnection() {
            runOnUiThread(() -> {
                loading.setVisibility(View.GONE);
                snackbar.setText("Check you're internet connection");
                snackbar.show();
            });
        }

        @Override
        public void onSuccessResultSearch(FactorResponse response) {
            myRoomDatabase.kalaDao().deleteKala();
            Intent intent = new Intent(CartsActivity.this, MainActivity.class);
            intent.putExtra(Moshtari.class.getName(),buyer.getmCode());
            startActivity(intent);
            finish();
        }
    };

    private void setupRecycler() {
        cartAdapter = new CartAdapter(this, kalaList, selectItem);
        list.setAdapter(cartAdapter);
    }

    private SelectItemList<Kala> selectItem = new SelectItemList<Kala>() {
        @Override
        public void onSelectItem(Kala kala, int position, View view, View view2) {
            Intent intent = new Intent(CartsActivity.this, ProductActivity.class);
            intent.putExtra(kala.getClass().getName(), kala); //second param is Serializable
            startActivity(intent);
        }

        @Override
        public void onSelectItemForCats(Kala kala, int position, View view, TextView txtNumber, boolean booleanAdd) {

            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    if (booleanAdd) {
                        if (null!=myRoomDatabase.kalaDao().existItemInDatabase(kala.getkCode())){

                            runOnUiThread(() -> {
                                int number=Integer.parseInt(txtNumber.getText().toString());
                                // Update UI components here
                                txtNumber.setText(String.valueOf(number+1));
                                kala.count+=1;
                                kalaList.get(position).count+=1;
                                myRoomDatabase.kalaDao().updateKala(kala);
                            });

                        }else {
//                            kala.setNumber((long) 1);
                            myRoomDatabase.kalaDao().insertKalas(kala);
                        }

                    }else if(null!=myRoomDatabase.kalaDao().existItemInDatabase(kala.getkCode())) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int number=Integer.parseInt(txtNumber.getText().toString());
                                if (number==1){
                                    myRoomDatabase.kalaDao().deleteKalas(kala);
                                    setupRecycler();


                                }else {
                                    txtNumber.setText(String.valueOf(number - 1));
//                                    kala.setNumber((long) (number - 1));
                                    myRoomDatabase.kalaDao().updateKala(kala);
                                }
                            }
                        });

                    }

                }
            });

        }
    };

    private void showSpinnerDialogBasic() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("مشتری خود را انتخاب کنید");

        // Create Spinner programmatically
        Spinner spinner = new Spinner(this) ;

        MoshtariDropDown adapter = new MoshtariDropDown(this,items);
        spinner.setAdapter(adapter);

        // Add Spinner to dialog
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(spinner);
        builder.setView(layout);

        builder.setPositiveButton("ثبت", (dialog, which) -> {

            SpinnerItem  item =(SpinnerItem) spinner.getSelectedItem();
            showConfirmationDialog(item.left);
        });

        builder.setNegativeButton("انصراف", null);
        builder.show();
    }

}