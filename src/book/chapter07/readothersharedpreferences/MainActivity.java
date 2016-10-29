package book.chapter07.readothersharedpreferences;

import android.os.Bundle;

/*
 读写其他应用的SharedPreferences，主要步骤：
 1、创建所需访问程序对应的Context。
 2、调用其他应用程序的Context的getSharedPreferences(String name,int mode)方法。
 3、调用SharedPreferences的edit（）方法写入数据。
 */
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		SharedPreferences accessPreferences,loginPreferences;
		Context appContext=null;
		try {
			appContext=createPackageContext("book.chapter07.readothersharedpreferences",Context.CONTEXT_IGNORE_SECURITY);//创建上下文
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		accessPreferences=appContext.getSharedPreferences("access", Context.MODE_WORLD_READABLE);
		int count=accessPreferences.getInt("count", 0);
		loginPreferences=appContext.getSharedPreferences("login", Context.MODE_WORLD_READABLE);
		String name=loginPreferences.getString("name", null);
		Toast.makeText(this, "你好，"+name+",SaveLoginInfo应用程序已经被使用了"+count+"次！", Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
