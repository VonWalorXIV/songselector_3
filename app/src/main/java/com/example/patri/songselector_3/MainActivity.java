package com.example.patri.songselector_3;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends Activity implements OnClickListener{

    private static final String [] songs = {"Just like Heaven",
            "The Kill",
            "Fire Water Burn",
            "Many of Horror",
            "I am the Highway",
            "Teenage Angst",
            "Jenny was a friend of mine",
            "When you where young",
            "Just a Boy",
            "The Funeral",
            "Black Swan Song",
            "Wires",
            "Ashes to Ashes",
            "Love will tear us apart",
            "Taste You",
    };

    private Random rnd = new Random();
    private long randomSeed=1;
    private Typeface ttf;

    private List<Integer> playedSongs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ttf = Typeface.createFromAsset(getAssets(),"JandaManateeSolid.ttf");
        ((TextView)findViewById(R.id.randSong)).setTypeface(ttf);
        ((TextView)findViewById(R.id.nextSong)).setTypeface(ttf);
        ((TextView)findViewById(R.id.heading)).setTypeface(ttf);
        ((TextView)findViewById(R.id.playagain)).setTypeface(ttf);
        startSongSelect();
    }

    private void startSongSelect(){
//        TextView nxSong = (TextView) findViewById(R.id.nextSong);
        TextView rndSong = (TextView) findViewById(R.id.randSong);
        rndSong.setOnClickListener(this);


    }

    private int genRand(){
        rnd = new Random();
        int max = songs.length;
        int min = 0;
        int result = rnd.nextInt(max-min) + min;
        return result;
    }

    private boolean checkIfPlayed(int toCheck){
        if (playedSongs.contains(toCheck)){
            return true;
        }else {
            return false;
        }
    };

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.randSong){
            //generate rnd number, select song from array, display in nxSong-Textview
            int songnumber = 0;
            Boolean played = true;

            while (played){
                songnumber = genRand();
                played = checkIfPlayed(songnumber);
            }

            playedSongs.add(songnumber);

            if (playedSongs.size() == songs.length){
                TextView lastSong = (TextView) findViewById(R.id.randSong);
                lastSong.setText("This is the last unplayed Song!");
                lastSong.setOnClickListener(null);
                TextView playAgain = (TextView)findViewById(R.id.playagain);
                playAgain.setText("Play Again?");
                playAgain.setOnClickListener(this);
            }

            TextView nxSong = (TextView) findViewById(R.id.nextSong);
            nxSong.setText(songs[songnumber]);


        } else if (view.getId() == R.id.playagain){
            playedSongs.clear();
            TextView lastSong = (TextView) findViewById(R.id.randSong);
            TextView nextSong = (TextView) findViewById(R.id.nextSong);
            TextView playAgain = (TextView) findViewById(R.id.playagain);
            playAgain.setText("");
            playAgain.setOnClickListener(null);
            nextSong.setText("next Song to play");
            lastSong.setText("Random Song");
            lastSong.setOnClickListener(this);
        }
    }
}
