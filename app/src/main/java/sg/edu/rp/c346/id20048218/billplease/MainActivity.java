package sg.edu.rp.c346.id20048218.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText etAmount;
    EditText etPax;
    ToggleButton tbtnSVS;
    ToggleButton tbtnGST;
    EditText etDiscount;
    RadioGroup rgPayment;
    Button btnSplit;
    Button btnReset;
    TextView tvTotal;
    TextView tvSplitPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAmount = findViewById(R.id.etAmount);
        etPax = findViewById(R.id.etPax);
        tbtnSVS = findViewById(R.id.tbtnSVS);
        tbtnGST = findViewById(R.id.tbtnGST);
        etDiscount = findViewById(R.id.etDiscount);
        rgPayment = findViewById(R.id.rgPayment);
        btnSplit = findViewById(R.id.btnSplit);
        btnReset = findViewById(R.id.btnReset);
        tvTotal = findViewById(R.id.tvTotal);
        tvSplitPay = findViewById(R.id.tvSplitPay);

        btnSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amt = etAmount.getText().toString();
                String pax = etPax.getText().toString();
                String disc = etDiscount.getText().toString();

                double dblAmt = Double.parseDouble(amt);
                int intpax = Integer.parseInt(pax);
                double newAmount = 0;
                double discount = Double.parseDouble(disc);
                double dblDiscount = discount/100 + 1;

                if (tbtnSVS.isChecked() == true && tbtnGST.isChecked() == true) {
                    newAmount = (dblAmt / dblDiscount) * 1.10 * 1.07;
                } else if (tbtnSVS.isChecked() == true && tbtnGST.isChecked() == false) {
                    newAmount = (dblAmt / dblDiscount) * 1.10;
                } else if (tbtnSVS.isChecked() == false && tbtnGST.isChecked() == true) {
                    newAmount = (dblAmt / dblDiscount) * 1.07;
                } else {
                    newAmount = dblAmt;
                }

                double splitPay = newAmount/intpax;
                tvTotal.setText("Total bill: $" + newAmount);
                tvSplitPay.setText("Each person has to pay $" + splitPay);

            }
        });

    }


}