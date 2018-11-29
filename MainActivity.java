package com.xtrovix.akash.asynctaskexample;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnStart;
    EditText edtTime;
    TextView txtResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btnStart);
        edtTime = findViewById(R.id.edtTime);
        txtResult = findViewById(R.id.txtResult);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyAsyncTask myAsyncTask = new MyAsyncTask();
                String time = edtTime.getText().toString();
                myAsyncTask.execute(time);
            }
        });
    }

    public class MyAsyncTask extends AsyncTask<String, String, String> {

        Context context;
        private String resp;
        ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... strings) {

            publishProgress("Sleeping....");

            int time = Integer.parseInt(strings[0])*1000;
            try {
                Thread.sleep(time);
                resp = "spelt for "+strings[0]+" seconds";
            } catch (InterruptedException e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;
        }

        public void onPreExecute() {
            progressDialog = ProgressDialog.show(MainActivity.this, "ProgressDialog", "wait for " + edtTime.getText().toString());
        }

        protected void onProgressUpdate(String... text) {
            txtResult.setText(text[0]);

        }
        protected void onPostExecute(String result)
        {
            progressDialog.dismiss();
            txtResult.setText(result);
        }
    }
}
