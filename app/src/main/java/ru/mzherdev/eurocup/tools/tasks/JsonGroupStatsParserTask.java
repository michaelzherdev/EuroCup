package ru.mzherdev.eurocup.tools.tasks;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import ru.mzherdev.eurocup.db.DBHandler;
import ru.mzherdev.eurocup.model.GroupStats;
import ru.mzherdev.eurocup.tools.JsonParser;

/**
 * Created by macuser on 12.07.16.
 */
public class JsonGroupStatsParserTask extends AsyncTask<String, Void, List<GroupStats>> {
    @Override
    protected List<GroupStats> doInBackground(String... strings) {
        List<GroupStats> groupStats = new JsonParser().parseGroupStats(strings[0]);
        return groupStats;
    }


    @Override
    protected void onPostExecute(List<GroupStats> groupStats) {
        super.onPostExecute(groupStats);
        Log.d("MainActivity", "groupStats: " + groupStats.size());
        for (GroupStats groupStat : groupStats) {
            DBHandler.getInstance().saveGroupStatsToRealm(groupStat);
        }
    }
}