package id.kostlab.guide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.btnGuide)
    Button btnGuide;
    @BindView(R.id.btnOther)
    Button btnOther;
    @BindView(R.id.btnDownload)
    Button btnDownload;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
//        Banner
        mAdView = findViewById(R.id.bannerMain);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
//        Inter

    }
    @OnClick(R.id.btnGuide)
    public void openGuide(){
        Intent i=new Intent(this,Guide.class);
        startActivity(i);
//        Toast.makeText(this,"GUIDE OPENED",Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.btnOther)
    public void openOther(){
        Intent i=new Intent(this,Other.class);
        startActivity(i);
//        Toast.makeText(this,"OTHER OPENED",Toast.LENGTH_LONG).show();
    }
    @OnClick(R.id.btnDownload)
    public void openDownload(){
        String url = "https://kostlab.id";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }


}