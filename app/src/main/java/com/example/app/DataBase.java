package com.example.app;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.stetho.Stetho;

import java.util.ArrayList;
import java.util.HashMap;

public class DataBase extends AppCompatActivity {
    String TAG = MainActivity.class.getSimpleName() + "My";
    private final String DB_NAME = "MyList.db";
    private String TABLE_NAME = "MyTABLE";
    private final int DB_VERSION = 1;
    SQLiteDataBaseHelper mDBHelper;

    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    ArrayList<HashMap<String, String>> getNowArray = new ArrayList<>();

    EditText ed_Name, ed_Phone, ed_Hobby, ed_Else;
    Button btn_Create, btn_Modify, btn_Clear;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setTitle("SQL資料庫");
        setContentView(R.layout.database);
        Stetho.initializeWithDefaults(this);
        mDBHelper = new SQLiteDataBaseHelper(this, DB_NAME, null, DB_VERSION, TABLE_NAME);
        mDBHelper.chickTable();
        arrayList = mDBHelper.showAll();
        itemSetting();
        recyclerViewSetting();
        buttonFunction();
    }

    //設置按鈕功能
    private void buttonFunction() {
        btn_Clear.setOnClickListener(v -> {
            clearAll();//清空目前所選以及所有editText
        });
        btn_Create.setOnClickListener(v -> {
            mDBHelper.addData(ed_Name.getText().toString()
                    , ed_Phone.getText().toString()
                    , ed_Hobby.getText().toString()
                    , ed_Else.getText().toString());
            arrayList = mDBHelper.showAll();
            myAdapter.notifyDataSetChanged();
            clearAll();
        });
        btn_Modify.setOnClickListener(v -> {
            mDBHelper.modify(getNowArray.get(0).get("id")
                    , ed_Name.getText().toString()
                    , ed_Phone.getText().toString()
                    , ed_Hobby.getText().toString()
                    , ed_Else.getText().toString());
            arrayList = mDBHelper.showAll();
            myAdapter.notifyDataSetChanged();
            clearAll();//清空目前所選以及所有editText
        });
    }
    //清空目前所選以及所有editText
    private void clearAll() {
        ed_Name.setText("");
        ed_Else.setText("");
        ed_Hobby.setText("");
        ed_Phone.setText("");
        getNowArray.clear();
    }
    //設置RecyclerView
    private void recyclerViewSetting() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);
        setRecyclerFunction(recyclerView);
    }
    //連接所有元件
    private void itemSetting() {
        btn_Create = findViewById(R.id.btn_Create);
        btn_Modify = findViewById(R.id.btn_Modify);
        btn_Clear = findViewById(R.id.btn_Clear);
        ed_Name = findViewById(R.id.ed_Name);
        ed_Phone = findViewById(R.id.ed_Phone);
        ed_Hobby = findViewById(R.id.ed_Hobby);
        ed_Else = findViewById(R.id.ed_else);
    }
    //設置Adapter
    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(android.R.layout.simple_list_item_1, null);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.tvTitle.setText(arrayList.get(position).get("name"));

            holder.itemView.setOnClickListener((v) -> {
                getNowArray.clear();
                getNowArray = mDBHelper.searchById(arrayList.get(position).get("id"));
                try {
                    ed_Name.setText(getNowArray.get(0).get("name"));
                    ed_Phone.setText(getNowArray.get(0).get("phone"));
                    ed_Hobby.setText(getNowArray.get(0).get("hobby"));
                    ed_Else.setText(getNowArray.get(0).get("elseInfo"));
                } catch (Exception e) {
                    Log.d(TAG, "onBindViewHolder: " + e.getMessage());
                }

            });

        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView tvTitle;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tvTitle = itemView.findViewById(android.R.id.text1);
            }
        }
    }
    //設置RecyclerView手勢功能
    private void setRecyclerFunction(RecyclerView recyclerView){
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                return makeMovementFlags(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                switch (direction){
                    case ItemTouchHelper.LEFT:
                    case ItemTouchHelper.RIGHT:
                        mDBHelper.deleteByIdEZ(arrayList.get(position).get("id"));
                        arrayList.remove(position);
                        arrayList = mDBHelper.showAll();
                        myAdapter.notifyItemRemoved(position);

                        break;

                }
            }
        });
        helper.attachToRecyclerView(recyclerView);
    }
}