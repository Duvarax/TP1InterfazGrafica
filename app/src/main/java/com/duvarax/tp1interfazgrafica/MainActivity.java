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

        mv.getRbActivado().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                binding.etEuros.setText("");
                binding.etDolares.setText("");

                binding.etEuros.setEnabled(aBoolean);
                binding.etDolares.setEnabled(!aBoolean);
            }
        });


        binding.rbEurosADolares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mv.desactivarEt(binding.rbEurosADolares.getText().toString());
            }
        });
        binding.rbDolaresAEuros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mv.desactivarEt(binding.rbDolaresAEuros.getText().toString());
            }
        });

        binding.btnConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mv.convertir(binding.etEuros.getText().toString(), binding.etDolares.getText().toString());
            }
        });
    }
}