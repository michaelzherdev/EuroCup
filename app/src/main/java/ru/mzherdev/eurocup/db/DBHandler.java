package ru.mzherdev.eurocup.db;

import android.util.Log;

import java.util.Iterator;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmModel;
import ru.mzherdev.eurocup.model.EuroInfo;
import ru.mzherdev.eurocup.model.GroupStats;
import ru.mzherdev.eurocup.model.Match;

/**
 * Created by macuser on 12.07.16.
 */

public final class DBHandler {

    private static final String TAG = DBHandler.class.getSimpleName();

    private static DBHandler dbHandler;

    private DBHandler() {
    }

    public static DBHandler getInstance() {
        if (dbHandler == null) {
            dbHandler = new DBHandler();
        }
        return dbHandler;
    }

    public void saveMatchToRealm(Match match) {
        Log.d(TAG, "save match: " + match);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        long id = realm.where(Match.class).max("id") == null ? 0 : (long) realm.where(Match.class).max("id");
        match.setId(++id);
        realm.copyToRealmOrUpdate(match);
        realm.commitTransaction();
        Log.d(TAG, "save match finished " + match);
    }

    public void saveEuroInfoToRealm(EuroInfo euroInfo) {
        Log.d(TAG, "save euroInfo: " + euroInfo);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        long id = realm.where(EuroInfo.class).max("id") == null ? 0 : (long) realm.where(EuroInfo.class).max("id");
        euroInfo.setId(++id);
        realm.copyToRealmOrUpdate(euroInfo);
        realm.commitTransaction();
        Log.d(TAG, "save euroInfo finished " + euroInfo);
    }

    public void saveGroupStatsToRealm(GroupStats groupStats) {
        Log.d(TAG, "save groupStats: " + groupStats);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        long id = realm.where(GroupStats.class).max("id") == null ? 0 : (long) realm.where(GroupStats.class).max("id");
        groupStats.setId(++id);
        realm.copyToRealmOrUpdate(groupStats);
        realm.commitTransaction();
        Log.d(TAG, "save groupStats finished " + groupStats);
    }

    public Match getMatchByMatchId(int matchId, int year) {
        return Realm.getDefaultInstance().where(Match.class).equalTo("matchId", matchId).equalTo("year", year).findFirst();
    }

    public EuroInfo getEuroInfoById(int id) {
        return Realm.getDefaultInstance().where(EuroInfo.class).equalTo("id", id).findFirst();
    }

    public List<Match> getPlayOffMatchesByYear(int year) {
        List<Match> matches = Realm.getDefaultInstance().where(Match.class)
                .equalTo("year", String.valueOf(year))
                .notEqualTo("round", "group A")
                .notEqualTo("round", "group B")
                .notEqualTo("round", "group C")
                .notEqualTo("round", "group D")
                .notEqualTo("round", "group E")
                .notEqualTo("round", "group F")
                .findAll();
//        for (Iterator<Match> iterator = matches.iterator(); iterator.hasNext(); ) {
//            Match match = iterator.next();
//            if (match.getRound().startsWith("group")) ;
//            iterator.remove();
//        }
        return matches;
    }

    public List<Match> getGroupMatchesByYear(int year) {
        return Realm.getDefaultInstance().where(Match.class).equalTo("year", String.valueOf(year)).beginsWith("round", "group").findAll();
    }
}
