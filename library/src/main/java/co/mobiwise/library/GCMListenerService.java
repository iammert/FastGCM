package co.mobiwise.library;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created by mertsimsek on 30/07/15.
 */
public class GCMListenerService extends GcmListenerService {

    /**
     * This is called by GCM when message is received.
     * @param from
     * @param data
     */
    @Override
    public void onMessageReceived(String from, Bundle data) {
        GCMManager.getInstance(getApplicationContext()).onMessage(from, data);
    }
}
