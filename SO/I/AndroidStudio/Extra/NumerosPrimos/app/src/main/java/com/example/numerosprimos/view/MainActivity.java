package com.example.numerosprimos.view;

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

import com.example.numerosprimos.R;
import com.example.numerosprimos.controller.NumerosPrimosController;

public class MainActivity extends AppCompatActivity {

    private EditText etNumero;
    private Button btnVerPrimos;
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

        etNumero = findViewById(R.id.etNumero);
        etNumero.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        tvRes = findViewById(R.id.tvRes);
        tvRes.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        btnVerPrimos = findViewById(R.id.btnVerPrimos);
        ctr = new NumerosPrimosController();

        btnVerPrimos.setOnClickListener(p -> verNumerosPrimos());

    }


    public void verNumerosPrimos(){
        int num1 = 0;
        int num2 = Integer.parseInt(etNumero.getText().toString());
        String res = getString(R.string.res) + " " + ctr.verSomatoriaNumerosPrimos(num1,num2);

        if(res.contains("Erro")){
            res = res.contains("Menor") ? "O número digitado deverá ser maior ou igual a 0"
                    : "O número digitado deverá ser menor ou igual a 100";
            etNumero.setText("");
        } else if (num2 < 2){
            res += "Nenhum número primo encontrado";
        }
        tvRes.setText(res);

    }
}