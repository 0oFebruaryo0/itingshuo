package com.example.itingshuo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import volley.VolleyManager;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.config.Urls;
import com.dialog.ChangeDialog;
import com.dialog.ResultDialog;
import com.dialog.UpdateDialog;
import com.entity.JShowMovie;
import com.entity.JShowTone;
import com.example.itingshuo.MovieActivity.HuiTingListener;
import com.example.itingshuo.MovieActivity.LuYinListener;
import com.example.itingshuo.MovieActivity.PlayAsyncTask;
import com.example.itingshuo.MovieActivity.ShangChuanListener;
import com.example.itingshuo.MovieActivity.UIHandler;
import com.recorder.AudioFileFunc;
import com.recorder.AudioRecordFunc;
import com.recorder.ErrorCode;
import com.recorder.PlayAudioTrack;

import android.support.v7.app.ActionBarActivity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ToneActivity extends ActionBarActivity {
	//���¿ؼ�
	private TextView tv_content;
	//����¼���ϴ�������ť
	private int mState = -1;    //-1:û��¼�ƣ�0��¼��wav
	private final static int FLAG_WAV = 0;
	private LinearLayout huiTing_bg;
	private LinearLayout luYin_bg;
	private LinearLayout shangChuan_bg;
	private TextView huiTing_text;
	private TextView luYin_text;
	private TextView shangChuan_text;
	 private UIHandler uiHandler;
	 //intent
	 private String typeid;
	 private String toneid;
	 public static final String TAG = "ToneActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_tone);
		tv_content=(TextView) findViewById(R.id.content);
		mGetIntent();
		requestDataFromServer();
		initRecorder();//����¼���ϴ��ؼ���ʼ��
		setRecorderListener();//��������¼���ϴ�
		uiHandler = new UIHandler();   
	}
	//����¼���ϴ��ؼ���ʼ��
		private void initRecorder(){
			huiTing_bg =  (LinearLayout) findViewById(R.id.huiti_bg);
			huiTing_text = (TextView) findViewById(R.id.huiti_text);	
			luYin_bg =  (LinearLayout) findViewById(R.id.luyin_bg);
			luYin_text = (TextView) findViewById(R.id.luyin_text);
			shangChuan_bg =  (LinearLayout) findViewById(R.id.shangchuan_bg);
			shangChuan_text = (TextView) findViewById(R.id.shangchuan_text);
		}
		private void setRecorderListener(){
			huiTing_bg.setOnClickListener(new HuiTingListener());
			luYin_bg.setOnClickListener(new LuYinListener());
			shangChuan_bg.setOnClickListener(new ShangChuanListener());
		}
		//����������
		class HuiTingListener implements OnClickListener{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//����¼��
				//Toast.makeText(MovieActivity.this,"huiting", Toast.LENGTH_SHORT).show();
				play();
			}
			
		}
		//¼��������
		class LuYinListener implements OnClickListener{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Toast.makeText(MovieActivity.this,"luYin", Toast.LENGTH_SHORT).show();
				if(ToneActivity.this.luYin_text.getText().equals("¼��")){ 
				record(FLAG_WAV);
				}else{
				stop();	
				}
				
				 
			}
			
		}
		//�ϴ�������
		class ShangChuanListener implements OnClickListener{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Toast.makeText(MovieActivity.this,"shangChuan", Toast.LENGTH_SHORT).show();
				
				getResult();
			}
			
		}
	    private final static int CMD_RECORDING_TIME = 2000;
	    private final static int CMD_RECORDFAIL = 2001;
	    private final static int CMD_STOP = 2002;
	    private final static int CMD_PLAYFAIL = 2003;
	   class UIHandler extends Handler{
	        public UIHandler() {
	        }
	        @Override
	        public void handleMessage(Message msg) {
	            // TODO Auto-generated method stub
	            Log.d("MyHandler", "handleMessage......");
	            super.handleMessage(msg);
	            Bundle b = msg.getData();
	            int vCmd = b.getInt("cmd");
	            switch(vCmd)
	            {
	            case CMD_RECORDING_TIME:
	            	ToneActivity.this.luYin_text.setText("���");
	                break;
	            case CMD_RECORDFAIL:
	            	int vErrorCode = b.getInt("msg");
	                String vMsg = ErrorCode.getErrorInfo(ToneActivity.this, vErrorCode);
	            	Toast.makeText(ToneActivity.this,"¼��ʧ��", Toast.LENGTH_SHORT).show();
	            	ToneActivity.this.luYin_text.setText("¼��");
	                break;
	            case CMD_STOP:                
	                int vFileType = b.getInt("msg");
	                switch(vFileType){
	                case FLAG_WAV:
	                    AudioRecordFunc mRecord_1 = AudioRecordFunc.getInstance(); 
	                    ToneActivity.this.luYin_text.setText("¼��");
	                    break;
	                }
	                break;
	            case CMD_PLAYFAIL:
	            	Toast.makeText(ToneActivity.this,"����¼����", Toast.LENGTH_SHORT).show();
	            	break;
	            default:
	                break;
	            }
	        }
	    };

	    /**
	     * ��ʼ¼��
	     * @param mFlag��0��¼��wav��ʽ��1��¼��amr��ʽ
	     */
	    private void record(int mFlag){
	        if(mState != -1){
	        	 Log.d("ces", "ggggg");
	            Message msg = new Message();
	            Bundle b = new Bundle();// �������
	            b.putInt("cmd",CMD_RECORDFAIL);
	            b.putInt("msg", ErrorCode.E_STATE_RECODING);
	            msg.setData(b); 
	            
	            uiHandler.sendMessage(msg); // ��Handler������Ϣ,����UI
	            return;
	        } 
	        int mResult = -1;       
	        AudioRecordFunc mRecord_1 = AudioRecordFunc.getInstance();
	        mResult = mRecord_1.startRecordAndFile();            
	        if(mResult == ErrorCode.SUCCESS){
	       	 Log.d("ces", "ssss");
	        	 Message msg = new Message();
	                Bundle b = new Bundle();// �������
	                b.putInt("cmd",CMD_RECORDING_TIME);
	                msg.setData(b); 
	                uiHandler.sendMessage(msg); // ��Handler������Ϣ,����UI
	                mState = mFlag;
	        }else{
	       	 Log.d("ces", "hhhhh");
	            Message msg = new Message();
	            Bundle b = new Bundle();// �������
	            b.putInt("cmd",CMD_RECORDFAIL);
	            b.putInt("msg", mResult);
	            msg.setData(b); 
	            uiHandler.sendMessage(msg); // ��Handler������Ϣ,����UI
	        }
	    }
	    /**
	     * ֹͣ¼��
	     */
	    private void stop(){
	        if(mState != -1){
	            AudioRecordFunc mRecord_1 = AudioRecordFunc.getInstance();
	            mRecord_1.stopRecordAndFile();         
	            Message msg = new Message();
	            Bundle b = new Bundle();// �������
	            b.putInt("cmd",CMD_STOP);
	            b.putInt("msg", mState);
	            msg.setData(b);
	            uiHandler.sendMessage(msg); // ��Handler������Ϣ,����UI 
	            mState = -1;
	        }
	    }    
	    /**
	     * ����¼��
	     */
	    private void play(){
	        if(mState != -1){
	           
	            Message msg = new Message();
	            Bundle b = new Bundle();// �������
	            b.putInt("cmd",CMD_PLAYFAIL);
	            b.putInt("msg", mState);
	            msg.setData(b);
	            uiHandler.sendMessage(msg); // ��Handler������Ϣ,����UI 
	            mState = -1;
	        }else{
	        	if(AudioFileFunc.getWavFilePath()!=""){
	        		try {
	    				PlayAudioTrack.PlayAudioTrack(AudioFileFunc.getWavFilePath());
	    			} catch (IOException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    		 

	        	}else{
	        		 Log.d("play", "�Ҳ���¼���ļ�������");
	        	}
	        }
	    }    
	    //�����ϴ�����
	    public void showUpdateDialog() {

			UpdateDialog.Builder builder = new UpdateDialog.Builder(this);
			builder.create().show();

		}
	  //���ý������
	    public void showResultDialog() {

			ResultDialog.Builder builder = new ResultDialog.Builder(this);
			builder.create().show();

		}
	    //����������ӻ�ý��
	    public void getResult(){
	    	UpdateDialog.Builder builder = new UpdateDialog.Builder(this);
	    	final Dialog updateDialog = builder.create();
	    	final ResultDialog.Builder builder2 = new ResultDialog.Builder(this);
	    	updateDialog.show();
	    	new Handler().postDelayed(new Runnable(){    
	    	    public void run() {    
	    	    //execute the task    
	    	    	updateDialog.dismiss();	    	    	
	    	    	Dialog resultDialog = builder2.create();
	    	    	resultDialog.show();
	    	    }    
	    	 }, 3000);
	    	
	    }
//
	    //��ȡintent������ֵ
	    public void mGetIntent(){
	 	    	Bundle bundle1 = getIntent().getExtras();
	 	    	typeid = bundle1.getString("typeid");
	 			toneid = bundle1.getString("toneid");
	 			Log.d("bundle","typeid: "+typeid);
	 			Log.d("bundle","toneid: "+toneid);		
	 			
	    }
	    /*
		 * ���������������
		 */
		private void requestDataFromServer(){
						Map<String,String> map = new HashMap<String,String>();
				        map.put("typeid", typeid);
				        map.put("toneid", toneid);		        
				       VolleyManager.newInstance().GsonPostRequest(TAG, map, Urls.SHOWTONE, JShowTone.class, new Response.Listener<JShowTone>() {
				           @Override
				           public void onResponse(JShowTone jtone) {
				          //    Log.d("111111111111111111111", "ok" +  jmovie.getData().getMovie().get(0).getMovie_name());
				        	//   Log.d("111111111111111111111", "ok" +  jmovie.getData().getMovie().get(0).getCover_addr());   
				        	   int length = 0;
				        	   if(jtone.getData().getStatus()!=0 && jtone.getData().getText()!=null){
				        		// tv_title.setText(jtone.getData().getText().get(0).getText_name());
				        		 String taiciString = jtone.getData().getText().get(0).getContent();
				        		 String taiciString2=taiciString.replaceAll("##", "\n");
				        		 tv_content.setText(taiciString2);
				        	   //Log.d("success", "ok" +  Urls.ROOT+jmovie.getData().getMovie().get(0).getSegment_addr());
				        	   }
			

				           }
				       }, new Response.ErrorListener() {
				           @Override
				           public void onErrorResponse(VolleyError error) {
				               Log.d("fail", "connect fail");

				           }
				       });
				        Log.d(TAG, "finish");
		}
}
