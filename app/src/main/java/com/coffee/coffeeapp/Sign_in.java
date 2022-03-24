package com.coffee.coffeeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.coffee.coffeeapp.Common.Common;
import com.coffee.coffeeapp.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.rengwuxian.materialedittext.MaterialEditText;

public class Sign_in extends AppCompatActivity {
    EditText edtPhone, edtPassword;
    Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtPassword = (MaterialEditText)findViewById(R.id.edtPassword);
        edtPhone=(MaterialEditText)findViewById(R.id.edtPhone);
        btnSignIn= (Button) findViewById(R.id.btnSignIn);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user= database.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {

                final ProgressDialog mDialog= new ProgressDialog(Sign_in.this);
                mDialog.setMessage("Please waiting...");
                mDialog.show();

                table_user.addValueEventListener (new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        //Check if user not exist in database
                        if (dataSnapshot.child(edtPhone.getText().toString()).exists()) {
                            //Get User infomation
                            mDialog.dismiss();
                            User user = dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);
                            user.setPhone(edtPhone.getText().toString());//set phone
                            if (user.getPassword().equals(edtPassword.getText().toString())) {

                                Intent homeIntent= new Intent(Sign_in.this, home1.class);
                                Common.currentUser= user;
                                startActivity(homeIntent);
                                finish();

                            }
                            else {
                                Toast.makeText(Sign_in.this, "Wrong Password!!!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        else {
                            Toast.makeText(Sign_in.this, "User does not exist in Database", Toast.LENGTH_SHORT).show();
                        }
                    }


                    @Override
                    public void onCancelled (DatabaseError databaseError) {


                    }
                });
            }
        });

    }
}
