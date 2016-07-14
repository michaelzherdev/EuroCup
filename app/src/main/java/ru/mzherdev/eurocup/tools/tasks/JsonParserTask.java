package ru.mzherdev.eurocup.tools.tasks;

import android.os.AsyncTask;
import android.util.Log;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ru.mzherdev.eurocup.db.DBHandler;
import ru.mzherdev.eurocup.db.model.Match;
import ru.mzherdev.eurocup.tools.JsonParser;

/**
 * Created by macuser on 12.07.16.
 */

public class JsonParserTask extends AsyncTask<String, Void, List<Match>> {
    @Override
    protected List<Match> doInBackground(String... strings) {
        List<Match> matchList = new JsonParser().parse(strings[0]);
        Collections.sort(matchList, new Comparator<Match>() {
            @Override
            public int compare(Match match1, Match match2) {
                return match2.getMatchId().compareTo(match1.getMatchId());
            }
        });
        return matchList;
    }

    @Override
    protected void onPostExecute(List<Match> matches) {
        super.onPostExecute(matches);
        Log.d("MainActivity", "matches: " + matches.size());
        for (Match match : matches) {
            DBHandler.getInstance().saveMatchToRealm(match);
        }
    }
}