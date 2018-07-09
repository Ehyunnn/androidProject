package com.test.android08gridview2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

class TestAdapter extends BaseAdapter {

    static Integer imgs[] = new Integer[]{
            R.drawable.sample_0,
            R.drawable.sample_1,
            R.drawable.sample_2,
            R.drawable.sample_3,
            R.drawable.sample_4,
            R.drawable.sample_5,
            R.drawable.sample_6,
            R.drawable.sample_7,
            R.drawable.sample_0,
            R.drawable.sample_1,
            R.drawable.sample_2,
            R.drawable.sample_3,
            R.drawable.sample_4,
            R.drawable.sample_5,
            R.drawable.sample_6,
            R.drawable.sample_7
    };
    Context c;
    public TestAdapter(Context c) {
        this.c = c;
    }

    @Override
    public int getCount() {
        return imgs.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView iv = null;
        if(convertView==null){
            iv = new ImageView(c);
            iv.setLayoutParams(new ViewGroup.LayoutParams(285, 285));
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            iv.setPadding(8, 8, 8, 8);
        }else{
            iv = (ImageView)convertView;
        }
        iv.setImageResource(imgs[position]);
        return iv;
    }
}
