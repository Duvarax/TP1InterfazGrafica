package com.duvarax.tp1interfazgrafica;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.duvarax.tp1interfazgrafica.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    MainActivityViewModel mv;

    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);
        context = getApplicationContext();
        mv.getValorConvertido().observe(this, new Observer<Double>() {
            @Override
            public void onChanged(Double conversion) {
                binding.tvConversion.setText(conversion+"");
            }
        });


        binding.btnConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                   mv.convertir(binding.rbDolaresAEuros.isChecked(), Double.parseDouble(binding.etCantidad.getText().toString()));
                }catch(NumberFormatException empetyString){
                    Toast.makeText(context, "Debe ingresar la cantidad de la moneda para hacer la conversion", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.rbDolaresAEuros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.rbEurosADolar.setChecked(false);
                binding.etCantidad.setText("");
            }
        });
        binding.rbEurosADolar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.rbDolaresAEuros.setChecked(false);
                binding.etCantidad.setText("");
            }
        });


    }
}