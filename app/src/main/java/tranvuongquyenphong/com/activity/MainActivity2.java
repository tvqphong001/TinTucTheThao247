package tranvuongquyenphong.com.activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import tranvuongquyenphong.com.R;
import tranvuongquyenphong.com.adapter.NewsAdapter;
import tranvuongquyenphong.com.fragment.fm_ChiTietThongTin;
import tranvuongquyenphong.com.fragment.fm_Home;
import tranvuongquyenphong.com.model.News;
import tranvuongquyenphong.com.model.TheLoai;
import tranvuongquyenphong.com.model.Users;
import tranvuongquyenphong.com.util.GetDataBase;

public class MainActivity2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView ListTinTuc;
    ArrayList<News> ListNews;
    NewsAdapter adapter;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    ImageView imvUser;
    TextView txtNameUser,txtEmailUser;
    ArrayList<TheLoai> listTheLoai;
    Button nav_head_btnLogin;
    AccessToken accessToken = AccessToken.getCurrentAccessToken();
    int TrangThaiDangNhap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listTheLoai = new ArrayList<>();
        TrangThaiDangNhap =0;
//        getListTheLoai();
//        Toast.makeText(this, String.valueOf(listTheLoai.size()), Toast.LENGTH_SHORT).show();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //addConTrols(navigationView);
        View hview = navigationView.inflateHeaderView(R.layout.nav_header_main2);
        nav_head_btnLogin = hview.findViewById(R.id.nav_head_btnLogin);
        addEvents();
    }

    private void addEvents() {
        if (TrangThaiDangNhap==0)
        {
            nav_head_btnLogin.setVisibility(View.INVISIBLE);
        }
    }

    private void addConTrols(NavigationView navigationView) {
        View hview = navigationView.inflateHeaderView(R.layout.nav_header_main2);
        imvUser = hview.findViewById(R.id.imvUser);
        //txtEmailUser = hview.findViewById(R.id.txtEmailUser);
        txtNameUser = hview.findViewById(R.id.txtNameUser);
        nav_head_btnLogin = hview.findViewById(R.id.nav_head_btnLogin);
        setDataUser();
    }

    public void getListTheLoai()
    {
        final ArrayList<TheLoai> list = new ArrayList<>();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        TheLoai theLoai = new TheLoai("123","543","123");
        mDatabase.child("TheLoai").push().setValue(theLoai);
        mDatabase.child("TheLoai").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                TheLoai theLoai = dataSnapshot.getValue(TheLoai.class);
                listTheLoai.add(theLoai);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void addConTrolss() {
        ListTinTuc = findViewById(R.id.list_tintuc);
        ListNews = new ArrayList<>();
        adapter = new NewsAdapter(MainActivity2.this,R.layout.item_list_tintuc,ListNews);
        //getdata_tintuc();

    }

    private void setDataUser() {
        if (accessToken !=null)
        {
            Users users = (Users) getIntent().getSerializableExtra("object_fb");
            String profilePicUrl = "https://graph.facebook.com/" + users.getId() +"/picture?type=large";
            Picasso.get().load(profilePicUrl).into(imvUser);
            txtNameUser.setText(users.getName());
            txtEmailUser.setText(users.getEmail());
            Toast.makeText(this, users.getEmail() + users.getName(), Toast.LENGTH_SHORT).show();
        }
    }

    private void getdata_tintuc() {
        mDatabase.child("News").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                News news = dataSnapshot.getValue(News.class);
                ListNews.add(news);
                ListTinTuc.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();
        if (id == R.id.nav_Home) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new fm_Home())
                    .commit();
        }
        if (id == R.id.nav_thongtincanhan) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new fm_ChiTietThongTin())
                    .commit();
        }
        if (id == R.id.nav_chonchuyenmuc) {
            startActivity(new Intent(MainActivity2.this,ChonChuyenMuc.class));
        }
        if (id == R.id.nav_setting) {
            startActivity(new Intent(MainActivity2.this,SettingActivity.class));
        }
        if (id == R.id.nav_history_new_save) {
            startActivity(new Intent(MainActivity2.this,HistoryRead_Activity.class));
        }
        if (id == R.id.nav_logout) {

        }
//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
