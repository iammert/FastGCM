package co.mobiwise.gcmexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import co.mobiwise.fastgcm.GCMListener;
import co.mobiwise.fastgcm.GCMManager;


public class GCMTest extends AppCompatActivity implements GCMListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gcmtest);
        GCMManager.getInstance(this).registerListener(this);
    }

    @Override
    public void onDeviceRegisted(String deviceToken) {
        GCMManager.getInstance(this).subscribeTopic("aqa");
    }

    @Override
    public void onMessage(String from, Bundle data) {
    }

    @Override
    public void onPlayServiceError() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GCMManager.getInstance(this).unRegisterListener();
    }
}
