package com.example.faiz.udacityexamples;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private int quantity = 0, noOforder;
    private boolean flagwhip, flagChoco;
    private TextView textPrice, textCusName, textQuantity, textthanks, textChocoTopping, textWhipTopping;
   private String customerName;
   private EditText edtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


    }

    public void orderNow(View view) {
        noOforder = quantity;
        orderSummery(noOforder);
    }

    public void Increament(View view) {


        quantity = ++quantity;
        displayQuantity(quantity);

    }

    public void Decreament(View view) {

        if (quantity >= 1) {
            quantity = --quantity;
            displayQuantity(quantity);
        }
    }

    public void displayQuantity(int quantity) {
        TextView textQu = (TextView) findViewById(R.id.textView);
        textQu.setText("" + quantity);
    }

    public void orderSummery(int quantity) {
        flagwhip = false;
        flagChoco = false;
        textPrice = (TextView) findViewById(R.id.text_dollar);
        int totalprice = quantity * 5;

        textQuantity = (TextView) findViewById(R.id.quatityText);
        textQuantity.setText("Quantity: " + quantity);
        textthanks = (TextView) findViewById(R.id.thanks);
        textthanks.setText("Thank You!!");

        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBoxwhip);
        if (checkBox.isChecked()) {
            flagwhip = true;
            totalprice = totalprice + quantity;

        }

        textWhipTopping = (TextView) findViewById(R.id.textwhip);
        textWhipTopping.setText("Add Whiped Cream! $1 " + flagwhip);
        CheckBox checkBoxChoco = (CheckBox) findViewById(R.id.checkBoxChoc);
        if (checkBoxChoco.isChecked()) {
            flagChoco = true;
            totalprice = totalprice + quantity * 2;
        }
        textPrice.setText("Total : $ " + totalprice);
        textChocoTopping = (TextView) findViewById(R.id.textchoco);
        textChocoTopping.setText("Add Chocolate ! $2 " + flagChoco);

         edtName = (EditText) findViewById(R.id.editTextName);
        customerName = edtName.getText().toString();
        textCusName = (TextView) findViewById(R.id.customName);
        textCusName.setText("Name: " + customerName);


        Intent intent = new Intent(Intent.ACTION_SEND);
        String[] recipients = {""};
        String s = textCusName.getText().toString()+"\n"+textWhipTopping.getText().toString()+"\n"+textChocoTopping.getText().toString()+"\n"+textQuantity.getText().toString()+"\n"+textPrice.getText().toString()+"\n"+textthanks.getText().toString();
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just_Java order For " + customerName);
        intent.putExtra(Intent.EXTRA_TEXT,s);
        intent.putExtra(Intent.EXTRA_CC, "ghi");
        intent.setType("text/html");
        startActivity(Intent.createChooser(intent, "Send mail"));


    }
}
