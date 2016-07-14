package ru.mzherdev.eurocup.db;

import android.util.Log;

import java.util.List;

import io.realm.Realm;
import io.realm.Sort;
import ru.mzherdev.eurocup.db.model.EuroInfo;
import ru.mzherdev.eurocup.db.model.GroupStats;
import ru.mzherdev.eurocup.db.model.Match;

/**
 * Created by Mikhail on 12.07.16.
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
        return Realm.getDefaultInstance()
                .where(Match.class)
                .equalTo("year", String.valueOf(year))
                .beginsWith("round", "group")
                .findAll()
                .sort("matchId", Sort.ASCENDING);
    }

    public List<Match> getGroupMatchesByYear(int year, String groupName) {
        return Realm.getDefaultInstance()
                .where(Match.class)
                .equalTo("year", String.valueOf(year))
                .equalTo("round", groupName)
                .findAll()
                .sort("matchId", Sort.ASCENDING);
    }

    public int getNumberOfGroups(int year) {
        switch (year) {
            case 1980:
            case 1984:
            case 1988:
            case 1992:
                return 2;
            case 1996:
            case 2000:
            case 2004:
            case 2008:
            case 2012:
                return 4;
            case 2016:
                return 6;
            default:
                return 0;

        }
    }

    public EuroInfo getEuroInfo(int position) {
        return Realm.getDefaultInstance().where(EuroInfo.class).equalTo("id", ++position).findFirst();
    }

    public List<GroupStats> getGroupStats(int year) {
        return Realm.getDefaultInstance().where(GroupStats.class).equalTo("year", year).findAll();
    }


}
