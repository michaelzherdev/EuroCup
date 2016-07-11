package ru.mzherdev.eurocup.tools;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
            Log.d("JsonParser", "getJSONArray size " + jsonArray.length());
            for (int i = 0; i < jsonArray.length(); i++) {
                Match match = new Match();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                match.setMatchId(jsonObject.getString("match_id"));
                match.setCountry(jsonObject.getString("country"));
                match.setCity(jsonObject.getString("city"));
                match.setStadium(jsonObject.getString("stadium"));
                match.setHomeTeam(jsonObject.getString("home_team"));
                match.setAwayTeam(jsonObject.getString("away_team"));
                match.setHomeGoalsHalfTime(jsonObject.getString("home_goals_half_time"));
                match.setAwayGoalsHalfTime(jsonObject.getString("away_goals_half_time"));
                match.setHomeGoalsFullTime(jsonObject.getString("home_goals_full_time"));
                match.setAwayGoalsFullTime(jsonObject.getString("away_goals_full_time"));
                match.setAdditionalTime(jsonObject.getString("additional_time"));
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

        for (Match match : matches)
            Log.d("JsonParser", "matches  " + match);

        return matches;
    }

}
