package com.example.mtms_task;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class MapActivity extends AppCompatActivity {

    private EditText editText_sourceLocation;
    private RecyclerView recyclerView_sourceLocation;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = db.getReference().child("Source");
    private LocationAdapter locationAdapter;
    private ArrayList<LocationModel> sourceLocationsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        editText_sourceLocation = findViewById(R.id.edtxt_location);
        editText_sourceLocation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                recyclerView_sourceLocation.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {
                recyclerView_sourceLocation.setVisibility(View.INVISIBLE);
            }
        });

        recyclerView_sourceLocation = findViewById(R.id.recyclerView_sourceLocations);
        recyclerView_sourceLocation.setHasFixedSize(true);
        recyclerView_sourceLocation.setLayoutManager(new LinearLayoutManager(this));

        sourceLocationsList = new ArrayList<>();
        locationAdapter = new LocationAdapter(this, sourceLocationsList);
        recyclerView_sourceLocation.setAdapter(locationAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                LocationModel model = new LocationModel("Cairo");
                sourceLocationsList.add(model);

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    LocationModel locationModel = dataSnapshot.getValue(LocationModel.class);
                    sourceLocationsList.add(locationModel);
                }
                locationAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}