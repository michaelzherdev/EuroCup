package ru.mzherdev.eurocup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ru.mzherdev.eurocup.adapter.EuroListAdapter;
import ru.mzherdev.eurocup.model.EuroInfo;

public class MainActivity extends AppCompatActivity {

    private Map<String, Integer> euros = new TreeMap<>(new Comparator<String>() {
        @Override
        public int compare(String s1, String s2) {
            return s1.split(" ")[1].compareTo(s2.split(" ")[1]);
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        setContentView(R.layout.activity_main);
        setupUI();
    }

    private void initData(){
        euros.put(getString(R.string.euro_place_1960), R.drawable.euro1960);
        euros.put(getString(R.string.euro_place_1964), R.drawable.euro1964);
        euros.put(getString(R.string.euro_place_1968), R.drawable.euro1968);
        euros.put(getString(R.string.euro_place_1972), R.drawable.euro1972);
        euros.put(getString(R.string.euro_place_1976), R.drawable.euro1976);
        euros.put(getString(R.string.euro_place_1980), R.drawable.euro1980);
        euros.put(getString(R.string.euro_place_1984), R.drawable.euro1984);
        euros.put(getString(R.string.euro_place_1988), R.drawable.euro1988);
        euros.put(getString(R.string.euro_place_1992), R.drawable.euro1992);
        euros.put(getString(R.string.euro_place_1996), R.drawable.euro1996);
        euros.put(getString(R.string.euro_place_2000), R.drawable.euro2000);
        euros.put(getString(R.string.euro_place_2004), R.drawable.euro2004);
        euros.put(getString(R.string.euro_place_2008), R.drawable.euro2008);
        euros.put(getString(R.string.euro_place_2012), R.drawable.euro2012);
        euros.put(getString(R.string.euro_place_2016), R.drawable.euro2016);
    }

    private void setupUI() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.euro_recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        List<EuroInfo> euroInfos = new ArrayList<>();
        for (Map.Entry<String, Integer> pair : euros.entrySet()) {
            EuroInfo euroInfo = new EuroInfo();
            euroInfo.setName(pair.getKey());
            euroInfo.setImage(pair.getValue());
            euroInfos.add(euroInfo);
        }
        EuroListAdapter adapter = new EuroListAdapter(getApplicationContext(), euroInfos);
        recyclerView.setAdapter(adapter);
    }
}
