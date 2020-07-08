package com.example.listviewwithdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowDataListViewActivity extends AppCompatActivity {
    private ListView listView;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data_list_view);

        listView=findViewById(R.id.listViewId);
        databaseHelper=new DatabaseHelper(this);
        loadData();
    }
    public  void loadData(){
        ArrayList<String>lisData=new ArrayList<>();
        Cursor cursor=databaseHelper.viewAllData();
        if (cursor.getCount()==0){
            Toast.makeText(getApplicationContext(),"There is no Data in Database",Toast.LENGTH_LONG).show();
            }
        else {
            while (cursor.moveToNext()){
                lisData.add(cursor.getString(0)+" \t"+cursor.getString(1)+"\t"+cursor.getString(2)+"\t"+cursor.getString(3));
            }
        }
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(this,R.layout.list_item,R.id.textViewId,lisData);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedValue=adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(),"Selected Value "+selectedValue,Toast.LENGTH_LONG).show();
            }
        });
    }


}
