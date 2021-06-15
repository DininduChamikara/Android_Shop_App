package com.ShopApp.shopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class AdminSingleProductView extends AppCompatActivity {

    private ImageView imageView;
    TextView textViewProName;
    TextView textViewPrice;
    TextView textViewDescription;
    Button btnDelete;

    DatabaseReference ref, DataRef;
    StorageReference StorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_single_product_view);

        imageView = findViewById(R.id.image_single_view_activity);
        textViewProName = findViewById(R.id.textView_single_view_activity);
        textViewPrice = findViewById(R.id.textView_single_view3_activity);
        textViewDescription = findViewById(R.id.textView_single_view2_activity);
        btnDelete = findViewById(R.id.btnDelete);

        //////////////////
        ref = FirebaseDatabase.getInstance().getReference().child("Products");

        String PRODUCT_KEY = getIntent().getStringExtra("PRODUCT_KEY");
        DataRef = FirebaseDatabase.getInstance().getReference().child("Products").child(PRODUCT_KEY);
        ////////////////
        StorageRef = FirebaseStorage.getInstance().getReference().child("Product Images").child(PRODUCT_KEY + ".jpg");

        ref.child(PRODUCT_KEY).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String proName = snapshot.child("pname").getValue().toString();
                    String image = snapshot.child("image").getValue().toString();
                    String price = snapshot.child("price").getValue().toString();
                    String description = snapshot.child("description").getValue().toString();

                    Picasso.get().load(image).into(imageView);
                    textViewProName.setText(proName);
                    textViewPrice.setText("Rs."+price);
                    textViewDescription.setText(description);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataRef.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        StorageRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                startActivity(new Intent(getApplicationContext(), AdminCrudView.class));
                            }
                        });
                    }
                });
            }
        });
    }
}