package ir.futureshow.restaurantfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import ir.futureshow.restaurantfinder.adapter.RestaurantModel;
import ir.futureshow.restaurantfinder.database.DbHelper;
import ir.futureshow.restaurantfinder.database.DbSchema;
import ir.futureshow.restaurantfinder.dialog.DialogFast1;

public class RestaurantActivity extends AppCompatActivity {

    private ImageView img_res;
    private TextView  txtBrowser, txtRate, txt_res_name, txt_res_tel, txt_res_address;
    private FloatingActionButton buttonRate, buttonFav;
    private int rate = 0;
    DbHelper dbHelper;

    RestaurantModel restaurant = new RestaurantModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estaurant);
        dbHelper = new DbHelper(this);
        Bundle bundle =getIntent().getExtras();
        String id = String.valueOf(bundle.getInt("id"));

        restaurant = dbHelper.getRestaurant(DbSchema.RestaurantTable.Cols.ID+" LIKE '"+id+"'");

        img_res = findViewById(R.id.img_res);
        txt_res_name = findViewById(R.id.txt_res_name);
        txt_res_tel = findViewById(R.id.txt_res_tel);
        txt_res_address = findViewById(R.id.txt_res_address);
        txtBrowser = findViewById(R.id.open_web);
        txtRate = findViewById(R.id.rate_user);
        buttonFav = findViewById(R.id.buttonFav);
        buttonRate = findViewById(R.id.buttonRate);

        img_res.setImageResource(restaurant.getImage1());
        txt_res_name.setText(restaurant.getName());
        txt_res_tel.setText(""+restaurant.getTel());
        txt_res_address.setText(""+restaurant.getAddress());
        if (restaurant.getFav_status().equals("1")) {
            buttonFav.setImageResource(R.drawable.ic_favorite_red_24dp);
        }







        txtRate.setText("4/5 | 0/5");
        txtBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(restaurant.getWebsite()));
                startActivity(intent);
            }
        });
        txt_res_tel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tel = "tel:+"+restaurant.getTel();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(tel));
                startActivity(intent);
            }
        });
        buttonRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogFast1 dialogFast1 = new DialogFast1(RestaurantActivity.this);
                dialogFast1.show();
                final ImageView star1 = dialogFast1.findViewById(R.id.star1);
                final ImageView star2 = dialogFast1.findViewById(R.id.start2);
                final ImageView star3 = dialogFast1.findViewById(R.id.start3);
                final ImageView star4 = dialogFast1.findViewById(R.id.start4);
                final ImageView star5 = dialogFast1.findViewById(R.id.start5);
                star1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        star1.setImageResource(R.drawable.ic_star_black_24dp);
                        star2.setImageResource(R.drawable.ic_star_border_black_24dp);
                        star3.setImageResource(R.drawable.ic_star_border_black_24dp);
                        star4.setImageResource(R.drawable.ic_star_border_black_24dp);
                        star5.setImageResource(R.drawable.ic_star_border_black_24dp);
                        rate = 1;
                        txtRate.setText("4/5 | " + rate + "/5");
                        txtRate.setVisibility(View.VISIBLE);
                        dialogFast1.dismiss();
                    }
                });
                star2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        star1.setImageResource(R.drawable.ic_star_black_24dp);
                        star2.setImageResource(R.drawable.ic_star_black_24dp);
                        star3.setImageResource(R.drawable.ic_star_border_black_24dp);
                        star4.setImageResource(R.drawable.ic_star_border_black_24dp);
                        star5.setImageResource(R.drawable.ic_star_border_black_24dp);
                        rate = 2;
                        txtRate.setText("4/5 | " + rate + "/5");
                        txtRate.setVisibility(View.VISIBLE);
                        dialogFast1.dismiss();
                    }
                });
                star3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        star1.setImageResource(R.drawable.ic_star_black_24dp);
                        star2.setImageResource(R.drawable.ic_star_black_24dp);
                        star3.setImageResource(R.drawable.ic_star_black_24dp);
                        star4.setImageResource(R.drawable.ic_star_border_black_24dp);
                        star5.setImageResource(R.drawable.ic_star_border_black_24dp);
                        rate = 3;
                        txtRate.setText("4/5 | " + rate + "/5");
                        txtRate.setVisibility(View.VISIBLE);
                        dialogFast1.dismiss();
                    }
                });
                star4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        star1.setImageResource(R.drawable.ic_star_black_24dp);
                        star2.setImageResource(R.drawable.ic_star_black_24dp);
                        star3.setImageResource(R.drawable.ic_star_black_24dp);
                        star4.setImageResource(R.drawable.ic_star_black_24dp);
                        star5.setImageResource(R.drawable.ic_star_border_black_24dp);
                        rate = 4;
                        txtRate.setText("4/5 | " + rate + "/5");
                        txtRate.setVisibility(View.VISIBLE);
                        dialogFast1.dismiss();
                    }
                });
                star5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        star1.setImageResource(R.drawable.ic_star_black_24dp);
                        star2.setImageResource(R.drawable.ic_star_black_24dp);
                        star3.setImageResource(R.drawable.ic_star_black_24dp);
                        star4.setImageResource(R.drawable.ic_star_black_24dp);
                        star5.setImageResource(R.drawable.ic_star_black_24dp);
                        rate = 5;
                        txtRate.setText("4/5 | " + rate + "/5");
                        txtRate.setVisibility(View.VISIBLE);
                        dialogFast1.dismiss();
                    }
                });
            }
        });
        buttonFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RestaurantModel updateRestaurant = new RestaurantModel();
                updateRestaurant.setId(restaurant.getId());
                updateRestaurant.setName(restaurant.getName());
                updateRestaurant.setType(restaurant.getType());
                updateRestaurant.setRate(restaurant.getRate());
                if (restaurant.getFav_status().equals("0")){
                    updateRestaurant.setFav_status("1");
                    buttonFav.setImageResource(R.drawable.ic_favorite_red_24dp);
                }else{
                    updateRestaurant.setFav_status("0");
                    buttonFav.setImageResource(R.drawable.ic_favorite_black_24dp);
                }
                updateRestaurant.setWebsite(restaurant.getWebsite());
                updateRestaurant.setImage1(restaurant.getImage1());
                updateRestaurant.setImage2(restaurant.getImage2());
                updateRestaurant.setTel(restaurant.getTel());
                updateRestaurant.setAddress(restaurant.getAddress());
                dbHelper.updateRestaurant(updateRestaurant);
            }
        });
    }
}