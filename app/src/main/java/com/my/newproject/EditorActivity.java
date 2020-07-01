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
import android.widget.Switch;
import android.widget.Button;
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
import android.support.v4.content.ContextCompat;
import android.support.v4.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;

public class EditorActivity extends AppCompatActivity {
	
	
	private Toolbar _toolbar;
	private FloatingActionButton _fab;
	private double cli = 0;
	private String edit_mode = "";
	private double index = 0;
	private String to_add_vars = "";
	private String b = "";
	private double tmp_index = 0;
	
	private ArrayList<String> test = new ArrayList<>();
	private ArrayList<String> global_method_list = new ArrayList<>();
	
	private TextView textview5;
	private LinearLayout linear1;
	private LinearLayout linear3;
	private LinearLayout linear2;
	private LinearLayout control_panel;
	private TextView textview1;
	private TextView textview2;
	private TextView textview4;
	private Switch switch1;
	private Button see_source;
	private Button button4;
	private TextView main;
	private TextView method_block;
	private ScrollView vscroll3;
	private ScrollView vs;
	private ScrollView fo_select_list;
	private LinearLayout function_collect;
	private Button add_var;
	private Button del_var;
	private LinearLayout line4;
	
	private Intent intent = new Intent();
	private ObjectAnimator anim = new ObjectAnimator();
	private ObjectAnimator anim2 = new ObjectAnimator();
	private AlertDialog.Builder dialog;
	private AlertDialog.Builder dell_var;
	private AlertDialog.Builder exitDialog;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.editor);
		initialize(_savedInstanceState);
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
		}
		else {
			initializeLogic();
		}
	}
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
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
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		control_panel = (LinearLayout) findViewById(R.id.control_panel);
		textview1 = (TextView) findViewById(R.id.textview1);
		textview2 = (TextView) findViewById(R.id.textview2);
		textview4 = (TextView) findViewById(R.id.textview4);
		switch1 = (Switch) findViewById(R.id.switch1);
		see_source = (Button) findViewById(R.id.see_source);
		button4 = (Button) findViewById(R.id.button4);
		main = (TextView) findViewById(R.id.main);
		method_block = (TextView) findViewById(R.id.method_block);
		vscroll3 = (ScrollView) findViewById(R.id.vscroll3);
		vs = (ScrollView) findViewById(R.id.vs);
		fo_select_list = (ScrollView) findViewById(R.id.fo_select_list);
		function_collect = (LinearLayout) findViewById(R.id.function_collect);
		add_var = (Button) findViewById(R.id.add_var);
		del_var = (Button) findViewById(R.id.del_var);
		line4 = (LinearLayout) findViewById(R.id.line4);
		dialog = new AlertDialog.Builder(this);
		dell_var = new AlertDialog.Builder(this);
		exitDialog = new AlertDialog.Builder(this);
		
		linear2.setOnClickListener(new View.OnClickListener() {
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
		
		see_source.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (test.size() == 0) {
					SketchwareUtil.showMessage(getApplicationContext(), "出错了呀😅你没有添加变量");
				}
				else {
					intent.setClass(getApplicationContext(), SourceCodeActivity.class);
					intent.putExtra("vars",test.toString());
					startActivity(intent);
					SketchwareUtil.showMessage(getApplicationContext(), "一切顺利😊");
				}
			}
		});
		
		button4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				SketchwareUtil.showMessage(getApplicationContext(), "保存");
				
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
		
		add_var.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				final Button b=new Button(getApplicationContext());
				b.setText("删除变量");
				//Bundle bundle=new Bundle();
				dell_var.setTitle("删除变量");
				dialog.setTitle("添加变量");
				dialog.setMessage("请选择变量类型");
				//RadioGroup r=new RadioGroup(getApplicationContext());
				final EditText t=new EditText(getApplicationContext());
				t.setHint("请输入变量名称");
				t.setTextColor(Color.BLACK);
				//t.setHintColor(Color.BLACK);
				dialog.setView(t);
				dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						String tmp=t.getText().toString();
						//Toast.makeText(getApplicationContext(),tmp,Toast.LENGTH_SHORT).show();
						TextView ti=new TextView(getApplicationContext());
						ti.setText("变量"+tmp);
						ti.setTextColor(Color.BLACK);
						line4.addView(ti);
						//line4.addView(b);
						TextView ti2=new TextView(getApplicationContext());
						ti2.setText("设置变量"+tmp+"为"+"");
						ti2.setTextColor(Color.BLACK);
						line4.addView(ti2);
						index++;
						test.add(tmp);
						to_add_vars = "tmp";
						Toast.makeText(getApplicationContext(),test.toString(),Toast.LENGTH_SHORT).show();
					}
				});
				dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						SketchwareUtil.showMessage(getApplicationContext(), "已取消");
					}
				});
				dialog.create().show();
				dell_var.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						SketchwareUtil.showMessage(getApplicationContext(), "已取消");
					}
				});
				dell_var.setPositiveButton("删除", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						Toast.makeText(getApplicationContext(),"删除",Toast.LENGTH_SHORT).show();
						Toast.makeText(getApplicationContext(),test.toString(),Toast.LENGTH_SHORT).show();
						int x=test.indexOf(t.getText().toString());
						test.remove(x);
					}
				});
			}
		});
		
		del_var.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				dell_var.setTitle("输入一个变量名来删除");
				final EditText pptv=new EditText(getApplicationContext());
				pptv.setTextColor(Color.BLACK);
				dell_var.setView(pptv);
				dell_var.setPositiveButton("删除", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						String tmp =pptv.getText().toString();
						tmp_index=test.indexOf(tmp);
						if (tmp_index > -1) {
							test.remove((int)(tmp_index));
							line4.removeViewAt((int)(tmp_index));
							setTitle(String.valueOf((long)(tmp_index - 1)));
							line4.removeViewAt((int)tmp_index-1);
						}
						else {
							SketchwareUtil.showMessage(getApplicationContext(), "不存在这个变量哦😜");
						}
						Toast.makeText(getApplicationContext(),test.indexOf(tmp)+"索引",Toast.LENGTH_SHORT).show();
					}
				});
				
				dell_var.create().show();
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
					if(moved_x+100f<800)
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
		fo_select_list.setBackgroundColor(0xFF66BB6A);
		RadioGroup r=new RadioGroup(getApplicationContext());
		for(int _repeat36 = 0; _repeat36 < (int)(5); _repeat36++) {
			if(_repeat36==1)
			{
				RadioButton btn=new RadioButton(getApplicationContext());
				btn.setText("变量");
				r.addView(btn);
			}
			if(_repeat36==2)
			{
				RadioButton btn=new RadioButton(getApplicationContext());
				btn.setText("对象列表");
				r.addView(btn);
			}
			if(_repeat36==3)
			{
				RadioButton btn=new RadioButton(getApplicationContext());
				btn.setText("逻辑");
				r.addView(btn);
			}
			if(_repeat36==4)
			{
				RadioButton btn=new RadioButton(getApplicationContext());
				btn.setText("运算");
				r.addView(btn);
			}
		}
		//View tmp=View.inflate(getApplicationContext(),my_id,null);//Viewgroup
		//Toast.makeText(getApplicationContext(),test.toString(),Toast.LENGTH_SHORT).show();
		fo_select_list.addView(r);
		class d implements CompoundButton.OnCheckedChangeListener
		{
			            @Override
			            public void onCheckedChanged(CompoundButton p1, boolean p2) 
			{
				
				            }
		}
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
		exitDialog.setTitle("确定退出?😉");
		exitDialog.setMessage("真的要退出吗😮");
		exitDialog.setPositiveButton("保存😏并退出", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				SketchwareUtil.showMessage(getApplicationContext(), "保存好了👌");
				finish();
			}
		});
		exitDialog.setNegativeButton("不保存退出", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				finish();
			}
		});
		exitDialog.create().show();
	}
	private void _tw () {
		Toast.makeText(getApplicationContext(),"",Toast.LENGTH_SHORT).show();
	}
	
	
	private void _tip_String (final String _str_toTip) {
		SketchwareUtil.showMessage(getApplicationContext(), _str_toTip);
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
