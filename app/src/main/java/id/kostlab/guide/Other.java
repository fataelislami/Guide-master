package id.kostlab.guide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.StartAppSDK;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Other extends AppCompatActivity {
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.btnOtherNext)
    Button btnOtherNext;
    @BindView(R.id.txtOtherTitle)
    TextView txtOtherTitle;
    @BindView(R.id.txtOtherDesc)
    TextView txtOtherDesc;
    int i=0;
    String[] data;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    Content content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        ButterKnife.bind(this);
        String startappOption = getString(R.string.startappOpt);
        String admobOption = getString(R.string.admobOpt);
        //        Init Intent
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                i= 0;
            } else {
                i= extras.getInt("page");
            }
        } else {
            i= (Integer) savedInstanceState.getSerializable("page");
        }
        if(admobOption.equals("true")) {
//        Init Ads
            MobileAds.initialize(this, new OnInitializationCompleteListener() {
                @Override
                public void onInitializationComplete(InitializationStatus initializationStatus) {
                }
            });
//        Init Banner
            mAdView = findViewById(R.id.bannerOther);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
//        Init Interstitial
            mInterstitialAd = new InterstitialAd(this);
            mInterstitialAd.setAdUnitId(getString(R.string.interstitial_id));
            mInterstitialAd.loadAd(new AdRequest.Builder().build());
        }
//        Init Startapp
        if(startappOption.equals("true")) {
            StartAppSDK.init(this, getString(R.string.start_app_id), true);
        }
//        Init Data
        content=new Content();
        data=content.otherList().get(i);
        txtOtherTitle.setText(data[0]);
        txtOtherDesc.setText(data[1]);
        i++;
    }

    @OnClick(R.id.btnBack)
    public void back(){
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
//        Toast.makeText(this,"Back Success",Toast.LENGTH_LONG).show();
    }
    @OnClick(R.id.btnOtherNext)
    public void next(){
        String startappOption = getString(R.string.startappOpt);
        String admobOption = getString(R.string.admobOpt);
        if(i%2==0){
            if(startappOption.equals("true")){
                StartAppAd.showAd(this);
            }
            if(admobOption.equals("true")){
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }
            }
        }
//        if(i%3==0){
//            Intent intent = new Intent(Other.this, Other.class);
//            int page = i;
//            intent.putExtra("page", page);
//            startActivity(intent);
//        }
        if(i<content.otherList().size()){
            data=content.otherList().get(i);
            txtOtherTitle.setText(data[0]);
            txtOtherDesc.setText(data[1]);
            i++;
        }else{
            i=0;
        }
    }


}