package com.coffee.coffeeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.coffee.coffeeapp.Database.Database;
import com.coffee.coffeeapp.Model.Drinks;
import com.coffee.coffeeapp.Model.Order;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DrinksDetail extends AppCompatActivity {

    TextView drinks_name, drinks_price, drinks_description;
    ImageView drinks_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart;
    ElegantNumberButton numberButton;

    String drinksId="";
    FirebaseDatabase database;
    DatabaseReference drinks;

    Drinks currentDrinks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks_detail);

        //Firebase
        database = FirebaseDatabase.getInstance();
        drinks = database.getReference("Drinks");

        //Init View
        numberButton = (ElegantNumberButton)findViewById(R.id.number_button);
        btnCart = (FloatingActionButton)findViewById(R.id.btnCart);

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              new Database(getBaseContext()).addToCart(new Order(
                  drinksId,
                currentDrinks.getName(),
                numberButton.getNumber(),
                currentDrinks.getPrice(),
                currentDrinks.getDiscount()

              ));

                Toast.makeText(DrinksDetail.this, "Added to Cart", Toast.LENGTH_SHORT).show();
            }
        });




        drinks_description = (TextView)findViewById(R.id.drinks_description);
        drinks_name = (TextView)findViewById(R.id.drinks_name);
        drinks_price = (TextView)findViewById(R.id.drinks_price);
        drinks_image = (ImageView) findViewById(R.id.img_drinks);

        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapseAppbar);

        //Get Drink id from intent
        if(getIntent() != null)
            drinksId = getIntent().getStringExtra("DrinksId");
        if(!drinksId.isEmpty())
        {
            getDetailDrinks(drinksId);
        }

    }

    private void getDetailDrinks(String drinksId) {

        drinks.child(drinksId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currentDrinks = dataSnapshot.getValue(Drinks.class);

                //Set Image
                Picasso.with(getBaseContext()).load(currentDrinks.getImage()).into(drinks_image);

                collapsingToolbarLayout.setTitle(currentDrinks.getName());

                drinks_price.setText(currentDrinks.getPrice());

                drinks_name.setText(currentDrinks.getName());

                drinks_description.setText(currentDrinks.getDescription());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
