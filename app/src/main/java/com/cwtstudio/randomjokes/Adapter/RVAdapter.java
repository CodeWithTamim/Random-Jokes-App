package com.cwtstudio.randomjokes.Adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.cwtstudio.randomjokes.Models.JokesModel;
import com.cwtstudio.randomjokes.R;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter <RVAdapter.ViewHolder> {
    Context context;
    List<JokesModel>jokesList;

    public RVAdapter(Context context, List<JokesModel> jokesList) {
        this.context = context;
        this.jokesList = jokesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.jokes_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
             holder.txtJoke.setText(jokesList.get(position).getSetup());
             holder.txtPunchLine.setText(jokesList.get(position).getPunchline());
             holder.btnCopy.setOnClickListener(new View.OnClickListener() {
             String jokeCopy = jokesList.get(position).getSetup();
             String punchLine = jokesList.get(position).getPunchline();

            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("Joke",jokeCopy+","+punchLine);
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(context, "Copied To Clipboard", Toast.LENGTH_SHORT).show();



            }
        });


    }


    @Override
    public int getItemCount() {
        return jokesList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtJoke,txtPunchLine;
        ImageView btnCopy;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtJoke = itemView.findViewById(R.id.txtJoke);
            txtPunchLine = itemView.findViewById(R.id.txtPunchLine);
            btnCopy = itemView.findViewById(R.id.btnCopy);




        }
    }





}
