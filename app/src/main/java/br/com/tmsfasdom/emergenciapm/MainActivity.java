package br.com.tmsfasdom.emergenciapm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boolean alarmeAtivo = (PendingIntent.getBroadcast(this, 0, new Intent("br.com.tmsfasdom.emergenciapm.EXECUTAR_SERVICO"), PendingIntent.FLAG_NO_CREATE) == null);

        if (alarmeAtivo) {
            Log.d("EmergenciaPM", "Novo alarme Setado");

            Intent _intent = new Intent("br.com.tmsfasdom.emergenciapm.EXECUTAR_SERVICO");
            PendingIntent p = PendingIntent.getBroadcast(this, 0, _intent, 0);

            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(System.currentTimeMillis());
            c.add(Calendar.SECOND, 5);

            AlarmManager alarme = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
            alarme.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 15000, p);
        } else {
            Log.d("EmergenciaPM", "Alarme ja ativo");
        }

    }
}
