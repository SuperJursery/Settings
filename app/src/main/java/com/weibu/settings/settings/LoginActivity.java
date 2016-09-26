package com.weibu.settings.settings;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class LoginActivity extends AppCompatActivity {
    //private TextView mPassword;
    private EditText mPassword = null;
    private Button mComfirm;
    private String EnterStr;
    //private View Toolbar_view;
    private volatile String pwd;
    private final String DEFAULT_PWD="20160901";
    private final String TAG="SettingsLogin";
    //private ActionBar actionBar;
    //android.uid.system

    private void getLatestPWD(){
        Context context = this;
        SharedPreferences StorageData = context.getSharedPreferences("passwd", Context.MODE_MULTI_PROCESS);
        pwd = StorageData.getString("passwd",DEFAULT_PWD);
        //Toast.makeText(LoginActivity.this, pwd, Toast.LENGTH_SHORT).show();
    }

    private void clearEnterDatas(){
        if (null == mPassword){
            Log.d("Jusery","Can't find EditText!");
            return;
        }
        mPassword.getText().clear();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Context context = this;
        //LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Toolbar_view = inflater.inflate(R.layout.activity_tool_bar, null, false);
        //actionBar = getActionBar();
        //actionBar.hide();
        //SharedPreferences StorageData = context.getSharedPreferences("passwd", context.MODE_PRIVATE);
        //pwd = StorageData.getString("passwd",DEFAULT_PWD);
        getLatestPWD();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Log.d("Jursery", "toolbar=" + toolbar);
        if (toolbar != null) {
            Log.d("Jursery", "This toolbar=" + toolbar);
            //toolbar.setTitle("ToolbarTEST");
            toolbar.setLogo(R.mipmap.ic_launcher);
            setSupportActionBar(toolbar);
            //toolbar.inflateMenu(R.menu.action_menu);
            toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    Log.d("Jursery", "do onMenuItemClick");
                    int menuItemId = item.getItemId();
                    if (menuItemId == R.id.action_change) {
                        //Toast.makeText(LoginActivity.this, "change password", Toast.LENGTH_SHORT).show();
                        Intent it = new  Intent();
                        it.setClass(LoginActivity.this,ChangePassword.class);
                        startActivity(it);
                    }
                    return true;
                }
            });
        }


        mPassword = (EditText) findViewById(R.id.editText);
        mComfirm = (Button) findViewById(R.id.comfirm);
        mComfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnterStr = mPassword.getText().toString();
                clearEnterDatas();
                getLatestPWD();
                //Toast.makeText(LoginActivity.this, pwd, Toast.LENGTH_SHORT).show();
                if (pwd.equals(EnterStr)) {
                    Toast.makeText(LoginActivity.this, "Comfirmed", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setClassName("com.android.settings", "com.android.settings.Settings");
                    intent.setAction("android.intent.action.VIEW");
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, R.string.wrong, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_menu, menu);
        Log.d("Jursery", "do onCreateOptionsMenu");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onStart(){
        Log.d(TAG,"Do onStart!");
        super.onStart();
    }

    @Override
    protected void onResume(){
        clearEnterDatas();
        getLatestPWD();
        Log.d(TAG,"Do onResume!");
        super.onResume();
    }

    @Override
    protected void onRestart(){
        Log.d(TAG,"Do onRestart!");
        super.onRestart();
    }

    @Override
    protected void onPause(){
        Log.d(TAG,"Do onPause!");
        super.onPause();
    }

    @Override
    protected void onStop(){
        Log.d(TAG,"Do onStop!");
        super.onStop();
    }

    @Override
    protected  void onDestroy(){
        Log.d(TAG,"Do onDestroy!");
        super.onDestroy();
    }
}
