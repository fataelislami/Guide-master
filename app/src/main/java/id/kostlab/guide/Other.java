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
        if(i%2==0){
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");
            }
        }
        if(i<=data.length){
            data=content.otherList().get(i);
            txtOtherTitle.setText(data[0]);
            txtOtherDesc.setText(data[1]);
            i++;
        }else{
            i=0;
        }
    }


}