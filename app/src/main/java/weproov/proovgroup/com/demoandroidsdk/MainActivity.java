package weproov.proovgroup.com.demoandroidsdk;

import androidx.lifecycle.Observer;
import android.content.Intent;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.weproov.sdk.WPConfig;
import com.weproov.sdk.WPLoadingActivity;
import com.weproov.sdk.WPParameters;
import com.weproov.sdk.WPPartOption;
import com.weproov.sdk.WPReportDownloadingController;
import com.weproov.sdk.WPReportLoadedObserver;


public class MainActivity extends AppCompatActivity {
    // Activity
    private Button mProovCodeValidateButton;
    private EditText mProovCodeInput;

    // Permets de télécharger un rapport et de recuperer les erreurs
    private WPReportDownloadingController mReportLoaderCtrl;

    private void startManagedActivity(String proovCode){
        WPParameters params = new WPParameters();
        params.partOptions.put(0, new WPPartOption(false,true));
        startActivity(WPLoadingActivity.getIntent(MainActivity.this, proovCode, params));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Bind data with view
        mProovCodeInput = findViewById(R.id.edit_text);
        mProovCodeValidateButton = findViewById(R.id.but_validate);

        // Initialisation du module
        WPConfig.init("fr");
        // Je prepare le downloader qui me seras util sur la custom view
        mReportLoaderCtrl = new WPReportDownloadingController(this, new WPReportLoadedObserver(this, "") {
            @Override
            public void onReportLoaded() {
                WPParameters params = new WPParameters();
                params.partOptions.put(0, new WPPartOption(false,true));
                startActivity(new Intent(MainActivity.this, CustomActivity.class));
            }
        });

        // Permets d'être notifié si une erreur survient lors du chargement d'un rapport
        mReportLoaderCtrl.getErrorEmitterLiveData().observe(this, new Observer<Throwable>() {
            @Override
            public void onChanged(@Nullable Throwable throwable) {
                finish();
            }
        });

        mProovCodeValidateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String proovCode = mProovCodeInput.getText().toString();
                if (proovCode.length() == 6) {
                    // Charger le rapport dans une view dédier
//                    startManagedActivity(proovCode);
                    // charger le raport dans une customView
                    mReportLoaderCtrl.load(proovCode);
                }
            }
        });
        //Connection au backend
        WPConfig.connect(this, "< api Key >", "< secret >", new WPConfig.ConnectionListener() {
            @Override
            public void onError(Exception e) {
                Log.e("WeProovSDK", "Error on connect", e);
            }
            @Override
            public void onConnected() {
                // SDK is connected to WeProov
                Log.d("WeProovSDK", "connected");
            }
        });
    }
}
