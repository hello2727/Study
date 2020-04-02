package org.techtown.optionmenu;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();
        //타이틀 텍스트가 사라지고 로고 이미지 보임
        actionBar.setLogo(R.drawable.home);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME|ActionBar.DISPLAY_USE_LOGO);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //메뉴 처음 설정 함수
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //메뉴 아이템 선택 되었을때 호출
        int curId = item.getItemId();

        switch (curId) {
            case R.id.menu_refresh:
                showToast("새로고침 메뉴 선택됨");
                break;
            case R.id.menu_search:
                showToast("검색 메뉴 선택됨");
                break;
            case R.id.menu_settings:
                showToast("설정 메뉴 선택됨");
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showToast(String data) {
        Toast.makeText(this, data, Toast.LENGTH_LONG).show();
    }
}
