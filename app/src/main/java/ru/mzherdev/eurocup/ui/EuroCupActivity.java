package ru.mzherdev.eurocup.ui;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ru.mzherdev.eurocup.R;
import ru.mzherdev.eurocup.RootActivity;
import ru.mzherdev.eurocup.tools.AnimUtils;
import ru.mzherdev.eurocup.ui.adapter.PlayOffEuroAdapter;
import ru.mzherdev.eurocup.db.DBHandler;
import ru.mzherdev.eurocup.db.model.EuroInfo;
import ru.mzherdev.eurocup.db.model.GroupStats;
import ru.mzherdev.eurocup.db.model.Match;
import ru.mzherdev.eurocup.tools.AppResources;

/**
 * Created by macuser on 11.07.16.
 */

public class EuroCupActivity extends RootActivity implements View.OnClickListener {

    private static final int NUMBER_TEAMS_IN_GROUP = 4;
    int euroCupNumber = 1;
    private int year = 1960;
    private int numberOfGroups = 0;

    private boolean isPlayOffBtnPressed = true;
    private boolean isGroupStageBtnPressed = false;

    private List<Match> matches = new ArrayList<>();
    private PlayOffEuroAdapter adapter;
    private List<GroupStats> groupStats;
    EuroInfo euroInfo;
    LinearLayout linearLayout;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_euro_cup);

        euroCupNumber = getIntent().getIntExtra("euro_data", euroCupNumber);
        euroInfo = DBHandler.getInstance().getEuroInfo(euroCupNumber);
        year = euroInfo.getYear();

        matches = DBHandler.getInstance().getPlayOffMatchesByYear(year);
        numberOfGroups = DBHandler.getInstance().getNumberOfGroups(year);
        groupStats = DBHandler.getInstance().getGroupStats(year);

        setupUI();
    }

    private void setupUI() {
        TextView textViewYear = (TextView) findViewById(R.id.euro_year);
        textViewYear.setText(getString(R.string.euro_name) + " " + year);

        TextView textViewLocation = (TextView) findViewById(R.id.euro_location);
        textViewLocation.setText(getString(R.string.euro_location) + " " + euroInfo.getLocation());

        ImageView imageViewWinner = (ImageView) findViewById(R.id.euro_winner_flag);
        int imageResourceWinner = AppResources.getFlagOfCountry(euroInfo.getWinner());
        Picasso.with(this)
                .load(imageResourceWinner)
                .resize(320, 224)
                .centerInside()
                .into(imageViewWinner);

        TextView textViewWinner = (TextView) findViewById(R.id.euro_winner);
        textViewWinner.setText(getString(R.string.euro_winner) + " " + euroInfo.getWinner());

        Button buttonPlayOff = (Button) findViewById(R.id.button_play_off);
        buttonPlayOff.setOnClickListener(this);

        Button buttonGroupStage = (Button) findViewById(R.id.button_group_stage);
        buttonGroupStage.setOnClickListener(this);

        Button buttonWiki = (Button) findViewById(R.id.button_wiki);
        buttonWiki.setOnClickListener(this);

        if (year < 1980) {
            buttonGroupStage.setVisibility(View.GONE);
        }

        recyclerView = (RecyclerView) findViewById(R.id.play_off_recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new PlayOffEuroAdapter(getApplicationContext(), matches);
        recyclerView.setAdapter(adapter);

        linearLayout = (LinearLayout) findViewById(R.id.group_linear_layout);
        initLinearLayout(linearLayout);
        linearLayout.setVisibility(View.GONE);
    }


    private TableRow getHeaderTableRow() {
        LinearLayout.LayoutParams layoutParams = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        TableRow tableRow = new TableRow(this);
        tableRow.setLayoutParams(layoutParams);
        tableRow.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));

        TextView placeTextView = new TextView(this);
        placeTextView.setGravity(Gravity.CENTER);
        placeTextView.setText(R.string.group_place);

        TextView countryTextView = new TextView(this);
        countryTextView.setText(R.string.group_country);

        TextView gamesTextView = new TextView(this);
        gamesTextView.setText(R.string.group_games);

        TextView winsTextView = new TextView(this);
        winsTextView.setText(R.string.group_wins);

        TextView drawTextView = new TextView(this);
        drawTextView.setText(R.string.group_draws);

        TextView losesTextView = new TextView(this);
        losesTextView.setText(R.string.group_loses);

        TextView ballsTextView = new TextView(this);
        ballsTextView.setText(R.string.group_balls);

        TextView pointsTextView = new TextView(this);
        pointsTextView.setText(R.string.group_points);

        tableRow.addView(placeTextView);
        tableRow.addView(countryTextView);
        tableRow.addView(gamesTextView);
        tableRow.addView(winsTextView);
        tableRow.addView(drawTextView);
        tableRow.addView(losesTextView);
        tableRow.addView(ballsTextView);
        tableRow.addView(pointsTextView);
        return tableRow;
    }

    private void initLinearLayout(LinearLayout linearLayout) {
        for (int i = 0; i < numberOfGroups; i++) {
            TextView groupTextView = new TextView(this);
            final String groupName = groupStats.get(i * NUMBER_TEAMS_IN_GROUP).getGroup();

            groupTextView.setText(groupName);
            groupTextView.setAllCaps(true);
            groupTextView.setGravity(Gravity.CENTER_HORIZONTAL);
            groupTextView.setTextColor(Color.BLACK);

            TableLayout tableLayout = new TableLayout(this);
            tableLayout.setLayoutParams(new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            tableLayout.setStretchAllColumns(true);
            tableLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), GroupResultsActivity.class);
                    intent.putExtra("euro_year", year);
                    intent.putExtra("euro_data", euroCupNumber);
                    intent.putExtra("euro_group", groupName);
                    startActivity(intent);
                }
            });
            initTable(tableLayout, i * NUMBER_TEAMS_IN_GROUP);

            linearLayout.addView(groupTextView);
            linearLayout.addView(tableLayout);
        }
    }

    private void initTable(TableLayout tableLayout, int pos) {
        tableLayout.addView(getHeaderTableRow());

        ViewGroup.LayoutParams layoutParams = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        for (int i = pos; i < pos + NUMBER_TEAMS_IN_GROUP; i++) {

            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(layoutParams);

            TextView placeTextView = new TextView(this);
            placeTextView.setText(groupStats.get(i).getPlace());
            placeTextView.setGravity(Gravity.CENTER);

            TextView countryTextView = new TextView(this);
            countryTextView.setText(groupStats.get(i).getCountry());

            TextView gamesTextView = new TextView(this);
            gamesTextView.setText(groupStats.get(i).getGames());

            TextView winsTextView = new TextView(this);
            winsTextView.setText(groupStats.get(i).getWins());

            TextView drawTextView = new TextView(this);
            drawTextView.setText(groupStats.get(i).getDraws());

            TextView losesTextView = new TextView(this);
            losesTextView.setText(groupStats.get(i).getLoses());

            TextView ballsTextView = new TextView(this);
            ballsTextView.setText(groupStats.get(i).getBalls());

            TextView pointsTextView = new TextView(this);
            pointsTextView.setText(groupStats.get(i).getPoints());

            tableRow.addView(placeTextView);
            tableRow.addView(countryTextView);
            tableRow.addView(gamesTextView);
            tableRow.addView(winsTextView);
            tableRow.addView(drawTextView);
            tableRow.addView(losesTextView);
            tableRow.addView(ballsTextView);
            tableRow.addView(pointsTextView);

            tableLayout.addView(tableRow);

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_play_off:
                if (!isPlayOffBtnPressed) {
                    isPlayOffBtnPressed = true;
//                    adapter.setMatches(matches);
//                    adapter.notifyDataSetChanged();
                    AnimUtils.expand(recyclerView);
                } else {
                    isPlayOffBtnPressed = false;
//                    adapter.setMatches(new ArrayList<Match>());
//                    adapter.notifyDataSetChanged();
                    AnimUtils.collapse(recyclerView);
                }
                break;
            case R.id.button_group_stage:
                if (!isGroupStageBtnPressed) {
                    AnimUtils.expand(linearLayout);
//                    linearLayout.setVisibility(View.VISIBLE);
                    isGroupStageBtnPressed = true;
                } else {
                    AnimUtils.collapse(linearLayout);
//                    linearLayout.setVisibility(View.GONE);
                    isGroupStageBtnPressed = false;
                }
                break;
            case R.id.button_wiki:
                String url = getString(R.string.wiki_url) + year;
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
