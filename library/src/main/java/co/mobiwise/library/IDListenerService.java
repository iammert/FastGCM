package co.mobiwise.library;

import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;

/**
 * Created by mertsimsek on 30/07/15.
 */
public class IDListenerService extends InstanceIDListenerService{

    /**
     * If you token is refreshed on GCM server side. GCM will notify
     * that you token has been changed. So we can start service again and
     * update our token.
     */
    @Override
    public void onTokenRefresh() {
        startService(new Intent(this, RegistrationService.class));
    }
}
