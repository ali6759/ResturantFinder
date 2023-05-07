package ir.futureshow.restaurantfinder.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ir.futureshow.restaurantfinder.database.DbHelper;
import ir.futureshow.restaurantfinder.database.DbSchema;
import ir.futureshow.restaurantfinder.MainActivity;
import ir.futureshow.restaurantfinder.R;

public class Fragment_login extends Fragment {

    private EditText edt_email, edt_password ;
    private TextView txt_sign;
    private Button btn_login;
    DbHelper dbHelper ;
    PageTransfer pageTransfer;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_login,container,false);
        dbHelper = new DbHelper(getActivity());
        pageTransfer = (PageTransfer) getActivity();
        initViews(v);
        return v;
    }

    private void initViews(View v) {
        edt_email = v.findViewById(R.id.edt_email);
        edt_password = v.findViewById(R.id.edt_password);
        txt_sign = v.findViewById(R.id.txt_sign);
        txt_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pageTransfer.pageTransfer(1);
            }
        });
        btn_login = v.findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edt_email.getText().toString().contains("@") && edt_email.getText().toString().length() >3){
                    if ( edt_password.getText().toString().length() >= 8){
                           User user =  dbHelper.getUser(DbSchema.UserTable.Cols.USER_EMAIL + " LIKE '" + edt_email.getText().toString() + "' AND " +DbSchema.UserTable.Cols.USER_PASSWORD + " LIKE '" + edt_password.getText().toString() + "'");
                        if (user == null) {
                            Toast.makeText(getActivity(), "Your account not founded", Toast.LENGTH_SHORT).show();
                        }else{
                            putBoolean("Login",true,getActivity());
                            startActivity(new Intent(getActivity(), MainActivity.class));
                            getActivity().finish();
                        }
                    }else{
                        Toast.makeText(getActivity(), "Password must be over 7 latter", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getActivity(), "Enter Your Email Address", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    static void putBoolean(String key, Boolean value, Context c) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(c);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

}
