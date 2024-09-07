package com.example.a21l_6085_smd_assignment_1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Arrays;
import java.util.List;

public class ReviewInformation  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_review_information);

        View rootView = findViewById(R.id.review_form_root);

        ViewCompat.setOnApplyWindowInsetsListener(rootView, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();

        Intent i= getIntent();
        senderEmail=i.getStringExtra("senderemail");
        senderName=i.getStringExtra("sendername");
        senderContactinfo=i.getStringExtra("sendercontactinfo");
        senderAddress=i.getStringExtra("senderaddress");
        senderCountry=i.getStringExtra("sendercountry");

        receiverEmail=i.getStringExtra("receiveremail");
        receiverName=i.getStringExtra("receivername");
        receiverAddress=i.getStringExtra("receiveraddress");
        receiverContactinfo=i.getStringExtra("receivercontactinfo");
        receiverCountry=i.getStringExtra("receivercountry");


        tvSenderAddress.setText(senderAddress);
        tvsenderContactInfo.setText(senderContactinfo);
        tvSenderCountry.setText(senderCountry);
        tvSenderEmail.setText(senderEmail);
        tvSenderName.setText(senderName);

        tvReceiverAddress.setText(receiverAddress);
        tvReceiverContactInfo.setText(receiverContactinfo);
        tvReceiverCountry.setText(receiverCountry);
        tvReceiverEmail.setText(receiverEmail);
        tvReceiverName.setText(receiverName);


         btnaddfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent i= new Intent(ReviewInformation.this, SenderForm.class);
                 startActivity(i);

             }
        });



    }


    private void init(){

             tvSenderName=findViewById(R.id.senderName);
             tvSenderEmail=findViewById(R.id.senderEmail);
             tvSenderAddress=findViewById(R.id.senderAddress);
             tvsenderContactInfo=findViewById(R.id.senderContactInfo);
             tvSenderCountry=findViewById(R.id.senderCountry);

            tvReceiverName=findViewById(R.id.tfreceiverfullname);
            tvReceiverEmail=findViewById(R.id.tfreceiveremail);
            tvReceiverAddress=findViewById(R.id.tfreceiveraddress);
            tvReceiverContactInfo=findViewById(R.id.tfreceivercontactinfo);
            tvReceiverCountry=findViewById(R.id.tfreceivercountry);

        btnaddfab= findViewById(R.id.addfab);

    }


  private FloatingActionButton btnaddfab;
     private TextView tvSenderName,tvSenderEmail,tvSenderAddress,tvsenderContactInfo,tvSenderCountry;
    private TextView tvReceiverName,tvReceiverEmail,tvReceiverAddress,tvReceiverContactInfo,tvReceiverCountry;
     private String receiverName,receiverAddress,receiverEmail,receiverContactinfo,receiverCountry;
    private String senderName,senderAddress,senderEmail,senderContactinfo,senderCountry;


}


