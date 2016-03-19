package com.speak;

import java.util.List;

import com.example.itingshuo.MovieActivity;
import com.example.itingshuo.R;
import com.example.itingshuo.SpeakListActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ClassListAdapter extends ArrayAdapter<ClassList> {
    private int resource;
	public ClassListAdapter(Context context, int resource, List<ClassList> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		this.resource = resource;//resourceΪlistView��ÿ������Ĳ���id
		
	}
	//getViewΪlistView��ÿ������Ĳ�����������
	//convertView���ڽ�֮ǰ���غõĲ��ֽ��л���
	//����һ��viewHolder�Կؼ����л���
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		//return super.getView(position, convertView, parent);
		
//		Fruit fruit = getItem(position);//���ʵ��
//		View view;
//		if(convertView==null){
//		 view = LayoutInflater.from(getContext()).inflate(resource, null);
//		}else {
//			view = convertView;
//		}
//		ImageView fruitImage =  (ImageView) view.findViewById(R.id.fruit_image);
//		TextView fruitName = (TextView) view.findViewById(R.id.fruit_name);
//		fruitImage.setImageResource(fruit.getImageId());
//		fruitName.setText(fruit.getName());
//		return view;
		
		ClassList classList = getItem(position);//���ʵ��
		View view;
		ViewHolder viewHolder;
		if(convertView==null){
		 view = LayoutInflater.from(getContext()).inflate(resource, null);
		 viewHolder = new ViewHolder();
		 viewHolder.classTitle =(TextView) view.findViewById(R.id.tv_speak_title);
		 viewHolder.classContent =(TextView) view.findViewById(R.id.tv_speak_title);
		 viewHolder.classCount = (TextView) view.findViewById(R.id.tv_speak_count);
		 viewHolder.speakBeginStudy = (ImageView) view.findViewById(R.id.img_speak_beginStudy);
		 view.setTag(viewHolder);
		}else {
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();
		}
		viewHolder.classTitle.setText(classList.getTitle());
		viewHolder.classContent.setText(classList.getContent());
		viewHolder.classCount.setText(classList.getCount());
		//����ѧϰ������
		viewHolder.speakBeginStudy.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Toast.makeText(getContext(), movie.getTitle()+"begin study", Toast.LENGTH_SHORT).show();
				mySendIntent();
			}
		});
		return view;
		
	}
class ViewHolder{
	TextView classContent;
	TextView classTitle;
	TextView classCount;
	ImageView speakBeginStudy;
}
public void mySendIntent(){
	Intent intent = new Intent(getContext(), SpeakListActivity.class);
//    //newһ��Bundle���󣬲���Ҫ���ݵ����ݴ���
//    Bundle bundle = new Bundle();
//    bundle.putInt("username", username);
//    bundle.putString("password", password);
//    //��bundle����assign��Intent
//    intent.putExtras(bundle);
//    //������ת
    getContext().startActivity(intent);
}
}
