package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test3 extends Activity {

    private ListView listView;
    private SimpleAdapter simple_adapter;
    private List<Map<String, Object>> dataList;
    private String[] name = {"Lion","Tiger","Monkey","Dog","Cat","Elephant"};
    private int[] images = {R.drawable.lion,R.drawable.tiger,R.drawable.monkey,R.drawable.dog,R.drawable.cat,R.drawable.elephant};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simpleadapter);

        //实验3
        listView = (ListView) findViewById(R.id.listView);
        dataList = new ArrayList< Map<String, Object>>();

        for(int i = 0;i < 6;i++){
            Map<String,Object> items = new HashMap<String, Object>(); //创建一个键值对的Map集合，用来存放名字和头像
            items.put("name", name[i]);      //放入名字， 根据下标获取数组
            items.put("pic", images[i]);  //放入头像， 根据下标获取数组
            dataList.add(items);   //把这个存放好数据的Map集合放入到list中，这就完成类数据源的准备工作
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(
                test3.this,/*传入一个上下文作为参数*/
                dataList,         /*传入相对应的数据源，这个数据源不仅仅是数据而且还是和界面相耦合的混合体。*/
                R.layout.simpleadapterlayout, /*设置具体某个items的布局，需要是新的布局，而不是ListView控件的布局*/
                new String[]{"name","pic"}, /*传入上面定义的键值对的键名称,会自动根据传入的键找到对应的值*/
                new int[]{R.id.name,R.id.image});/*传入items布局文件中需要指定传入的控件，这里直接上id即可*/

        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView context = (TextView) view.findViewById(R.id.name);
                Toast.makeText(test3.this, context.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}