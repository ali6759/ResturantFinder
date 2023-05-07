package ir.futureshow.restaurantfinder;

import android.app.ActivityOptions;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import ir.futureshow.restaurantfinder.Login.LoginActivity;


public class SplashActivity extends AppCompatActivity {

    VideoView myVideoView;
    TextView textViewSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        myVideoView = findViewById(R.id.video_view);
        textViewSplash = findViewById(R.id.txt_splash);

        Animation animationSplash = AnimationUtils.loadAnimation(SplashActivity.this , R.anim.splash_animation);
        textViewSplash.startAnimation(animationSplash);
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.splash);
        myVideoView.setVideoURI(videoUri);
        myVideoView.start();
        myVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Uri videoUri2 = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.splash);
                myVideoView.setVideoURI(videoUri2);
                myVideoView.start();
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this , LoginActivity.class);
                ActivityOptions options = ActivityOptions.makeCustomAnimation(SplashActivity.this , R.anim.pull_in_from_left , R.anim.pull_out_from_left);

                SplashActivity.this.startActivity(intent , options.toBundle());
                SplashActivity.this.finish();
            }
        } , 4100);
    }
}
