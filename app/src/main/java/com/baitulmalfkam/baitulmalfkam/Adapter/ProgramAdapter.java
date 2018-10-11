package com.baitulmalfkam.baitulmalfkam.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baitulmalfkam.baitulmalfkam.POJO.Program;
import com.baitulmalfkam.baitulmalfkam.Program.AAQ;
import com.baitulmalfkam.baitulmalfkam.R;

import java.util.ArrayList;
import java.util.List;

public class ProgramAdapter extends RecyclerView.Adapter<ProgramAdapter.ProgramViewHolder> {
    ArrayList<Program> mProgram;
    Context mContext;
    protected Itemlistener mListener;

    public ProgramAdapter (Context context, ArrayList<Program>program, Itemlistener itemlistener) {
        mProgram = program;
        mContext = context;
        mListener = itemlistener;
    }
    public class ProgramViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView icon_program;
        private TextView nama_program;
        private  Program program;

        public ProgramViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            icon_program = itemView.findViewById(R.id.icon_program);
            nama_program = itemView.findViewById(R.id.nama_program);
        }
        public void setData (Program program){
            this.program = program;
            nama_program.setText(program.namaProgram);
            icon_program.setImageResource(program.gambarProgram);
        }

        @Override
        public void onClick(View view) {
            if (mListener != null){
                mListener.onItemClick(program);
                Intent detailIntent = new Intent(mContext,AAQ.class);

                detailIntent.putExtra("gambar", program.gambarProgram);
                detailIntent.putExtra("lengkap",program.detailprogram);
                detailIntent.putExtra("namap",program.namaProgram);
                mContext.startActivity(detailIntent);
            }

        }
    }

    @Override
    public ProgramViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.program_model,parent,false);

        return new ProgramViewHolder(view);
    }


    @Override
    public void onBindViewHolder( ProgramViewHolder holder, int position) {
        holder.setData(mProgram.get(position));

    }

    @Override
    public int getItemCount() {
        return mProgram.size();
    }

    public interface Itemlistener {
        void onItemClick (Program program);
    }
}
