package ru.mzherdev.eurocup.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ru.mzherdev.eurocup.R;
import ru.mzherdev.eurocup.RootActivity;
import ru.mzherdev.eurocup.ui.adapter.PlayOffEuroAdapter;
import ru.mzherdev.eurocup.db.DBHandler;
import ru.mzherdev.eurocup.db.model.EuroInfo;
import ru.mzherdev.eurocup.db.model.Match;

/**
 * Created by macuser on 12.07.16.
 */

public class GroupResultsActivity extends RootActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_results);

        int euroCupYear = getIntent().getIntExtra("euro_year", 1960);
        int euroCupNumber = getIntent().getIntExtra("euro_data", 1);
        String groupName = getIntent().getStringExtra("euro_group");

        EuroInfo euroInfo = DBHandler.getInstance().getEuroInfo(euroCupNumber);
        List<Match> matches = DBHandler.getInstance().getGroupMatchesByYear(euroCupYear, groupName);

        Log.d("GroupActivity", "euroinfo " + euroInfo + "\n euro matches " + matches);

        TextView textViewYear = (TextView) findViewById(R.id.group_euro_year);
        textViewYear.setText(getString(R.string.euro_name) + " " + euroInfo.getYear());

        TextView textViewLocation = (TextView) findViewById(R.id.group_euro_location);
        textViewLocation.setText(getString(R.string.euro_location) + " " + euroInfo.getLocation());

        ImageView imageViewFlag = (ImageView) findViewById(R.id.group_euro_flag);
        int imageResourceFlag = getResources().getIdentifier(euroInfo.getSymbol(), null, getPackageName());
        Picasso.with(this)
                .load(imageResourceFlag)
                .resize(160, 112)
                .centerInside()
                .into(imageViewFlag);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.group_recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        PlayOffEuroAdapter adapter = new PlayOffEuroAdapter(getApplicationContext(), matches);
        recyclerView.setAdapter(adapter);
    }
}
