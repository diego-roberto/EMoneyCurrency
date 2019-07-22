package com.example.emoneycurrency;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.emoneycurrency.R;


    //
//      ESTA ACTIVITY AINDA NÃO FAZ P*%& NENHUMA! :(
    //


public class CalculateActicity extends AppCompatActivity {

    private ViewHolder mviewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        this.mviewHolder.textView = findViewById(R.id.text_result);

        this.clearField();

        this.loadDataFromActicity();

        //this.mviewHolder.textView.setText();

    }

    private void loadDataFromActicity() {
        Bundle extra = getIntent().getExtras();
        if (extra != null){
            String valueFromActivity = extra.getString("valueToSearch");

            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, valueFromActivity);
            startActivity(intent);

//            System.out.println(valueFromActivity);
        }
    }

    private void clearField(){
        this.mviewHolder.textView.setText("");
    }

    private static class ViewHolder{
        TextView textView;
    }

}
