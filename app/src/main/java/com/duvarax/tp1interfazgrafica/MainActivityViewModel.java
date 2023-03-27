package com.duvarax.tp1interfazgrafica;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MainActivityViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<Double> valorConvertido;

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
    public void convertir(Boolean dolarAEuro, Double cantidad){
        if (dolarAEuro){
            convertirDolarAEuro(cantidad);
        }else{
            convertirEuroADolar(cantidad);
        }
    }
}
