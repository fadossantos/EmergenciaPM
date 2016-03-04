package br.com.tmsfasdom.emergenciapm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Denize on 03/03/2016.
 */
public class ScreenReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            Log.d("EmergenciaPM", "onReceive: Recebeu evento ScreenOff " + ScreenOnOffService.contador);
            ScreenOnOffService.aumentaContador();
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            Log.d("EmergenciaPM", "onReceive: Recebeu evento ScreenOn " + ScreenOnOffService.contador);
            ScreenOnOffService.aumentaContador();
        }
    }
}
