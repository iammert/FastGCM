package co.mobiwise.fastgcm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import co.mobiwise.library.GCMListener;
import co.mobiwise.library.GCMManager;

public class GCMTest extends AppCompatActivity implements GCMListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gcmtest);
        GCMManager.getInstance(this).registerListener(this);
    }

    @Override
    public void onDeviceRegisted(String deviceToken) {
        Log.v("TEST","TOKEN : " + deviceToken);
        GCMManager.getInstance(this).subscribeTopic("asd");
    }

    @Override
    public void onMessage(String from, Bundle data) {
        Log.v("TEST",data.getString("message"));
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
