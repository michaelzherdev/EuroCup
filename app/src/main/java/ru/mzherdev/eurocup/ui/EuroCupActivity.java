package ru.mzherdev.eurocup.ui;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

import ru.mzherdev.eurocup.R;
import ru.mzherdev.eurocup.model.Match;
import ru.mzherdev.eurocup.tools.AppResources;
import ru.mzherdev.eurocup.tools.JsonParser;

/**
 * Created by macuser on 11.07.16.
 */

public class EuroCupActivity extends Activity implements View.OnClickListener{

    int year = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_euro_cup);
        setupUI();
    }

    private void setupUI() {
        String euroInfo = getIntent().getStringExtra("euro_data");

        String location = euroInfo.split(" ")[0];
        year = Integer.parseInt(euroInfo.split(" ")[1]);

        TextView textViewYear = (TextView) findViewById(R.id.euro_year);
        textViewYear.setText(getString(R.string.app_name) + " " + year);

        TextView textViewLocation = (TextView) findViewById(R.id.euro_location);
        textViewLocation.setText(getString(R.string.euro_location) + " " + location);

        ImageView imageViewWinner = (ImageView) findViewById(R.id.euro_winner_flag);


        TextView textViewWinner = (TextView) findViewById(R.id.euro_winner);
        textViewWinner.setText(getString(R.string.euro_winner) + " " + location);

        Button buttonPlayOff = (Button) findViewById(R.id.button_play_off);
        buttonPlayOff.setOnClickListener(this);

        Button buttonGroupStage = (Button) findViewById(R.id.button_group_stage);
        buttonGroupStage.setOnClickListener(this);

        if(year < 1980) {
            buttonGroupStage.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_play_off:
                Log.d("EuroCupActivity", "onClick play-off");
                new ReadJsonDataTask().execute(year);
                break;
            case R.id.button_group_stage:
                Log.d("EuroCupActivity", "onClick group_stage");
                break;
            default:
                break;
        }
    }

    private class ReadJsonDataTask extends AsyncTask<Integer, Void, String> {
        @Override
        protected String doInBackground(Integer... euroYear) {
            InputStream inputStream = getResources().openRawResource(AppResources.getFile(euroYear[0]));
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

            Log.d("JsonParser", stringBuffer.toString());
            return stringBuffer.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            new JsonParserTask().execute(result);
        }
    }

    private class JsonParserTask extends AsyncTask<String, Void, List<Match>> {
        @Override
        protected List<Match> doInBackground(String... strings) {
            List<Match> matchList = new JsonParser().parse(strings[0]);
            return matchList;
        }

    }
}
