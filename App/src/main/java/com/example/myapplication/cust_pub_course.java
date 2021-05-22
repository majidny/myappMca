package com.example.myapplication;



import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class cust_pub_course extends BaseAdapter {
    String[] course_name,discription,duration,amount;
    private Context context;

    public cust_pub_course(Context appcontext, String[] course_name,String[] discription,String[] duration,String[] amount)
    {
        this.context=appcontext;

        this.course_name=course_name;
        this.discription=discription;
        this.duration=duration;
        this.amount=amount;

    }

    @Override
    public int getCount() {
        return amount.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(view==null)
        {
            gridView=new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView=inflator.inflate(R.layout.customviewcourse,null);

        }
        else
        {
            gridView=(View)view;

        }

        TextView tv1=(TextView)gridView.findViewById(R.id.textView2);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView3);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView4);
        TextView tv4=(TextView)gridView.findViewById(R.id.textView5);


        tv1.setText(course_name[i]);
        tv2.setText(discription[i]);
        tv3.setText("Duration : "+duration[i]);
        tv4.setText("Rs."+amount[i]);

        return gridView;
    }
}
