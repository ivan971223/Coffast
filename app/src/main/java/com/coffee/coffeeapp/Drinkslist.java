package com.coffee.coffeeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coffee.coffeeapp.Interface.ItemClickListener;
import com.coffee.coffeeapp.Model.Drinks;
import com.coffee.coffeeapp.ViewHolder.DrinksViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Drinkslist extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference drinksList;

    String categoryId = "";

    FirebaseRecyclerAdapter<Drinks, DrinksViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks_list);

        //Firebase
        database = FirebaseDatabase.getInstance();
        drinksList = database.getReference("Drinks");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_drinks);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Get Intent here
        if (getIntent() != null)
            categoryId = getIntent().getStringExtra("CategoryId");
        if (!categoryId.isEmpty() && categoryId != null) {
            loadListFood(categoryId);
        }


    }

    private void loadListFood(String categoryId) {

        //adapter = new FirebaseRecyclerAdapter<Drinks, DrinksViewHolder>(DrinksViewHolder.class,
               // R.layout.drinks_item,
               // Drinks.class,
                //drinksList.orderByChild("MenuId").equalTo(categoryId)

        FirebaseRecyclerOptions< Drinks > drinks =
                        new FirebaseRecyclerOptions.Builder<Drinks>().setQuery(drinksList.orderByChild("menuId").equalTo(categoryId), Drinks.class).setLifecycleOwner(this).build();

        adapter = new FirebaseRecyclerAdapter<Drinks, DrinksViewHolder>(drinks){
        // Select from Drinks where MenuId =


            @Override
            public DrinksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drinks_item, parent, false);
                return new DrinksViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull DrinksViewHolder viewHolder, int position, @NonNull Drinks model) {
                viewHolder.drinks_name.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage()).into(viewHolder.drinks_image);

                final Drinks local = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //Toast.makeText(Drinkslist.this, "" + local.getName(), Toast.LENGTH_SHORT).show();
                        Intent drinksDetail= new Intent(Drinkslist.this, DrinksDetail.class);
                        drinksDetail.putExtra("DrinksId", adapter.getRef(position).getKey());//send drinks id to new activity
                        startActivity(drinksDetail);
                    }
                });


            }
        };
        //Set Adapter
        Log.d("TAG",""+adapter.getItemCount());
        recyclerView.setAdapter(adapter);
    }

}
