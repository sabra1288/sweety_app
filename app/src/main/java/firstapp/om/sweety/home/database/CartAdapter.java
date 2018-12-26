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

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

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

    public CartAdapter(Context context, ArrayList<ColdItem> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.cart_adapter,parent,false);
        cardView= view.findViewById(R.id.cardView1);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        TextDrawable drawable=TextDrawable.builder().buildRound(""+list.get(position).getQuantity(),Color.RED);
        holder.imageView.setImageDrawable(drawable);

        Locale locale =new Locale("en","US");
        NumberFormat format= NumberFormat.getCurrencyInstance(locale);

        int price=(Integer.parseInt("20"))*(list.get(position).getQuantity());

        holder.textView1.setText(format.format(price));

        holder.textView.setText(list.get(position).getName());


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
        TextView textView,textView1;


        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cart_item_count);
            textView = itemView.findViewById(R.id.cart_item_name);
            textView1 = itemView.findViewById(R.id.cart_item_price);



        }

    }
}