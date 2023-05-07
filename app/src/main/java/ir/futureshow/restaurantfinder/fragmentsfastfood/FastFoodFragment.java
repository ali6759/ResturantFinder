package ir.futureshow.restaurantfinder.fragmentsfastfood;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ir.futureshow.restaurantfinder.R;
import ir.futureshow.restaurantfinder.RestaurantActivity;
import ir.futureshow.restaurantfinder.adapter.RestaurantModel;
import ir.futureshow.restaurantfinder.adapter.MainAdapter;
import ir.futureshow.restaurantfinder.database.DbHelper;
import ir.futureshow.restaurantfinder.database.DbSchema;

    public class FastFoodFragment extends Fragment implements MainAdapter.OnRestaurantClicked {

        private RecyclerView mRecyclerView;
        private ArrayList<RestaurantModel> mFast = new ArrayList<>();

        DbHelper dbHelper;
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_fast_food, container, false);
            dbHelper = new DbHelper(getActivity());
            mRecyclerView = view.findViewById(R.id.fast_rec);
            mRecyclerView.setHasFixedSize(true);
            mFast = dbHelper.readRestaurants(DbSchema.RestaurantTable.Cols.TYPE+"='1'");
            mRecyclerView.setAdapter(new MainAdapter(mFast , this ,getActivity()));
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



            return view;
        }

        @Override
        public void OnResClick(int id) {
            Intent intent = new Intent(getActivity() , RestaurantActivity.class);
            intent.putExtra("id" , id);
            startActivity(intent);
        }



        @Override
        public void OnFavClick(RestaurantModel restaurantModel,int position) {
            RestaurantModel updateRestaurant = new RestaurantModel();
            updateRestaurant.setId(restaurantModel.getId());
            updateRestaurant.setName(restaurantModel.getName());
            updateRestaurant.setType(restaurantModel.getType());
            updateRestaurant.setRate(restaurantModel.getRate());
            updateRestaurant.setWebsite(restaurantModel.getWebsite());
            updateRestaurant.setImage1(restaurantModel.getImage1());
            updateRestaurant.setImage2(restaurantModel.getImage2());
            updateRestaurant.setTel(restaurantModel.getTel());
            updateRestaurant.setAddress(restaurantModel.getAddress());
            if (restaurantModel.getFav_status().equals("0")) {
                updateRestaurant.setFav_status("1");
            } else {
                updateRestaurant.setFav_status("0");
            }
            dbHelper.updateRestaurant(updateRestaurant);
            mFast = dbHelper.readRestaurants(DbSchema.RestaurantTable.Cols.TYPE+"='1'");
            mRecyclerView.setAdapter(new MainAdapter(mFast , this ,getActivity()));
        }
}
