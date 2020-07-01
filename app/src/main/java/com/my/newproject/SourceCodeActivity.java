package com.my.newproject;

import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.graphics.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.util.*;
import java.text.*;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.widget.LinearLayout;
import java.util.HashMap;
import java.util.ArrayList;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

public class SourceCodeActivity extends AppCompatActivity {
	
	
	private Toolbar _toolbar;
	private FloatingActionButton _fab;
	private DrawerLayout _drawer;
	private double length = 0;
	private String str = "";
	private HashMap<String, Object> h = new HashMap<>();
	
	private ArrayList<String> vars = new ArrayList<>();
	
	private Button button1;
	private TextView t2;
	private LinearLayout linear1;
	private EditText edittext1;
	private HorizontalScrollView hscroll1;
	private LinearLayout line2;
	
	private Intent intent = new Intent();
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.source_code);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		_toolbar = (Toolbar) findViewById(R.id._toolbar);
		setSupportActionBar(_toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				onBackPressed();
			}
		});
		_fab = (FloatingActionButton) findViewById(R.id._fab);
		
		_drawer = (DrawerLayout) findViewById(R.id._drawer);ActionBarDrawerToggle _toggle = new ActionBarDrawerToggle(SourceCodeActivity.this, _drawer, _toolbar, R.string.app_name, R.string.app_name);
		_drawer.addDrawerListener(_toggle);
		_toggle.syncState();
		
		LinearLayout _nav_view = (LinearLayout) findViewById(R.id._nav_view);
		
		button1 = (Button) findViewById(R.id.button1);
		t2 = (TextView) findViewById(R.id.t2);
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		edittext1 = (EditText) findViewById(R.id.edittext1);
		hscroll1 = (HorizontalScrollView) findViewById(R.id.hscroll1);
		line2 = (LinearLayout) findViewById(R.id.line2);
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
	}
	private void initializeLogic() {
		SketchwareUtil.showMessage(getApplicationContext(), getIntent().getStringExtra("vars"));
		setTitle(getIntent().getStringExtra("vars"));
		t2.setText(getIntent().getStringExtra("vars"));
		String s= getIntent().getStringExtra("vars");
		for(int _repeat20 = 0; _repeat20 < (int)(getIntent().getStringExtra("vars").length()); _repeat20++) {
			TextView tmp=new TextView(getApplicationContext());
			tmp.setText("var"+" "+s);
			tmp.setTextColor(Color.BLACK);
			linear1.addView(tmp);
		}
		String []split_tmp=s.split(",");
		int for_index;
		int length_int=split_tmp.toString().length();
		TextView tmp=new TextView(getApplicationContext());
		tmp.setText(split_tmp[0]);
		line2.addView(tmp);
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		if (_drawer.isDrawerOpen(GravityCompat.START)) {
			_drawer.closeDrawer(GravityCompat.START);
		}
		else {
			super.onBackPressed();
		}
	}
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input){
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels(){
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels(){
		return getResources().getDisplayMetrics().heightPixels;
	}
	
}
