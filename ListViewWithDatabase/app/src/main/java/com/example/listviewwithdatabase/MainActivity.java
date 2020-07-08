package com.example.listviewwithdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText nameEditText,ageEditText,genderEditText,idEditText;
    Button saveButton,viewButton,updateButton,deleteButton;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper=new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase=databaseHelper.getWritableDatabase();

        nameEditText=findViewById(R.id.editTextNameID);
        ageEditText=findViewById(R.id.editTexAgetAgeID);
        genderEditText=findViewById(R.id.editTextGenderID);
        idEditText=findViewById(R.id.editTextID);

        saveButton=findViewById(R.id.saveButtonId);
        viewButton=findViewById(R.id.viewButtonId);
        updateButton=findViewById(R.id.updateButtonId);
        deleteButton=findViewById(R.id.deleteButtonId);

        saveButton.setOnClickListener(this);
        viewButton.setOnClickListener(this);
        updateButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String name=nameEditText.getText().toString();
        String age=ageEditText.getText().toString();
        String gender=genderEditText.getText().toString();
        String id=idEditText.getText().toString();


        if (view.getId()==R.id.saveButtonId){

            if(id.equals("")&& name.equals("")){
                Toast.makeText(getApplicationContext(),"Please Enter ID and Name ",Toast.LENGTH_LONG).show();

            }
            else if(view.getId()==R.id.saveButtonId){
                long rowId=databaseHelper.insertData(name,age,gender);
                if(rowId>-1){
                    Toast.makeText(getApplicationContext(),"Row inserted "+rowId+"successfully ",Toast.LENGTH_LONG).show();
                    idEditText.setText("");
                    nameEditText.setText("");
                    ageEditText.setText("");
                    genderEditText.setText("");

                }
                else {
                    Toast.makeText(getApplicationContext(),"Data is not inserted "+rowId+"successfully ",Toast.LENGTH_LONG).show();
                }


            }

        }
        else if(view.getId()==R.id.viewButtonId){
            Intent intent=new Intent(MainActivity.this,ShowDataListViewActivity.class);
            startActivity(intent);
        }

       // if(view.getId()==R.id.viewButtonId){







//            Cursor cursor= databaseHelper.viewAllData();
//            if(cursor.getCount()==0){
//                //viewData("Error","Data not Found ");
//                return;
//            }
//            StringBuffer stringBuffer=new StringBuffer();
//            while (cursor.moveToNext()){
//                stringBuffer.append("ID :"+cursor.getString(0)+"\n");
//                stringBuffer.append("Name :"+cursor.getString(1)+"\n");
//                stringBuffer.append("Age :"+cursor.getString(2)+"\n");
//                stringBuffer.append("Gender :"+cursor.getString(3)+"\n\n\n");
//            }
//          //  viewData("ResultSet",stringBuffer.toString());
       // }
        else if(view.getId()==R.id.updateButtonId){
            Boolean checkedUpdate=  databaseHelper.updateData(id,name,age,gender);

            if(checkedUpdate==true){
                Toast.makeText(getApplicationContext(),"Row updated successfully ",Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(getApplicationContext(),"Row updated not successfully ",Toast.LENGTH_LONG).show();
            }
        }

        else if(view.getId()==R.id.deleteButtonId){
            int d_data=   databaseHelper.deleteData(id);
            if(d_data>0){
                Toast.makeText(getApplicationContext(),"Data Delete successfully ",Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(getApplicationContext(),"Data is not Delete successfully ",Toast.LENGTH_LONG).show();
            }

        }
    }
}
