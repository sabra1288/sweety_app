package firstapp.om.sweety.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import firstapp.om.sweety.R;

public class HalwaAdapter extends Adapter<HalwaAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<HalwaItem> list;
    HalwaItem halwaItem;

    public HalwaAdapter(Context context, ArrayList<HalwaItem> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.halwa_item,parent,false);

        //Toast.makeText(context, cakeItem.getImage(), Toast.LENGTH_SHORT).show();
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        halwaItem=list.get(position);
        holder.textView.setText(halwaItem.getName());
        Picasso.with(context).load(halwaItem.getImage()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {

        return list.size();
    }



    public  class MyViewHolder extends ViewHolder {
        ImageView imageView;
        TextView textView;



        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.name);


        }

    }
}