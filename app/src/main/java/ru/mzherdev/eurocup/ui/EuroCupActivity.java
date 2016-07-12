package ru.mzherdev.eurocup.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import io.realm.Realm;
import ru.mzherdev.eurocup.R;
import ru.mzherdev.eurocup.adapter.EuroListAdapter;
import ru.mzherdev.eurocup.adapter.PlayOffEuroAdapter;
import ru.mzherdev.eurocup.db.DBHandler;
import ru.mzherdev.eurocup.model.EuroInfo;
import ru.mzherdev.eurocup.model.GroupStats;
import ru.mzherdev.eurocup.model.Match;
import ru.mzherdev.eurocup.tools.AppResources;
import ru.mzherdev.eurocup.tools.JsonParser;

/**
 * Created by macuser on 11.07.16.
 */

public class EuroCupActivity extends Activity implements View.OnClickListener {

    private int year = 1960;
    private List<Match> matches = new ArrayList<>();
    private PlayOffEuroAdapter adapter;
    private boolean isPlayOffBtnPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_euro_cup);
        setupUI();
    }

    private void setupUI() {
        Integer euroCupNumber = getIntent().getIntExtra("euro_data", 1);

        EuroInfo euroInfo = Realm.getDefaultInstance().where(EuroInfo.class).equalTo("id", ++euroCupNumber).findFirst();

        TextView textViewYear = (TextView) findViewById(R.id.euro_year);
        year = euroInfo.getYear();
        textViewYear.setText(getString(R.string.app_name) + " " + year);

        TextView textViewLocation = (TextView) findViewById(R.id.euro_location);
        textViewLocation.setText(getString(R.string.euro_location) + " " + euroInfo.getLocation());

        ImageView imageViewWinner = (ImageView) findViewById(R.id.euro_winner_flag);
        int imageResourceWinner = getResources().getIdentifier(euroInfo.getWinnerFlag(), null, getPackageName());
        Picasso.with(this)
                .load(imageResourceWinner)
                .resize(160, 112)
                .centerInside()
                .into(imageViewWinner);

        TextView textViewWinner = (TextView) findViewById(R.id.euro_winner);
        textViewWinner.setText(getString(R.string.euro_winner) + " " + euroInfo.getWinner());

        Button buttonPlayOff = (Button) findViewById(R.id.button_play_off);
        buttonPlayOff.setOnClickListener(this);

        Button buttonGroupStage = (Button) findViewById(R.id.button_group_stage);
        buttonGroupStage.setOnClickListener(this);

        if (year < 1980) {
            buttonGroupStage.setVisibility(View.INVISIBLE);
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.play_off_recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new PlayOffEuroAdapter(getApplicationContext(), matches);
        recyclerView.setAdapter(adapter);

        Log.d("MainActivity", "all matches: " + Realm.getDefaultInstance().where(Match.class).findAll().size());
        Log.d("MainActivity", "all infos: " + Realm.getDefaultInstance().where(EuroInfo.class).findAll().size());
        Log.d("MainActivity", "all groupStats: " + Realm.getDefaultInstance().where(GroupStats.class).findAll().size());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_play_off:
                Log.d("EuroCupActivity", "onClick play-off");
                if (!isPlayOffBtnPressed) {
                    isPlayOffBtnPressed = true;
                    matches = DBHandler.getInstance().getPlayOffMatchesByYear(year);
                    adapter.setMatches(matches);
                    adapter.notifyDataSetChanged();
                } else {
                    isPlayOffBtnPressed = false;
                    adapter.setMatches(new ArrayList<Match>());
                    adapter.notifyDataSetChanged();
                }
                break;
            case R.id.button_group_stage:
                Log.d("EuroCupActivity", "onClick group_stage");
                Intent intent = new Intent(this, GroupTableActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
