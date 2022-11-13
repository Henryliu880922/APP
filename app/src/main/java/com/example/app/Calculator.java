package com.example.app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button; //引用按鈕元件
import android.widget.EditText; //引用文字編輯框元件
import android.text.TextUtils; //TextUtils類用於處理字串

import androidx.appcompat.app.AppCompatActivity;

//定義實現監聽介面的類MainActivity
public class Calculator extends AppCompatActivity implements View.OnClickListener {

    Button btn_num0,btn_num1,btn_num2,btn_num3,btn_num4,btn_num5,btn_num6,btn_num7,btn_num8,btn_num9,btn_delete,btn_div,btn_multi,btn_minus,btn_plus,btn_equal,btn_dot,btn_clear;
    private EditText et_calc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Calculator");
        setContentView(R.layout.calculator); //顯示activity_main.xml定義的使用者介面
        initViewAndListener(); //呼叫函式initViewAndListener()
    }

    //定義函式initViewAndListener()，用於與使用者介面程式中的元件建立關聯，並分別註冊監聽介面
    private void initViewAndListener() {
        btn_num0 = (Button)findViewById(R.id.btn_num0);
        btn_num0.setOnClickListener(this);
        btn_num1 = (Button)findViewById(R.id.btn_num1);
        btn_num1.setOnClickListener(this);
        btn_num2 =(Button) findViewById(R.id.btn_num2);
        btn_num2.setOnClickListener(this);
        btn_num3 = (Button)findViewById(R.id.btn_num3);
        btn_num3.setOnClickListener(this);
        btn_num4 = (Button)findViewById(R.id.btn_num4);
        btn_num4.setOnClickListener(this);
        btn_num5 = (Button)findViewById(R.id.btn_num5);
        btn_num5.setOnClickListener(this);
        btn_num6 = (Button)findViewById(R.id.btn_num6);
        btn_num6.setOnClickListener(this);
        btn_num7 = (Button)findViewById(R.id.btn_num7);
        btn_num7.setOnClickListener(this);
        btn_num8 = (Button)findViewById(R.id.btn_num8);
        btn_num8.setOnClickListener(this);
        btn_num9 = (Button)findViewById(R.id.btn_num9);
        btn_num9.setOnClickListener(this);
        btn_delete = (Button)findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(this);
        btn_div = (Button)findViewById(R.id.btn_div);
        btn_div.setOnClickListener(this);
        btn_multi = (Button)findViewById(R.id.btn_multi);
        btn_multi.setOnClickListener(this);
        btn_minus = (Button)findViewById(R.id.btn_minus);
        btn_minus.setOnClickListener(this);
        btn_plus = (Button)findViewById(R.id.btn_plus);
        btn_plus.setOnClickListener(this);
        btn_equal = (Button)findViewById(R.id.btn_equal);
        btn_equal.setOnClickListener(this);
        et_calc = (EditText)findViewById(R.id.et_calc);
        btn_dot = (Button)findViewById(R.id.btn_dot);
        btn_dot.setOnClickListener(this);
        btn_clear = (Button)findViewById(R.id.btn_clear);
        btn_clear.setOnClickListener(this);
    }

    private boolean lastIsOperator; //記錄當前是否進行計算操作
    private String lastOperators = ""; //記錄上一次進行計算操作的結果

    private double firstNumber = 0D; //定義並初始化第一次的取值
    private double secondNumber = 0D; //定義並初始化第二次的取值

    //onClick()方法，觸發OnClickListener介面
    public void onClick(View v) {
        String currentText = et_calc.getText().toString(); //獲取當前文字編輯框的內容
        String operatorNumber = ""; //定義並初始化運算元為””
        if (currentText.equals("0")) { //若當前輸入數為0則設定文字編輯框為””
            et_calc.setText("");
        }
        operatorNumber = et_calc.getText().toString(); //根據編輯框的內容更改運算元值
        if(!lastOperators.equals("")) //判斷上一次操作的數是否為空，非空則建立索引，隨之修改當前運算元
        {
            int index = operatorNumber.lastIndexOf(lastOperators);
            operatorNumber = operatorNumber.substring(index+1);
        }

        //分別獲取按鈕的內容至文字編輯框
        switch (v.getId()) {
            case R.id.btn_num0:
                et_calc.setText(et_calc.getText() + "0");
                lastIsOperator = false;
                break;
            case R.id.btn_num1:
                et_calc.setText(et_calc.getText() + "1");
                lastIsOperator = false;
                break;
            case R.id.btn_num2:
                et_calc.setText(et_calc.getText() + "2");
                lastIsOperator = false;
                break;
            case R.id.btn_num3:
                et_calc.setText(et_calc.getText() + "3");
                lastIsOperator = false;
                break;
            case R.id.btn_num4:
                et_calc.setText(et_calc.getText() + "4");
                lastIsOperator = false;
                break;
            case R.id.btn_num5:
                et_calc.setText(et_calc.getText() + "5");
                lastIsOperator = false;
                break;
            case R.id.btn_num6:
                et_calc.setText(et_calc.getText() + "6");
                lastIsOperator = false;
                break;
            case R.id.btn_num7:
                et_calc.setText(et_calc.getText() + "7");
                lastIsOperator = false;
                break;
            case R.id.btn_num8:
                et_calc.setText(et_calc.getText() + "8");
                lastIsOperator = false;
                break;
            case R.id.btn_num9:
                et_calc.setText(et_calc.getText() + "9");
                lastIsOperator = false;
                break;
            case R.id.btn_dot:
                et_calc.setText(et_calc.getText() + ".");
                lastIsOperator = false;
                break;
            //歸零按鈕，將當前運算元直接清零
            case R.id.btn_clear:
                et_calc.setText("");
                lastIsOperator = false;
                firstNumber=0D;
                secondNumber=0D;
                lastOperators="=";
                break;
            //清除按鈕，若當前文字編輯框內容為空，點選一次則刪除一個字串
            case R.id.btn_delete:
                if (TextUtils.isEmpty(et_calc.getText())) {
                    return;
                }
                lastIsOperator = false;
                et_calc.setText(currentText.substring(0,currentText.length() - 1).length() > 0 ? currentText.substring(0,currentText.length() - 1) : "0");
                break;
            //計算按鈕，若當前內容非空、或者以及上一次有操作記錄且記錄不是”=”的情況下，將當前的操作記錄設定為+、-、*、/、=
            case R.id.btn_div:
                if ((TextUtils.isEmpty(et_calc.getText())
                        || lastIsOperator) && !lastOperators.equals("=")) {
                    return;
                }
                operatorCalc(operatorNumber,"÷");
                lastIsOperator = true;
                et_calc.setText(et_calc.getText() + "÷");
                lastOperators = "÷";
                break;
            case R.id.btn_multi:
                if ((TextUtils.isEmpty(et_calc.getText())
                        || lastIsOperator) && !lastOperators.equals("=")) {
                    return;
                }
                operatorCalc(operatorNumber,"*");
                lastIsOperator = true;
                et_calc.setText(et_calc.getText() + "*");
                lastOperators = "*";
                break;
            case R.id.btn_minus:
                if ((TextUtils.isEmpty(et_calc.getText())
                        || lastIsOperator) && !lastOperators.equals("=")) {
                    return;
                }
                operatorCalc(operatorNumber,"-");
                lastIsOperator = true;
                et_calc.setText(et_calc.getText() + "-");
                lastOperators = "-";
                break;
            case R.id.btn_plus:
                if ((TextUtils.isEmpty(et_calc.getText())
                        || lastIsOperator) && !lastOperators.equals("=")) {
                    return;
                }
                operatorCalc(operatorNumber,"+");
                lastIsOperator = true;
                et_calc.setText(et_calc.getText() + "+");
                lastOperators = "+";
                break;
            case R.id.btn_equal:
                double result = 0D;
                if(TextUtils.isEmpty(lastOperators))
                {
                    return;
                }
                operatorResult(operatorNumber);
                secondNumber = 0D;
                lastOperators ="=";
                lastIsOperator = true;
                et_calc.setText(et_calc.getText() + "\n=" + String.valueOf(firstNumber));
                break;
        }
    }

    //運算函式，按具體操作進行+、-、*、/的運算
    private void operate(String operatorNumber)
    {
        if(secondNumber != 0D) //計算情況一：第二次輸入的數不為0
        {
            if(lastOperators.equals("÷"))
            {
                secondNumber = secondNumber / Double.parseDouble(operatorNumber);
                firstNumber = firstNumber / secondNumber;
            }
            else if(lastOperators.equals("*"))
            {
                secondNumber = secondNumber * Double.parseDouble(operatorNumber);
                firstNumber = firstNumber * secondNumber;
            }
            else if(lastOperators.equals("+"))
            {
                secondNumber = Double.parseDouble(operatorNumber);
                firstNumber = firstNumber + secondNumber;
            }
            else if(lastOperators.equals("-"))
            {
                secondNumber = Double.parseDouble(operatorNumber);
                firstNumber = firstNumber - secondNumber;
            }
        }
            else //計算情況二：第二次輸入的數為0
        {
            if(lastOperators.equals("÷"))
            {
                firstNumber = firstNumber / Double.parseDouble(operatorNumber);
            }
            else if(lastOperators.equals("*"))
            {
                firstNumber = firstNumber * Double.parseDouble(operatorNumber);
            }
            else if(lastOperators.equals("+"))
            {
                firstNumber = firstNumber + Double.parseDouble(operatorNumber);
            }
            else if(lastOperators.equals("-"))
            {
                firstNumber= firstNumber - Double.parseDouble(operatorNumber);
            }
        }
    }
    //分別返回計算結果
    public void operatorResult(String operatorNumber)
    {
        operate(operatorNumber);

    }
    //按當前計算結果進行下一次的資料輸入及計算
    public void operatorCalc(String operatorNumber,String currentOperator)
    {
        if(TextUtils.isEmpty(lastOperators))
        {
            firstNumber = Double.parseDouble(operatorNumber);
            return;
        }

        if(lastOperators.equals(currentOperator))
        {
            if(lastOperators.equals("÷"))
            {
                firstNumber = firstNumber / Double.parseDouble(operatorNumber);
            }
            else if(lastOperators.equals("*"))
            {
                firstNumber = firstNumber * Double.parseDouble(operatorNumber);
            }
            else if(lastOperators.equals("+"))
            {
                firstNumber = firstNumber + Double.parseDouble(operatorNumber);
            }
            else if(lastOperators.equals("-"))
            {
                firstNumber = firstNumber - Double.parseDouble(operatorNumber);
            }

            return;
        }
        operate(operatorNumber);

    }

}