package fam.doa.subnetcalculator;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.Formatter;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.facebook.ads.AbstractAdListener;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdActivity;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;

import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class HomeActivity extends AppCompatActivity {

    private final String TAG = InterstitialAdActivity.class.getSimpleName();

    boolean doubleBackToExitPressedOnce = false;


    int count = 0;

    LinearLayout dl, bl;
    private EditText first_octate;
    private EditText second_octate;
    private EditText third_octate;
    private EditText fourth_octate;
    private EditText cidr_value;


    private TextView device_ipa;
    private TextView device_mac;
    private TextView decimal_network;
    private TextView decimal_subnet_mask;
    private TextView decimal_first_host;
    private TextView decimal_last_host;
    private TextView decimal_broadcast;

    private TextView invalid_message;

    private TextView binary_network;
    private TextView binary_subnet_mask;
    private TextView binary_first_host;
    private TextView binary_last_host;
    private TextView binary_broadcast;


    int first_octate_value, second_octate_value, third_octate_value, fourth_octate_value;
    int cidr_value_integer;

    int binary_first_octate_size, binary_second_octate_size, binary_third_octate_size, binary_fourth_octate_size;

    int temp_size1 = 0;
    int temp_size2 = 0;
    int temp_size3 = 0;
    int temp_size4 = 0;

    StringBuilder temp_string1 = new StringBuilder();
    StringBuilder temp_string2 = new StringBuilder();
    StringBuilder temp_string3 = new StringBuilder();
    StringBuilder temp_string4 = new StringBuilder();

    StringBuffer extra_h = new StringBuffer();
    StringBuffer extra_b = new StringBuffer();
    String binary_h;
    String binary_b;


    StringBuilder cidr_srring = new StringBuilder();


    String s;
    String binary_first_part, binary_second_part, binary_third_part, binary_fourth_part;

    int decimal_first_part, decimal_second_part, decimal_third_part, decimal_fourth_part;

    int m, h, b;
    private AdView adView;
    private InterstitialAd interstitial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);


        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        first_octate = (EditText) findViewById(R.id.first_octate_ET);
        second_octate = (EditText) findViewById(R.id.second_octate_ET);
        third_octate = (EditText) findViewById(R.id.third_octate_ET);
        fourth_octate = (EditText) findViewById(R.id.fourth_octate_ET);
        cidr_value = (EditText) findViewById(R.id.cidr_value_ET);

        dl = findViewById(R.id.decimallayout);
        bl = findViewById(R.id.binanylin);


        first_octate.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (first_octate.getText().toString().length() == 3)     //size as per your requirement
                {
                    second_octate.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });


        second_octate.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (second_octate.getText().toString().length() == 3)     //size as per your requirement
                {
                    third_octate.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });


        third_octate.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (third_octate.getText().toString().length() == 3)     //size as per your requirement
                {
                    fourth_octate.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });


        fourth_octate.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (fourth_octate.getText().toString().length() == 3)     //size as per your requirement
                {
                    cidr_value.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });

        device_ipa = findViewById(R.id.device_ip);
        device_mac = findViewById(R.id.device_mac);

        decimal_network = (TextView) findViewById(R.id.decimal_network_TV);
        decimal_subnet_mask = (TextView) findViewById(R.id.decimal_subnet_TV);
        decimal_first_host = (TextView) findViewById(R.id.decimal_first_host_TV);
        decimal_last_host = (TextView) findViewById(R.id.decimal_last_host_TV);
        decimal_broadcast = (TextView) findViewById(R.id.decimal_broadcast_TV);

        invalid_message = (TextView) findViewById(R.id.invalid_message_TV);

        binary_network = (TextView) findViewById(R.id.binary_network_TV);
        binary_subnet_mask = (TextView) findViewById(R.id.binary_subnet_TV);
        binary_first_host = (TextView) findViewById(R.id.binary_first_host_TV);
        binary_last_host = (TextView) findViewById(R.id.binary_last_host_TV);
        binary_broadcast = (TextView) findViewById(R.id.binary_broadcast_TV);


