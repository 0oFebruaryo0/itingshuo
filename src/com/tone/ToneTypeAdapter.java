package com.tone;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.itingshuo.MovieActivity;
import com.example.itingshuo.R;
import com.example.itingshuo.ResultActivity;
import com.example.itingshuo.ToneActivity;
import com.example.itingshuo.ToneListActivity;

public class ToneTypeAdapter extends ArrayAdapter<ToneType> {
    private int resource;
	public ToneTypeAdapter(Context context, int resource, List<ToneType> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		this.resource = resource;//resourceΪlistView��ÿ������Ĳ���id
		
	}
	//getViewΪlistView��ÿ������Ĳ�����������
	//convertView���ڽ�֮ǰ���غõĲ��ֽ��л���
	//����һ��viewHolder�Կؼ����л���
	@SuppressLint("ResourceAsColor")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		//return super.getView(position, convertView, parent);
			
		View view;
		ViewHolder viewHolder;
		if(convertView==null){
		 view = LayoutInflater.from(getContext()).inflate(resource, null);
		 viewHolder = new ViewHolder();
		 viewHolder.toneTypeText =(TextView) view.findViewById(R.id.tone_type_text);
		 viewHolder.toneTypeImg =(ImageView) view.findViewById(R.id.tone_type_img);
		 viewHolder.toneBeginStudy = (ImageView) view.findViewById(R.id.img_toneType_start);
		 view.setTag(viewHolder);
		}else {
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();
		}
		final int typeid = position+1;
		final ToneType toneType = getItem(position);//���ʵ��,final���ܱ�onclick���ڲ������ã�position��Ҫ������Ҳ��Ҫ��final
		if(toneType!=null){
			Log.d("tonetype",toneType.getToneTypeText());
		viewHolder.toneTypeText.setText(toneType.getToneTypeText());
		viewHolder.toneTypeImg.setImageResource(toneType.getToneTypeImg());
		//����ѧϰ������
		viewHolder.toneBeginStudy.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mySendIntent(typeid);
			}
		});
		}
		
		return view;
		
	}
class ViewHolder{
	TextView toneTypeText;
	ImageView toneBeginStudy;
	ImageView toneTypeImg;
}
public void mySendIntent(int typeid){
	Intent intent = new Intent(getContext(), ToneListActivity.class);
    //newһ��Bundle���󣬲���Ҫ���ݵ����ݴ���
    Bundle bundle = new Bundle();
   bundle.putInt("typeid", typeid);
   Log.d("send", ""+typeid);
    //��bundle����assign��Intent
    intent.putExtras(bundle);
    //������ת
    getContext().startActivity(intent);
}
}