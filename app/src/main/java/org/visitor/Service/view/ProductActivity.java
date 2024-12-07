package org.visitor.Service.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import org.alarmamir.R;
import org.visitor.Room.AppExecutors;
import org.visitor.Room.MyRoomDatabase;
import org.visitor.Service.presenter.model.Kala;

public class ProductActivity extends AppCompatActivity {
    MyRoomDatabase myRoomDatabase;
    private  Kala kala;
    private TextView txtNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            kala = (Kala)getIntent().getSerializableExtra(Kala.class.getName()); //Obtaining data
            setData(kala);
        }

    }

    private void setData(Kala kala) {
        myRoomDatabase = MyRoomDatabase.getAppDatabase(this);
        /* .......*/
        FrameLayout flCarts=findViewById(R.id.flCarts);
        flCarts.setVisibility(View.VISIBLE);
        flCarts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ProductActivity.this,CartsActivity.class);
                startActivity(intent);
            }
        });
        txtNumber=findViewById(R.id.txtNumber);
        txtNumber.setText(String.valueOf(myRoomDatabase.kalaDao().getAllKalaList().size()));
        ImageView imgAdd=findViewById(R.id.imgAdd);
        ImageView imgRemove=findViewById(R.id.imgRemove);
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addRoom(true);

            }
        });
        imgRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addRoom(false);
            }
        });
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
        txtName.setText("نام: "+kala.getkName());
        /* .......*/
        txtKVahed.setText("واحد: "+kala.getkVahed());
        txtKVahedColi.setText("واحد کلی: "+kala.getkVahedKoli());
        txtZarib.setText("ضریب واحد: "+String.valueOf(kala.getkZarib()));
        /* .......*/
        txtPriceHamkar.setText("فروش همکار: "+kala.getkForoshH());
        txtPriceForosh.setText("فروش جزیی: "+kala.getkForoshM());
        txtPriceOmde.setText("فروش عمده: "+kala.getkOmde());
        /* .......*/
        txtCode.setText("کد: "+kala.getkCode());


        if (null!=kala.getkPic()) {
            final String pureBase64Encoded = kala.getkPic().substring(kala.getkPic().indexOf(",") + 1);
            final byte[] decodedBytes = Base64.decode(pureBase64Encoded, Base64.DEFAULT);
            Glide.with(this).load(decodedBytes).transform(new CenterCrop(),new RoundedCorners(25)).into(img);
        }
    }

    private void addRoom(boolean b) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                if (b) {
                    myRoomDatabase.kalaDao().insertKalas(kala);
                }else if(myRoomDatabase.kalaDao().existItemInDatabase(kala.getkCode()) != null) {
                    myRoomDatabase.kalaDao().deleteKala();
                }
                txtNumber.setText(String.valueOf(myRoomDatabase.kalaDao().getAllKalaList().size()));

            }
        });
    }
}