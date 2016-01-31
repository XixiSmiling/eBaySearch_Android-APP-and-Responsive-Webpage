package com.usc.xiweiliu.myebaysearch;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static SearchInput input;
    static ErrorMsg errorMsg;
    static DataService dataService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize UI
        Spinner spinner = (Spinner) findViewById(R.id.sortBy);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.sortBy_array, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(spinnerAdapter);

        errorMsg = new ErrorMsg((TextView) findViewById(R.id.errorMsg));
        errorMsg.msg.setVisibility(View.GONE);


        // get input data
        input = new SearchInput((EditText) findViewById(R.id.keyword),
                (EditText) findViewById(R.id.priceFrom),
                (EditText) findViewById(R.id.priceTo),
                (Spinner) findViewById(R.id.sortBy));

        dataService = new DataService();

    }

    public void clearClickHandler(View view) {
        input.keyword.setText("");
        input.priceFrom.setText("");
        input.priceTo.setText("");
        input.sortBy.setSelection(0);
        errorMsg.msg.setVisibility(View.GONE);
    }

    public void searchClickHandler(View view) {
        if (errorMsg.isValid(input)) {
            new GetItemsAsyncTask().execute();
        }
    }

    private class GetItemsAsyncTask extends AsyncTask<Void, Void, Boolean> {

//        private DataService dataService;
//        private SearchInput input;

        public GetItemsAsyncTask() {
//            this.dataService = dataService;
//            this.input = input;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            return dataService.searchItems(input);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (!result) {
                errorMsg.postErrorMsg(3);
            } else {
                Intent intent = new Intent(MainActivity.this, ResultListActivity.class);
                startActivity(intent);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
