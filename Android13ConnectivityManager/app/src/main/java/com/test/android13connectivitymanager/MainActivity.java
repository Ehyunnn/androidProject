package com.test.android13connectivitymanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView networkState;   //네트웍 상태표시용
    private EditText networkInfo;   //네트웍 정보표시용
    private ConnectivityManager connMgr; //시스템서비스를 얻어서 연결
    private NetworkInfo[] niArray;   //// 사용가능한 네트워크 파악용
    private String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        networkState = findViewById(R.id.TextView01);
        networkInfo = findViewById(R.id.EditText01);

        str = "";
        connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        niArray = connMgr.getAllNetworkInfo();
        for (NetworkInfo n : niArray) {
            str = str.concat(n.toString() + "\n\n");
        }
        networkInfo.setText(str);
    }//end onCreate()

    @Override
    protected void onStart() {
        super.onStart();
        //앱이 종료되지 않은 상태일때에도 네트워크 상태를 체크할 수 있도록
        //onStart()에 정의
        NetworkInfo ni = connMgr.getActiveNetworkInfo();

        if (ni != null) {
            if (connMgr.getActiveNetworkInfo().isAvailable()) {
                networkState.setText("무선네트워크 연결됨");
                String type = ni.getTypeName();
                Log.i("testLog", type);//WIFI,Wifi,wifi
                if (type.equalsIgnoreCase("mobile")) {
                    networkState.setText(
                            "현재 연결 네트워크  : 3G/4G/LTE무선 데이터 망" + "(" + type + ")");
                } else if (type.equalsIgnoreCase("wifi")) {
                    networkState.setText("현재 연결 네트워크  : " + type);
                }
                //사용중인 네트워크 정보 EditText로 출력
                str += ("getActiveNetworkInfo : \n"
                        + ni.toString() + "\n");
                networkInfo.setText(str + ">>>"
                        + ni.getState().toString());
            }
        }else{
            networkState.setText("무선네트워크 연결 실패");
        }
    }
}//end class
