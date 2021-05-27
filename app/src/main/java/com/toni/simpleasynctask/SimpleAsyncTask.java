package com.toni.simpleasynctask;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class SimpleAsyncTask extends AsyncTask<Void, Void, String> {

    private WeakReference<TextView> texto;
    private WeakReference<ProgressBar> progresBar;

    public SimpleAsyncTask(TextView texto, ProgressBar progressBar) {
        this.texto = new WeakReference<>(texto);
        this.progresBar = new WeakReference<>(progressBar);
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Despierta después de dormir 2 segundos";
    }

    @Override
    protected void onPostExecute(String s) {
        texto.get().setText(s);
    }


}
