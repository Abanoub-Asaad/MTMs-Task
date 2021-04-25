package com.example.mtms_task;

import android.os.Bundle;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

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
    private TextView textView_sourceLocation, textView_destination;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference sourceRef = db.collection("Source");
    private SourceLocationAdapter sourceLocationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        Query query = sourceRef;
        FirestoreRecyclerOptions<SourceLocationModel> options = new FirestoreRecyclerOptions.Builder<SourceLocationModel>()
                .setQuery(query, SourceLocationModel.class)
                .build();
        sourceLocationAdapter = new SourceLocationAdapter(options);
        recyclerView_sourceLocation = findViewById(R.id.recyclerView_sourceLocations);
        recyclerView_sourceLocation.setHasFixedSize(true);
        recyclerView_sourceLocation.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_sourceLocation.setAdapter(sourceLocationAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        sourceLocationAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        sourceLocationAdapter.stopListening();
    }
}
