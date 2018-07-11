package tranvuongquyenphong.com.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import tranvuongquyenphong.com.R;

public class ChonChuyenMuc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_chuyen_muc);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle(getResources().getString(R.string.app_name));
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        String abc = "abc"
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
