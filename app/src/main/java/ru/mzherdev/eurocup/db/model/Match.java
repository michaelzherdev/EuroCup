package ru.mzherdev.eurocup.db.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Mikhail on 11.07.16.
 */

public class Match extends RealmObject{

    @PrimaryKey
    private long id = 0;
    private Integer matchId;
    private String country;
    private String city;
    private String stadium;
    private String homeTeam;
    private String awayTeam;
    private String homeGoalsHalfTime;
    private String awayGoalsHalfTime;
    private String homeGoalsFullTime;
    private String awayGoalsFullTime;
    private boolean additionalTime;
    private String penalty;
    private String year;
    private String month;
    private String day;
    private String round;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getHomeGoalsHalfTime() {
        return homeGoalsHalfTime;
    }

    public void setHomeGoalsHalfTime(String homeGoalsHalfTime) {
        this.homeGoalsHalfTime = homeGoalsHalfTime;
    }

    public String getAwayGoalsHalfTime() {
        return awayGoalsHalfTime;
    }

    public void setAwayGoalsHalfTime(String awayGoalsHalfTime) {
        this.awayGoalsHalfTime = awayGoalsHalfTime;
    }

    public String getHomeGoalsFullTime() {
        return homeGoalsFullTime;
    }

    public void setHomeGoalsFullTime(String homeGoalsFullTime) {
        this.homeGoalsFullTime = homeGoalsFullTime;
    }

    public String getAwayGoalsFullTime() {
        return awayGoalsFullTime;
    }

    public void setAwayGoalsFullTime(String awayGoalsFullTime) {
        this.awayGoalsFullTime = awayGoalsFullTime;
    }

    public boolean isAdditionalTime() {
        return additionalTime;
    }

    public void setAdditionalTime(boolean additionalTime) {
        this.additionalTime = additionalTime;
    }

    public String getPenalty() {
        return penalty;
    }

    public void setPenalty(String penalty) {
        this.penalty = penalty;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    @Override
    public String toString() {
        return "Match{" +
                "matchId='" + matchId + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", stadium='" + stadium + '\'' +
                ", homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", homeGoalsHalfTime='" + homeGoalsHalfTime + '\'' +
                ", awayGoalsHalfTime='" + awayGoalsHalfTime + '\'' +
                ", homeGoalsFullTime='" + homeGoalsFullTime + '\'' +
                ", awayGoalsFullTime='" + awayGoalsFullTime + '\'' +
                ", additionalTime='" + additionalTime + '\'' +
                ", penalty='" + penalty + '\'' +
                ", year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", day='" + day + '\'' +
                ", round='" + round + '\'' +
                '}';
    }
}
