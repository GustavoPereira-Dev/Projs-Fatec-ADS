package fatec.zl.edu.maioresomaimpar.view;

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

import fatec.zl.edu.maioresomaimpar.R;
import fatec.zl.edu.maioresomaimpar.controller.MaiorESomaImparController;

public class MainActivity extends AppCompatActivity {

    private EditText etNum1;
    private EditText etNum2;
    private Button btnMaior;
    private Button btnSomatoria;
    private TextView tvRes;
    private MaiorESomaImparController ctr;

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

        ctr = new MaiorESomaImparController();
        etNum1 = findViewById(R.id.etNum1);
        etNum1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        etNum2 = findViewById(R.id.etNum2);
        etNum2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tvRes = findViewById(R.id.tvRes);
        tvRes.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        btnMaior = findViewById(R.id.btnMaior);
        btnSomatoria = findViewById(R.id.btnSomatoria);

        btnMaior.setOnClickListener(m -> maior());
        btnSomatoria.setOnClickListener(s -> somatoria());
    }

    private void maior(){
        int num1 = Integer.parseInt(etNum1.getText().toString());
        int num2 = Integer.parseInt(etNum2.getText().toString());
        String res = getString(R.string.res) + " " + ctr.verMaior(num1,num2);
        tvRes.setText(res);
    }

    private void somatoria(){
        int num1 = Integer.parseInt(etNum1.getText().toString());
        int num2 = Integer.parseInt(etNum2.getText().toString());
        String res = getString(R.string.res) + " " + ctr.verSomatoriaImpar(num1,num2);
        tvRes.setText(res);
    }
}