package org.visitor;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.visitor.Service.presenter.model.FactorDetail;
import org.visitor.Service.presenter.model.FactorDetails;

import static org.junit.Assert.*;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public  void factorDetailParse(){
        String factorDetailString ="[\n" +
                "  {\n" +
                "    \"K_Code\": 1001,\n" +
                "    \"K_Name\": \"پیمانکاری تجهیز و بهسازی ایستگاههای تاکسی\",\n" +
                "    \"FK_koli\": 0,\n" +
                "    \"K_zarib\": 0,\n" +
                "    \"K_Vahed\": \"عدد\",\n" +
                "    \"FK_Num\": 1,\n" +
                "    \"FK_Mab\": 5226396700,\n" +
                "    \"FK_Mab_koli\": 0\n" +
                "  },{   \n" +
                " \"K_Name\": \"پیمانکاری تجهیز و بهسازی ایستگاههای تاکسی\",\n" +
                "    \"FK_koli\": 0,\n" +
                "    \"K_zarib\": 0,\n" +
                "    \"K_Vahed\": \"عدد\",\n" +
                "    \"FK_Num\": 1,\n" +
                "    \"FK_Mab\": 5226396700,\n" +
                "    \"FK_Mab_koli\": 0\n" +
                "},{   \n" +
                " \"K_Name\": \"پیمانکاری تجهیز و بهسازی ایستگاههای تاکسی\",\n" +
                "    \"FK_koli\": 0,\n" +
                "    \"K_zarib\": 0,\n" +
                "    \"K_Vahed\": \"عدد\",\n" +
                "    \"FK_Num\": 1,\n" +
                "    \"FK_Mab\": 5226396700,\n" +
                "    \"FK_Mab_koli\": 0\n" +
                "},{   \n" +
                " \"K_Name\": \"پیمانکاری تجهیز و بهسازی ایستگاههای تاکسی\",\n" +
                "    \"FK_koli\": 0,\n" +
                "    \"K_zarib\": 0,\n" +
                "    \"K_Vahed\": \"عدد\",\n" +
                "    \"FK_Num\": 1,\n" +
                "    \"FK_Mab\": 5226396700,\n" +
                "    \"FK_Mab_koli\": 0\n" +
                "},{   \n" +
                " \"K_Name\": \"پیمانکاری تجهیز و بهسازی ایستگاههای تاکسی\",\n" +
                "    \"FK_koli\": 0,\n" +
                "    \"K_zarib\": 0,\n" +
                "    \"K_Vahed\": \"عدد\",\n" +
                "    \"FK_Num\": 1,\n" +
                "    \"FK_Mab\": 5226396700,\n" +
                "    \"FK_Mab_koli\": 0\n" +
                "},{   \n" +
                " \"K_Name\": \"پیمانکاری تجهیز و بهسازی ایستگاههای تاکسی\",\n" +
                "    \"FK_koli\": 0,\n" +
                "    \"K_zarib\": 0,\n" +
                "    \"K_Vahed\": \"عدد\",\n" +
                "    \"FK_Num\": 1,\n" +
                "    \"FK_Mab\": 5226396700,\n" +
                "    \"FK_Mab_koli\": 0\n" +
                "},{   \n" +
                " \"K_Name\": \"پیمانکاری تجهیز و بهسازی ایستگاههای تاکسی\",\n" +
                "    \"FK_koli\": 0,\n" +
                "    \"K_zarib\": 0,\n" +
                "    \"K_Vahed\": \"عدد\",\n" +
                "    \"FK_Num\": 1,\n" +
                "    \"FK_Mab\": 5226396700,\n" +
                "    \"FK_Mab_koli\": 0\n" +
                "},{   \n" +
                " \"K_Name\": \"پیمانکاری تجهیز و بهسازی ایستگاههای تاکسی\",\n" +
                "    \"FK_koli\": 0,\n" +
                "    \"K_zarib\": 0,\n" +
                "    \"K_Vahed\": \"عدد\",\n" +
                "    \"FK_Num\": 1,\n" +
                "    \"FK_Mab\": 5226396700,\n" +
                "    \"FK_Mab_koli\": 0\n" +
                "},{   \n" +
                " \"K_Name\": \"پیمانکاری تجهیز و بهسازی ایستگاههای تاکسی\",\n" +
                "    \"FK_koli\": 0,\n" +
                "    \"K_zarib\": 0,\n" +
                "    \"K_Vahed\": \"عدد\",\n" +
                "    \"FK_Num\": 1,\n" +
                "    \"FK_Mab\": 5226396700,\n" +
                "    \"FK_Mab_koli\": 0\n" +
                "},{   \n" +
                " \"K_Name\": \"پیمانکاری تجهیز و بهسازی ایستگاههای تاکسی\",\n" +
                "    \"FK_koli\": 0,\n" +
                "    \"K_zarib\": 0,\n" +
                "    \"K_Vahed\": \"عدد\",\n" +
                "    \"FK_Num\": 1,\n" +
                "    \"FK_Mab\": 5226396700,\n" +
                "    \"FK_Mab_koli\": 0\n" +
                "},{   \n" +
                " \"K_Name\": \"پیمانکاری تجهیز و بهسازی ایستگاههای تاکسی\",\n" +
                "    \"FK_koli\": 0,\n" +
                "    \"K_zarib\": 0,\n" +
                "    \"K_Vahed\": \"عدد\",\n" +
                "    \"FK_Num\": 1,\n" +
                "    \"FK_Mab\": 5226396700,\n" +
                "    \"FK_Mab_koli\": 0\n" +
                "},{   \n" +
                " \"K_Name\": \"پیمانکاری تجهیز و بهسازی ایستگاههای تاکسی\",\n" +
                "    \"FK_koli\": 0,\n" +
                "    \"K_zarib\": 0,\n" +
                "    \"K_Vahed\": \"عدد\",\n" +
                "    \"FK_Num\": 1,\n" +
                "    \"FK_Mab\": 5226396700,\n" +
                "    \"FK_Mab_koli\": 0\n" +
                "},{   \n" +
                " \"K_Name\": \"پیمانکاری تجهیز و بهسازی ایستگاههای تاکسی\",\n" +
                "    \"FK_koli\": 0,\n" +
                "    \"K_zarib\": 0,\n" +
                "    \"K_Vahed\": \"عدد\",\n" +
                "    \"FK_Num\": 1,\n" +
                "    \"FK_Mab\": 5226396700,\n" +
                "    \"FK_Mab_koli\": 0\n" +
                "},{   \n" +
                " \"K_Name\": \"پیمانکاری تجهیز و بهسازی ایستگاههای تاکسی\",\n" +
                "    \"FK_koli\": 0,\n" +
                "    \"K_zarib\": 0,\n" +
                "    \"K_Vahed\": \"عدد\",\n" +
                "    \"FK_Num\": 1,\n" +
                "    \"FK_Mab\": 5226396700,\n" +
                "    \"FK_Mab_koli\": 0\n" +
                "},{   \n" +
                " \"K_Name\": \"پیمانکاری تجهیز و بهسازی ایستگاههای تاکسی\",\n" +
                "    \"FK_koli\": 0,\n" +
                "    \"K_zarib\": 0,\n" +
                "    \"K_Vahed\": \"عدد\",\n" +
                "    \"FK_Num\": 1,\n" +
                "    \"FK_Mab\": 5226396700,\n" +
                "    \"FK_Mab_koli\": 0\n" +
                "},{   \n" +
                " \"K_Name\": \"پیمانکاری تجهیز و بهسازی ایستگاههای تاکسی\",\n" +
                "    \"FK_koli\": 0,\n" +
                "    \"K_zarib\": 0,\n" +
                "    \"K_Vahed\": \"عدد\",\n" +
                "    \"FK_Num\": 1,\n" +
                "    \"FK_Mab\": 5226396700,\n" +
                "    \"FK_Mab_koli\": 0\n" +
                "}\n" +
                "]";
        Type FactorDetailList = new TypeToken<ArrayList<FactorDetail>>(){}.getType();
        List<FactorDetail> factorDetail= new Gson().fromJson(factorDetailString,FactorDetailList);
        assertEquals(factorDetail.get(0).k_Code,1001);
        assertThat(factorDetail.get(1).k_Name, CoreMatchers.containsString("تاکسی"));
    }
}