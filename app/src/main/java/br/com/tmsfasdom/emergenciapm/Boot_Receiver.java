package br.com.tmsfasdom.emergenciapm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

public class Boot_Receiver extends BroadcastReceiver {
    public Boot_Receiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.d("EmergenciaPM", "onReceive: Boot Completo recebido pelo receiver!!!!!!!!!!!!!!!!!! ");

        boolean alarmeAtivo = (PendingIntent.getBroadcast(context, 0, new Intent("br.com.tmsfasdom.emergenciapm.EXECUTAR_SERVICO"), PendingIntent.FLAG_NO_CREATE) == null);

        if (alarmeAtivo) {
            Log.d("EmergenciaPM", "Novo alarme Setado");

            Intent _intent = new Intent("br.com.tmsfasdom.emergenciapm.EXECUTAR_SERVICO");
            PendingIntent p = PendingIntent.getBroadcast(context, 0, _intent, 0);

            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(System.currentTimeMillis());
            c.add(Calendar.SECOND, 5);

            AlarmManager alarme = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            alarme.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 15000, p);
        } else {
            Log.d("EmergenciaPM", "Alarme ja ativo");
        }
    }
}
