package com.example.mtms_task;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LocationsViewHolder extends RecyclerView.ViewHolder {

    TextView NAME;

    public LocationsViewHolder(@NonNull View itemView) {
        super(itemView);

        NAME = itemView.findViewById(R.id.txtView_sourceLocation);
    }
}
