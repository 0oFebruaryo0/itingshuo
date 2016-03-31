package com.entity;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;

public class WavEntity {
	 //����������,<input file>��ǩ�е�name
    private String mName ="uploadfile";
    //�ļ���
    private String mFileName ;
    //�ļ��� mime����Ҫ�����ĵ���ѯ
    private String mMime ;
    //��Ҫ�ϴ���ͼƬ��Դ����Ϊ�������Ϊ�˷��������ֱ�Ӱ� bigmap ����������������Ŀ��һ�㲻������������ǰ�ͼƬ��·�����������������ͼƬ���ж�����ת��
    private String filepath;
    public void setmFileName(String mFileName){
    	this.mFileName = mFileName;
    	
    }
    public void setFilepath(String filepath){
    	this.filepath = filepath;
    	
    }
//    public FormImage(Bitmap mBitmap) {
//        this.mBitmap = mBitmap;
//    }

    public String getName() {
       return mName;
    }

    public String getFileName() {
        return mFileName;
    }
    //��ͼƬ���ж�����ת��
    public byte[] getValue() {  	
    	byte[] byt=null;
    	FileInputStream input=null;
		try {
			input= new FileInputStream (filepath) ;
			byt = new byte[input.available()];
			input.read(byt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  finally{
			try {
				if(input!=null)
				input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	   return byt;  
    }
    //wav��mine
    public String getMime() {
        return "audio/wav";
    }
}
