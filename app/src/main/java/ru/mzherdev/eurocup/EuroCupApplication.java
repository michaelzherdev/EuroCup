package ru.mzherdev.eurocup;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Mikhail on 12.07.16.
 */

public class EuroCupApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this).deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(realmConfig);
    }
}
