package ir.futureshow.restaurantfinder.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import ir.futureshow.restaurantfinder.R;

public class DialogFast1 extends Dialog {

    public DialogFast1(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_content);
    }
}
