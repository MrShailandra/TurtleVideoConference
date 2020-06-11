package com.turtlesoftsolution.turtlevideoconference;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;
import org.jitsi.meet.sdk.JitsiMeetUserInfo;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    Button start_call;
    EditText mainEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().hide();
        }
        start_call=findViewById(R.id.start_call);
        mainEditText = findViewById(R.id.mainEditText);
        start_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initialize_video();
            }
        });
    }
    public void initialize_video()
    {
        String name = mainEditText.getText().toString().trim();
        if(name.length()>3)
        {
            JitsiMeetUserInfo userInfo=new JitsiMeetUserInfo();
            JitsiMeetConferenceOptions options
                    = new JitsiMeetConferenceOptions.Builder()
                    .setRoom(name)
                    .setUserInfo(userInfo)
                    .setFeatureFlag("invite.enabled",false)
                    .setFeatureFlag("pip.enabled",true)
                    .setServerURL(buildURL("https://meet.jit.si"))
                    .setWelcomePageEnabled(false)
                    .build();
            JitsiMeetActivity.launch(this, options);
        }
        else{
            Toast.makeText(this, "Meeting Name Must be Longer than 3 Words", Toast.LENGTH_SHORT).show();
        }
    }
    private URL buildURL(String urlStr) {
        try {
            return new URL(urlStr);
        } catch (MalformedURLException e) {
            return null;
        }
    }
}
