package ru.mzherdev.eurocup.tools;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ru.mzherdev.eurocup.model.EuroInfo;
import ru.mzherdev.eurocup.model.GroupStats;
import ru.mzherdev.eurocup.model.Match;

/**
 * Created by macuser on 11.07.16.
 */

public class JsonParser {

    public List<Match> parse(String data) {
        Log.d("JsonParser", "start parsing..");
        JSONArray jsonArray;
        List<Match> matches = new ArrayList<>();
        try {
            jsonArray = new JSONArray(data);
            for (int i = 0; i < jsonArray.length(); i++) {
                Match match = new Match();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                match.setMatchId(jsonObject.getInt("match_id"));
                match.setCountry(jsonObject.getString("country"));
                match.setCity(jsonObject.getString("city"));
                match.setStadium(jsonObject.getString("stadium"));
                match.setHomeTeam(jsonObject.getString("home_team"));
                match.setAwayTeam(jsonObject.getString("away_team"));
                match.setHomeGoalsHalfTime(jsonObject.getString("home_goals_half_time"));
                match.setAwayGoalsHalfTime(jsonObject.getString("away_goals_half_time"));
                match.setHomeGoalsFullTime(jsonObject.getString("home_goals_full_time"));
                match.setAwayGoalsFullTime(jsonObject.getString("away_goals_full_time"));
                match.setAdditionalTime(jsonObject.getBoolean("additional_time"));
                if (jsonObject.has("penalty"))
                    match.setPenalty(jsonObject.getString("penalty"));
                match.setYear(jsonObject.getString("year"));
                match.setMonth(jsonObject.getString("month"));
                match.setDay(jsonObject.getString("day"));
                match.setRound(jsonObject.getString("round"));
                matches.add(match);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return matches;
    }

    public List<EuroInfo> parseCups(String data) {
        Log.d("JsonParser", "start parsing..");
        JSONArray jsonArray;
        List<EuroInfo> euroInfos = new ArrayList<>();
        try {
            jsonArray = new JSONArray(data);
            for (int i = 0; i < jsonArray.length(); i++) {
                EuroInfo euroInfo = new EuroInfo();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                euroInfo.setId(jsonObject.getInt("id"));
                euroInfo.setLocation(jsonObject.getString("location"));
                euroInfo.setWinner(jsonObject.getString("winner"));
                euroInfo.setWinnerFlag(jsonObject.getString("winner_flag"));
                euroInfo.setYear(jsonObject.getInt("year"));
                euroInfo.setSymbol(jsonObject.getString("symbol"));

                euroInfos.add(euroInfo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return euroInfos;
    }

    public List<GroupStats> parseGroupStats(String data) {
        Log.d("JsonParser", "start parsing..");
        JSONArray jsonArray;
        List<GroupStats> groupStatistics = new ArrayList<>();
        try {
            jsonArray = new JSONArray(data);
            for (int i = 0; i < jsonArray.length(); i++) {
                GroupStats groupStats = new GroupStats();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                groupStats.setGroupId(jsonObject.getInt("id"));
                groupStats.setYear(jsonObject.getInt("year"));
                groupStats.setGroup(jsonObject.getString("group"));
                groupStats.setPlace(jsonObject.getString("place"));
                groupStats.setCountry(jsonObject.getString("country"));
                groupStats.setGames(jsonObject.getString("games"));
                groupStats.setWins(jsonObject.getString("wins"));
                groupStats.setDraws(jsonObject.getString("draws"));
                groupStats.setLoses(jsonObject.getString("loses"));
                groupStats.setBalls(jsonObject.getString("balls"));
                groupStats.setPoints(jsonObject.getString("points"));

                groupStatistics.add(groupStats);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return groupStatistics;
    }

}
