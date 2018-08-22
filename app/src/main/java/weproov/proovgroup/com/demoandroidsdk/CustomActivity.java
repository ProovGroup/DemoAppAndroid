package weproov.proovgroup.com.demoandroidsdk;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.weproov.sdk.WPParameters;
import com.weproov.sdk.WPPartOption;
import com.weproov.sdk.WPReportFragment;

public class CustomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);

        WPParameters params = new WPParameters();
        params.partOptions.put(0, new WPPartOption(false,true));

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(com.weproov.sdk.R.id.container, WPReportFragment.newInstance(params));
        transaction.commit();
    }

}
