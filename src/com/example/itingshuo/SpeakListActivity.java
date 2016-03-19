package com.example.itingshuo;

import java.util.ArrayList;
import java.util.List;

import com.anim.ListAnim;
import com.speak.ClassList;
import com.speak.ClassListAdapter;
import com.speak.JuziList;
import com.speak.JuziListAdapter;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ListView;

public class SpeakListActivity extends ActionBarActivity {
	  private JuziListAdapter adapter = null;  
      private List<JuziList> juziList;
      private ListView juziListView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.speak_juzi_fragment);  
		juziListView = (ListView) findViewById(R.id.lv_speak_juzi);
		 //�����ؼ�������
	    Classinit(); 
        adapter = new JuziListAdapter(SpeakListActivity.this, R.layout.speak_juzi_item, juziList);
        juziListView.setLayoutAnimation(new ListAnim().getListAnim());
        juziListView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.speak_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	   /*
     * ���class���ݵĶ���
     * ��Ҫ�������ӿ�
     */

  public void Classinit(){
  	juziList = new ArrayList<JuziList>();
  	JuziList class1 = new JuziList();
  	class1.setTitle("����1");
  	class1.setContent("����1����");
  	class1.setTime("10.12");
  	juziList.add(class1);
  	JuziList class2 = new JuziList();
  	class2.setTitle("����1");
  	class2.setContent("����2����");
	class2.setTime("10.12");
	juziList.add(class2);
  	JuziList class3 = new JuziList();
  	class3.setTitle("����1");
  	class3.setContent("����3����");
	class3.setTime("10.12");
	juziList.add(class3);
  	JuziList class4 = new JuziList();
  	class4.setTitle("����1");
  	class4.setContent("����4����");
	class4.setTime("10.12");
	juziList.add(class4);
  	JuziList class5 = new JuziList();
  	class5.setTitle("����1");
  	class5.setContent("����5����");
	class5.setTime("10.12");
	juziList.add(class5);
	JuziList class6 = new JuziList();
  	class6.setTitle("����1");
  	class6.setContent("����6����");
	class6.setTime("10.12");
	JuziList class7 = new JuziList();
  	class7.setTitle("����1");
  	class7.setContent("����7����");
	class7.setTime("10.12");
	juziList.add(class7);
  }
  
 
}
