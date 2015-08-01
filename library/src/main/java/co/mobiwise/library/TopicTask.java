package co.mobiwise.library;

import android.content.Context;
import android.os.AsyncTask;

import com.google.android.gms.gcm.GcmPubSub;

import java.io.IOException;

/**
 * Created by mertsimsek on 31/07/15.
 */
public class TopicTask extends AsyncTask<Void, Void, Boolean>{

    private Context mContext;
    private String mToken;
    private String mTopic;
    private boolean mSubsribe;

    /**
     * Task Constructor.
     * @param mContext Context
     * @param mToken Device token
     * @param mTopic Topic name
     * @param mSbscribe If this is true, then subscribe. Otherwise unsubscribe.
     */
    public TopicTask(Context mContext, String mToken, String mTopic, boolean mSbscribe) {
        this.mContext = mContext;
        this.mToken = mToken;
        this.mTopic = mTopic;
        this.mSubsribe = mSbscribe;
    }

    /**
     * Subsribe to topic or unsubscribe from topic.
     * @param params
     * @return
     */
    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            if(mSubsribe)
                GcmPubSub.getInstance(mContext).subscribe(mToken, "/topics/" + mTopic, null);
            else
                GcmPubSub.getInstance(mContext).unsubscribe(mToken, "/topics/" + mTopic);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
