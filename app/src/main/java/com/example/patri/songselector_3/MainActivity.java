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

    private static String [] songs = {"Just like Heaven",
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
            "Just like heaven"
    };

//    private static String [] songs = {"Song 1", "Song 2", "Song 3"};

    private Random rnd = new Random();
    private long randomSeed=1;
    private Typeface ttf;

    int songnumber = 0;

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
        ((TextView)findViewById(R.id.playedLater)).setTypeface(ttf);
        ((TextView)findViewById(R.id.lastSongIndicator)).setTypeface(ttf);
        startSongSelect();
    }

    private void startSongSelect(){
//        TextView nxSong = (TextView) findViewById(R.id.nextSong);
        TextView rndSong = (TextView) findViewById(R.id.randSong);
        rndSong.setText("");

        TextView gespieltLater = (TextView) findViewById(R.id.playedLater);
        gespieltLater.setText("");

        TextView nameLied = (TextView) findViewById(R.id.nextSong);
        nameLied.setText("START");
        nameLied.setOnClickListener(this);


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

    public int selectSong(){
        Boolean played = true;

        int snumber = 0;

        while (played){
            snumber = genRand();
            played = checkIfPlayed(snumber);
        };

        return snumber;
    };

    public void checkDone(){
        if (playedSongs.size() == songs.length){
            TextView lastSongIndicator = (TextView) findViewById(R.id.lastSongIndicator);
            lastSongIndicator.setText("");
            TextView nxSong = (TextView) findViewById(R.id.nextSong);
            nxSong.setText("All Songs played");
            TextView lastSong = (TextView) findViewById(R.id.randSong);
            lastSong.setText("");
            lastSong.setOnClickListener(null);
            TextView playLater = (TextView) findViewById(R.id.playedLater);
            playLater.setText("");
            playLater.setOnClickListener(null);
            TextView playAgain = (TextView)findViewById(R.id.playagain);
            playAgain.setText("Play Again?");
            playAgain.setOnClickListener(this);

        } else {
            if (playedSongs.size() == songs.length-1){
                TextView lastSongIndicator = (TextView) findViewById(R.id.lastSongIndicator);
                lastSongIndicator.setText("LAST UNPLAYED SONG");
            }
            songnumber = selectSong();

            TextView nxSong = (TextView) findViewById(R.id.nextSong);
            nxSong.setText(songs[songnumber]);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.randSong){
            //generate rnd number, select song from array, display in nxSong-Textview

            playedSongs.add(songnumber);
            checkDone();

        } else if (view.getId() == R.id.playagain){
            playedSongs.clear();

            TextView rndSong = (TextView) findViewById(R.id.randSong);
            rndSong.setText("");
            rndSong.setOnClickListener(null);

            TextView gespieltLater = (TextView) findViewById(R.id.playedLater);
            gespieltLater.setText("");
            gespieltLater.setOnClickListener(null);

            TextView nameLied = (TextView) findViewById(R.id.nextSong);
            nameLied.setText("START");
            nameLied.setOnClickListener(this);

            TextView playAgain = (TextView)findViewById(R.id.playagain);
            playAgain.setText("");
            playAgain.setOnClickListener(null);

        } else if (view.getId() == R.id.playedLater){
            int oldSongNumber = songnumber;
            while (oldSongNumber == songnumber) {
                songnumber = selectSong();
            }
            TextView nxSong = (TextView) findViewById(R.id.nextSong);
            nxSong.setText(songs[songnumber]);
        } else if (view.getId() == R.id.nextSong){
            songnumber = selectSong();
            TextView nxSong = (TextView) findViewById(R.id.nextSong);
            nxSong.setText(songs[songnumber]);
            TextView nameLied = (TextView) findViewById(R.id.nextSong);
            nameLied.setOnClickListener(null);
            TextView rndSong = (TextView) findViewById(R.id.randSong);
            rndSong.setText("Next Song");
            rndSong.setOnClickListener(this);
            TextView gespieltLater = (TextView) findViewById(R.id.playedLater);
            gespieltLater.setText("Later");
            gespieltLater.setOnClickListener(this);
        }
    }
}
