package com.example.listview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private MyAdapter list_item_adapter;
    private String finaltext;

    @ViewInject(R.id.listview_sex)
    ListView listView;

    private List<String> items = new ArrayList<String>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ViewUtils.inject(this);

        items.add("中国");
        items.add("泰国");

        //初始化适配器
        list_item_adapter = new MyAdapter(this, R.layout.list_item);
        //为适配器加载数据
        list_item_adapter.addAll(items);
        //加载listitem的适配器
        listView.setAdapter(list_item_adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try{
                    for(int ctr=0;ctr <= items.size();ctr++){
                        if(position == ctr){
                            listView.getChildAt(ctr).setBackgroundColor(Color.CYAN);
                        }else {
                            listView.getChildAt(ctr).setBackgroundColor(Color.rgb(153,153,153));
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                finaltext = listView.getItemAtPosition(position).toString();
            }
        });
    }


    //自定义listview的数据适配器
    public class MyAdapter extends ArrayAdapter<String> {
        private int resource;
        private final LayoutInflater inflater;

        public MyAdapter(Context context, int resource)
        {
            super(context,resource);
            this.resource = resource;
            inflater = LayoutInflater.from(context);
        }

        //重写ArrayAdapter中的getview方法来适应当前数据的显示
        @Override
        public View getView(int position, View row, ViewGroup parent) {
            TextItemHolder holder = null;
            if(row == null){
                row = inflater.inflate(resource, parent,false);
                holder = new TextItemHolder();
                ViewUtils.inject(holder, row);
                row.setTag(holder);
            }else {
                holder = (TextItemHolder)row.getTag();
            }

            //根据得到的数据来绘制每一行
            final String sex = this.getItem(position);
            holder.sex_text.setText(sex);
            return row;
        }
    }

    // 持有者模式 用于加速列表
    public class TextItemHolder {
        @ViewInject(R.id.tx)
        private TextView sex_text;
    }
}
