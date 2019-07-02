package ar.codeslu.plax.settingsitems;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nightonke.jellytogglebutton.JellyToggleButton;
import com.nightonke.jellytogglebutton.State;

import java.util.HashMap;
import java.util.Map;

import ar.codeslu.plax.EditProfile;
import ar.codeslu.plax.R;
import ar.codeslu.plax.global.AppBack;
import ar.codeslu.plax.global.Global;

public class SecuSetting extends AppCompatActivity {
    JellyToggleButton lockT,screenT;
    Button lockB;
    boolean clicked = false;
    DatabaseReference mData,mchat;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secu_setting);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        lockT = findViewById(R.id.lockT);
        screenT = findViewById(R.id.screenST);
        lockB = findViewById(R.id.lockB);
        mAuth = FirebaseAuth.getInstance();
        mData = FirebaseDatabase.getInstance().getReference(Global.USERS);
        mchat = FirebaseDatabase.getInstance().getReference(Global.CHATS);

        //set data
        if(((AppBack) getApplication()).shared().getBoolean("lock", false))
        {
            //lock screen
            ((AppBack) getApplication()).lockscreen(((AppBack) getApplication()).shared().getBoolean("lock", false));
            lockT.setChecked(true);
            lockB.setVisibility(View.VISIBLE);
        }
        else
        {
            lockT.setChecked(false);
            lockB.setVisibility(View.GONE);
        }

        screenT.setChecked(((AppBack) getApplication()).shared().getBoolean("screenP", false));

        lockT.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {
        if (isChecked) {
            lockB.setVisibility(View.VISIBLE);
            ((AppBack) getApplication()).lockscreenE();
        } else {
            lockB.setVisibility(View.GONE);
            ((AppBack) getApplication()).editSharePrefs().putBoolean("lock", false);
            ((AppBack) getApplication()).editSharePrefs().apply();
        }
    }

        });


        screenT.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("screen", true);
                    mData.child(mAuth.getCurrentUser().getUid()).updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });
                    try {
                        for (int i = 0; i < Global.diaG.size(); i++) {

                            mchat.child(Global.diaG.get(i).getId()).child(mAuth.getCurrentUser().getUid()).updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    ((AppBack) getApplication()).editSharePrefs().putBoolean("screenP", true);
                                    ((AppBack) getApplication()).editSharePrefs().apply();

                                }
                            })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(SecuSetting.this, R.string.check_conn, Toast.LENGTH_SHORT).show();
                                        }
                                    });

                        }
                    }
                    catch (NullPointerException e)
                    {
                        Toast.makeText(SecuSetting.this, R.string.check_conn, Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Map<String, Object> map = new HashMap<>();
                    map.put("screen", false);
                    mData.child(mAuth.getCurrentUser().getUid()).updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });
                    try {
                        for (int i = 0; i < Global.diaG.size(); i++) {

                            mchat.child(Global.diaG.get(i).getId()).child(mAuth.getCurrentUser().getUid()).updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    ((AppBack) getApplication()).editSharePrefs().putBoolean("screenP", false);
                                    ((AppBack) getApplication()).editSharePrefs().apply();

                                }
                            })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(SecuSetting.this, R.string.check_conn, Toast.LENGTH_SHORT).show();
                                        }
                                    });

                        }
                    }
                    catch (NullPointerException e)
                    {
                        Toast.makeText(SecuSetting.this, R.string.check_conn, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        lockT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked = true;
            }
        });

        lockB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AppBack) getApplication()).lockscreenE();
                ((AppBack) getApplication()).editSharePrefs().putBoolean("lock", true);
                ((AppBack) getApplication()).editSharePrefs().apply();
            }
        });



    }


    @Override
    public void onResume() {
        super.onResume();
        Global.currentactivity = this;
        AppBack myApp = (AppBack) this.getApplication();
        if (myApp.wasInBackground) {
            //init data
            Map<String, Object> map = new HashMap<>();
            map.put(Global.Online, true);
            mData.child(mAuth.getCurrentUser().getUid()).updateChildren(map);
            Global.local_on = true;
            //lock screen
            ((AppBack) getApplication()).lockscreen(((AppBack) getApplication()).shared().getBoolean("lock", false));
        }

        myApp.stopActivityTransitionTimer();
    }

    @Override
    public void onPause() {
        super.onPause();
        ((AppBack) this.getApplication()).startActivityTransitionTimer();
    }

}