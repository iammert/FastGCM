package co.mobiwise.library;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

/**
 * Created by mertsimsek on 31/07/15.
 */
public class RegistrationService extends IntentService {

    /**
     * Class tag
     */
    private static final String tag="RegistrationService.class.getSimpleName()";

    /**
     * Constructor
     */
    public RegistrationService() {
        super(tag);
    }

    /**
     * This Intent is responsible for get token from GCM server and send
     * broadcast. After we get token, we save this token to shared preferences.
     * @param intent
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        try {
            InstanceID instanceID = InstanceID.getInstance(this);
            String token = instanceID.getToken(getString(R.string.gcm_defaultSenderId), GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            sharedPreferences.edit().putBoolean(Constants.SHARED_KEY_HAS_TOKEN, true).apply();
            sharedPreferences.edit().putString(Constants.SHARED_KEY_TOKEN, token).apply();

        } catch (Exception e) {
            sharedPreferences.edit().putBoolean(Constants.SHARED_KEY_HAS_TOKEN, false).apply();
        }

        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(Constants.INTENT_REGISTRATION_COMPLETE));
    }
}
