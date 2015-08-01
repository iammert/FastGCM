package co.mobiwise.library;

import android.os.Bundle;

/**
 * Created by mertsimsek on 31/07/15.
 */
public interface GCMListener {

    /**
     * Called when device is registered to GCM servers and received token
     * @param deviceToken
     */
    void onDeviceRegisted(String deviceToken);

    /**
     * Called when downstream message receive by device.
     * @param from
     * @param data
     */
    void onMessage(String from, Bundle data);

    /**
     * Called when device is unable to google play service.
     */
    void onPlayServiceError();

}
