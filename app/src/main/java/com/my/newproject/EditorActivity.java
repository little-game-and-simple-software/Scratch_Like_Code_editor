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
import java.util.ArrayList;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.Switch;
import android.widget.ScrollView;
import android.content.Intent;
import android.net.Uri;
import android.animation.ObjectAnimator;
import android.view.animation.LinearInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.CompoundButton;
import java.text.DecimalFormat;

public class EditorActivity extends AppCompatActivity {
	
	
	private Toolbar _toolbar;
	private FloatingActionButton _fab;
	private double cli = 0;
	private String edit_mode = "";
	
	private ArrayList<String> test = new ArrayList<>();
	private ArrayList<String> global_method_list = new ArrayList<>();
	
	private TextView textview5;
	private LinearLayout linear1;
	private ImageView function;
	private Button button3;
	private TextView main;
	private TextView line;
	private TextView method_block;
	private LinearLayout control_panel;
	private TextView textview1;
	private TextView textview2;
	private TextView textview4;
	private Switch switch1;
	private ScrollView vscroll3;
	private ScrollView vs;
	private LinearLayout function_collect;
	private Button add_var;
	private Button del_var;
	
	private Intent intent = new Intent();
	private ObjectAnimator anim = new ObjectAnimator();
	private ObjectAnimator anim2 = new ObjectAnimator();
	private AlertDialog.Builder dialog;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.editor);
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
		
		textview5 = (TextView) findViewById(R.id.textview5);
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		function = (ImageView) findViewById(R.id.function);
		button3 = (Button) findViewById(R.id.button3);
		main = (TextView) findViewById(R.id.main);
		line = (TextView) findViewById(R.id.line);
		method_block = (TextView) findViewById(R.id.method_block);
		control_panel = (LinearLayout) findViewById(R.id.control_panel);
		textview1 = (TextView) findViewById(R.id.textview1);
		textview2 = (TextView) findViewById(R.id.textview2);
		textview4 = (TextView) findViewById(R.id.textview4);
		switch1 = (Switch) findViewById(R.id.switch1);
		vscroll3 = (ScrollView) findViewById(R.id.vscroll3);
		vs = (ScrollView) findViewById(R.id.vs);
		function_collect = (LinearLayout) findViewById(R.id.function_collect);
		add_var = (Button) findViewById(R.id.add_var);
		del_var = (Button) findViewById(R.id.del_var);
		dialog = new AlertDialog.Builder(this);
		
		button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent.setClass(getApplicationContext(), SourceCodeActivity.class);
				startActivity(intent);
			}
		});
		
		main.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (edit_mode.equals("move")) {
					main.setOnTouchListener(new  OnTouchListener()
					{
						@Override
						public boolean onTouch(View v,MotionEvent e)
						{
							 float pressed_x=0f;
							float pressed_y=0f;
							if(e.getAction()==MotionEvent.ACTION_DOWN)
							{
								    pressed_x=e.getX();
								    pressed_y=e.getY();
								textview2.setText("按下x"+pressed_x);
							}
							
							
							 if(e.getAction()==MotionEvent.ACTION_MOVE)
							{
								  float moved_x=0f;
								float moved_y=0f;
								  moved_x=e.getRawX();
								moved_y=e.getRawY();
								 main.setX(moved_x-pressed_x);
								main.setY(moved_y-pressed_y); textview1.setText("rawx"+moved_x);
								  
							}
							return true;
						}
					}
					);
				}
				else {
					if (edit_mode.equals("line")) {
						
					}
				}
			}
		});
		
		line.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				new Canvas();
				
			}
		});
		
		method_block.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		textview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2)  {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					edit_mode = "move";
				}
				else {
					edit_mode = "line";
				}
				//line是连线
				SketchwareUtil.showMessage(getApplicationContext(), edit_mode);
			}
		});
		
		add_var.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				dialog.setTitle("添加变量");
				dialog.setMessage("请输入变量");
				dialog.setView(R.layout.dialog_content1);
				dialog.setPositiveButton("哈哈哈", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				dialog.create().show();
			}
		});
		
		del_var.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		_fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				anim.setTarget(control_panel);
				anim.setPropertyName("translationY");
				anim.setFloatValues((float)(600), (float)(0));
				anim.setDuration((int)(500));
				anim.start();
				anim2.setTarget(_fab);
				anim2.setPropertyName("translationY");
				anim2.setFloatValues((float)(0), (float)(-666));
				anim2.start();
			}
		});
	}
	private void initializeLogic() {
		function.setOnTouchListener(new  OnTouchListener()
		{
			@Override
			public boolean onTouch(View v,MotionEvent e)
			{
				 float pressed_x=0f;
				float pressed_y=0f;
				if(e.getAction()==MotionEvent.ACTION_DOWN)
				{
					    pressed_x=e.getX();
					    pressed_y=e.getY();
					textview2.setText("按下x"+pressed_x);
				}
				
				
				 if(e.getAction()==MotionEvent.ACTION_MOVE)
				{
					  float moved_x=0f;
					float moved_y=0f;
					  moved_x=e.getRawX();
					moved_y=e.getRawY();
					 function.setX(moved_x-pressed_x);
					function.setY(moved_y-pressed_y); textview1.setText("rawx"+moved_x);
					  
				}
				return true;
			}
		}
		);
		method_block.setOnTouchListener(new  OnTouchListener()
		{
			@Override
			public boolean onTouch(View v,MotionEvent e)
			{
				 float pressed_x=0f;
				float pressed_y=0f;
				if(e.getAction()==MotionEvent.ACTION_DOWN)
				{
					    pressed_x=e.getX();
					    pressed_y=e.getY();
					textview2.setText("按下x"+pressed_x);
				}
				
				
				 if(e.getAction()==MotionEvent.ACTION_MOVE)
				{
					  float moved_x=0f;
					float moved_y=0f;
					  moved_x=e.getRawX();
					moved_y=e.getRawY();
					if(moved_x+200f<800)
					{
						 method_block.setX(moved_x-pressed_x);
						method_block.setY(moved_y-pressed_y);
					} textview1.setText("rawx"+moved_x
					);
					  
				}
				return true;
			}
		}
		);
		textview4.setText(String.valueOf(SketchwareUtil.getDisplayHeightPixels(getApplicationContext())));
		textview5.setText(String.valueOf(SketchwareUtil.getDisplayWidthPixels(getApplicationContext())));
		//global 全局方法表
		//多选布局
		View tmp=View.inflate(this,R.layout.function_list,null);
		vs.addView(tmp);
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	private void _j (final boolean _j) {
		
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
