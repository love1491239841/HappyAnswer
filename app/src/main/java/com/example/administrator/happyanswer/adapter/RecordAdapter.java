package com.example.administrator.happyanswer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.administrator.happyanswer.R;
import com.example.administrator.happyanswer.bean.Record;

import java.util.List;

public class RecordAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Record>RecordList;
    private Context context;

    public RecordAdapter(List<Record> RecordList, Context context) {
        this.RecordList = RecordList;
        this.context = context;
    }
    class RecordHolder extends RecyclerView.ViewHolder{
        private ImageView ra_icon;
        private TextView ra_name;
        private TextView ra_titlenumber;
        public RecordHolder(View itemView) {
            super(itemView);
            ra_icon=(ImageView)itemView.findViewById(R.id.record_item_icon);
            ra_name=(TextView)itemView.findViewById(R.id.record_item_name);
            ra_titlenumber=(TextView)itemView.findViewById(R.id.record_item_titlenumber);
        }
    }
    @Override
    public int getItemCount() {
        return RecordList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecordHolder(LayoutInflater.from(context).inflate(R.layout.record_item, parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Record Record = RecordList.get(position);
        if (position == 0){
            ((RecordHolder) holder).ra_icon.setImageResource(R.mipmap.diyi);
            ((RecordHolder) holder).ra_name.setText(Record.getUsername());
            ((RecordHolder) holder).ra_titlenumber.setText(Record.getTitlenumber());
        }else if (position == 1){
            ((RecordHolder) holder).ra_icon.setImageResource(R.mipmap.dier);
            ((RecordHolder) holder).ra_name.setText(Record.getUsername());
            ((RecordHolder) holder).ra_titlenumber.setText(Record.getTitlenumber());
        }else if (position == 2){
            ((RecordHolder) holder).ra_icon.setImageResource(R.mipmap.disan);
            ((RecordHolder) holder).ra_name.setText(Record.getUsername());
            ((RecordHolder) holder).ra_titlenumber.setText(Record.getTitlenumber());
        }else {
            ((RecordHolder) holder).ra_icon.setImageResource(R.mipmap.din);
            ((RecordHolder) holder).ra_name.setText(Record.getUsername());
            ((RecordHolder) holder).ra_titlenumber.setText(Record.getTitlenumber());
        }
    }
}
