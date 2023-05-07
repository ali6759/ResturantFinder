package ir.futureshow.restaurantfinder;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import ir.futureshow.restaurantfinder.fragmentsfastfood.FastFoodFragment;
import ir.futureshow.restaurantfinder.fragmentsrestaurant.RestaurantFragment;
import ir.futureshow.restaurantfinder.fragmentssetting.SettingFragment;

public class MainActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    private BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;
    private FastFoodFragment fastFoodFragment;
    private RestaurantFragment restaurantFragment;
    private SettingFragment settingFragment;
    private ImageView btnFav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_nav);
        toolbar = findViewById(R.id.toolbar);
        btnFav = findViewById(R.id.btn_fav_list);
        frameLayout = findViewById(R.id.frame_bot);
        setActionBar(toolbar);
        getActionBar().setDisplayShowTitleEnabled(false);

        fastFoodFragment = new FastFoodFragment();
        restaurantFragment = new RestaurantFragment();
        settingFragment = new SettingFragment();
        setFragment(fastFoodFragment);

        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , FavList.class);
                ActivityOptions activityOptions = ActivityOptions.makeCustomAnimation(MainActivity.this , R.anim.pull_in_from_left , R.anim.pull_out_from_left);
                startActivity(intent , activityOptions.toBundle());
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.fast_food_bot:
                        setFragment(fastFoodFragment);
                        return true;
                    case R.id.restaurant_menu:
                        setFragment(restaurantFragment);
                        return true;
                    case R.id.setting_menu:
                        setFragment(settingFragment);
                        return true;
                }
                return false;
            }
        });
    }


    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_bot , fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
