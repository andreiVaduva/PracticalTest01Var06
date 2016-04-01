package practicaltest01var06.eim.systems.cs.pub.ro.practicaltest01var06;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by andrei on 01.04.2016.
 */
public class PracticalTest01Var06Service extends Service {

    private ProcessingThread processingThread = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int firstNumber = intent.getIntExtra("firstNumber", -1);
        int secondNumber = intent.getIntExtra("secondNumber", -1);

        processingThread = new ProcessingThread(this, firstNumber, secondNumber);
        processingThread.start();

        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        processingThread.stopThread();
    }
}