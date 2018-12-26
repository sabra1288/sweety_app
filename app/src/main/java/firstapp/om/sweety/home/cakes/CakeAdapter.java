package firstapp.om.sweety.home.cakes;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import firstapp.om.sweety.R;

public class CakeAdapter extends RecyclerView.Adapter<CakeAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<CakeItem> list;
    CakeItem cakeItem;
    private CardView cardView;
    private ItemOnClick itemOnClick;

    public  interface ItemOnClick{

        void onItemClick(int position);
    }
    public void  newMethod(ItemOnClick onclick){

        this.itemOnClick=onclick;

    }





    public CakeAdapter(Context context,ArrayList<CakeItem> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.cake_item,parent,false);
        cardView= view.findViewById(R.id.cardView1);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        cakeItem=list.get(position);
        holder.textView.setText(cakeItem.getName());
        Picasso.with(context).load(cakeItem.getImage()).into(holder.imageView);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemOnClick.onItemClick(position);
            }
        });



    }
    public void registerOnclick(ItemOnClick onClick){
        this.itemOnClick=onClick;
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