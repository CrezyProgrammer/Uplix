package ar.codeslu.plax.holders;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.stfalcon.chatkit.link.AutoLinkMode;
import com.stfalcon.chatkit.link.AutoLinkOnClickListener;
import com.stfalcon.chatkit.link.AutoLinkTextView;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import ar.codeslu.plax.R;
import ar.codeslu.plax.custom.MessageSelectD;
import ar.codeslu.plax.custom.ReactCustom;
import ar.codeslu.plax.global.Global;

import com.stfalcon.chatkit.me.Message;
import com.thefinestartist.finestwebview.FinestWebView;

/**
 * Created by mostafa on 01/02/19.
 */

public class IncomeHolder
        extends MessagesListAdapter.IncomingMessageViewHolder<Message> {

    AutoLinkTextView autoLinkTextView;

    public IncomeHolder(View itemView, Object payload) {
        super(itemView);
    }

    @Override
    public void onBind(final Message message) {
        super.onBind(message);
        //react
        ImageView react = itemView.findViewById(R.id.react);
        if (message.isDeleted()) {
            react.setVisibility(View.GONE);
        } else {
            react.setVisibility(View.VISIBLE);
        }
        switch (message.getReact()) {
            case "like":
                react.setImageDrawable(Global.conA.getResources().getDrawable(R.drawable.like));
                break;
            case "funny":
                react.setImageDrawable(Global.conA.getResources().getDrawable(R.drawable.funny));
                break;
            case "love":
                react.setImageDrawable(Global.conA.getResources().getDrawable(R.drawable.love));
                break;
            case "sad":
                react.setImageDrawable(Global.conA.getResources().getDrawable(R.drawable.sad));
                break;
            case "angry":
                react.setImageDrawable(Global.conA.getResources().getDrawable(R.drawable.angry));
                break;
            case "no":
                react.setImageDrawable(Global.conA.getResources().getDrawable(R.drawable.emoji_blue));
                break;
        }
        react.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Global.check_int(Global.conA)) {
                    String text = "";
                    try {
                        if (message.getText() != null)
                            text = message.getText();


                    } catch (NullPointerException e) {
                        text = "";
                    }

                    ReactCustom cdd = new ReactCustom(Global.chatactivity, message.getId(), message.getMessid(), text);
                    cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    cdd.getWindow().getAttributes().windowAnimations = R.style.CustomDialogAnimation;
                    cdd.show();
                } else
                    Toast.makeText(Global.conA, Global.conA.getResources().getString(R.string.check_int), Toast.LENGTH_SHORT).show();
            }
        });
        react.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                if (Global.check_int(Global.conA)) {
                    String text = "";
                    try {
                        if (message.getText() != null)
                            text = message.getText();


                    } catch (NullPointerException e) {
                        text = "";
                    }

                    ReactCustom cdd = new ReactCustom(Global.chatactivity, message.getId(), message.getMessid(), text);
                    cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    cdd.getWindow().getAttributes().windowAnimations = R.style.CustomDialogAnimation;
                    cdd.show();
                } else
                    Toast.makeText(Global.conA, Global.conA.getResources().getString(R.string.check_int), Toast.LENGTH_SHORT).show();

                return true;
            }
        });

        autoLinkTextView = (AutoLinkTextView) itemView.findViewById(R.id.messageText);

        if (message.isDeleted()) {
            time.setVisibility(View.GONE);
            bubble.setBackground(ContextCompat.getDrawable(Global.conA, R.drawable.shape_incoming_deleted));
            text.setTextColor(Global.conA.getResources().getColor(R.color.white));

        } else {
            time.setVisibility(View.VISIBLE);

            //dark init
            if (Global.DARKSTATE) {
                autoLinkTextView.enableUnderLine();
                autoLinkTextView.setPhoneModeColor(ContextCompat.getColor(Global.conA, R.color.white));
                autoLinkTextView.setUrlModeColor(ContextCompat.getColor(Global.conA, R.color.white));
                autoLinkTextView.setEmailModeColor(ContextCompat.getColor(Global.conA, R.color.white));
                autoLinkTextView.setSelectedStateColor(ContextCompat.getColor(Global.conA, R.color.white));
                bubble.setBackground(ContextCompat.getDrawable(Global.conA, R.drawable.shape_incoming_message_d));
                text.setTextColor(Global.conA.getResources().getColor(R.color.white));
            } else {
                autoLinkTextView.enableUnderLine();
                autoLinkTextView.setPhoneModeColor(ContextCompat.getColor(Global.conA, R.color.black));
                autoLinkTextView.setUrlModeColor(ContextCompat.getColor(Global.conA, R.color.black));
                autoLinkTextView.setEmailModeColor(ContextCompat.getColor(Global.conA, R.color.black));
                autoLinkTextView.setSelectedStateColor(ContextCompat.getColor(Global.conA, R.color.black));
                bubble.setBackground(ContextCompat.getDrawable(Global.conA, R.drawable.shape_incoming_message));
                text.setTextColor(Global.conA.getResources().getColor(R.color.black));
            }
        }
        bubble.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                MessageSelectD cdd = new MessageSelectD(Global.chatactivity, message, Global.currFid, 1, getAdapterPosition());
                cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                cdd.getWindow().getAttributes().windowAnimations = R.style.CustomDialogAnimation;
                cdd.show();
                return true;
            }
        });


        autoLinkTextView.addAutoLinkMode(
                AutoLinkMode.MODE_PHONE,
                AutoLinkMode.MODE_URL, AutoLinkMode.MODE_EMAIL);
        autoLinkTextView.setAutoLinkText(message.getText());
        autoLinkTextView.setAutoLinkOnClickListener(new AutoLinkOnClickListener() {
            @Override
            public void onAutoLinkTextClick(AutoLinkMode autoLinkMode, String matchedText) {
                switch (autoLinkMode) {
                    case MODE_URL:
                        if (matchedText.startsWith("w"))
                            matchedText = "http://" + matchedText;
                        new FinestWebView.Builder(Global.conA).show(matchedText);
//                        Intent intent = new Intent(Intent.ACTION_VIEW);
//                        intent.setData(Uri.parse(matchedText));
//                        String title = matchedText;
//                        Intent chooser = Intent.createChooser(intent, title);
//                        Global.conA.startActivity(chooser);
                        break;
                    case MODE_PHONE:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (ActivityCompat.checkSelfPermission(Global.conA, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Global.conA, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                                ActivityCompat.requestPermissions((Activity) Global.conA, new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.READ_PHONE_STATE},
                                        804);
                            } else {
                                Intent intent2 = new Intent(Intent.ACTION_DIAL);
                                intent2.setData(Uri.parse("tel:" + matchedText));
                                Global.conA.startActivity(intent2);
                            }
                        } else {
                            Intent intent2 = new Intent(Intent.ACTION_DIAL);
                            intent2.setData(Uri.parse("tel:" + matchedText));
                            Global.conA.startActivity(intent2);
                        }
                        break;
                    case MODE_EMAIL:
                        final Intent emailIntent = new Intent(Intent.ACTION_VIEW);
                        emailIntent.setData(Uri.parse("mailto:"));
                        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{matchedText});
                        try {
                            Global.conA.startActivity(emailIntent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                }


            }
        });
        Date date = message.getCreatedAt();
        DateFormat format = new SimpleDateFormat("hh:mm aa");
        String timee = format.format(date);
        time.setText("  " + timee);
        time.setTextSize(10);
        time.setTextColor(Global.conA.getResources().getColor(R.color.mess_time));


    }
}