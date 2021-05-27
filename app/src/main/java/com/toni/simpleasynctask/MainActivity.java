package com.toni.simpleasynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private static final String ESTADO_TEXTO = "texto_actual";
    TextView txt1;

    ExecutorService ejecutor;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt1 = findViewById(R.id.txt1);

        if(savedInstanceState != null){
            txt1.setText(savedInstanceState.getString(ESTADO_TEXTO));
        }

        ejecutor = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());
    }

    public void empezarTarea(View view){
        txt1.setText(R.string.siesta);
        //new SimpleAsyncTask(txt1).execute();
        ejecutor.execute(new Runnable() {
            @Override
            public void run() {
                //trabajo en segundo plano
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //trabajo en el hilo principal
                        txt1.setText("Despierta después de dormir 2 segundos");
                    }
                });
            }
        });
    }

@Override
    protected void onSaveInstanceState(Bundle estado){
        super.onSaveInstanceState(estado);
        //Guardo el estado del textview
    estado.putString(ESTADO_TEXTO, txt1.getText().toString());
}


}