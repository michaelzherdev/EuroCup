package ru.mzherdev.eurocup.tools;

import ru.mzherdev.eurocup.R;

/**
 * Created by Mikhail on 11.07.2016.
 */

public final class AppResources {

    private AppResources() {}

    public static int getFile(int year) {
        int resource = 0;
        switch (year) {
            case 1960:
                resource = R.raw.cup_1960;
                break;
            case 1964:
                resource = R.raw.cup_1964;
                break;
            case 1968:
                resource = R.raw.cup_1968;
                break;
            case 1972:
                resource = R.raw.cup_1972;
                break;
            case 1976:
                resource = R.raw.cup_1976;
                break;
            case 1980:
                resource = R.raw.cup_1980;
                break;
            case 1984:
                resource = R.raw.cup_1984;
                break;
            case 1988:
                resource = R.raw.cup_1988;
                break;
            case 1992:
                resource = R.raw.cup_1992;
                break;
            case 1996:
                resource = R.raw.cup_1996;
                break;
            case 2000:
                resource = R.raw.cup_2000;
                break;
            case 2004:
                resource = R.raw.cup_2004;
                break;
            case 2008:
                resource = R.raw.cup_2008;
                break;
            case 2012:
                resource = R.raw.cup_2012;
                break;
            case 2016:
                resource = R.raw.cup_2016;
                break;
            default:
                break;
        }
        return resource;
    }
}
