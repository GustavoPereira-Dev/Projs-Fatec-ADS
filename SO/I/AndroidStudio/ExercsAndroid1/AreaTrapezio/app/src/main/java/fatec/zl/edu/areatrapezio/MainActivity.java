package fatec.zl.edu.areatrapezio;

import android.app.appsearch.GetByDocumentIdRequest;
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

/*
 *@author: Gustavo Pereira
 */
public class MainActivity extends AppCompatActivity {
    private EditText etBaseA;
    private EditText etBaseB;

    private EditText etAltura;
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

        etBaseA = findViewById(R.id.etBaseA);
        etBaseA.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        etBaseB = findViewById(R.id.etBaseB);
        etBaseB.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        etAltura = findViewById(R.id.etAltura);
        etAltura.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tvRes = findViewById(R.id.tvRes);
        tvRes.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        btnCalc = findViewById(R.id.btnCalc);
        btnCalc.setOnClickListener(calc -> calc());

    }

    private void calc(){
        int baseA = Integer.parseInt(etBaseA.getText().toString());
        int baseB = Integer.parseInt(etBaseB.getText().toString());
        int altura = Integer.parseInt(etAltura.getText().toString());
        float area;

        if(baseA > baseB){
            area = (float) ((baseA + baseB) * altura) / 2;
        } else{
            area = (float) ((baseB + baseA) * altura) / 2;
        }
        String res = getString(R.string.res) + " " + area + " cm^2";
        tvRes.setText(res);

    }

}