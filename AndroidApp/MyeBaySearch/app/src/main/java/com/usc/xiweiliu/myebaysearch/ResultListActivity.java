package com.usc.xiweiliu.myebaysearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class ResultListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_list);

        TextView resultTitle = (TextView) findViewById(R.id.result_title);
        resultTitle.setText("Results for '" + MainActivity.input.getKeyword() + "'");
        MainActivity.dataService.parseResponse();

        // Get ListView object from xml
        final ListView resultListView = (ListView) findViewById(R.id.result_list);

        // Initialize an adapter
        ItemAdapter adapter = new ItemAdapter(this, MainActivity.dataService);

        // Assign adapter to ListView

        resultListView.setAdapter(adapter);

        // Set a listener to ListView
        resultListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item item = (Item) resultListView.getItemAtPosition(position);

                Bundle bundle = new Bundle();
                bundle.putParcelable(DetailActivity.ITEM, item);

                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}
