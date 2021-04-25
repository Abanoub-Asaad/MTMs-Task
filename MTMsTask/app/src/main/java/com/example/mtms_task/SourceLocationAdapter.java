package com.example.mtms_task;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class SourceLocationAdapter extends FirestoreRecyclerAdapter<SourceLocationModel, SourceLocationAdapter.SourceLocationHolder> {

    public SourceLocationAdapter(@NonNull FirestoreRecyclerOptions<SourceLocationModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull SourceLocationHolder holder, int position, @NonNull SourceLocationModel model) {
        holder.textViewSourceLocation.setText(model.getName());
    }

    @NonNull
    @Override
    public SourceLocationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview, parent, false);
        return new SourceLocationHolder(view);
    }

    class SourceLocationHolder extends RecyclerView.ViewHolder{

        TextView textViewSourceLocation;

        public SourceLocationHolder(@NonNull View itemView) {
            super(itemView);
            textViewSourceLocation = itemView.findViewById(R.id.txtView_sourceLocation);
        }
    }
}
