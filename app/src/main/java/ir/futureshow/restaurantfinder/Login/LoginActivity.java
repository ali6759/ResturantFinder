package ir.futureshow.restaurantfinder.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import ir.futureshow.restaurantfinder.R;
import ir.futureshow.restaurantfinder.adapter.RestaurantModel;
import ir.futureshow.restaurantfinder.database.DbHelper;

public class LoginActivity extends AppCompatActivity implements PageTransfer {

    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHelper = new DbHelper(this);
        if (getBoolean("Login", this)) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, new Fragment_login());
            fragmentTransaction.commit();
        } else {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, new Fragment_signup());
            fragmentTransaction.commit();


            dbHelper.addNewRestaurant(new RestaurantModel(1, "SHAY",
                    "0", "4.7/5", "15149372001",
                    "1414 Notre-Dame St W, Montreal, Quebec H3C 1K8"
                    , "http://www.shaymtl.com/",
                    "0", R.drawable.shay1, R.drawable.shay2));

            dbHelper.addNewRestaurant(new RestaurantModel(2, "7-\tRestaurant Jako",
                    "0", "4.7/5", "4383803840",
                    "1862 Boul. de Maisonneuve Ouest, Montréal, QC H3H 1J8",
                    "https://www.facebook.com/RestaurantJakoMtl/", "0"
                    , R.drawable.jako1, R.drawable.jako2));

            dbHelper.addNewRestaurant(new RestaurantModel(3, "ShangHai Girll",
                    "0", "3/5", "5149359478",
                    "4050 Sainte-Catherine O, Montréal, QC H3Z 1P2",
                    "https://www.google.com", "0",
                    R.drawable.shanghai_grill1,
                    R.drawable.shanghai_grill2));

            dbHelper.addNewRestaurant(new RestaurantModel(4, "La Socca",
                    "0", "1/5", "5147662824  ",
                    "278 Rue Elgar, Verdun, QC H3E 1C9",
                    "http://www.lasocca.com", "0"
                    , R.drawable.lasocca1
                    , R.drawable.lasocca2));

            dbHelper.addNewRestaurant(new RestaurantModel(5, "Cafe Cantina",
                    "0", "2/5", "5149033511",
                    "1880 Centre St, Montreal, Quebec H3K 1H9",
                    "https://www.google.com", "0",
                    R.drawable.cantina1,
                    R.drawable.cantina2));

            dbHelper.addNewRestaurant(new RestaurantModel(6,
                    "Beba", "0", "./5", "5147507087",
                    "3900 Rue Éthel, Verdun, QC H4G 1S4",
                    "https://www.google.com", "0"
                    , R.drawable.beba2, R.drawable.beba2));

            dbHelper.addNewRestaurant(new RestaurantModel(7, "Pigor",
                    "0", "5/5", "5149070816",
                    "3780 Wellington St, Verdun, Quebec H4G 1V2",
                    "https://www.restaurantpigor.com", "0",
                    R.drawable.pigor,
                    R.drawable.pigor2));


            dbHelper.addNewRestaurant(new RestaurantModel(8,
                    "Five Guys",
                    "1", "4.4/5", "4502380378",
                    "600 Rue Lucien-Paiement Suite 120, Laval, Quebec H7N 0A5"
                    , "https://restaurants.fiveguys.ca/600-rue-lucien-paiement",
                    "0", R.drawable.five_guys1, R.drawable.five_guys2));

            dbHelper.addNewRestaurant(new RestaurantModel(9, "McDonald' s"
                    , "1", "5/5", "5148463766",
                    "3026 Notre-Dame St W, Montreal, Quebec H4C 1P1",
                    "https://www.mcdonalds.com/ca/en-ca/restaurant",
                    "0", R.drawable.mcdonalds1, R.drawable.mcdonalds2));

            dbHelper.addNewRestaurant(new RestaurantModel(10,
                    "Domino's Pizza", "1", "3/5",
                    "5149323030",
                    "3628 Notre-Dame St W, Montreal, Quebec H4C 1P5 ",
                    "https://pizza.dominos.ca/montreal-quebec-10700/", "0",
                    R.drawable.dominos1, R.drawable.dominos2));

            dbHelper.addNewRestaurant(new RestaurantModel(11,
                    "A&W Canada", "1", "3.9/5",
                    "5143031205",
                    "5120 Chem. Queen Mary, Montréal, QC H3W 1X2",
                    "https://web.aw.ca/", "0",
                    R.drawable.aw1, R.drawable.aw2));

            dbHelper.addNewRestaurant(new RestaurantModel(12,
                    "Yumi Burger", "1", "4.2/5",
                    "5148463336",
                    "1448 Mackay St, Montreal, Quebec H3G 2H6",
                    "https://yumiburger.ca/", "0",
                    R.drawable.yumiburger1, R.drawable.yumiburger2));

            dbHelper.addNewRestaurant(new RestaurantModel(13,
                    "Chef On Call", "1", "4.4/5",
                    "5148442044",
                    "3430 Park Ave, Montreal, Quebec H2X 2H5",
                    "http://www.chefoncalldelivery.com/", "0",
                    R.drawable.chefoncalldelivery1, R.drawable.chefoncalldelivery2));

            dbHelper.addNewRestaurant(new RestaurantModel(14,
                    "Franji Sandwich Mtl", "1", "4.9/5",
                    "4384762688",
                    "1429A Bishop St, Montreal, Quebec H3G 2E4",
                    "http://www.franji.ca/", "0",
                    R.drawable.franji1, R.drawable.franji2));


        }

    }

    static Boolean getBoolean(String key, Context c) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(c);
        return prefs.getBoolean(String.valueOf(key), false);
    }

    @Override
    public void pageTransfer(int toPage) {
        if (toPage == 1) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, new Fragment_signup());
            fragmentTransaction.commit();
        } else if (toPage == 2) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, new Fragment_login());
            fragmentTransaction.commit();
        }
    }
}