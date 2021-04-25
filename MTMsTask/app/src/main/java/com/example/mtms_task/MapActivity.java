package com.example.mtms_task;

import android.os.Bundle;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class MapActivity extends AppCompatActivity {

    private RecyclerView recyclerView_sourceLocation;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private TextView textView_sourceLocation, textView_destination;
    private CollectionReference collectionReference = db.collection("Source");
    private FirestoreRecyclerOptions<SourceLocationModel> options;
    private FirestoreRecyclerAdapter<SourceLocationModel, LocationsViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        recyclerView_sourceLocation = findViewById(R.id.recyclerView_sourceLocations);
        recyclerView_sourceLocation.setHasFixedSize(true);
        recyclerView_sourceLocation.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        readData();
    }

    private void readData() {
        options = new FirestoreRecyclerOptions.Builder<SourceLocationModel>().setQuery(collectionReference, SourceLocationModel.class).build();
        adapter = new FirestoreRecyclerAdapter<SourceLocationModel, LocationsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull LocationsViewHolder holder, int position, @NonNull SourceLocationModel model) {
                holder.NAME.setText(model.getName());
            }

            @NonNull
            @Override
            public LocationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview, parent, false);
                return new LocationsViewHolder(view);
            }
        };
        //adapter.startListening();
        recyclerView_sourceLocation.setAdapter(adapter);
    }
}
