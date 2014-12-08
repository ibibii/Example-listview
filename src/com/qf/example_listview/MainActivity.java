package com.qf.example_listview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {
	private int[] images = { R.drawable.emo_im_angel, R.drawable.emo_im_cool,
			R.drawable.emo_im_crying, R.drawable.emo_im_embarrassed,
			R.drawable.emo_im_foot_in_mouth, R.drawable.emo_im_happy,
			R.drawable.emo_im_kissing, R.drawable.emo_im_laughing,
			R.drawable.emo_im_lips_are_sealed, R.drawable.emo_im_money_mouth,
			R.drawable.emo_im_sad, R.drawable.emo_im_surprised,
			R.drawable.emo_im_tongue_sticking_out, R.drawable.emo_im_undecided,
			R.drawable.emo_im_winking, R.drawable.emo_im_wtf,
			R.drawable.emo_im_yelling };
	//private String name[] = getResources().getStringArray(R.array.listarrays);如果,在这里,会出现空指针.也就是没有运算.
	private ListView lv ;
	private SimpleAdapter adapter;
	private List<HashMap<String,Object>> datas;
	private AlertDialog.Builder dialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv = (ListView) findViewById(R.id.listview);
		datas = new ArrayList<HashMap<String,Object>>();
		HashMap<String,Object> map;
		String name[] = getResources().getStringArray(R.array.listarrays);
		for(int i=0;i<images.length;i++){
			map = new HashMap<String,Object>();
			map.put("img", images[i]);
			map.put("face", name[i]);
			datas.add(map);
		}
		adapter = new SimpleAdapter(MainActivity.this, datas, R.layout.lv_item, new String []{"img","face"}, new int []{R.id.imgv,R.id.tx});
		lv.setAdapter(adapter);
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				onListViewLongClick(position);
				return true;
			}
		});
	}
	public void onListViewLongClick(final int position){
		dialog = new AlertDialog.Builder(MainActivity.this);
		dialog.setTitle("是否删除").setNegativeButton("no", null).setPositiveButton("yes",new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				datas.remove(position);
				adapter.notifyDataSetChanged();
			}
		} );
		dialog.show();
	}
}
