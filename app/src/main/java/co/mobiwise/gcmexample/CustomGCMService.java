package co.mobiwise.gcmexample;

import android.os.Bundle;

import co.mobiwise.fastgcm.GCMListenerService;

/**
 * Created by mertsimsek on 04/08/15.
 */
public class CustomGCMService extends GCMListenerService {

    @Override
    public void onMessageReceived(String from, Bundle data) {
        super.onMessageReceived(from, data);

        //Here is called even app is not working.
        //create your notification here.
    }
}
