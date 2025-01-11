package com.example.badshahwazir;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button playAudioButton;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // Make sure you have this layout file

        // Find all the ImageView elements
        ImageView spinner1 = findViewById(R.id.circularSpinner1);
        ImageView spinner2 = findViewById(R.id.circularSpinner2);
        ImageView spinner3 = findViewById(R.id.circularSpinner3);
        ImageView spinner4 = findViewById(R.id.circularSpinner4);
        ImageView circle1 = findViewById(R.id.circular1);
        ImageView circle2 = findViewById(R.id.circular2);
        ImageView circle3 = findViewById(R.id.circular3);
        ImageView circle4 = findViewById(R.id.circular4);

        // Load the rotation animation from the anim folder
        Animation rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);

        // Start the animation for all the ImageView elements
        spinner1.startAnimation(rotateAnimation);
        spinner2.startAnimation(rotateAnimation);
        spinner3.startAnimation(rotateAnimation);
        spinner4.startAnimation(rotateAnimation);
        circle1.startAnimation(rotateAnimation);
        circle2.startAnimation(rotateAnimation);
        circle3.startAnimation(rotateAnimation);
        circle4.startAnimation(rotateAnimation);

        // Initialize the playAudioButton
        playAudioButton = findViewById(R.id.playAudioButton);

        // Initialize MediaPlayer
        mediaPlayer = new MediaPlayer();

        // Set an OnClickListener for the button to play the audio
        playAudioButton.setOnClickListener(v -> playAudio());
    }

    private void playAudio() {
        try {
            // Set the data source to the file you want to play
            mediaPlayer = MediaPlayer.create(this, R.raw.audio); // for raw folder files
            if (mediaPlayer != null) {
                mediaPlayer.start();
                Toast.makeText(this, "Playing Recorded Audio", Toast.LENGTH_SHORT).show();

                // Set up listeners for when the audio finishes playing
                mediaPlayer.setOnCompletionListener(mp -> {
                    mp.release();
                    Toast.makeText(this, "Audio Finished", Toast.LENGTH_SHORT).show();
                });
            } else {
                Toast.makeText(this, "Failed to load audio", Toast.LENGTH_SHORT).show();
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error playing audio: Invalid argument", Toast.LENGTH_SHORT).show();
        } catch (SecurityException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error playing audio: Security issue", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error playing audio", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Release the MediaPlayer when the activity is destroyed
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }
}
