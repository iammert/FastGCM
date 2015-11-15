package co.mobiwise.fastgcm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

/**
 * Created by mertsimsek on 31/07/15.
 */
public class GCMManager implements IGCMManager{

    /**
     * Class instance
     */
    private static GCMManager instance = null;

    /**
     * Context
     */
    private Context mContext;

    /**
     * Shared preferences saves token and isRegistered etc.
     */
    private SharedPreferences mSharedPreferences;

    /**
     * GCM Listener (OnRegistered, onMessage)
     */
    private GCMListener mGcmListener;

    /**
     * Private constructor and call init()
     * @param mContext
     */
    private GCMManager(Context mContext) {
        this.mContext = mContext;
        init();
    }

    /**
     * Singleton instance method
     * @param context
     * @return
     */
    public static GCMManager getInstance(Context context){
        if(instance == null)
            instance = new GCMManager(context);
        return instance;
    }

    /**
     * Initializes shared preferences, checks google play services if available,
     * and register device to GCM server.
     */
    public void init() {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        LocalBroadcastManager.getInstance(mContext).registerReceiver(mMessageReceiver, new IntentFilter(Constants.INTENT_REGISTRATION_COMPLETE));

        int mStatus = GooglePlayServicesUtil.isGooglePlayServicesAvailable(mContext);
        if(mStatus == ConnectionResult.SUCCESS)
            mContext.startService(new Intent(mContext, RegistrationService.class));
        else{
            if(mGcmListener != null)
                mGcmListener.onPlayServiceError();
        }
    }

    /**
     * Subscribe to topic
     * @param topicName
     */
    @Override
    public void subscribeTopic(String topicName) {
        if(getToken() != null)
            new TopicTask(mContext, getToken(), topicName, true).execute();
    }

    /**
     * Unsucbsribe from topic
     * @param topicName
     */
    @Override
    public void unSubscribeTopic(String topicName) {
        if(getToken() != null)
            new TopicTask(mContext, getToken(), topicName, false).execute();
    }

    /**
     * Register listener
     * @param mGcmListener
     */
    @Override
    public void registerListener(GCMListener mGcmListener) {
        this.mGcmListener = mGcmListener;
    }

    /**
     * Unregister listener. No longer need to notify.
     */
    @Override
    public void unRegisterListener() {
        mGcmListener = null;
        LocalBroadcastManager.getInstance(mContext).unregisterReceiver(mMessageReceiver);
    }

    /**
     * Called by service when message received. Notify Listener
     * if it s not null.
     * @param from
     * @param data
     */
    public void onMessage(String from, Bundle data) {
        if(mGcmListener != null)
            mGcmListener.onMessage(from, data);
    }

    /**
     * get Token from shared preferences. Noo need to go GCM server every time
     * we need token.
     * @return
     */
    private String getToken() {
        boolean hasToken = mSharedPreferences.getBoolean(Constants.SHARED_KEY_HAS_TOKEN, false);
        if(hasToken)
            return mSharedPreferences.getString(Constants.SHARED_KEY_TOKEN,"");
        else
            return null;
    }

    /**
     * Message receiver onReceive method called when registration ID(Token) is
     * available
     */
    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            boolean hasToken = mSharedPreferences.getBoolean(Constants.SHARED_KEY_HAS_TOKEN, false);

            if(hasToken && mGcmListener != null){
                String token = mSharedPreferences.getString(Constants.SHARED_KEY_TOKEN,"");
                mGcmListener.onDeviceRegisted(token);
            }
        }
    };
}
