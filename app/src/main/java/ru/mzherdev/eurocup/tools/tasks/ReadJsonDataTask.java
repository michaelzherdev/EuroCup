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

public class ReadJsonDataTask extends AsyncTask<Integer, Void, String> {

    private Context context;

    public ReadJsonDataTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Integer... euroYear) {
        InputStream inputStream = context.getResources().openRawResource(AppResources.getFile(euroYear[0]));
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

        Log.d("ReadJsonDataTask", stringBuffer.toString());
        return stringBuffer.toString();
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        new JsonParserTask().execute(result);
    }
}