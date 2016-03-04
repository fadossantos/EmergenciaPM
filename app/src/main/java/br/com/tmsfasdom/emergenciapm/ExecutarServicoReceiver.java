package br.com.tmsfasdom.emergenciapm;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ExecutarServicoReceiver extends BroadcastReceiver {
    public ExecutarServicoReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!(isMyServiceRunning(ScreenOnOffService.class, context))) {
            Intent serviceIntent = new Intent(context, ScreenOnOffService.class);
            context.startService(serviceIntent);
        }
    }

    private boolean isMyServiceRunning(Class<?> serviceClass, Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                Log.d("EmergenciaPM", "Servico escuta ScreenOn/Off ja esta rodando");
                return true;
            }
        }
        Log.d("EmergenciaPM", "Servico escuta ScreenOn/Off nao esta rodando");
        return false;
    }

}
