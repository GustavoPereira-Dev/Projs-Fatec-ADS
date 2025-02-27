package fatec.zl.edu.numerosprimos.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import fatec.zl.edu.numerosprimos.R;
import fatec.zl.edu.numerosprimos.controller.NumerosPrimosController;


public class MainActivity extends AppCompatActivity {

    private EditText etNum1;
    private EditText etNum2;
    private Button btnCalc;
    private TextView tvRes;
    private NumerosPrimosController ctr;

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

        ctr = new NumerosPrimosController();

        etNum1 = findViewById(R.id.etNum1);
        etNum1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        etNum2 = findViewById(R.id.etNum2);
        etNum2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tvRes = findViewById(R.id.tvRes);
        tvRes.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        btnCalc = findViewById(R.id.btnCalc);
        btnCalc.setOnClickListener(p -> somatoriaPrimos());
    }

    private void somatoriaPrimos(){
        int num1 = Integer.parseInt(etNum1.getText().toString());
        int num2 = Integer.parseInt(etNum2.getText().toString());
        String res = getString(R.string.res) + " " + ctr.verSomatoriaNumerosPrimos(num1,num2);
        if(res.contains("Erro")){
            res = res.contains("Menor") ? "O menor número deverá ser maior ou igual a 0"
                    : "O maior número deverá ser menor ou igual a 50";
            etNum1.setText("");
            etNum2.setText("");
        }
        tvRes.setText(res);
    }
}