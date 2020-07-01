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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Button;

public class GgameActivity extends AppCompatActivity {
	
	
	private Toolbar _toolbar;
	
	private LinearLayout linear1;
	private LinearLayout linear10;
	private LinearLayout canvas_area;
	private LinearLayout joystick_area;
	private LinearLayout progress;
	private TextView textview3;
	private TextView time;
	private TextView textview4;
	private LinearLayout space;
	private TextView textview2;
	private LinearLayout canvas_inimigo;
	private LinearLayout linear4;
	private LinearLayout test_ball;
	private ImageView inimigo;
	private LinearLayout test_ball2;
	private ImageView nave;
	private TextView pos_x;
	private TextView pos_y;
	private LinearLayout linear5;
	private Button button1;
	private LinearLayout linear9;
	private LinearLayout joystick_align;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.ggame);
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
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear10 = (LinearLayout) findViewById(R.id.linear10);
		canvas_area = (LinearLayout) findViewById(R.id.canvas_area);
		joystick_area = (LinearLayout) findViewById(R.id.joystick_area);
		progress = (LinearLayout) findViewById(R.id.progress);
		textview3 = (TextView) findViewById(R.id.textview3);
		time = (TextView) findViewById(R.id.time);
		textview4 = (TextView) findViewById(R.id.textview4);
		space = (LinearLayout) findViewById(R.id.space);
		textview2 = (TextView) findViewById(R.id.textview2);
		canvas_inimigo = (LinearLayout) findViewById(R.id.canvas_inimigo);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		test_ball = (LinearLayout) findViewById(R.id.test_ball);
		inimigo = (ImageView) findViewById(R.id.inimigo);
		test_ball2 = (LinearLayout) findViewById(R.id.test_ball2);
		nave = (ImageView) findViewById(R.id.nave);
		pos_x = (TextView) findViewById(R.id.pos_x);
		pos_y = (TextView) findViewById(R.id.pos_y);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		button1 = (Button) findViewById(R.id.button1);
		linear9 = (LinearLayout) findViewById(R.id.linear9);
		joystick_align = (LinearLayout) findViewById(R.id.joystick_align);
	}
	private void initializeLogic() {
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
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
