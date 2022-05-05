package sg.edu.rp.c346.id20048218.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
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
                if (TextUtils.isEmpty(etAmount.getText().toString()))
                {
                    Toast.makeText(MainActivity.this,
                            "Empty field not allowed!",
                            Toast.LENGTH_SHORT).show();
                }

                if (TextUtils.isEmpty(etPax.getText().toString()))
                {
                    Toast.makeText(MainActivity.this,
                            "Empty field not allowed!",
                            Toast.LENGTH_SHORT).show();
                }

                if (TextUtils.isEmpty(etDiscount.getText().toString()))
                {
                    Toast.makeText(MainActivity.this,
                            "Empty field not allowed!",
                            Toast.LENGTH_SHORT).show();
                }

                String amt = etAmount.getText().toString();
                String pax = etPax.getText().toString();
                String disc = etDiscount.getText().toString();

                double dblAmt = Double.parseDouble(amt);
                int intpax = Integer.parseInt(pax);
                double newAmount = 0;
                double discount = Double.parseDouble(disc);
                double dblDiscount = discount / 100 + 1;

                if (tbtnSVS.isChecked() == true && tbtnGST.isChecked() == true) {
                    newAmount = (dblAmt / dblDiscount) * 1.10 * 1.07;
                } else if (tbtnSVS.isChecked() == true && tbtnGST.isChecked() == false) {
                    newAmount = (dblAmt / dblDiscount) * 1.10;
                } else if (tbtnSVS.isChecked() == false && tbtnGST.isChecked() == true) {
                    newAmount = (dblAmt / dblDiscount) * 1.07;
                } else {
                    newAmount = dblAmt;
                }

                String strTotal = String.format("%.2f", newAmount);
                tvTotal.setText("Total bill: $" + strTotal);

                double splitPay = newAmount / intpax;
                String strSplitPay = String.format("%.2f", splitPay);

                int selectedId = rgPayment.getCheckedRadioButtonId();
                if (selectedId == R.id.rbCash){
                    tvSplitPay.setText("Each person has to pay $" + strSplitPay + " in cash");
                } else {
                    tvSplitPay.setText("Each person has to pay $" + strSplitPay + " via PayNow to 91234567");
                }

                btnReset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        etAmount.getText().clear();
                        etPax.getText().clear();
                        etDiscount.getText().clear();
                        tbtnSVS.setChecked(false);
                        tbtnGST.setChecked(false);
                    }
                });

            }
        });

    }


}