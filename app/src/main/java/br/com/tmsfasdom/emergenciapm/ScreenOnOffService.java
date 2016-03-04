package br.com.tmsfasdom.emergenciapm;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class ScreenOnOffService extends Service {
    public static int contador = 0;
    public static boolean isCounterRunning = false;

    public ScreenOnOffService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // register receiver that handles screen on and screen off logic
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        BroadcastReceiver mReceiver = new ScreenReceiver();
        registerReceiver(mReceiver, filter);
    }

    public static void aumentaContador(){
        contador += 1;
        if (contador == 1) {
            //Log.d("EmergenciaPM", "aumentaContador: Primeira vez que aperta o botao, iniciando contador... 5 segundos para zerar o numero de toques.");
            if (!isCounterRunning) {
                new Thread("Contador") {
                    @Override
                    public void run() {
                        super.run();
                        isCounterRunning = true;
                        try {
                            Thread.sleep(5000);
                            zeraContador();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        isCounterRunning = false;
                    }
                }.start();
            }
        }

        //5 numero de toques no botao power para disparar chamada de emergencia
        if (contador >= 5)
        {
            Log.d("EmergenciaPM", "aumentaContador: Atingiu 5 toques em menos de 5 segundos --> Disparar Chamada de Emergencia");
            zeraContador();
        }
    }

    private static void zeraContador() {
        contador = 0;
    }
}
