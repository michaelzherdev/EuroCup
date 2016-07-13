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

    public static int getFlagOfCountry(String countryName) {
        switch (countryName) {
            case "Albania":
                return R.drawable.flag_albania;
            case "Austria":
                return R.drawable.flag_austria;
            case "Belgium":
                return R.drawable.flag_belgium;
            case "Bulgaria":
                return R.drawable.flag_bulgaria;
            case "Czechoslovakia":
            case "Chech Republic":
                return R.drawable.flag_chech;
            case "Croatia":
                return R.drawable.flag_croatia;
            case "Denmark":
                return R.drawable.flag_denmark;
            case "England":
                return R.drawable.flag_england;
            case "France":
                return R.drawable.flag_france;
            case "Germany":
            case "FRG":
                return R.drawable.flag_germany;
            case "Greece":
                return R.drawable.flag_greece;
            case "Hungary":
                return R.drawable.flag_hungary;
            case "Iceland":
                return R.drawable.flag_iceland;
            case "Ireland":
                return R.drawable.flag_ireland;
            case "Italy":
                return R.drawable.flag_italy;
            case "Latvia":
                return R.drawable.flag_latvia;
            case "Netherlands":
                return R.drawable.flag_netherlands;
            case "North Ireland":
                return R.drawable.flag_northern_ireland;
            case "Norway":
                return R.drawable.flag_norway;
            case "Poland":
                return R.drawable.flag_poland;
            case "Portugal":
                return R.drawable.flag_portugal;
            case "Romania":
                return R.drawable.flag_romania;
            case "Russia":
                return R.drawable.flag_russia;
            case "Scotland":
                return R.drawable.flag_scotland;
            case "Slovakia":
                return R.drawable.flag_slovakia;
            case "Slovenia":
                return R.drawable.flag_slovenia;
            case "SNG":
                return R.drawable.flag_sng;
            case "Spain":
                return R.drawable.flag_spain_after_1977;
            case "Sweden":
                return R.drawable.flag_sweden;
            case "Switzerland":
                return R.drawable.flag_switzerland;
            case "Turkey":
                return R.drawable.flag_turkey;
            case "Ukraine":
                return R.drawable.flag_ukraine;
            case "USSR":
                return R.drawable.flag_ussr;
            case "Wales":
                return R.drawable.flag_wales;
            case "Yugoslavia":
                return R.drawable.flag_yugoslavia;
            default:
                return R.drawable.flag_ussr;
        }
    }

    public static int getGroups(int year) {
        int resource = 0;
            switch (year) {
                case 1980:
                    resource = R.raw.groups_1980;
                    break;
                case 1984:
                    resource = R.raw.groups_1984;
                    break;
                case 1988:
                    resource = R.raw.groups_1988;
                    break;
                case 1992:
                    resource = R.raw.groups_1992;
                    break;
                case 1996:
                    resource = R.raw.groups_1996;
                    break;
                case 2000:
                    resource = R.raw.groups_2000;
                    break;
                case 2004:
                    resource = R.raw.groups_2004;
                    break;
                case 2008:
                    resource = R.raw.groups_2008;
                    break;
                case 2012:
                    resource = R.raw.groups_2012;
                    break;
                case 2016:
                    resource = R.raw.groups_2016;
                    break;
                default:
                    break;
        }
        return resource;
    }
}
