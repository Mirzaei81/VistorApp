package org.visitor.Service.view;

import static android.view.View.GONE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import org.alarmamir.R;
import org.visitor.Room.AppExecutors;
import org.visitor.Room.KalaDao;
import org.visitor.Room.MyRoomDatabase;
import org.visitor.Service.presenter.model.Kala;

import java.util.Locale;

public class ProductActivity extends BaseActivity {
    MyRoomDatabase myRoomDatabase;
    private  Kala kala;
    private TextView cartCountView;
    private TextView txtNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            kala = (Kala)getIntent().getSerializableExtra(Kala.class.getName()); //Obtaining data
            setData(kala);
        }
    }
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_product;
    }

    private void setData(Kala kala) {
        /* .......*/
        ProgressBar loading = findViewById(R.id.progressbar);
        if(loading!=null)
            loading.setVisibility(GONE);
        /* .......*/
        myRoomDatabase = MyRoomDatabase.getAppDatabase(this);
        /* .......*/
        FrameLayout flCarts=findViewById(R.id.flCarts);
        flCarts.setVisibility(View.VISIBLE);
        flCarts.setOnClickListener(view -> {
            Intent intent=new Intent(ProductActivity.this,CartsActivity.class);
            startActivity(intent);
        });

        cartCountView=findViewById(R.id.cartCount);
        txtNumber=findViewById(R.id.txtNumber);
        txtNumber.setText(String.format(Locale.US,"%d",kala.count));
        ImageView imgAdd=findViewById(R.id.imgAdd);
        ImageView imgRemove=findViewById(R.id.imgRemove);
        imgAdd.setOnClickListener(view -> addRoom(true));
        imgRemove.setOnClickListener(view -> addRoom(false));
        TextView txtCode=findViewById(R.id.txtCode);
        ImageView img=findViewById(R.id.img);
        TextView txtName=findViewById(R.id.txtName);
       /* .......*/
        TextView txtKVahed=findViewById(R.id.txtKVahed);
        TextView txtKVahedColi=findViewById(R.id.txtKVahedColi);
        TextView txtZarib=findViewById(R.id.txtZarib);
        /* .......*/
        TextView txtPriceHamkar=findViewById(R.id.txtPriceHamkar);
        TextView txtPriceForosh=findViewById(R.id.txtPriceForosh);
        TextView txtPriceOmde=findViewById(R.id.txtPriceOmde);
        /* .......*/
        TextView add2Cart = findViewById(R.id.txtCart);
        add2Cart.setOnClickListener((l)-> {

            int cartCount =1;
            try {
                cartCount = Integer.parseInt(cartCountView.getText().toString());
            } catch (Exception ignored) {
            }
            KalaDao  kalaDao= myRoomDatabase.kalaDao();
            if (kalaDao.existItemInDatabase(kala.getkCode())==null){
                kalaDao.insertKalas(kala);
                cartCount+=1;
            }else{
                kalaDao.updateKala(kala);
            }
            int finalCartCount = cartCount;
            runOnUiThread(() -> {
                cartCountView.setText(String.format(Locale.US,"%d", finalCartCount));
            });
        });
        /* .......*/
        TextView txtTitle=findViewById(R.id.txtTitle);
        txtTitle.setText(kala.getkName());
        /* .......*/
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        /* .......*/
        txtName.setText("نام: "+kala.getkName());
        /* .......*/
        txtKVahed.setText("واحد: "+kala.getkVahed());
        txtKVahedColi.setText("واحد کلی: "+kala.getkVahedKoli());
        txtZarib.setText("ضریب واحد: "+String.valueOf(kala.getkZarib()));
        /* .......*/
        txtPriceHamkar.setText("فروش همکار: "+formatter.format(kala.getkForoshH())+"تومان");
        txtPriceForosh.setText("فروش جزیی: "+formatter.format(kala.getkForoshM())+"تومان");
        txtPriceOmde.setText("فروش عمده: "+formatter.format(kala.getkOmde())+"تومان");
        /* .......*/
        txtCode.setText("کد: "+kala.getkCode());

        if (null!=kala.getkPic()) {
            final String pureBase64Encoded = kala.getkPic().substring(kala.getkPic().indexOf(",") + 1);
            final byte[] decodedBytes = Base64.decode(pureBase64Encoded, Base64.DEFAULT);
            Glide.with(this).load(decodedBytes).transform(new CenterCrop(),new RoundedCorners(25)).into(img);
        }
    }

    private void addRoom(boolean b) {
        AppExecutors.getInstance().diskIO().execute(() -> {
            if(myRoomDatabase.kalaDao().existItemInDatabase(kala.getkCode()) != null){
                if (b) {
                    kala.count+=1;
                    myRoomDatabase.kalaDao().updateKala(kala);
                }else {
                    kala.count-=1;
                    myRoomDatabase.kalaDao().updateKala(kala);
                }
            }else{
                if (b) {
                    kala.count+=1;
                    myRoomDatabase.kalaDao().insertKalas(kala);
                }
            }
            runOnUiThread(()->{
                txtNumber.setText(String.format("%d",kala.count));
            });
        });
    }
}