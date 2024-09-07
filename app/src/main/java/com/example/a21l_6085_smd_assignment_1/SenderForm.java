 package com.example.a21l_6085_smd_assignment_1;


 import android.content.Intent;
 import android.os.Bundle;

 import androidx.activity.EdgeToEdge;
 import androidx.appcompat.app.AppCompatActivity;
 import androidx.core.view.ViewCompat;
 import androidx.core.view.WindowInsetsCompat;
 import androidx.core.view.WindowInsetsCompat.Type;
 import androidx.core.view.WindowInsetsControllerCompat;

 import android.util.Patterns;
 import android.view.View;
 import android.view.ViewGroup;
 import android.widget.Button;
 import android.widget.Toast;

 import androidx.core.graphics.Insets;

 import com.google.android.material.textfield.TextInputEditText;

 import java.util.Arrays;
 import java.util.List;

 public class SenderForm extends AppCompatActivity {

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
          EdgeToEdge.enable(this);

          setContentView(R.layout.activity_sender_form);

          View rootView = findViewById(R.id.sender_form_root);

          ViewCompat.setOnApplyWindowInsetsListener(rootView, (v, insets) -> {
             Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
             v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
             return insets;
         });

          init();




        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // checks

                if( tfaddress==null || tfcontactinfo==null || tfname==null|| tfcountry==null ||tfemail==null){
                    Toast.makeText(SenderForm.this,"All fields are required",Toast.LENGTH_SHORT).show();
                    return;
                }

                // if not null then parse as string

                name=tfname.getText().toString().trim();
                email=tfemail.getText().toString().trim();
                address=tfaddress.getText().toString().trim();
                contactinfo=tfcontactinfo.getText().toString().trim();
                country=tfcountry.getText().toString().trim();
                if(name.equals("")|| email.equals("")||address.equals("")||contactinfo.equals("")||country.equals("")){
                    Toast.makeText(SenderForm.this,"All fields are required",Toast.LENGTH_SHORT).show();
                    return;
                }

                // check on email

                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    Toast.makeText(SenderForm.this,"Provide Valid Email address",Toast.LENGTH_SHORT).show();
                    return;
                }

                // check on contact information  input type is phone so it will always be a number
                if(!contactinfo.matches("^\\d{11}$")){
                    Toast.makeText(SenderForm.this,"Provide Valid 11 digit phone number  ",Toast.LENGTH_SHORT).show();
                    return;

                }

                // address
                // Regex to allow letters, numbers, spaces, and common punctuation
                String regex = "^[a-zA-Z0-9 ,.-]+$";
                if(! address.matches(regex)){
                    Toast.makeText(SenderForm.this,"Provide Valid address  ",Toast.LENGTH_SHORT).show();
                    return;
                }

                // validating name :  no special chars or digits
                  regex = "^[a-zA-Z\\s'-]+$";

                if(!name.matches(regex)){
                    Toast.makeText(SenderForm.this,"Provide Valid name  ",Toast.LENGTH_SHORT).show();
                    return;
                }

                // validating country


                if(! VALID_COUNTRIES.contains(country.toLowerCase())){
                    Toast.makeText(SenderForm.this,"Provide Valid Country  ",Toast.LENGTH_SHORT).show();
                    return;
                }


                // sending information to next Receivers form
                Intent i=new Intent(SenderForm.this, ReceiverForm.class);
                i.putExtra("senderemail",email);
                i.putExtra("sendername",name);
                i.putExtra("senderaddress",address);
                i.putExtra("sendercontactinfo",contactinfo);
                i.putExtra("sendercountry",country);
                startActivity(i);



            }
        });


     }


     private void init(){
         btnnext=findViewById(R.id.btnnext);
         tfname=findViewById(R.id.tffullname);
         tfemail=findViewById(R.id.tfemail);
         tfcontactinfo=findViewById(R.id.tfcontactinfo);
         tfaddress=findViewById(R.id.tfaddress);
         tfcountry=findViewById(R.id.tfcountry);



     }

     List<String> VALID_COUNTRIES = Arrays.asList(
             "united utates", "canada", "mexico", "united kingdom", "france",
             "india", "china", "japan","pakistan"
     );

     private Button btnnext;
     private TextInputEditText tfname,tfemail,tfcontactinfo,tfaddress,tfcountry;
     private String name,address,email,contactinfo,country;


 }

