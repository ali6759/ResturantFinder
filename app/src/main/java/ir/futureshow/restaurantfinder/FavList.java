package ir.futureshow.restaurantfinder;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ir.futureshow.restaurantfinder.adapter.RestaurantModel;
import ir.futureshow.restaurantfinder.adapter.MainAdapter;
import ir.futureshow.restaurantfinder.database.DbHelper;
import ir.futureshow.restaurantfinder.database.DbSchema;

public class FavList extends AppCompatActivity {

    private ArrayList<RestaurantModel> itemModels = new ArrayList<>();
    private RecyclerView recyclerView;
    private DbHelper dbHelper;
    private MainAdapter restaurantAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_list);
        dbHelper = new DbHelper(FavList.this);
        recyclerView = findViewById(R.id.fav_rec);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(FavList.this));
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        loadData();
    }

    private void loadData() {
        itemModels = dbHelper.readRestaurants(DbSchema.RestaurantTable.Cols.FAV_STATUS + "='1'");
        restaurantAdapter = new MainAdapter(itemModels, new MainAdapter.OnRestaurantClicked() {
            @Override
            public void OnResClick(int id) {
                Intent intent = new Intent(FavList.this, RestaurantActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }

            @Override
            public void OnFavClick(RestaurantModel restaurantModel, int position) {
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
                    restaurantAdapter.notifyItemRemoved(position);
                    itemModels.remove(position);
                    updateRestaurant.setFav_status("0");
                }
                dbHelper.updateRestaurant(updateRestaurant);
                itemModels = dbHelper.readRestaurants(DbSchema.RestaurantTable.Cols.TYPE + "='0'");
                recyclerView.setAdapter(new MainAdapter(itemModels, this, FavList.this));
            }
        }, FavList.this);

        recyclerView.setAdapter(restaurantAdapter);
    }


    private ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            final int position = viewHolder.getAdapterPosition();
            final RestaurantModel favModel = itemModels.get(position);

            RestaurantModel updateRestaurant = new RestaurantModel();
            updateRestaurant.setId(favModel.getId());
            updateRestaurant.setName(favModel.getName());
            updateRestaurant.setType(favModel.getType());
            updateRestaurant.setRate(favModel.getRate());
            updateRestaurant.setWebsite(favModel.getWebsite());
            updateRestaurant.setImage1(favModel.getImage1());
            updateRestaurant.setImage2(favModel.getImage2());
            updateRestaurant.setTel(favModel.getTel());
            updateRestaurant.setAddress(favModel.getAddress());

            restaurantAdapter.notifyItemRemoved(position);
            itemModels.remove(position);
            updateRestaurant.setFav_status("0");
            dbHelper.updateRestaurant(updateRestaurant);
        }
    };

}
