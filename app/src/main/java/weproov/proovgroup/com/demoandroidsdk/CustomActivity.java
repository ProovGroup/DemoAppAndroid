package weproov.proovgroup.com.demoandroidsdk;

import android.os.Bundle;

import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

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
