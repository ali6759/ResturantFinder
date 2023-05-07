package ir.futureshow.restaurantfinder.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ir.futureshow.restaurantfinder.R;
import ir.futureshow.restaurantfinder.database.DbHelper;


public class MainAdapter extends RecyclerView.Adapter<MainAdapter.holder> {

    Context context;
    ArrayList<RestaurantModel> list;
    private OnRestaurantClicked mOnRestaurantClicked;
    DbHelper dbHelper;

    public MainAdapter(ArrayList<RestaurantModel> restaurants, OnRestaurantClicked onRestaurantClicked, Context context) {
        this.list = restaurants;
        this.context = context;
        this.mOnRestaurantClicked = onRestaurantClicked;
        dbHelper = new DbHelper(context);
    }

    @NonNull
    @Override
    public MainAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new holder(LayoutInflater.from(context).inflate(R.layout.card_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.holder holder, @SuppressLint("RecyclerView") int position) {
        RestaurantModel modelRestaurant = list.get(position);
        holder.imgRestaurant.setImageResource(modelRestaurant.getImage2());
        if (modelRestaurant.getFav_status().equals("0")) {
            holder.button.setBackgroundResource(R.drawable.ic_favorite_black_24dp);
        } else {
            holder.button.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
        }
        holder.txtName.setText(modelRestaurant.getName());
        holder.txtRate.setText(modelRestaurant.getRate());

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (modelRestaurant.getFav_status().equals("0")) {
                    holder.button.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
                } else {
                    holder. button.setBackgroundResource(R.drawable.ic_favorite_black_24dp);
                }
                mOnRestaurantClicked.OnFavClick(modelRestaurant, position);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnRestaurantClicked.OnResClick(modelRestaurant.getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder extends RecyclerView.ViewHolder {

        private ImageView imgRestaurant;
        private TextView txtName, txtRate;
        private Button button;

        public holder(@NonNull View itemView) {
            super(itemView);
            imgRestaurant = itemView.findViewById(R.id.image_card1);
            txtName = itemView.findViewById(R.id.text1_card1);
            txtRate = itemView.findViewById(R.id.text2_card1);
            button = itemView.findViewById(R.id.button_card1);

        }
    }


    public interface OnRestaurantClicked {
        void OnResClick(int id);
        void OnFavClick(RestaurantModel restaurantModel, int position);
    }

}
