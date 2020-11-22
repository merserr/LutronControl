package com.example.lutroncontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    public final static String ROOM_NAME = "ROOM_NAME";
    private static final String LOG_TAG = "==MainActivity==";
    public final static String MASSAGE = "inputMassage";
    public static final String BROADCAST_ACTION = "com.example.lutroncontrol";
    final String ipaddress = "192.168.1.4";
    final int port=1667;
    String r001, r003, r004, r005, r008, r009, r010, r011, r012, r013;
    String r101, r102, r103, r104, r105, r106, r107, r108, r109, r110, r111, r112, r113, r114;
    String r201, r202, r204, r205, r206, r207, r208, r209, r210, r211;
    String r301, r302, r303, r304, r304a, r305, r306, r314, r315;

    Button button001, button003, button004, button005, button008, button009, button010, button011, button012, button013;
    Button button101, button102, button103, button104, button105, button106, button107, button108, button109, button110, button111, button112, button113, button114;
    Button button201, button202, button204, button205, button206, button207, button208, button209, button210, button211;
    Button button301, button302, button303, button304, button304a, button305, button306, button314, button315;

    Button button_morning, button_day, button_evening, button_night, button_on_off, buttonSend;
    TextView room_name;
    FrameLayout frame;
    int processor=0;
    int link=0;
    int keypad=0;
    int button=0;
    String sending_command_prepared="";
    String sending_command="";
    final GradientDrawable gd = new GradientDrawable();
    final GradientDrawable gd1 = new GradientDrawable();
    final GradientDrawable gd2 = new GradientDrawable();
    final GradientDrawable gd3 = new GradientDrawable();
    final float alfahight=1;

    BroadcastReceiver br;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        r001=r003=r004=r005=r008=r009=r010=r011=r012=r013="0";
        r101=r102=r103=r104=r105=r106=r107=r108=r109=r110=r111=r112=r113=r114="0";
        r201=r202=r204=r205=r206=r207=r208=r209=r210=r211="0";
        r301=r302=r303=r304=r304a=r305=r306=r314=r315="0";
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
        room_name = findViewById(R.id.textView);
        frame = findViewById(R.id.frame);
        button_morning = findViewById(R.id.button_morning);
        button_day = findViewById(R.id.button_day);
        button_evening = findViewById(R.id.button_evening);
        button_night = findViewById(R.id.button_night);
        button_on_off = findViewById(R.id.button_on_off);
        buttonSend = findViewById(R.id.buttonSend);
        button314 = findViewById(R.id.button314);
        button306 = findViewById(R.id.button306);
        button303 = findViewById(R.id.button303);
        button301 = findViewById(R.id.button301);
        button302 = findViewById(R.id.button302);
        button305 = findViewById(R.id.button305);
        button315 = findViewById(R.id.button315);
        button304a = findViewById(R.id.button304a);
        button304 = findViewById(R.id.button304);
        button210 = findViewById(R.id.button210);
        button211 = findViewById(R.id.button211);
        button202 = findViewById(R.id.button202);
        button201 = findViewById(R.id.button201);
        button204 = findViewById(R.id.button204);
        button205 = findViewById(R.id.button205);
        button209 = findViewById(R.id.button209);
        button208 = findViewById(R.id.button208);
        button207 = findViewById(R.id.button207);
        button206 = findViewById(R.id.button206);
        button110 = findViewById(R.id.button110);
        button112 = findViewById(R.id.button112);
        button107 = findViewById(R.id.button107);
        button108 = findViewById(R.id.button108);
        button104 = findViewById(R.id.button104);
        button105 = findViewById(R.id.button105);
        button106 = findViewById(R.id.button106);
        button114 = findViewById(R.id.button114);
        button109 = findViewById(R.id.button109);
        button111 = findViewById(R.id.button111);
        button103 = findViewById(R.id.button103);
        button101 = findViewById(R.id.button101);
        button102 = findViewById(R.id.button102);
        button113 = findViewById(R.id.button113);
        button012 = findViewById(R.id.button012);
        button013 = findViewById(R.id.button013);
        button011 = findViewById(R.id.button011);
        button004 = findViewById(R.id.button004);
        button003 = findViewById(R.id.button003);
        button001 = findViewById(R.id.button001);
        button005 = findViewById(R.id.button005);
        button010 = findViewById(R.id.button010);
        button009 = findViewById(R.id.button009);
        button008 = findViewById(R.id.button008);

        room_name.setVisibility(View.INVISIBLE);
        frame.setVisibility(View.INVISIBLE);
        button_morning.setVisibility(View.INVISIBLE);
        button_day.setVisibility(View.INVISIBLE);
        button_evening.setVisibility(View.INVISIBLE);
        button_night.setVisibility(View.INVISIBLE);
        button_on_off.setVisibility(View.INVISIBLE);

        button_morning.setOnClickListener(this);
        button_day.setOnClickListener(this);
        button_evening.setOnClickListener(this);
        button_night.setOnClickListener(this);
        button_on_off.setOnClickListener(this);

        button001.setOnLongClickListener((View.OnLongClickListener) this);
        button003.setOnLongClickListener((View.OnLongClickListener) this);
        button004.setOnLongClickListener((View.OnLongClickListener) this);
        button005.setOnLongClickListener((View.OnLongClickListener) this);
        button008.setOnLongClickListener((View.OnLongClickListener) this);
        button009.setOnLongClickListener((View.OnLongClickListener) this);
        button010.setOnLongClickListener((View.OnLongClickListener) this);
        button011.setOnLongClickListener((View.OnLongClickListener) this);
        button012.setOnLongClickListener((View.OnLongClickListener) this);
        button013.setOnLongClickListener((View.OnLongClickListener) this);
        button101.setOnLongClickListener((View.OnLongClickListener) this);
        button102.setOnLongClickListener((View.OnLongClickListener) this);
        button103.setOnLongClickListener((View.OnLongClickListener) this);
        button104.setOnLongClickListener((View.OnLongClickListener) this);
        button105.setOnLongClickListener((View.OnLongClickListener) this);
        button106.setOnLongClickListener((View.OnLongClickListener) this);
        button107.setOnLongClickListener((View.OnLongClickListener) this);
        button108.setOnLongClickListener((View.OnLongClickListener) this);
        button109.setOnLongClickListener((View.OnLongClickListener) this);
        button110.setOnLongClickListener((View.OnLongClickListener) this);
        button111.setOnLongClickListener((View.OnLongClickListener) this);
        button112.setOnLongClickListener((View.OnLongClickListener) this);
        button113.setOnLongClickListener((View.OnLongClickListener) this);
        button114.setOnLongClickListener((View.OnLongClickListener) this);
        button201.setOnLongClickListener((View.OnLongClickListener) this);
        button202.setOnLongClickListener((View.OnLongClickListener) this);
        button204.setOnLongClickListener((View.OnLongClickListener) this);
        button205.setOnLongClickListener((View.OnLongClickListener) this);
        button206.setOnLongClickListener((View.OnLongClickListener) this);
        button207.setOnLongClickListener((View.OnLongClickListener) this);
        button208.setOnLongClickListener((View.OnLongClickListener) this);
        button209.setOnLongClickListener((View.OnLongClickListener) this);
        button210.setOnLongClickListener((View.OnLongClickListener) this);
        button211.setOnLongClickListener((View.OnLongClickListener) this);
        button301.setOnLongClickListener((View.OnLongClickListener) this);
        button302.setOnLongClickListener((View.OnLongClickListener) this);
        button303.setOnLongClickListener((View.OnLongClickListener) this);
        button304.setOnLongClickListener((View.OnLongClickListener) this);
        button304a.setOnLongClickListener((View.OnLongClickListener) this);
        button305.setOnLongClickListener((View.OnLongClickListener) this);
        button306.setOnLongClickListener((View.OnLongClickListener) this);
        button314.setOnLongClickListener((View.OnLongClickListener) this);
        button315.setOnLongClickListener((View.OnLongClickListener) this);

        //gd1.setColor(getColor(R.color.colorPrimary)); // Changes this drawbale to use a single color instead of a gradient

        // Light is on, buttons control not visible
        gd.setCornerRadius(5);
        gd.setStroke(5, getColor(R.color.colorBord));
        gd.setColor(getColor(R.color.colorLightOn));
        //gd.setStroke(5, getColor(R.color.colorRED));

        // Light is off, buttons control not visible
        gd1.setCornerRadius(5);
        gd1.setStroke(3, getColor(R.color.colorBord));
        gd1.setColor(getColor(R.color.colorLightOff));

        // Light is on, buttons control visible
        gd2.setCornerRadius(5);
        gd2.setStroke(7, getColor(R.color.colorRED));
        gd2.setColor(getColor(R.color.colorLightOn));

        // Light is off, buttons control visible
        gd3.setCornerRadius(5);
        gd3.setStroke(7, getColor(R.color.colorRED));
        gd3.setColor(getColor(R.color.colorLightOff));

        buttonSend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //        Log.d(LOG_TAG, "buttonSend click");
                room_name.setVisibility(View.INVISIBLE);
                frame.setVisibility(View.INVISIBLE);
                button_morning.setVisibility(View.INVISIBLE);
                button_day.setVisibility(View.INVISIBLE);
                button_evening.setVisibility(View.INVISIBLE);
                button_night.setVisibility(View.INVISIBLE);
                button_on_off.setVisibility(View.INVISIBLE);

               // alfaset();
               // setallalfa(1f);
                setallalfa(alfahight);

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, TCPService.class);
                intent.putExtra("ipaddress", ipaddress);
                intent.putExtra("port", port);
                intent.putExtra("command", "");
                MainActivity.this.startService(intent);
            }
        });


    }

    void Processing(String inputMassage){
        Log.d(LOG_TAG, "input Processing Massage1: "+ inputMassage);

        int dummyi;
//        if (!inputMassage.matches("\\[(\\[\"-?\\d{10}\",\"-?\\d\\d?\\d?\\.\\d\",\"-?\\d\\d?\\d?\\.\\d\",\"-?\\d\\d?\\d?\\.\\d\",\"-?\\d\\d?\\d?\\.\\d\",\"-?\\d\\d?\\d?\\.\\d\",\"-?\\d\\d?\\d?\\.\\d\",\"-?\\d\\d?\\d?\\.\\d\",\"-?\\d\\d?\\d?\\.\\d\"\\],?)+\\]")) { }
        //if (inputMassage.matches("KBP, \\[\\d:\\d:\\d?\\d\\], \\d")) {
       // inputMassage = "cli: Ok r001="1";
        if (inputMassage.matches("\\{\"cli\": \"Ok\".*")) {
            //if (inputMassage.matches("\\{\"cli.*")) {

            Log.d(LOG_TAG, "input Processing Massage2: "+ inputMassage);

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
            dummyi = inputMassage.indexOf("r304a") + 9;
            r304a = inputMassage.substring(dummyi, (dummyi + 1));
            dummyi = inputMassage.indexOf("r305") + 8;
            r305 = inputMassage.substring(dummyi, (dummyi + 1));
            dummyi = inputMassage.indexOf("r306") + 8;
            r306 = inputMassage.substring(dummyi, (dummyi + 1));
            dummyi = inputMassage.indexOf("r314") + 8;
            r314 = inputMassage.substring(dummyi, (dummyi + 1));
            dummyi = inputMassage.indexOf("r315") + 8;
            r315 = inputMassage.substring(dummyi, (dummyi + 1));

        //    r315 = "1";
        //    setallalfa(alfahight);
            alfaset();

        } else if (inputMassage.matches("No connect to .*")) {
            Toast.makeText(MainActivity.this, inputMassage, Toast.LENGTH_LONG).show();
        } else if (inputMassage.matches("\\{\"cli\": \"NOT\".*")) {
            Toast.makeText(MainActivity.this, "Client not ready", Toast.LENGTH_LONG).show();
        }
        r315 = "1";
    }
    void ControlSection (String room, int processor, int link, int keypad, int button) {
        room_name.setVisibility(View.VISIBLE);
        frame.setVisibility(View.VISIBLE);
        button_morning.setVisibility(View.VISIBLE);
        button_day.setVisibility(View.VISIBLE);
        button_evening.setVisibility(View.VISIBLE);
        button_night.setVisibility(View.VISIBLE);
        button_on_off.setVisibility(View.VISIBLE);

        room_name.setText("Room " + room);
        sending_command_prepared = "KBP, ["+String.valueOf(processor)+":"+String.valueOf(link)+":"+String.valueOf(keypad)+"], ";
        Log.d(LOG_TAG, "sending_command_prepared = " + sending_command_prepared);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_morning: button=1; sending_command=sending_command_prepared + String.valueOf(button); Log.d(LOG_TAG, "button_morning"); break;
            case R.id.button_day:     button=2; sending_command=sending_command_prepared + String.valueOf(button); Log.d(LOG_TAG, "button_day");     break;
            case R.id.button_evening: button=3; sending_command=sending_command_prepared + String.valueOf(button); Log.d(LOG_TAG, "button_evening"); break;
            case R.id.button_night:   button=4; sending_command=sending_command_prepared + String.valueOf(button); Log.d(LOG_TAG, "button_night");     break;
            case R.id.button_on_off:  button=6; sending_command=sending_command_prepared + String.valueOf(button); Log.d(LOG_TAG, "button_on_off"); break;
        }

        Intent intent = new Intent();
        intent.setClass(MainActivity.this, TCPService.class);
        intent.putExtra("ipaddress", ipaddress);
        intent.putExtra("port", port);
        intent.putExtra("command", sending_command);
        Log.d(LOG_TAG, "sending_command: "+sending_command);
        MainActivity.this.startService(intent);
    }

    public boolean onLongClick(View v) {

         Log.d(LOG_TAG, "onLongClick");
        alfaset();
        setallalfa(0.2f);

        switch (v.getId()) {
            case R.id.button001:
                ControlSection("001", 2, 4, 22, button);
                button001.setAlpha(alfahight);
                if(r001.charAt(0) == '1'){button001.setBackgroundDrawable(gd2);}else{button001.setBackgroundDrawable(gd3);}
                break;
            case R.id.button003:
                ControlSection("003", 2, 4, 15, button);
                button003.setAlpha(alfahight);
                if(r003.charAt(0) == '1'){button003.setBackgroundDrawable(gd2);}else{button003.setBackgroundDrawable(gd3);}
                break;
            case R.id.button004:
                ControlSection("004", 1, 4, 1, button);
                button004.setAlpha(alfahight);
                if(r004.charAt(0) == '1'){button004.setBackgroundDrawable(gd2);}else{button004.setBackgroundDrawable(gd3);}
                break;
            case R.id.button005:
                ControlSection("005", 2, 4, 11, button);
                button005.setAlpha(alfahight);
                if(r005.charAt(0) == '1'){button005.setBackgroundDrawable(gd2);}else{button005.setBackgroundDrawable(gd3);}
                break;
            case R.id.button008:
                ControlSection("008", 2, 5, 30, button);
                button008.setAlpha(alfahight);
                if(r008.charAt(0) == '1'){button008.setBackgroundDrawable(gd2);}else{button008.setBackgroundDrawable(gd3);}
                break;
            case R.id.button009:
                ControlSection("009", 2, 4, 3, button);
                button009.setAlpha(alfahight);
                if(r009.charAt(0) == '1'){button009.setBackgroundDrawable(gd2);}else{button009.setBackgroundDrawable(gd3);}
                break;
            case R.id.button010:
                ControlSection("010", 2, 4, 10, button);
                button010.setAlpha(alfahight);
                if(r010.charAt(0) == '1'){button010.setBackgroundDrawable(gd2);}else{button010.setBackgroundDrawable(gd3);}
                break;
            case R.id.button011:
                ControlSection("011", 1, 4, 6, button);
                button011.setAlpha(alfahight);
                if(r011.charAt(0) == '1'){button011.setBackgroundDrawable(gd2);}else{button011.setBackgroundDrawable(gd3);}
                break;
            case R.id.button012:
                ControlSection("012", 1, 4, 16, button);
                button012.setAlpha(alfahight);
                if(r012.charAt(0) == '1'){button012.setBackgroundDrawable(gd2);}else{button012.setBackgroundDrawable(gd3);}
                break;
            case R.id.button013:
                ControlSection("013", 1, 4, 11, button);
                button013.setAlpha(alfahight);
                if(r013.charAt(0) == '1'){button013.setBackgroundDrawable(gd2);}else{button013.setBackgroundDrawable(gd3);}
                break;
            case R.id.button101:
                ControlSection("101", 2, 5, 21, button);
                button101.setAlpha(alfahight);
                if(r101.charAt(0) == '1'){button101.setBackgroundDrawable(gd2);}else{button101.setBackgroundDrawable(gd3);}
                break;
            case R.id.button102:
                ControlSection("102", 2, 5, 19, button);
                button102.setAlpha(alfahight);
                if(r102.charAt(0) == '1'){button102.setBackgroundDrawable(gd2);}else{button102.setBackgroundDrawable(gd3);}
                break;
            case R.id.button103:
                ControlSection("103", 1, 5, 29, button);
                button103.setAlpha(alfahight);
                if(r103.charAt(0) == '1'){button103.setBackgroundDrawable(gd2);}else{button103.setBackgroundDrawable(gd3);}
                break;
            case R.id.button104:
                ControlSection("104", 1, 6, 15, button);
                button104.setAlpha(alfahight);
                if(r104.charAt(0) == '1'){button104.setBackgroundDrawable(gd2);}else{button104.setBackgroundDrawable(gd3);}
                break;
            case R.id.button105:
                ControlSection("105", 2, 6, 7, button);
                button105.setAlpha(alfahight);
                if(r105.charAt(0) == '1'){button105.setBackgroundDrawable(gd2);}else{button105.setBackgroundDrawable(gd3);}
                break;
            case R.id.button106:
                ControlSection("106", 2, 6, 14, button);
                button106.setAlpha(alfahight);
                if(r106.charAt(0) == '1'){button106.setBackgroundDrawable(gd2);}else{button106.setBackgroundDrawable(gd3);}
                break;
            case R.id.button107:
                ControlSection("107", 1, 5, 17, button);
                button107.setAlpha(alfahight);
                if(r107.charAt(0) == '1'){button107.setBackgroundDrawable(gd2);}else{button107.setBackgroundDrawable(gd3);}
                break;
            case R.id.button108:
                ControlSection("108", 1, 5, 3, button);
                button108.setAlpha(alfahight);
                if(r108.charAt(0) == '1'){button108.setBackgroundDrawable(gd2);}else{button108.setBackgroundDrawable(gd3);}
                break;
            case R.id.button109:
                ControlSection("109", 2, 6, 16, button);
                button109.setAlpha(alfahight);
                if(r109.charAt(0) == '1'){button109.setBackgroundDrawable(gd2);}else{button109.setBackgroundDrawable(gd3);}
                break;
            case R.id.button110:
                ControlSection("110", 1, 4, 19, button);
                button110.setAlpha(alfahight);
                if(r110.charAt(0) == '1'){button110.setBackgroundDrawable(gd2);}else{button110.setBackgroundDrawable(gd3);}
                break;
            case R.id.button111:
                ControlSection("111", 1, 5, 19, button);
                button111.setAlpha(alfahight);
                if(r111.charAt(0) == '1'){button111.setBackgroundDrawable(gd2);}else{button111.setBackgroundDrawable(gd3);}
                break;
            case R.id.button112:
                ControlSection("112", 1, 5, 8, button);
                button112.setAlpha(alfahight);
                if(r112.charAt(0) == '1'){button112.setBackgroundDrawable(gd2);}else{button112.setBackgroundDrawable(gd3);}
                break;
            case R.id.button113:
                ControlSection("113", 2, 5, 7, button);
                button113.setAlpha(alfahight);
                if(r113.charAt(0) == '1'){button113.setBackgroundDrawable(gd2);}else{button113.setBackgroundDrawable(gd3);}
                break;
            case R.id.button114:
                ControlSection("114", 2, 5, 1, button);
                button114.setAlpha(alfahight);
                if(r114.charAt(0) == '1'){button114.setBackgroundDrawable(gd2);}else{button114.setBackgroundDrawable(gd3);}
                break;
            case R.id.button201:
                ControlSection("201", 4, 4, 20, button);
                button201.setAlpha(alfahight);
                if(r201.charAt(0) == '1'){button201.setBackgroundDrawable(gd2);}else{button201.setBackgroundDrawable(gd3);}
                break;
            case R.id.button202:
                ControlSection("202", 3, 5, 7, button);
                button202.setAlpha(alfahight);
                if(r202.charAt(0) == '1'){button202.setBackgroundDrawable(gd2);}else{button202.setBackgroundDrawable(gd3);}
                break;
            case R.id.button204:
                ControlSection("204", 4, 6, 19, button);
                button204.setAlpha(alfahight);
                if(r204.charAt(0) == '1'){button204.setBackgroundDrawable(gd2);}else{button204.setBackgroundDrawable(gd3);}
                break;
            case R.id.button205:
                ControlSection("205", 4, 4, 6, button);
                button205.setAlpha(alfahight);
                if(r205.charAt(0) == '1'){button205.setBackgroundDrawable(gd2);}else{button205.setBackgroundDrawable(gd3);}
                break;
            case R.id.button206:
                ControlSection("206", 4, 4, 9, button);
                button206.setAlpha(alfahight);
                if(r206.charAt(0) == '1'){button206.setBackgroundDrawable(gd2);}else{button206.setBackgroundDrawable(gd3);}
                break;
            case R.id.button207:
                ControlSection("207", 4, 4, 25, button);
                button207.setAlpha(alfahight);
                if(r207.charAt(0) == '1'){button207.setBackgroundDrawable(gd2);}else{button207.setBackgroundDrawable(gd3);}
                break;
            case R.id.button208:
                ControlSection("208", 3, 4, 1, button);
                button208.setAlpha(alfahight);
                if(r208.charAt(0) == '1'){button208.setBackgroundDrawable(gd2);}else{button208.setBackgroundDrawable(gd3);}
                break;
            case R.id.button209:
                ControlSection("209", 3, 5, 21, button);
                button209.setAlpha(alfahight);
                if(r209.charAt(0) == '1'){button209.setBackgroundDrawable(gd2);}else{button209.setBackgroundDrawable(gd3);}
                break;
            case R.id.button210:
                ControlSection("210", 3, 4, 31, button);
                button210.setAlpha(alfahight);
                if(r210.charAt(0) == '1'){button210.setBackgroundDrawable(gd2);}else{button210.setBackgroundDrawable(gd3);}
                break;
            case R.id.button211:
                ControlSection("211", 3, 6, 1, button);
                button211.setAlpha(alfahight);
                if(r211.charAt(0) == '1'){button211.setBackgroundDrawable(gd2);}else{button211.setBackgroundDrawable(gd3);}
                break;
            case R.id.button301:
                ControlSection("301", 4, 6, 8, button);
                button301.setAlpha(alfahight);
                if(r301.charAt(0) == '1'){button301.setBackgroundDrawable(gd2);}else{button301.setBackgroundDrawable(gd3);}
                break;
            case R.id.button302:
                ControlSection("302", 4, 6, 4, button);
                button302.setAlpha(alfahight);
                if(r302.charAt(0) == '1'){button302.setBackgroundDrawable(gd2);}else{button302.setBackgroundDrawable(gd3);}
                break;
            case R.id.button303:
                ControlSection("303", 3, 5, 3, button);
                button303.setAlpha(alfahight);
                if(r303.charAt(0) == '1'){button303.setBackgroundDrawable(gd2);}else{button303.setBackgroundDrawable(gd3);}
                break;
            case R.id.button304:
                ControlSection("304", 4, 6, 5, button);
                button304.setAlpha(alfahight);
                if(r304.charAt(0) == '1'){button304.setBackgroundDrawable(gd2);}else{button304.setBackgroundDrawable(gd3);}
                break;
            case R.id.button304a:
                ControlSection("304a", 3, 5, 2, button);
                button304a.setAlpha(alfahight);
                if(r304a.charAt(0) == '1'){button304a.setBackgroundDrawable(gd2);}else{button304a.setBackgroundDrawable(gd3);}
                break;
            case R.id.button305:
                ControlSection("305", 4, 6, 10, button);
                button305.setAlpha(alfahight);
                if(r305.charAt(0) == '1'){button305.setBackgroundDrawable(gd2);}else{button305.setBackgroundDrawable(gd3);}
                break;
            case R.id.button306:
                ControlSection("306", 3, 6, 17, button);
                button306.setAlpha(alfahight);
                if(r306.charAt(0) == '1'){button306.setBackgroundDrawable(gd2);}else{button306.setBackgroundDrawable(gd3);}
                break;
            case R.id.button314:
                ControlSection("314", 3, 6, 21, button);
                button314.setAlpha(alfahight);
                if(r314.charAt(0) == '1'){button314.setBackgroundDrawable(gd2);}else{button314.setBackgroundDrawable(gd3);}
                break;
            case R.id.button315:
                ControlSection("315", 3, 6, 23, button);
                button315.setAlpha(alfahight);
                if(r315.charAt(0) == '1'){button315.setBackgroundDrawable(gd2);}else{button315.setBackgroundDrawable(gd3);}
               break;
        }
        return false;
    }

    void alfaset(){

    if(r001.charAt(0) == '1'){button001.setBackgroundDrawable(gd);}else{button001.setBackgroundDrawable(gd1);}
    if(r003.charAt(0) == '1'){button003.setBackgroundDrawable(gd);}else{button003.setBackgroundDrawable(gd1);}
    if(r004.charAt(0) == '1'){button004.setBackgroundDrawable(gd);}else{button004.setBackgroundDrawable(gd1);}
    if(r005.charAt(0) == '1'){button005.setBackgroundDrawable(gd);}else{button005.setBackgroundDrawable(gd1);}
    if(r008.charAt(0) == '1'){button008.setBackgroundDrawable(gd);}else{button008.setBackgroundDrawable(gd1);}
    if(r009.charAt(0) == '1'){button009.setBackgroundDrawable(gd);}else{button009.setBackgroundDrawable(gd1);}
    if(r010.charAt(0) == '1'){button010.setBackgroundDrawable(gd);}else{button010.setBackgroundDrawable(gd1);}
    if(r011.charAt(0) == '1'){button011.setBackgroundDrawable(gd);}else{button011.setBackgroundDrawable(gd1);}
    if(r012.charAt(0) == '1'){button012.setBackgroundDrawable(gd);}else{button012.setBackgroundDrawable(gd1);}
    if(r013.charAt(0) == '1'){button013.setBackgroundDrawable(gd);}else{button013.setBackgroundDrawable(gd1);}
    //    String r101, r102, r103, r104, r105, r106, r107, r108, r109, r110, r111, r112, r113, r114;
    if(r101.charAt(0) == '1'){button101.setBackgroundDrawable(gd);}else{button101.setBackgroundDrawable(gd1);}
    if(r102.charAt(0) == '1'){button102.setBackgroundDrawable(gd);}else{button102.setBackgroundDrawable(gd1);}
    if(r103.charAt(0) == '1'){button103.setBackgroundDrawable(gd);}else{button103.setBackgroundDrawable(gd1);}
    if(r104.charAt(0) == '1'){button104.setBackgroundDrawable(gd);}else{button104.setBackgroundDrawable(gd1);}
    if(r105.charAt(0) == '1'){button105.setBackgroundDrawable(gd);}else{button105.setBackgroundDrawable(gd1);}
    if(r106.charAt(0) == '1'){button106.setBackgroundDrawable(gd);}else{button106.setBackgroundDrawable(gd1);}
    if(r107.charAt(0) == '1'){button107.setBackgroundDrawable(gd);}else{button107.setBackgroundDrawable(gd1);}
    if(r108.charAt(0) == '1'){button108.setBackgroundDrawable(gd);}else{button108.setBackgroundDrawable(gd1);}
    if(r109.charAt(0) == '1'){button109.setBackgroundDrawable(gd);}else{button109.setBackgroundDrawable(gd1);}
    if(r110.charAt(0) == '1'){button110.setBackgroundDrawable(gd);}else{button110.setBackgroundDrawable(gd1);}
    if(r111.charAt(0) == '1'){button111.setBackgroundDrawable(gd);}else{button111.setBackgroundDrawable(gd1);}
    if(r112.charAt(0) == '1'){button112.setBackgroundDrawable(gd);}else{button112.setBackgroundDrawable(gd1);}
    if(r113.charAt(0) == '1'){button113.setBackgroundDrawable(gd);}else{button113.setBackgroundDrawable(gd1);}
    if(r114.charAt(0) == '1'){button114.setBackgroundDrawable(gd);}else{button114.setBackgroundDrawable(gd1);}
    //    String r201, r202, r204, r205, r206, r207, r208, r209, r210, r211;
    if(r201.charAt(0) == '1'){button201.setBackgroundDrawable(gd);}else{button201.setBackgroundDrawable(gd1);}
    if(r202.charAt(0) == '1'){button202.setBackgroundDrawable(gd);}else{button202.setBackgroundDrawable(gd1);}
    if(r204.charAt(0) == '1'){button204.setBackgroundDrawable(gd);}else{button204.setBackgroundDrawable(gd1);}
    if(r205.charAt(0) == '1'){button205.setBackgroundDrawable(gd);}else{button205.setBackgroundDrawable(gd1);}
    if(r206.charAt(0) == '1'){button206.setBackgroundDrawable(gd);}else{button206.setBackgroundDrawable(gd1);}
    if(r207.charAt(0) == '1'){button207.setBackgroundDrawable(gd);}else{button207.setBackgroundDrawable(gd1);}
    if(r208.charAt(0) == '1'){button208.setBackgroundDrawable(gd);}else{button208.setBackgroundDrawable(gd1);}
    if(r209.charAt(0) == '1'){button209.setBackgroundDrawable(gd);}else{button209.setBackgroundDrawable(gd1);}
    if(r210.charAt(0) == '1'){button210.setBackgroundDrawable(gd);}else{button210.setBackgroundDrawable(gd1);}
    if(r211.charAt(0) == '1'){button211.setBackgroundDrawable(gd);}else{button211.setBackgroundDrawable(gd1);}
    //    String r301, r302, r303, r304, r304a, r305, r306, r314, r315;
    if(r301.charAt(0) == '1'){button301.setBackgroundDrawable(gd);}else{button301.setBackgroundDrawable(gd1);}
    if(r302.charAt(0) == '1'){button302.setBackgroundDrawable(gd);}else{button302.setBackgroundDrawable(gd1);}
    if(r303.charAt(0) == '1'){button303.setBackgroundDrawable(gd);}else{button303.setBackgroundDrawable(gd1);}
    if(r304.charAt(0) == '1'){button304.setBackgroundDrawable(gd);}else{button304.setBackgroundDrawable(gd1);}
    if(r304.charAt(0) == '2'){button304.setBackgroundDrawable(gd);}else{button304.setBackgroundDrawable(gd1);}
    if(r304a.charAt(0) == '1'){button304a.setBackgroundDrawable(gd);}else{button304a.setBackgroundDrawable(gd1);}
    if(r304a.charAt(0) == '2'){button304a.setBackgroundDrawable(gd);}else{button304a.setBackgroundDrawable(gd1);}
    if(r305.charAt(0) == '1'){button305.setBackgroundDrawable(gd);}else{button305.setBackgroundDrawable(gd1);}
    if(r306.charAt(0) == '1'){button306.setBackgroundDrawable(gd);}else{button306.setBackgroundDrawable(gd1);}
    if(r314.charAt(0) == '1'){button314.setBackgroundDrawable(gd);}else{button314.setBackgroundDrawable(gd1);}
    if(r315.charAt(0) == '1'){button315.setBackgroundDrawable(gd);}else{button315.setBackgroundDrawable(gd1);}
}

    void setallalfa(float alfalow){
    button001.setAlpha(alfalow);
    button003.setAlpha(alfalow);
    button004.setAlpha(alfalow);
    button005.setAlpha(alfalow);
    button008.setAlpha(alfalow);
    button009.setAlpha(alfalow);
    button010.setAlpha(alfalow);
    button011.setAlpha(alfalow);
    button012.setAlpha(alfalow);
    button013.setAlpha(alfalow);
        //    String r101, r102, r103, r104, r105, r106, r107, r108, r109, r110, r111, r112, r113, r114;
    button101.setAlpha(alfalow);
    button102.setAlpha(alfalow);
    button103.setAlpha(alfalow);
    button104.setAlpha(alfalow);
    button105.setAlpha(alfalow);
    button106.setAlpha(alfalow);
    button107.setAlpha(alfalow);
    button108.setAlpha(alfalow);
    button109.setAlpha(alfalow);
    button110.setAlpha(alfalow);
    button111.setAlpha(alfalow);
    button112.setAlpha(alfalow);
    button113.setAlpha(alfalow);
    button114.setAlpha(alfalow);
        //    String r201, r202, r204, r205, r206, r207, r208, r209, r210, r211;
    button201.setAlpha(alfalow);
    button202.setAlpha(alfalow);
    button204.setAlpha(alfalow);
    button205.setAlpha(alfalow);
    button206.setAlpha(alfalow);
    button207.setAlpha(alfalow);
    button208.setAlpha(alfalow);
    button209.setAlpha(alfalow);
    button210.setAlpha(alfalow);
    button211.setAlpha(alfalow);
        //    String r301, r302, r303, r304, r304a, r305, r306, r314, r315;
    button301.setAlpha(alfalow);
    button302.setAlpha(alfalow);
    button303.setAlpha(alfalow);
    button304.setAlpha(alfalow);
    button304a.setAlpha(alfalow);
    button305.setAlpha(alfalow);
    button306.setAlpha(alfalow);
    button314.setAlpha(alfalow);
    button315.setAlpha(alfalow);
    }
}