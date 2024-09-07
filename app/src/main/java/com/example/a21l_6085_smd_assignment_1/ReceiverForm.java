package com.example.a21l_6085_smd_assignment_1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Arrays;
import java.util.List;

public class ReceiverForm  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_receiver_form);

        View rootView = findViewById(R.id.receiver_form_root);

        ViewCompat.setOnApplyWindowInsetsListener(rootView, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();

         Intent i= getIntent();
        email=i.getStringExtra("senderemail");
        name=i.getStringExtra("sendername");
        contactinfo=i.getStringExtra("sendercontactinfo");
        address=i.getStringExtra("senderaddress");
        country=i.getStringExtra("sendercountry");




        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // checks

                if( tfreceiveraddress==null || tfreceivercontactinfo==null || tfreceivername==null|| tfreceivercountry==null ||tfreceiveremail==null){
                    Toast.makeText(ReceiverForm.this,"All fields are required  ",Toast.LENGTH_SHORT).show();
                    return;
                }

                // if not null then parse as string

                receiverName=tfreceivername.getText().toString().trim();
                receiverEmail=tfreceiveremail.getText().toString().trim();
                receiverAddress=tfreceiveraddress.getText().toString().trim();
                receiverContactinfo=tfreceivercontactinfo.getText().toString().trim();
                receiverCountry=tfreceivercountry.getText().toString().trim();


                if(receiverName.equals("")|| receiverEmail.equals("")||receiverAddress.equals("")||receiverContactinfo.equals("")||receiverCountry.equals("")){
                    Toast.makeText(ReceiverForm.this,"All fields are required",Toast.LENGTH_SHORT).show();
                    return;
                }

                // check on email

               if(!Patterns.EMAIL_ADDRESS.matcher(receiverEmail).matches()){
                    Toast.makeText(ReceiverForm.this,"Provide Valid Email address",Toast.LENGTH_SHORT).show();
                    return;
                }

                // check on contact information  input type is phone so it will always be a number
                if(!receiverContactinfo.matches("^\\d{11}$")){
                    Toast.makeText(ReceiverForm.this,"Provide Valid 11 digit phone number  ",Toast.LENGTH_SHORT).show();
                    return;

                }

                // validating name :  no special chars or digits
                String regex = "^[a-zA-Z\\s'-]+$";

                if(!name.matches(regex)){
                    Toast.makeText(ReceiverForm.this,"Provide Valid name  ",Toast.LENGTH_SHORT).show();
                    return;
                }

                // validating address : letters, numbers, spaces, and common punctuation only

                  regex = "^[a-zA-Z0-9 ,.-]+$";
                if(!receiverAddress.matches(regex)){
                    Toast.makeText(ReceiverForm.this,"Provide Valid address  ",Toast.LENGTH_SHORT).show();
                    return;
                }

                // validating country


                if(! VALID_COUNTRIES.contains(receiverCountry.toLowerCase())){
                    Toast.makeText(ReceiverForm.this,"Provide Valid Country  ",Toast.LENGTH_SHORT).show();
                    return;
                }


                // checks on if email , contact info is same as both sender and receiver should be different

                if(receiverEmail.equals(email)){
                    Toast.makeText(ReceiverForm.this,"Email can't be same as sender  ",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(receiverContactinfo.equals(contactinfo)){
                    Toast.makeText(ReceiverForm.this,"Contact Info can't be same as sender   ",Toast.LENGTH_SHORT).show();
                    return;
                }
                // sending information to next Receivers form
                Intent i=new Intent(ReceiverForm.this, ReviewInformation.class);
                i.putExtra("senderemail",email);
                i.putExtra("sendername",name);
                i.putExtra("senderaddress",address);
                i.putExtra("sendercontactinfo",contactinfo);
                i.putExtra("sendercountry",country);

                 i.putExtra("receivername",receiverName);
                i.putExtra("receiveremail",receiverEmail);
                i.putExtra("receivercountry",receiverCountry);

                i.putExtra("receiveraddress",receiverAddress);
                i.putExtra("receivercontactinfo",receiverContactinfo);
                 startActivity(i);



            }
        });


    }


    private void init(){
        btnnext=findViewById(R.id.btnnext);
        tfreceivername=findViewById(R.id.tfreceiverfullname);
        tfreceiveremail=findViewById(R.id.tfreceiveremail);
        tfreceivercontactinfo=findViewById(R.id.tfreceivercontactinfo);
        tfreceiveraddress=findViewById(R.id.tfreceiveraddress);
        tfreceivercountry=findViewById(R.id.tfreceivercountry);



    }

    List<String> VALID_COUNTRIES = Arrays.asList(
            "united utates", "canada", "mexico", "united kingdom", "france",
            "india", "china", "japan","pakistan"
    );

    private Button btnnext;
    private TextInputEditText tfreceivername,tfreceiveremail,tfreceivercontactinfo,tfreceiveraddress,tfreceivercountry;
    private String name,address,email,contactinfo,country;
    private String receiverName,receiverAddress,receiverEmail,receiverContactinfo,receiverCountry;


}


