package com.duvarax.tp1interfazgrafica;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Locale;

public class MainActivityViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<Double> valorConvertido;
    private MutableLiveData<Boolean> rbActivado;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<Double> getValorConvertido(){
        if (valorConvertido == null){
            valorConvertido = new MutableLiveData<>();
        }
        return valorConvertido;
    }

    public LiveData<Boolean> getRbActivado(){
        if(rbActivado == null){
            rbActivado = new MutableLiveData<>();
        }
        return rbActivado;
    }


    public void desactivarEt(String x){
        if(x.equalsIgnoreCase("euros a dolares")){
            rbActivado.setValue(true);
        }else{
            rbActivado.setValue(false);
        }

    }

    public void convertirDolarAEuro(Double dolar){
        try{
            valorConvertido.setValue(dolar*0.93);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public void convertirEuroADolar(Double euro){
        try{
            valorConvertido.setValue(euro*1.08);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public void convertir(String etEuros, String etDolares){
        System.out.println(etDolares.length());
        System.out.println(etEuros.length());
        if(etEuros.length() > 0){
            convertirEuroADolar(Double.parseDouble(etEuros));
        }
        if(etDolares.length() > 0){
            convertirDolarAEuro(Double.parseDouble(etDolares));
        }
    }
}
