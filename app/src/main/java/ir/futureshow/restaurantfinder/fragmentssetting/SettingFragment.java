package ir.futureshow.restaurantfinder.fragmentssetting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.snackbar.Snackbar;

import ir.futureshow.restaurantfinder.About;
import ir.futureshow.restaurantfinder.R;

public class SettingFragment extends Fragment {

    private Button buttonAbout , buttonRateApp ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        buttonAbout = view.findViewById(R.id.about);
        buttonRateApp = view.findViewById(R.id.rate_app);

        buttonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                About about = new About();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_bot , about);
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        buttonRateApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "This feature is currently unavailable", Snackbar.LENGTH_LONG).show();
            }
        });
        return view;
    }

}
