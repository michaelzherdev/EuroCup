package ru.mzherdev.eurocup.tools.tasks;

import android.os.AsyncTask;
import android.util.Log;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ru.mzherdev.eurocup.db.DBHandler;
import ru.mzherdev.eurocup.model.EuroInfo;
import ru.mzherdev.eurocup.model.Match;
import ru.mzherdev.eurocup.tools.JsonParser;

/**
 * Created by macuser on 12.07.16.
 */

public class JsonCupsParserTask extends AsyncTask<String, Void, List<EuroInfo>> {
    @Override
    protected List<EuroInfo> doInBackground(String... data) {
        List<EuroInfo> euroInfos = new JsonParser().parseCups(data[0]);
        return euroInfos;
    }

    @Override
    protected void onPostExecute(List<EuroInfo> euroInfos) {
        super.onPostExecute(euroInfos);
        for (EuroInfo euroInfo : euroInfos) {
            DBHandler.getInstance().saveEuroInfoToRealm(euroInfo);
        }
    }
}
