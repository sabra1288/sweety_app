package firstapp.om.sweety.home;



import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import firstapp.om.sweety.R;

public class ImageViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private int[] images={R.drawable.cake_gallery,R.drawable.cake1,R.drawable.cake2};

    public ImageViewPagerAdapter(Context context) {

        this.context = context;
    }

    @Override
    public int getCount() {

        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view=layoutInflater.inflate(R.layout.pager_image,null);

        ImageView imageView=(ImageView) view.findViewById(R.id.p_image);

        imageView.setImageResource(images[position]);

        ViewPager vp=(ViewPager) container;

        vp.addView(view,0);

        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        },3000);



        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        ViewPager vp=(ViewPager) container;
        View view=(View) object;
        vp.removeView(view);
    }


}
