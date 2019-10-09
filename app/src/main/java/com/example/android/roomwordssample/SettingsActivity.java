package com.example.android.roomwordssample;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.Timepickerfragment;
import com.alertReceiver;

import java.text.DateFormat;
import java.util.Calendar;

public class SettingsActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mTextView=findViewById(R.id.text1);


        Button button=(Button) findViewById(R.id.timepicker);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment  timepicker = new Timepickerfragment();
                timepicker.show(getSupportFragmentManager(),"time picker");

            }
        });

        Button buttoncancelalarm=findViewById(R.id.cancel);
        buttoncancelalarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAlarm();
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar c= Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY,hourOfDay);
        c.set(Calendar.MINUTE,minute);
        c.set(Calendar.SECOND,0);

        updateTimeText(c);
        startAlarm(c);
    }

    private void updateTimeText(Calendar c){
        String timeText ="Alarm set for: ";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        mTextView.setText(timeText);

    }

    private  void startAlarm(Calendar c){
        AlarmManager alarmManager =(AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent=new Intent(this, alertReceiver.class);
        PendingIntent pendingIntent =PendingIntent.getBroadcast(this,1,intent, 0);

        if(c.before(Calendar.getInstance())){
            c.add(Calendar.DATE,1);
        }

        alarmManager.setExact(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pendingIntent);

    }

    private  void  cancelAlarm(){
        AlarmManager alarmManager =(AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent=new Intent(this, alertReceiver.class);
        PendingIntent pendingIntent =PendingIntent.getBroadcast(this,1,intent, 0);

        alarmManager.cancel(pendingIntent);
        mTextView.setText("Alarm canceled");
    }


}




