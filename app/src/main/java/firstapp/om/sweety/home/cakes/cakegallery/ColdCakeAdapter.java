package firstapp.om.sweety.home.cakes.cakegallery;

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
import firstapp.om.sweety.home.cakes.CakeAdapter;

public class ColdCakeAdapter extends RecyclerView.Adapter<ColdCakeAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<ColdItem> list;
    private ColdItem coldItem;
    private CardView cardView;

    private ColdCakeAdapter.OnClickItme onClickItme;

    public interface OnClickItme {

        void onClick(int position);
    }



    public void  newMethod(ColdCakeAdapter.OnClickItme onClick){

         this.onClickItme=onClick;

    }




    public ColdCakeAdapter(Context context, ArrayList<ColdItem> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.cold_item,parent,false);
        cardView= view.findViewById(R.id.cardView1);


        return new MyViewHolder(view);
    }
    public void registerOnclick(OnClickItme onClick){
        this.onClickItme=onClick;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        coldItem=list.get(position);
        holder.textView.setText(coldItem.getName());
        Picasso.with(context).load(coldItem.getImage()).into(holder.imageView);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
onClickItme.onClick(position);
            }
        });


    }

    @Override
    public int getItemCount() {

        return list.size();
    }



    public  class MyViewHolder extends ViewHolder   {
        ImageView imageView;
        TextView textView;



        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.name);


        }


    }
}