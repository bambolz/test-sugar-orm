package com.bi.template;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.activeandroid.query.Select;
import com.bi.template.model.MockObject;

import java.util.List;

public class TitleListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_list);
        List<MockObject> mockObjects = new Select()
                .from(MockObject.class).execute();
        ListView listViewTitles = (ListView) findViewById(R.id.lvTitles);
        ArrayAdapter<MockObject> arrayAdapter = new ArrayAdapter<MockObject>(this,
                android.R.layout.simple_list_item_1,
                mockObjects);
        listViewTitles.setAdapter(arrayAdapter);
    }
}
