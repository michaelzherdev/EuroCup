package ru.mzherdev.eurocup.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Mikhail on 10.07.2016.
 */

public class EuroInfo extends RealmObject {

    @PrimaryKey
    private long id;
    private String location;
    private String winner;
    private String winnerFlag;
    private int year;
    private String symbol;

    private int image;
    private String name;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getWinnerFlag() {
        return winnerFlag;
    }

    public void setWinnerFlag(String winner_flag) {
        this.winnerFlag = winner_flag;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
