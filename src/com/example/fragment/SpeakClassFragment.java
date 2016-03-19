package com.example.fragment;

import java.util.ArrayList;
import java.util.List;

import com.anim.ListAnim;
import com.example.itingshuo.R;
import com.speak.ClassList;
import com.speak.ClassListAdapter;

import android.R.anim;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;//��֪ΪʲôҪ�������
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.ListView;

public class SpeakClassFragment extends LazyFragment {
	  // ��־λ����־�Ѿ���ʼ����ɡ�
    private boolean isPrepared;
	  private ClassListAdapter adapter = null;  
      private List<ClassList> classList;
      private ListView classListView;
	    @Override  
	    public void onCreate(Bundle savedInstanceState) {  
	        super.onCreate(savedInstanceState);  
	        
	      
	    }  
	    @Override  
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,  
	            Bundle savedInstanceState) {
	    	Log.d("oncreate", "onCreateView");
	        View v = inflater.inflate(R.layout.speak_class_fragment,null);  
	        classListView = (ListView) v.findViewById(R.id.lv_speak_class);
	        //XXX��ʼ��view�ĸ��ؼ�
	        isPrepared = true;
	        lazyLoad();	        
	        return v;  
	    }  
	    //������
	    @Override
	    protected void lazyLoad() {
	    	// TODO Auto-generated method stub
	    	 if(!isPrepared || !isVisible) {
	             return;
	         }
	         //�����ؼ�������
	    	    Classinit(); 
		        adapter = new ClassListAdapter(getActivity(), R.layout.speak_class_item, classList);  
		        classListView.setLayoutAnimation(new ListAnim().getListAnim());
		        classListView.setAdapter(adapter);
	    }
	    
	      /*
	       * ���class���ݵĶ���
	       * ��Ҫ�������ӿ�
	       */

	    public void Classinit(){
	    	classList = new ArrayList<ClassList>();
	    	ClassList class1 = new ClassList();
	    	class1.setTitle("class1");
	    	class1.setContent("classContent1");
	    	class1.setCount("һ��2������");
	    	classList.add(class1);
	    	ClassList class2 = new ClassList();
	    	class2.setTitle("class2");
	    	class2.setContent("classContent2");
	    	class2.setCount("һ��2������");
	    	classList.add(class2);
	    	ClassList class3 = new ClassList();
	    	class3.setTitle("class3");
	    	class3.setContent("classContent3");
	    	class3.setCount("һ��2������");
	    	classList.add(class3);
	    	ClassList class4 = new ClassList();
	    	class4.setTitle("class4");
	    	class4.setContent("classContent4");
	    	class4.setCount("һ��2������");
	    	classList.add(class4);
	    	ClassList class5 = new ClassList();
	    	class5.setTitle("class1");
	    	class5.setContent("classContent5");
	    	class5.setCount("һ��2������");
	    	classList.add(class5);
	    	ClassList class6 = new ClassList();
	    	class6.setTitle("class6");
	    	class6.setContent("classContent6");
	    	class6.setCount("һ��2������");
	    	classList.add(class6);
	    	ClassList class7 = new ClassList();
	    	class7.setTitle("class7");
	    	class7.setContent("classContent7");
	    	class7.setCount("һ��2������");
	    	classList.add(class7);
	    }
	    
	   
	    
	   
}
