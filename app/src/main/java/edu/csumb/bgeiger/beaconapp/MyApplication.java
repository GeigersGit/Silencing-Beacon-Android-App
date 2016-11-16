package edu.csumb.bgeiger.beaconapp;

        import android.app.Application;
        import android.content.Context;
        import android.widget.Toast;

        import com.estimote.sdk.Beacon;
        import com.estimote.sdk.BeaconManager;
        import com.estimote.sdk.Region;

        import java.util.List;
        import java.util.UUID;

public class MyApplication extends Application {

    private BeaconManager beaconManager;
    private int count = 0;

    @Override
    public void onCreate() {
        super.onCreate();


        beaconManager = new BeaconManager(getApplicationContext());

        beaconManager.setMonitoringListener(new BeaconManager.MonitoringListener() {
            @Override
            public void onEnteredRegion(Region region, List<Beacon> list)
            {
                Context context = getApplicationContext();
                count++;
                Toast.makeText(context, "YOU MADE IT FAM" + count, Toast.LENGTH_LONG).show();

            }
            @Override
            public void onExitedRegion(Region region)
            {
                Context context = getApplicationContext();
                count--;
                Toast.makeText(context, "YOU GONE" + count, Toast.LENGTH_LONG).show();
            }
        });
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startMonitoring(new Region(
                        "monitored region",
                        UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"),
                        23985, 64678));
            }
        });
    }
}