package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.NumberFormat;

public class BMI extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi);

        EditText et_weight = findViewById(R.id.et_weight);
        EditText et_height = findViewById(R.id.et_height);
        Button btn_cale = findViewById(R.id.btn_cale);
        TextView tv_result = findViewById(R.id.tv_result);

        btn_cale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_weight.getText().length() != 0 || et_weight.getText().length() != 0
                ) {
                    float weight = Float.parseFloat(et_weight.getText().toString());
                    float height = Float.parseFloat(et_height.getText().toString());
                    float bmi = weight / ((height / 100) * height / 100);
                    NumberFormat numberFormat = NumberFormat.getNumberInstance();
                    numberFormat.setMaximumFractionDigits(2);
                    tv_result.setText("BMI：" + numberFormat.format(bmi));
                        if (bmi < 18.5) {
                            tv_result.setText(tv_result.getText() + "\n太瘦");
                        } else if (bmi >= 24) {
                            tv_result.setText(tv_result.getText() + "\n太胖");
                        } else if (bmi < 24 && bmi >= 18.5) {
                            tv_result.setText(tv_result.getText() + "\n完美體態");
                        } else {
                            Toast.makeText(BMI.this, "請輸入身高/體重，否則無法運算", Toast.LENGTH_LONG).show();
                        }
                }
            }
        });
    }
        public void onBackPress(){
            moveTaskToBack(true);
        }
}