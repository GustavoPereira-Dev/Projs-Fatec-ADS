package fatec.zl.edu.bhaskara;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/*
 *@author: Gustavo Pereira
 */

public class MainActivity extends AppCompatActivity {
    private EditText etA;
    private EditText etB;
    private EditText etC;

    private Button btnCalc;

    private TextView tvRes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etA = findViewById(R.id.etA);
        etA.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        etB = findViewById(R.id.etB);
        etB.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        etC = findViewById(R.id.etC);
        etC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tvRes = findViewById(R.id.tvRes);
        tvRes.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        btnCalc = findViewById(R.id.btnCalc);

        btnCalc.setOnClickListener(calc -> calcular());
    }

    private void calcular(){
        double A = Double.parseDouble(etA.getText().toString());
        double B = Double.parseDouble(etB.getText().toString());
        double C = Double.parseDouble(etC.getText().toString());
        String res = getString(R.string.res);
        double delta = Math.pow(B,2) -(4 * A * C);
        if(delta >= 0){
            int x1 = (int) ((-B + Math.sqrt(delta)) / 2 * A);
            int x2 = (int) ((-B - Math.sqrt(delta)) / 2 * A);
            if(delta == 0){
                res += "x1 e x2 sao iguais (" + x1 + ")";
            } else{
                res += "x1 = " + x1 + "; x2 = " + x2;
            }
        } else{
            res += " nao existem raizes reais, numero negativo";
        }
        
        tvRes.setText(res);
    }

}