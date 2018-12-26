package firstapp.om.sweety.home.database;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import firstapp.om.sweety.R;
import firstapp.om.sweety.home.cakes.cakegallery.ColdItem;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<ColdItem> list;
    private CardView cardView;

    private ItemOnClick itemOnClick;

    public  interface ItemOnClick{

        void onItemClick(int position);
    }
    public void  newMethod(ItemOnClick onclick){

        this.itemOnClick=onclick;

    }

    public OrderAdapter(Context context, ArrayList<ColdItem> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.activity_order_adapter,parent,false);
        cardView= view.findViewById(R.id.cardView1);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {


    }
    public void registerOnclick(ItemOnClick onClick){
        this.itemOnClick=onClick;
    }

    @Override
    public int getItemCount() {

        return list.size();
    }



    public  class MyViewHolder extends ViewHolder {



        TextView textView,textView1,textView2,textView3;


        public MyViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.order_name);
            textView1 = itemView.findViewById(R.id.order_address);
            textView2 = itemView.findViewById(R.id.order_phone);
            textView3 = itemView.findViewById(R.id.order_status);



        }

    }
}