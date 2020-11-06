package com.example.lutroncontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "==MainActivity==";
    public final static String MASSAGE = "inputMassage";
    public static final String BROADCAST_ACTION = "com.example.lutroncontrol";
    final String ipaddress = "192.168.1.7";
//    int port=1667;
final int port=22;
    String r001, r003, r004, r005, r008, r009, r010, r011, r012, r013;
    String r101, r102, r103, r104, r105, r106, r107, r108, r109, r110, r111, r112, r113, r114;
    String r201, r202, r204, r205, r206, r207, r208, r209, r210, r211;
    String r301, r302, r303, r304, r304a, r305, r306, r314, r315;

    ImageButton button001, button003, button004, button005, button008, button009, button010, button011, button012, button013;
    ImageButton button101, button102, button103, button104, button105, button106, button107, button108, button109, button110, button111, button112, button113, button114;
    ImageButton button201, button202, button204, button205, button206, button207, button208, button209, button210, button211;
    ImageButton button301, button302, button303, button304, button304a, button305, button306, button314, button315;

    //   ImageButton button1, button2, button3, button4, button5, button6, button7, button8, button9, button10, button11, button12, button13, button14, button15, button16, button17, button18, button19, button20;
    //   ImageButton button21, button22, button23, button24, button25, button26, button27, button28, button29, button30, button31, button32, button33, button34, button35, button36, button37, button38, button39, button40, button41, button42, button43;

    BroadcastReceiver br;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



        //==========================================================================
        // Receive massage from TCPService and make Toast
        //==========================================================================
        // create BroadcastReceiver

        br = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                String massage1 = intent.getStringExtra(MASSAGE);
                //        Log.d(LOG_TAG, "onReceive: massage1 = " + massage1);
                assert massage1 != null;
                Processing(massage1);
                //    Toast.makeText(MainActivity.this, "Not connect to " + massage1 +" !", Toast.LENGTH_LONG).show();

            }
        };
        // create filter for BroadcastReceiver
        IntentFilter intFilt = new IntentFilter(BROADCAST_ACTION);
        // registration (On) BroadcastReceiver
        registerReceiver(br, intFilt);
        //==========================================================================


        Button buttonSend = findViewById(R.id.buttonSend);
        button314 = findViewById(R.id.imagebutton314);
        button306 = findViewById(R.id.imagebutton306);
        button303 = findViewById(R.id.imagebutton303);
        button301 = findViewById(R.id.imagebutton301);
        button302 = findViewById(R.id.imagebutton302);
        button305 = findViewById(R.id.imagebutton305);
        button315 = findViewById(R.id.imagebutton315);
        button304a = findViewById(R.id.imagebutton304a);
        button304 = findViewById(R.id.imagebutton304);
        button210 = findViewById(R.id.imagebutton210);
        button211 = findViewById(R.id.imagebutton211);
        button202 = findViewById(R.id.imagebutton202);
        button201 = findViewById(R.id.imagebutton201);
        button204 = findViewById(R.id.imagebutton204);
        button205 = findViewById(R.id.imagebutton205);
        button209 = findViewById(R.id.imagebutton209);
        button208 = findViewById(R.id.imagebutton208);
        button207 = findViewById(R.id.imagebutton207);
        button206 = findViewById(R.id.imagebutton206);
        button110 = findViewById(R.id.imagebutton110);
        button112 = findViewById(R.id.imagebutton112);
        button107 = findViewById(R.id.imagebutton107);
        button108 = findViewById(R.id.imagebutton108);
        button104 = findViewById(R.id.imagebutton104);
        button105 = findViewById(R.id.imagebutton105);
        button106 = findViewById(R.id.imagebutton106);
        button114 = findViewById(R.id.imagebutton114);
        button109 = findViewById(R.id.imagebutton109);
        button111 = findViewById(R.id.imagebutton111);
        button103 = findViewById(R.id.imagebutton103);
        button101 = findViewById(R.id.imagebutton101);
        button102 = findViewById(R.id.imagebutton102);
        button113 = findViewById(R.id.imagebutton113);
        button012 = findViewById(R.id.imagebutton012);
        button013 = findViewById(R.id.imagebutton013);
        button011 = findViewById(R.id.imagebutton011);
        button004 = findViewById(R.id.imagebutton004);
        button003 = findViewById(R.id.imagebutton003);
        button001 = findViewById(R.id.imagebutton001);
        button005 = findViewById(R.id.imagebutton005);
        button010 = findViewById(R.id.imagebutton010);
        button009 = findViewById(R.id.imagebutton009);
        button008 = findViewById(R.id.imagebutton008);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //        Log.d(LOG_TAG, "buttonSend click");
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, TCPService.class);
                intent.putExtra("ipaddress", ipaddress);
                intent.putExtra("port", port);
                MainActivity.this.startService(intent);
            }
        });

        button001.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                Log.d(LOG_TAG, "button001 LongClick");



                return false;
            }
        });

    }

    @SuppressLint("ResourceAsColor")
    void Processing(String inputMassage){
        //   Log.d(LOG_TAG, "inputMassage: "+ inputMassage);

        int dummyi;

        dummyi = inputMassage.indexOf("r001") + 8;
        r001 = inputMassage.substring(dummyi, (dummyi + 1));
        dummyi = inputMassage.indexOf("r003") + 8;
        r003 = inputMassage.substring(dummyi, (dummyi + 1));
        dummyi = inputMassage.indexOf("r004") + 8;
        r004 = inputMassage.substring(dummyi, (dummyi + 1));
        dummyi = inputMassage.indexOf("r005") + 8;
        r005 = inputMassage.substring(dummyi, (dummyi + 1));
        dummyi = inputMassage.indexOf("r008") + 8;
        r008 = inputMassage.substring(dummyi, (dummyi + 1));
        dummyi = inputMassage.indexOf("r009") + 8;
        r009 = inputMassage.substring(dummyi, (dummyi + 1));
        dummyi = inputMassage.indexOf("r010") + 8;
        r010 = inputMassage.substring(dummyi, (dummyi + 1));
        dummyi = inputMassage.indexOf("r011") + 8;
        r011 = inputMassage.substring(dummyi, (dummyi + 1));
        dummyi = inputMassage.indexOf("r012") + 8;
        r012 = inputMassage.substring(dummyi, (dummyi + 1));
        dummyi = inputMassage.indexOf("r013") + 8;
        r013 = inputMassage.substring(dummyi, (dummyi + 1));

        dummyi = inputMassage.indexOf("r101") + 8;
        r101 = inputMassage.substring(dummyi, (dummyi + 1));
        dummyi = inputMassage.indexOf("r102") + 8;
        r102 = inputMassage.substring(dummyi, (dummyi + 1));
        dummyi = inputMassage.indexOf("r103") + 8;
        r103 = inputMassage.substring(dummyi, (dummyi + 1));
        dummyi = inputMassage.indexOf("r104") + 8;
        r104 = inputMassage.substring(dummyi, (dummyi + 1));
        dummyi = inputMassage.indexOf("r105") + 8;
        r105 = inputMassage.substring(dummyi, (dummyi + 1));
        dummyi = inputMassage.indexOf("r106") + 8;
        r106 = inputMassage.substring(dummyi, (dummyi + 1));
        dummyi = inputMassage.indexOf("r107") + 8;
        r107 = inputMassage.substring(dummyi, (dummyi + 1));
        dummyi = inputMassage.indexOf("r108") + 8;
        r108 = inputMassage.substring(dummyi, (dummyi + 1));
        dummyi = inputMassage.indexOf("r109") + 8;
        r109 = inputMassage.substring(dummyi, (dummyi + 1));
        dummyi = inputMassage.indexOf("r110") + 8;
        r110 = inputMassage.substring(dummyi, (dummyi + 1));
        dummyi = inputMassage.indexOf("r111") + 8;
        r111 = inputMassage.substring(dummyi, (dummyi + 1));
        dummyi = inputMassage.indexOf("r112") + 8;
        r112 = inputMassage.substring(dummyi, (dummyi + 1));
        dummyi = inputMassage.indexOf("r113") + 8;
        r113 = inputMassage.substring(dummyi, (dummyi + 1));
        dummyi = inputMassage.indexOf("r114") + 8;
        r114 = inputMassage.substring(dummyi, (dummyi + 1));

        dummyi = inputMassage.indexOf("r201") + 8;
        r201 = inputMassage.substring(dummyi, (dummyi + 1));
        dummyi = inputMassage.indexOf("r202") + 8;
        r202 = inputMassage.substring(dummyi, (dummyi + 1));
        dummyi = inputMassage.indexOf("r204") + 8;
        r204 = inputMassage.substring(dummyi, (dummyi + 1));
        dummyi = inputMassage.indexOf("r205") + 8;
        r205 = inputMassage.substring(dummyi, (dummyi + 1));
        dummyi = inputMassage.indexOf("r206") + 8;
        r206 = inputMassage.substring(dummyi, (dummyi + 1));
        dummyi = inputMassage.indexOf("r207") + 8;
        r207 = inputMassage.substring(dummyi, (dummyi + 1));
        dummyi = inputMassage.indexOf("r208") + 8;
        r208 = inputMassage.substring(dummyi, (dummyi + 1));
        dummyi = inputMassage.indexOf("r209") + 8;
        r209 = inputMassage.substring(dummyi, (dummyi + 1));
        dummyi = inputMassage.indexOf("r210") + 8;
        r210 = inputMassage.substring(dummyi, (dummyi + 1));
        dummyi = inputMassage.indexOf("r211") + 8;
        r211 = inputMassage.substring(dummyi, (dummyi + 1));

        dummyi = inputMassage.indexOf("r301") + 8;
        r301 = inputMassage.substring(dummyi, (dummyi + 1));
        dummyi = inputMassage.indexOf("r302") + 8;
        r302 = inputMassage.substring(dummyi, (dummyi + 1));
        dummyi = inputMassage.indexOf("r303") + 8;
        r303 = inputMassage.substring(dummyi, (dummyi + 1));
        dummyi = inputMassage.indexOf("r304") + 8;
        r304 = inputMassage.substring(dummyi, (dummyi + 1));
        dummyi = inputMassage.indexOf("r304a") + 8;
        r304a = inputMassage.substring(dummyi, (dummyi + 1));
        dummyi = inputMassage.indexOf("r305") + 8;
        r305 = inputMassage.substring(dummyi, (dummyi + 1));
        dummyi = inputMassage.indexOf("r306") + 8;
        r306 = inputMassage.substring(dummyi, (dummyi + 1));
        dummyi = inputMassage.indexOf("r314") + 8;
        r314 = inputMassage.substring(dummyi, (dummyi + 1));
        dummyi = inputMassage.indexOf("r315") + 8;
        r315 = inputMassage.substring(dummyi, (dummyi + 1));

/*
    Log.d(LOG_TAG, "r001= "+ r001);
    Log.d(LOG_TAG, "r003= "+ r003);
    Log.d(LOG_TAG, "r004= "+ r004);
    Log.d(LOG_TAG, "r005= "+ r005);
    Log.d(LOG_TAG, "r008= "+ r008);
    Log.d(LOG_TAG, "r009= "+ r009);
    Log.d(LOG_TAG, "r010= "+ r010);
    Log.d(LOG_TAG, "r011= "+ r011);
    Log.d(LOG_TAG, "r012= "+ r012);
    Log.d(LOG_TAG, "r013= "+ r013);
*/




        float alfalow=0.15f;

        if(r001.charAt(0) == '1'){button001.setAlpha(1f);}else{button001.setAlpha(alfalow);}
        if(r003.charAt(0) == '1'){button003.setAlpha(1f);}else{button003.setAlpha(alfalow);}
        if(r004.charAt(0) == '1'){button004.setAlpha(1f);}else{button004.setAlpha(alfalow);}
        if(r005.charAt(0) == '1'){button005.setAlpha(1f);}else{button005.setAlpha(alfalow);}
        if(r008.charAt(0) == '1'){button008.setAlpha(1f);}else{button008.setAlpha(alfalow);}
        if(r009.charAt(0) == '1'){button009.setAlpha(1f);}else{button009.setAlpha(alfalow);}
        if(r010.charAt(0) == '1'){button010.setAlpha(1f);}else{button010.setAlpha(alfalow);}
        if(r011.charAt(0) == '1'){button011.setAlpha(1f);}else{button011.setAlpha(alfalow);}
        if(r012.charAt(0) == '1'){button012.setAlpha(1f);}else{button012.setAlpha(alfalow);}
        if(r013.charAt(0) == '1'){button013.setAlpha(1f);}else{button013.setAlpha(alfalow);}
        //    String r101, r102, r103, r104, r105, r106, r107, r108, r109, r110, r111, r112, r113, r114;
        if(r101.charAt(0) == '1'){button101.setAlpha(1f);}else{button101.setAlpha(alfalow);}
        if(r102.charAt(0) == '1'){button102.setAlpha(1f);}else{button102.setAlpha(alfalow);}
        if(r103.charAt(0) == '1'){button103.setAlpha(1f);}else{button103.setAlpha(alfalow);}
        if(r104.charAt(0) == '1'){button104.setAlpha(1f);}else{button104.setAlpha(alfalow);}
        if(r105.charAt(0) == '1'){button105.setAlpha(1f);}else{button105.setAlpha(alfalow);}
        if(r106.charAt(0) == '1'){button106.setAlpha(1f);}else{button106.setAlpha(alfalow);}
        if(r107.charAt(0) == '1'){button107.setAlpha(1f);}else{button107.setAlpha(alfalow);}
        if(r108.charAt(0) == '1'){button108.setAlpha(1f);}else{button108.setAlpha(alfalow);}
        if(r109.charAt(0) == '1'){button109.setAlpha(1f);}else{button109.setAlpha(alfalow);}
        if(r110.charAt(0) == '1'){button110.setAlpha(1f);}else{button110.setAlpha(alfalow);}
        if(r111.charAt(0) == '1'){button111.setAlpha(1f);}else{button111.setAlpha(alfalow);}
        if(r112.charAt(0) == '1'){button112.setAlpha(1f);}else{button112.setAlpha(alfalow);}
        if(r113.charAt(0) == '1'){button113.setAlpha(1f);}else{button113.setAlpha(alfalow);}
        if(r114.charAt(0) == '1'){button114.setAlpha(1f);}else{button114.setAlpha(alfalow);}
        //    String r201, r202, r204, r205, r206, r207, r208, r209, r210, r211;
        if(r201.charAt(0) == '1'){button201.setAlpha(1f);}else{button201.setAlpha(alfalow);}
        if(r202.charAt(0) == '1'){button202.setAlpha(1f);}else{button202.setAlpha(alfalow);}
        if(r204.charAt(0) == '1'){button204.setAlpha(1f);}else{button204.setAlpha(alfalow);}
        if(r205.charAt(0) == '1'){button205.setAlpha(1f);}else{button205.setAlpha(alfalow);}
        if(r206.charAt(0) == '1'){button206.setAlpha(1f);}else{button206.setAlpha(alfalow);}
        if(r207.charAt(0) == '1'){button207.setAlpha(1f);}else{button207.setAlpha(alfalow);}
        if(r208.charAt(0) == '1'){button208.setAlpha(1f);}else{button208.setAlpha(alfalow);}
        if(r209.charAt(0) == '1'){button209.setAlpha(1f);}else{button209.setAlpha(alfalow);}
        if(r210.charAt(0) == '1'){button210.setAlpha(1f);}else{button210.setAlpha(alfalow);}
        if(r211.charAt(0) == '1'){button211.setAlpha(1f);}else{button211.setAlpha(alfalow);}
        //    String r301, r302, r303, r304, r304a, r305, r306, r314, r315;
        if(r301.charAt(0) == '1'){button301.setAlpha(1f);}else{button301.setAlpha(alfalow);}
        if(r302.charAt(0) == '1'){button302.setAlpha(1f);}else{button302.setAlpha(alfalow);}
        if(r303.charAt(0) == '1'){button303.setAlpha(1f);}else{button303.setAlpha(alfalow);}
        if(r304.charAt(0) == '1'){button304.setAlpha(1f);}else{button304.setAlpha(alfalow);}
        if(r304.charAt(0) == '2'){button304.setAlpha(0.5f);}
        if(r304a.charAt(0) == '1'){button304a.setAlpha(1f);}else{button304a.setAlpha(alfalow);}
        if(r304a.charAt(0) == '2'){button304a.setAlpha(0.5f);}
        if(r305.charAt(0) == '1'){button305.setAlpha(1f);}else{button305.setAlpha(alfalow);}
        if(r306.charAt(0) == '1'){button306.setAlpha(1f);}else{button306.setAlpha(alfalow);}
        if(r314.charAt(0) == '1'){button314.setAlpha(1f);}else{button314.setAlpha(alfalow);}
        if(r315.charAt(0) == '1'){button315.setAlpha(1f);}else{button315.setAlpha(alfalow);}


        //   button304.setAlpha(0.5f);
        //   button304.setBackground(Drawable.createFromPath("@drawable/btn_top_border"));

    }


}