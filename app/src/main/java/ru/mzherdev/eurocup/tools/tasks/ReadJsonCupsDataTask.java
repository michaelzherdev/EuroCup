package ru.mzherdev.eurocup.tools.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import ru.mzherdev.eurocup.tools.AppResources;

/**
 * Created by macuser on 12.07.16.
 */

public class ReadJsonCupsDataTask extends AsyncTask<Integer, Void, String> {

    private Context context;

    public ReadJsonCupsDataTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Integer... filename) {
        InputStream inputStream = context.getResources().openRawResource(filename[0]);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer stringBuffer = new StringBuffer();
        String line = "";
        try {
            while ((line = reader.readLine()) != null)
                stringBuffer.append(line);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuffer.toString();
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        new JsonCupsParserTask().execute(result);
    }
}
