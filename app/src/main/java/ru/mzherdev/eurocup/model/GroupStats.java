package ru.mzherdev.eurocup.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by macuser on 12.07.16.
 */

public class GroupStats extends RealmObject{
    @PrimaryKey
    private long id;
    private String group;
    private String place;
    private String country;
    private String games;
    private String wins;
    private String draws;
    private String loses;
    private String balls;
    private String points;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGames() {
        return games;
    }

    public void setGames(String games) {
        this.games = games;
    }

    public String getWins() {
        return wins;
    }

    public void setWins(String wins) {
        this.wins = wins;
    }

    public String getDraws() {
        return draws;
    }

    public void setDraws(String draws) {
        this.draws = draws;
    }

    public String getLoses() {
        return loses;
    }

    public void setLoses(String loses) {
        this.loses = loses;
    }

    public String getBalls() {
        return balls;
    }

    public void setBalls(String balls) {
        this.balls = balls;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "GroupStats{" +
                "id=" + id +
                ", group='" + group + '\'' +
                ", place='" + place + '\'' +
                ", country='" + country + '\'' +
                ", games='" + games + '\'' +
                ", wins='" + wins + '\'' +
                ", draws='" + draws + '\'' +
                ", loses='" + loses + '\'' +
                ", balls='" + balls + '\'' +
                ", points='" + points + '\'' +
                '}';
    }
}