//IP ADDRESS FIND
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        String ipadress = Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());
        device_ipa.setText(ipadress);
        WifiInfo mac = wifiManager.getConnectionInfo();
        String macadress = mac.getMacAddress();
        device_mac.setText(macadress);

        adView = new AdView(this, getString(R.string.facebook_banner), AdSize.BANNER_HEIGHT_50);
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);
        adContainer.addView(adView);
        adView.loadAd();


        bl.setVisibility(View.GONE);
    }


    private void showInterstitial() {
        if(interstitial.isAdLoaded())
        {
            interstitial.show();
        }
    }


    public void calculate() {
        count++;
        if (count % 2 == 0) {
            interstitial = new com.facebook.ads.InterstitialAd(this, getString(R.string.facebook_interstial));
            interstitial.setAdListener(new InterstitialAdListener() {
                @Override
                public void onInterstitialDisplayed(Ad ad) {

                }

                @Override
                public void onInterstitialDismissed(Ad ad) {

                }

                @Override
                public void onError(Ad ad, AdError adError) {

                }

                @Override
                public void onAdLoaded(Ad ad) {
                    showInterstitial();
                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            });
            interstitial.loadAd();
        }


        if (first_octate.getText().toString().isEmpty() || second_octate.getText().toString().isEmpty() ||
                third_octate.getText().toString().isEmpty() || fourth_octate.getText().toString().isEmpty()) {

            Toast.makeText(this, "Please enter a valid IP", Toast.LENGTH_SHORT).show();

            invalid_message.setVisibility(View.VISIBLE);
            invalid_message.setText("Check IP. Every octate must be required");

            decimal_network.setText("");
            decimal_subnet_mask.setText("");
            decimal_first_host.setText("");
            decimal_last_host.setText("");
            decimal_broadcast.setText("");

            binary_network.setText("");
            binary_subnet_mask.setText("");
            binary_first_host.setText("");
            binary_last_host.setText("");
            binary_broadcast.setText("");

        } else {


//            if (first_octate.length() ==3){
//
//                int maxLength = 3;
//                first_octate.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});
//            }

            if (cidr_value.getText().toString().isEmpty()) {

                first_octate_value = Integer.parseInt(String.valueOf(first_octate.getText()));
                second_octate_value = Integer.parseInt(String.valueOf(second_octate.getText()));
                third_octate_value = Integer.parseInt(String.valueOf(third_octate.getText()));
                fourth_octate_value = Integer.parseInt(String.valueOf(fourth_octate.getText()));

                if (first_octate_value > 255 || second_octate_value > 255 || third_octate_value > 255 || fourth_octate_value > 255) {

                    Toast.makeText(this, "Check IP Every octate must be between 0-255", Toast.LENGTH_LONG).show();

                    invalid_message.setVisibility(View.VISIBLE);
                    invalid_message.setText("Check IP. Every octate must be between 0-255");

                    decimal_network.setText("");
                    decimal_subnet_mask.setText("");
                    decimal_first_host.setText("");
                    decimal_last_host.setText("");
                    decimal_broadcast.setText("");

                    binary_network.setText("");
                    binary_subnet_mask.setText("");
                    binary_first_host.setText("");
                    binary_last_host.setText("");
                    binary_broadcast.setText("");

                } else {

                    invalid_message.setVisibility(View.GONE);

                    String binary_first_octate = Integer.toBinaryString(first_octate_value);
                    String binary_second_octate = Integer.toBinaryString(second_octate_value);
                    String binary_third_octate = Integer.toBinaryString(third_octate_value);
                    String binary_fourth_octate = Integer.toBinaryString(fourth_octate_value);


                    binary_first_octate_size = binary_first_octate.length();
                    binary_second_octate_size = binary_second_octate.length();
                    binary_third_octate_size = binary_third_octate.length();
                    binary_fourth_octate_size = binary_fourth_octate.length();

                    if (binary_first_octate_size < 8) {

                        temp_size1 = 8 - binary_first_octate_size;
                        for (int i = 0; i < temp_size1; i++) {

                            temp_string1.append("0");
                        }


                    }

                    if (binary_second_octate_size < 8) {

                        temp_size2 = 8 - binary_second_octate_size;
                        for (int i = 0; i < temp_size2; i++) {

                            temp_string2.append("0");
                        }


                    }

                    if (binary_third_octate_size < 8) {

                        temp_size3 = 8 - binary_third_octate_size;
                        for (int i = 0; i < temp_size3; i++) {

                            temp_string3.append("0");
                        }


                    }

                    if (binary_fourth_octate_size < 8) {

                        temp_size4 = 8 - binary_fourth_octate_size;
                        for (int i = 0; i < temp_size4; i++) {

                            temp_string4.append("0");
                        }


                    }


                    temp_size1 = 0;
                    temp_size2 = 0;
                    temp_size3 = 0;
                    temp_size4 = 0;


                    if (first_octate_value < 128) {

                        decimal_network.setText(first_octate.getText().toString() + ".0.0.0");
                        decimal_subnet_mask.setText("255.0.0.0");
                        decimal_first_host.setText(first_octate.getText().toString() + ".0.0.1");
                        decimal_last_host.setText(first_octate.getText().toString() + ".255.255.254");
                        decimal_broadcast.setText(first_octate.getText().toString() + ".255.255.255");


                        binary_network.setText(temp_string1 + binary_first_octate + ".00000000.00000000.00000000");
                        binary_subnet_mask.setText("11111111.00000000.00000000.00000000");
                        binary_first_host.setText(temp_string1 + binary_first_octate + ".00000000.00000000.00000001");
                        binary_last_host.setText(temp_string1 + binary_first_octate + ".11111111.11111111.11111110");
                        binary_broadcast.setText(temp_string1 + binary_first_octate + ".11111111.11111111.11111111");

                        temp_string1.setLength(0);

                    } else if (first_octate_value > 127 && first_octate_value < 192) {

                        decimal_network.setText(first_octate.getText().toString() + "."
                                + second_octate.getText().toString() + ".0.0");
                        decimal_subnet_mask.setText("255.255.0.0");
                        decimal_first_host.setText(first_octate.getText().toString() + "." +
                                second_octate.getText().toString() + "." + "0.1");
                        decimal_last_host.setText(first_octate.getText().toString() + "." +
                                second_octate.getText().toString() + "." + "255.254");
                        decimal_broadcast.setText(first_octate.getText().toString() + "." +
                                second_octate.getText().toString() + ".255.255");


                        binary_network.setText(temp_string1 + binary_first_octate + "." + temp_string2 +
                                binary_second_octate + ".00000000.00000000");
                        binary_subnet_mask.setText("11111111.11111111.00000000.00000000");
                        binary_first_host.setText(temp_string1 + binary_first_octate + "." + temp_string2 +
                                binary_second_octate + ".00000000.00000001");
                        binary_last_host.setText(temp_string1 + binary_first_octate + "." + temp_string2 +
                                binary_second_octate + ".11111111.11111110");
                        binary_broadcast.setText(temp_string1 + binary_first_octate + "." + temp_string2 +
                                binary_second_octate + ".11111111.11111111");

                        temp_string1.setLength(0);
                        temp_string2.setLength(0);

                    } else if (first_octate_value > 191 && first_octate_value < 224) {

                        decimal_network.setText(first_octate.getText().toString() + "."
                                + second_octate.getText().toString() + "." + third_octate.getText().toString() + ".0");
                        decimal_subnet_mask.setText("255.255.255.0");
                        decimal_first_host.setText(first_octate.getText().toString() + "." +
                                second_octate.getText().toString() + "." + third_octate.getText().toString() + ".1");
                        decimal_last_host.setText(first_octate.getText().toString() + "." + second_octate.getText().toString() +
                                "." + third_octate.getText().toString() + ".254");
                        decimal_broadcast.setText(first_octate.getText().toString() + "." + second_octate.getText().toString() +
                                "." + third_octate.getText().toString() + ".255");


                        binary_network.setText(temp_string1 + binary_first_octate + "." + temp_string2 +
                                binary_second_octate + "." + temp_string3 + binary_third_octate + ".00000000");
                        binary_subnet_mask.setText("11111111.11111111.11111111.00000000");
                        binary_first_host.setText(temp_string1 + binary_first_octate + "." + temp_string2 +
                                binary_second_octate + "." + temp_string3 + binary_third_octate + ".00000001");
                        binary_last_host.setText(temp_string1 + binary_first_octate + "." + temp_string2 +
                                binary_second_octate + "." + temp_string3 + binary_third_octate + ".11111110");
                        binary_broadcast.setText(temp_string1 + binary_first_octate + "." + temp_string2 +
                                binary_second_octate + "." + temp_string3 + binary_third_octate + ".11111111");


                        temp_string1.setLength(0);
                        temp_string2.setLength(0);
                        temp_string3.setLength(0);

                    } else if (first_octate_value > 223 && first_octate_value < 240) {

                        decimal_network.setText(first_octate.getText().toString() + "." + second_octate.getText().toString() + "" +
                                third_octate.getText().toString() + "." + fourth_octate.getText().toString());
                        decimal_subnet_mask.setText("255.255.255.255");
                        decimal_first_host.setText(first_octate.getText().toString() + "." + second_octate.getText().toString() + "." +
                                third_octate.getText().toString() + "." + fourth_octate.getText().toString());

                        decimal_last_host.setText(first_octate.getText().toString() + "." + second_octate.getText().toString() + "." +
                                third_octate.getText().toString() + "." + fourth_octate.getText().toString());

                        decimal_broadcast.setText(first_octate.getText().toString() + "." + second_octate.getText().toString() + "." +
                                third_octate.getText().toString() + "." + fourth_octate.getText().toString());


                        binary_network.setText(temp_string1 + binary_first_octate + "." + temp_string2 + binary_second_octate + "." +
                                temp_string3 + binary_third_octate + "." + temp_string4 + binary_fourth_octate);

                        binary_subnet_mask.setText("11111111.11111111.11111111.11111111");

                        binary_first_host.setText(temp_string1 + binary_first_octate + "." + temp_string2 + binary_second_octate + "." +
                                temp_string3 + binary_third_octate + "." + temp_string4 + binary_fourth_octate);

                        binary_last_host.setText(temp_string1 + binary_first_octate + "." + temp_string2 + binary_second_octate + "." +
                                temp_string3 + binary_third_octate + "." + temp_string4 + binary_fourth_octate);

                        binary_broadcast.setText(temp_string1 + binary_first_octate + "." + temp_string2 + binary_second_octate + "." +
                                temp_string3 + binary_third_octate + "." + temp_string4 + binary_fourth_octate);

                        temp_string1.setLength(0);
                        temp_string2.setLength(0);
                        temp_string3.setLength(0);
                        temp_string4.setLength(0);


                    } else if (first_octate_value > 239 && first_octate_value < 256) {

                        decimal_network.setText("not defined");
                        decimal_subnet_mask.setText("not defined");
                        decimal_first_host.setText("not defined");
                        decimal_last_host.setText("not defined");
                        decimal_broadcast.setText("not defined");

                        binary_network.setText("not defined");
                        binary_subnet_mask.setText("not defined");
                        binary_first_host.setText("not defined");
                        binary_last_host.setText("not defined");
                        binary_broadcast.setText("not defined");

                        temp_string1.setLength(0);
                        temp_string2.setLength(0);
                        temp_string3.setLength(0);
                        temp_string4.setLength(0);
                    }


                }


            } else {

                first_octate_value = Integer.parseInt(String.valueOf(first_octate.getText()));
                second_octate_value = Integer.parseInt(String.valueOf(second_octate.getText()));
                third_octate_value = Integer.parseInt(String.valueOf(third_octate.getText()));
                fourth_octate_value = Integer.parseInt(String.valueOf(fourth_octate.getText()));


                if (first_octate_value > 255 || second_octate_value > 255 || third_octate_value > 255 || fourth_octate_value > 255) {

                    Toast.makeText(this, "Check IP. Every octate must be between 0-255", Toast.LENGTH_SHORT).show();

                    invalid_message.setVisibility(View.VISIBLE);
                    invalid_message.setText("Check IP. Every octate must be between 0-255");

                    decimal_network.setText("");
                    decimal_subnet_mask.setText("");
                    decimal_first_host.setText("");
                    decimal_last_host.setText("");
                    decimal_broadcast.setText("");

                    binary_network.setText("");
                    binary_subnet_mask.setText("");
                    binary_first_host.setText("");
                    binary_last_host.setText("");
                    binary_broadcast.setText("");


                } else {

                    cidr_value_integer = Integer.parseInt(String.valueOf(cidr_value.getText()));

                    if (cidr_value_integer > 32) {

                        Toast.makeText(this, "Check CIDR value. It must be between 0-32", Toast.LENGTH_SHORT).show();

                        invalid_message.setVisibility(View.VISIBLE);
                        invalid_message.setText("Check CIDR value. It must be between 0-32");

                        decimal_network.setText("");
                        decimal_subnet_mask.setText("");
                        decimal_first_host.setText("");
                        decimal_last_host.setText("");
                        decimal_broadcast.setText("");

                        binary_network.setText("");
                        binary_subnet_mask.setText("");
                        binary_first_host.setText("");
                        binary_last_host.setText("");
                        binary_broadcast.setText("");

                    } else if (cidr_value_integer != 0 || cidr_value_integer != 8 || cidr_value_integer != 16 || cidr_value_integer != 24) {

                        invalid_message.setVisibility(View.GONE);

                        cidr_value_integer = Integer.parseInt(String.valueOf(cidr_value.getText()));

                        first_octate_value = Integer.parseInt(String.valueOf(first_octate.getText()));
                        second_octate_value = Integer.parseInt(String.valueOf(second_octate.getText()));
                        third_octate_value = Integer.parseInt(String.valueOf(third_octate.getText()));
                        fourth_octate_value = Integer.parseInt(String.valueOf(fourth_octate.getText()));


                        String binary_first_octate = Integer.toBinaryString(first_octate_value);
                        String binary_second_octate = Integer.toBinaryString(second_octate_value);
                        String binary_third_octate = Integer.toBinaryString(third_octate_value);
                        String binary_fourth_octate = Integer.toBinaryString(fourth_octate_value);


                        binary_first_octate_size = binary_first_octate.length();
                        binary_second_octate_size = binary_second_octate.length();
                        binary_third_octate_size = binary_third_octate.length();
                        binary_fourth_octate_size = binary_fourth_octate.length();

                        if (binary_first_octate_size < 8) {

                            temp_size1 = 8 - binary_first_octate_size;
                            for (int i = 0; i < temp_size1; i++) {

                                temp_string1.append("0");
                            }


                        }

                        if (binary_second_octate_size < 8) {

                            temp_size2 = 8 - binary_second_octate_size;
                            for (int i = 0; i < temp_size2; i++) {

                                temp_string2.append("0");
                            }


                        }

                        if (binary_third_octate_size < 8) {

                            temp_size3 = 8 - binary_third_octate_size;
                            for (int i = 0; i < temp_size3; i++) {

                                temp_string3.append("0");
                            }


                        }

                        if (binary_fourth_octate_size < 8) {

                            temp_size4 = 8 - binary_fourth_octate_size;
                            for (int i = 0; i < temp_size4; i++) {

                                temp_string4.append("0");
                            }


                        }


                        temp_size1 = 0;
                        temp_size2 = 0;
                        temp_size3 = 0;
                        temp_size4 = 0;


                        for (int j = 0; j < cidr_value_integer; j++) {

                            cidr_srring.append("1");

                        }

                        for (int k = cidr_value_integer; k < 32; k++) {

                            cidr_srring.append("0");

                        }


                        s = cidr_srring.toString();


                        binary_first_part = s.substring(0, 8);
                        binary_second_part = s.substring(8, 16);
                        binary_third_part = s.substring(16, 24);
                        binary_fourth_part = s.substring(24, 32);


                        binary_subnet_mask.setText(binary_first_part + "." + binary_second_part + "." +
                                binary_third_part + "." + binary_fourth_part);

                        decimal_first_part = Integer.parseInt(binary_first_part, 2);
                        decimal_second_part = Integer.parseInt(binary_second_part, 2);
                        decimal_third_part = Integer.parseInt(binary_third_part, 2);
                        decimal_fourth_part = Integer.parseInt(binary_fourth_part, 2);

                        decimal_subnet_mask.setText(decimal_first_part + "." + decimal_second_part + "." +
                                decimal_third_part + "." + decimal_fourth_part);


                        if (decimal_first_part > 0 && decimal_first_part < 255) {

                            m = 256 - decimal_first_part;
                            int temp_h = first_octate_value / m;
                            h = temp_h * m;
                            b = (m + h) - 1;

                            decimal_network.setText(String.valueOf(h) + ".0.0.0");
                            decimal_first_host.setText(String.valueOf(h) + ".0.0.1");
                            decimal_last_host.setText(String.valueOf(b) + ".255.255.254");
                            decimal_broadcast.setText(String.valueOf(b) + ".255.255.255");


                            binary_h = Integer.toBinaryString(h);
                            binary_b = Integer.toBinaryString(b);

                            if (binary_h.length() < 8) {
                                for (int a = binary_h.length(); a < 8; a++) {

                                    extra_h.append("0");
                                }
                            }

                            if (binary_b.length() < 8) {
                                for (int a = binary_b.length(); a < 8; a++) {

                                    extra_b.append("0");
                                }
                            }


                            binary_network.setText(extra_h + binary_h + ".00000000.00000000.00000000");
                            binary_first_host.setText(extra_h + binary_h + ".00000000.00000000.00000001");
                            binary_last_host.setText(extra_b + binary_b + ".11111111.11111111.11111110");
                            binary_broadcast.setText(extra_b + binary_b + ".11111111.11111111.11111111");

                            extra_b.setLength(0);
                            extra_h.setLength(0);
                            binary_h = "";
                            binary_b = "";


                        } else if (decimal_second_part > 0 && decimal_second_part < 255) {

                            m = 256 - decimal_second_part;
                            int temp_h = second_octate_value / m;
                            h = temp_h * m;
                            b = (m + h) - 1;

                            decimal_network.setText(first_octate.getText().toString() + "." + String.valueOf(h) + ".0.0");
                            decimal_first_host.setText(first_octate.getText().toString() + "." + String.valueOf(h) + ".0.1");
                            decimal_last_host.setText(first_octate.getText().toString() + "." + String.valueOf(b) + ".255.254");
                            decimal_broadcast.setText(first_octate.getText().toString() + "." + String.valueOf(b) + ".255.255");


                            binary_h = Integer.toBinaryString(h);
                            binary_b = Integer.toBinaryString(b);

                            if (binary_h.length() < 8) {

                                for (int a = binary_h.length(); a < 8; a++) {

                                    extra_h.append("0");
                                }
                            }

                            if (binary_b.length() < 8) {
                                for (int a = binary_b.length(); a < 8; a++) {

                                    extra_b.append("0");
                                }
                            }

                            binary_network.setText(temp_string1 + binary_first_octate + "." + extra_h + binary_h + ".00000000.00000000");
                            binary_first_host.setText(temp_string1 + binary_first_octate + "." + extra_h + binary_h + ".00000000.00000001");
                            binary_last_host.setText(temp_string1 + binary_first_octate + "." + extra_b + binary_b + ".11111111.11111110");
                            binary_broadcast.setText(temp_string1 + binary_first_octate + "." + extra_b + binary_b + ".11111111.11111111");


                            extra_b.setLength(0);
                            extra_h.setLength(0);
                            binary_h = "";
                            binary_b = "";
                            temp_string1.setLength(0);


                        } else if (decimal_third_part > 0 && decimal_third_part < 255) {


                            m = 256 - decimal_third_part;
                            int temp_h = third_octate_value / m;
                            h = temp_h * m;
                            b = (m + h) - 1;

                            decimal_network.setText(first_octate.getText().toString() + "." + second_octate.getText().toString() + "." +
                                    String.valueOf(h) + ".0");
                            decimal_first_host.setText(first_octate.getText().toString() + "." + second_octate.getText().toString() + "." +
                                    String.valueOf(h) + ".1");
                            decimal_last_host.setText(first_octate.getText().toString() + "." + second_octate.getText().toString() + "." +
                                    String.valueOf(b) + ".254");
                            decimal_broadcast.setText(first_octate.getText().toString() + "." + second_octate.getText().toString() + "." +
                                    String.valueOf(b) + ".255");


                            binary_h = Integer.toBinaryString(h);
                            binary_b = Integer.toBinaryString(b);

                            if (binary_h.length() < 8) {

                                for (int a = binary_h.length(); a < 8; a++) {

                                    extra_h.append("0");
                                }
                            }

                            if (binary_b.length() < 8) {


                                for (int a = binary_b.length(); a < 8; a++) {

                                    extra_b.append("0");
                                }
                            }

                            binary_network.setText(temp_string1 + binary_first_octate + "." + temp_string2 + binary_second_octate + "." + extra_h + binary_h + ".00000000");
                            binary_first_host.setText(temp_string1 + binary_first_octate + "." + temp_string2 + binary_second_octate + "." + extra_h + binary_h + ".00000001");
                            binary_last_host.setText(temp_string1 + binary_first_octate + "." + temp_string2 + binary_second_octate + "." + extra_b + binary_b + ".11111110");
                            binary_broadcast.setText(temp_string1 + binary_first_octate + "." + temp_string2 + binary_second_octate + "." + extra_b + binary_b + ".11111111");


                            extra_b.setLength(0);
                            extra_h.setLength(0);
                            binary_h = "";
                            binary_b = "";


                            temp_string1.setLength(0);
                            temp_string2.setLength(0);


                        } else if (decimal_fourth_part > 0 && decimal_fourth_part < 255) {

                            m = 256 - decimal_fourth_part;
                            int temp_h = fourth_octate_value / m;
                            h = temp_h * m;
                            b = (m + h) - 1;

                            int copy_h = h;
                            int copy_b = b;

                            decimal_network.setText(first_octate.getText().toString() + "." + second_octate.getText().toString() + "." +
                                    third_octate.getText().toString() + "." + String.valueOf(h));
                            decimal_first_host.setText(first_octate.getText().toString() + "." + second_octate.getText().toString() + "." +
                                    third_octate.getText().toString() + "." + String.valueOf(h + 1));
                            decimal_last_host.setText(first_octate.getText().toString() + "." + second_octate.getText().toString() + "." +
                                    third_octate.getText().toString() + "." + String.valueOf(b - 1));
                            decimal_broadcast.setText(first_octate.getText().toString() + "." + second_octate.getText().toString() + "." +
                                    third_octate.getText().toString() + "." + String.valueOf(b));


                            binary_h = Integer.toBinaryString(h);
                            binary_b = Integer.toBinaryString(b);


                            int sum_h = copy_h + 1;
                            int subtract_b = copy_b - 1;

                            String sum = Integer.toBinaryString(sum_h);
                            String subtract = Integer.toBinaryString(subtract_b);

                            StringBuffer extra_sum = new StringBuffer();
                            StringBuffer extra_subtract = new StringBuffer();

                            if (sum.length() < 8) {

                                for (int p = sum.length(); p < 8; p++) {

                                    extra_sum.append("0");

                                }
                            }


                            if (subtract.length() < 8) {

                                for (int p = subtract.length(); p < 8; p++) {

                                    extra_subtract.append("0");

                                }
                            }


                            if (binary_h.length() < 8) {
                                for (int a = binary_h.length(); a < 8; a++) {

                                    extra_h.append("0");
                                }
                            }

                            if (binary_b.length() < 8) {
                                for (int a = binary_b.length(); a < 8; a++) {

                                    extra_b.append("0");
                                }
                            }


                            sum = extra_sum + sum;
                            subtract = extra_subtract + subtract;


                            binary_network.setText(temp_string1 + binary_first_octate + "." + temp_string2 + binary_second_octate + "." +
                                    temp_string3 + binary_third_octate + "." + extra_h + binary_h);
                            binary_first_host.setText(temp_string1 + binary_first_octate + "." + temp_string2 + binary_second_octate + "." +
                                    temp_string3 + binary_third_octate + "." + sum);

                            binary_last_host.setText(temp_string1 + binary_first_octate + "." + temp_string2 + binary_second_octate + "." +
                                    temp_string3 + binary_third_octate + "." + subtract);

                            binary_broadcast.setText(temp_string1 + binary_first_octate + "." + temp_string2 + binary_second_octate + "." +
                                    temp_string3 + binary_third_octate + "." + extra_b + binary_b);


                            extra_b.setLength(0);
                            extra_h.setLength(0);
                            binary_h = "";
                            binary_b = "";
                            extra_sum.setLength(0);
                            extra_subtract.setLength(0);

                            temp_string1.setLength(0);
                            temp_string2.setLength(0);
                            temp_string3.setLength(0);


                        } else if (decimal_first_part == 255 && decimal_second_part == 255 && decimal_third_part == 255 && decimal_fourth_part == 255) {


                            decimal_network.setText(first_octate.getText().toString() + "." + second_octate.getText().toString() + "." +
                                    third_octate.getText().toString() + "." + fourth_octate.getText().toString());

                            decimal_subnet_mask.setText("255.255.255.255");
                            decimal_first_host.setText(first_octate.getText().toString() + "." + second_octate.getText().toString() + "." +
                                    third_octate.getText().toString() + "." + fourth_octate.getText().toString());

                            decimal_last_host.setText(first_octate.getText().toString() + "." + second_octate.getText().toString() + "." +
                                    third_octate.getText().toString() + "." + fourth_octate.getText().toString());

                            decimal_broadcast.setText(first_octate.getText().toString() + "." + second_octate.getText().toString() + "." +
                                    third_octate.getText().toString() + "." + fourth_octate.getText().toString());


                            binary_network.setText(temp_string1 + binary_first_octate + "." + temp_string2 + binary_second_octate + "." +
                                    temp_string3 + binary_third_octate + "." + temp_string4 + binary_fourth_octate);

                            binary_first_host.setText(temp_string1 + binary_first_octate + "." + temp_string2 + binary_second_octate + "." +
                                    temp_string3 + binary_third_octate + "." + temp_string4 + binary_fourth_octate);

                            binary_last_host.setText(temp_string1 + binary_first_octate + "." + temp_string2 + binary_second_octate + "." +
                                    temp_string3 + binary_third_octate + "." + temp_string4 + binary_fourth_octate);

                            binary_broadcast.setText(temp_string1 + binary_first_octate + "." + temp_string2 + binary_second_octate + "." +
                                    temp_string3 + binary_third_octate + "." + temp_string4 + binary_fourth_octate);


                            temp_string1.setLength(0);
                            temp_string2.setLength(0);
                            temp_string3.setLength(0);
                            temp_string4.setLength(0);


                        }


                        cidr_srring.setLength(0);


                        temp_string1.setLength(0);
                        temp_string2.setLength(0);
                        temp_string3.setLength(0);
                        temp_string4.setLength(0);


                    }

                    if (cidr_value_integer == 0 || cidr_value_integer == 8 || cidr_value_integer == 16 || cidr_value_integer == 24) {


                        String binary_first_octate = Integer.toBinaryString(first_octate_value);
                        String binary_second_octate = Integer.toBinaryString(second_octate_value);
                        String binary_third_octate = Integer.toBinaryString(third_octate_value);
                        String binary_fourth_octate = Integer.toBinaryString(fourth_octate_value);


                        binary_first_octate_size = binary_first_octate.length();
                        binary_second_octate_size = binary_second_octate.length();
                        binary_third_octate_size = binary_third_octate.length();
                        binary_fourth_octate_size = binary_fourth_octate.length();

                        if (binary_first_octate_size < 8) {

                            temp_size1 = 8 - binary_first_octate_size;
                            for (int i = 0; i < temp_size1; i++) {

                                temp_string1.append("0");
                            }


                        }

                        if (binary_second_octate_size < 8) {

                            temp_size2 = 8 - binary_second_octate_size;
                            for (int i = 0; i < temp_size2; i++) {

                                temp_string2.append("0");
                            }


                        }

                        if (binary_third_octate_size < 8) {

                            temp_size3 = 8 - binary_third_octate_size;
                            for (int i = 0; i < temp_size3; i++) {

                                temp_string3.append("0");
                            }


                        }

                        if (binary_fourth_octate_size < 8) {

                            temp_size4 = 8 - binary_fourth_octate_size;
                            for (int i = 0; i < temp_size4; i++) {

                                temp_string4.append("0");
                            }


                        }


                        temp_size1 = 0;
                        temp_size2 = 0;
                        temp_size3 = 0;
                        temp_size4 = 0;


                        if (cidr_value_integer == 0) {

                            decimal_network.setText("0.0.0.0");
                            decimal_subnet_mask.setText("0.0.0.0");
                            decimal_first_host.setText("0.0.0.1");
                            decimal_last_host.setText("255.255.255.254");
                            decimal_broadcast.setText("255.255.255.255");

                            binary_network.setText("00000000.00000000.00000000.0000000");
                            binary_subnet_mask.setText("00000000.00000000.00000000.00000000");
                            binary_first_host.setText("00000000.00000000.00000000.00000001");
                            binary_last_host.setText("11111111.11111111.11111111.11111110");
                            binary_broadcast.setText("11111111.11111111.11111111.11111111");


                        } else if (cidr_value_integer == 8) {


                            decimal_network.setText(first_octate.getText().toString() + ".0.0.0");
                            decimal_subnet_mask.setText("255.0.0.0");
                            decimal_first_host.setText(first_octate.getText().toString() + ".0.0.1");
                            decimal_last_host.setText(first_octate.getText().toString() + ".255.255.254");
                            decimal_broadcast.setText(first_octate.getText().toString() + ".255.255.255");


                            binary_network.setText(temp_string1 + binary_first_octate + ".00000000.00000000.00000000");
                            binary_subnet_mask.setText("11111111.00000000.00000000.00000000");
                            binary_first_host.setText(temp_string1 + binary_first_octate + ".00000000.00000000.00000001");
                            binary_last_host.setText(temp_string1 + binary_first_octate + ".11111111.11111111.11111110");
                            binary_broadcast.setText(temp_string1 + binary_first_octate + ".11111111.11111111.11111111");

                            temp_string1.setLength(0);


                        } else if (cidr_value_integer == 16) {


                            decimal_network.setText(first_octate.getText().toString() + "."
                                    + second_octate.getText().toString() + ".0.0");
                            decimal_subnet_mask.setText("255.255.0.0");
                            decimal_first_host.setText(first_octate.getText().toString() + "." +
                                    second_octate.getText().toString() + "." + "0.1");
                            decimal_last_host.setText(first_octate.getText().toString() + "." +
                                    second_octate.getText().toString() + "." + "255.254");
                            decimal_broadcast.setText(first_octate.getText().toString() + "." +
                                    second_octate.getText().toString() + ".255.255");


                            binary_network.setText(temp_string1 + binary_first_octate + "." + temp_string2 +
                                    binary_second_octate + ".00000000.00000000");
                            binary_subnet_mask.setText("11111111.11111111.00000000.00000000");
                            binary_first_host.setText(temp_string1 + binary_first_octate + "." + temp_string2 +
                                    binary_second_octate + ".00000000.00000001");
                            binary_last_host.setText(temp_string1 + binary_first_octate + "." + temp_string2 +
                                    binary_second_octate + ".11111111.11111110");
                            binary_broadcast.setText(temp_string1 + binary_first_octate + "." + temp_string2 +
                                    binary_second_octate + ".11111111.11111111");

                            temp_string1.setLength(0);
                            temp_string2.setLength(0);


                        } else if (cidr_value_integer == 24) {


                            decimal_network.setText(first_octate.getText().toString() + "."
                                    + second_octate.getText().toString() + "." + third_octate.getText().toString() + ".0");
                            decimal_subnet_mask.setText("255.255.255.0");
                            decimal_first_host.setText(first_octate.getText().toString() + "." +
                                    second_octate.getText().toString() + "." + third_octate.getText().toString() + ".1");
                            decimal_last_host.setText(first_octate.getText().toString() + "." + second_octate.getText().toString() +
                                    "." + third_octate.getText().toString() + ".254");
                            decimal_broadcast.setText(first_octate.getText().toString() + "." + second_octate.getText().toString() +
                                    "." + third_octate.getText().toString() + ".255");


                            binary_network.setText(temp_string1 + binary_first_octate + "." + temp_string2 +
                                    binary_second_octate + "." + temp_string3 + binary_third_octate + ".00000000");
                            binary_subnet_mask.setText("11111111.11111111.11111111.00000000");
                            binary_first_host.setText(temp_string1 + binary_first_octate + "." + temp_string2 +
                                    binary_second_octate + "." + temp_string3 + binary_third_octate + ".00000001");
                            binary_last_host.setText(temp_string1 + binary_first_octate + "." + temp_string2 +
                                    binary_second_octate + "." + temp_string3 + binary_third_octate + ".11111110");
                            binary_broadcast.setText(temp_string1 + binary_first_octate + "." + temp_string2 +
                                    binary_second_octate + "." + temp_string3 + binary_third_octate + ".11111111");


                            temp_string1.setLength(0);
                            temp_string2.setLength(0);
                            temp_string3.setLength(0);

                        }

                    }


                }


            }


        }


        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);


    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_for_all, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog);
                } else {
                    builder = new AlertDialog.Builder(this);
                }
                builder.setTitle("Thanks for using my app")
                        .setMessage("Do you want to exit from here?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                AppExit();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .show();

            default:
                return super.onOptionsItemSelected(item);
        }


    }


    public void AppExit() {

        this.finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

    }

    public void rateApp(MenuItem item) {

        rateApps();

    }


    public void shareApp(MenuItem item) {

        ApplicationInfo app = getApplicationContext().getApplicationInfo();
        String filePath = app.sourceDir;
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.createChooser(intent, "IP Calculator");
        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(filePath)));
        startActivity(Intent.createChooser(intent, "share IP Calculator to others using"));

    }

    public void moreApps(MenuItem item) {


        Intent intent;
        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/dev?id=7556100466882241730"));
        startActivity(intent);

    }

    public void rateApps() {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=fam.doa.subnetcalculator"));
        startActivity(i);
    }


    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again for exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    public void getip(MenuItem item) {

        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        String ipadress = Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());
        // device_ip.setText(ipadress);
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Your Device Ip Address")
                .setMessage(ipadress)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        // AppExit();
                    }
                })
                .show();

    }

    public void exit(MenuItem item) {

        System.exit(1);
        finish();
    }

    public void elear(MenuItem item) {

        first_octate.getText().clear();
        second_octate.getText().clear();
        third_octate.getText().clear();
        fourth_octate.getText().clear();
        cidr_value.getText().clear();

        decimal_network.setText("");
        decimal_subnet_mask.setText("");
        decimal_first_host.setText("");
        decimal_last_host.setText("");
        decimal_broadcast.setText("");

        invalid_message.setText("");

        binary_network.setText("");
        binary_subnet_mask.setText("");
        binary_first_host.setText("");
        binary_last_host.setText("");
        binary_broadcast.setText("");


    }



    public void decimal(View view) {
        calculate();
        bl.setVisibility(View.GONE);
        bl.requestFocus();
        dl.setVisibility(View.VISIBLE);
        dl.requestFocus();
    }

    public void Binary(View view) {
        calculate();
        dl.setVisibility(View.GONE);
        dl.requestFocus();
        bl.setVisibility(View.VISIBLE);
        bl.requestFocus();
    }
}