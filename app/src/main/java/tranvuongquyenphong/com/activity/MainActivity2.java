package tranvuongquyenphong.com.activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import tranvuongquyenphong.com.R;
import tranvuongquyenphong.com.adapter.VP_MainAdapter;
import tranvuongquyenphong.com.adapter.NewsAdapter;
import tranvuongquyenphong.com.model.News;
import tranvuongquyenphong.com.model.TheLoai;
import tranvuongquyenphong.com.model.Users;

public class MainActivity2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView ListTinTuc;
    public static ArrayList<News> ListNews;
    NewsAdapter adapter;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    ImageView imvUser;
    TextView txtNameUser, txtEmailUser;
    ArrayList<TheLoai> listTheLoai;
    Button nav_head_btnLogin;
    AccessToken accessToken = AccessToken.getCurrentAccessToken();
    int TrangThaiDangNhap;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listTheLoai = new ArrayList<>();
        TrangThaiDangNhap = 0;
//        getListTheLoai();
//        Toast.makeText(this, String.valueOf(listTheLoai.size()), Toast.LENGTH_SHORT).show();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

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
        addConTrolss();
        addEvents();
        setdataTinTuc();
    }
    private void setdataTinTuc() {
        News news1 = new News("1","Tinh Hinh The gioi", "123", "250","20/3/27","123","the gioi dang co nhung dien bien...","https://www.facebook.com/photo.php?fbid=1467375573375508&set=a.100888520024227.1537.100003092374700&type=3&theater");
        News news2 = new News("2","Hoang Sa truong sa ","444","213","12/3/2015","21","Hoang sa truong sa la cua viet nam","https://www.facebook.com/profile.php?id=100002856345630");
        News news3 = new News("3","Khong so cho","33","21","42/3/133","123","Tinh hinh trom cho hien nay dang dien bien phuc tap","https://scontent.fsgn5-4.fna.fbcdn.net/v/t1.0-9/13921020_2069406656618392_3435404849346958726_n.jpg?_nc_cat=0&oh=51631ab306af094e95af0ea8eaadcebe&oe=5BAD3010");
        News news4 = new News("1","Tinh Hinh The gioi", "123", "250","20/3/27","123","the gioi dang co nhung dien bien...","https://www.facebook.com/photo.php?fbid=1467375573375508&set=a.100888520024227.1537.100003092374700&type=3&theater");
        News news5 = new News("2","Hoang Sa truong sa ","444","213","12/3/2015","21","Hoang sa truong sa la cua viet nam","https://www.facebook.com/profile.php?id=100002856345630");
        News news6 = new News("3","Khong so cho","33","21","42/3/133","123","Tinh hinh trom cho hien nay dang dien bien phuc tap","https://scontent.fsgn5-4.fna.fbcdn.net/v/t1.0-9/13921020_2069406656618392_3435404849346958726_n.jpg?_nc_cat=0&oh=51631ab306af094e95af0ea8eaadcebe&oe=5BAD3010");
        News news7 = new News("1","Tinh Hinh The gioi", "123", "250","20/3/27","123","the gioi dang co nhung dien bien...","https://www.facebook.com/photo.php?fbid=1467375573375508&set=a.100888520024227.1537.100003092374700&type=3&theater");
        News news8 = new News("2","Hoang Sa truong sa ","444","213","12/3/2015","21","Hoang sa truong sa la cua viet nam","https://www.facebook.com/profile.php?id=100002856345630");
        News news9 = new News("3","Khong so cho","33","21","42/3/133","123","Tinh hinh trom cho hien nay dang dien bien phuc tap","https://scontent.fsgn5-4.fna.fbcdn.net/v/t1.0-9/13921020_2069406656618392_3435404849346958726_n.jpg?_nc_cat=0&oh=51631ab306af094e95af0ea8eaadcebe&oe=5BAD3010");
        News news10 = new News("1","Tinh Hinh The gioi", "123", "250","20/3/27","123","the gioi dang co nhung dien bien...","https://www.facebook.com/photo.php?fbid=1467375573375508&set=a.100888520024227.1537.100003092374700&type=3&theater");
        News news11 = new News("2","Hoang Sa truong sa ","444","213","12/3/2015","21","Hoang sa truong sa la cua viet nam","https://www.facebook.com/profile.php?id=100002856345630");
        News news12 = new News("3","Khong so cho","33","21","42/3/133","123","Tinh hinh trom cho hien nay dang dien bien phuc tap","https://scontent.fsgn5-4.fna.fbcdn.net/v/t1.0-9/13921020_2069406656618392_3435404849346958726_n.jpg?_nc_cat=0&oh=51631ab306af094e95af0ea8eaadcebe&oe=5BAD3010");
        ListNews.add(news1);
        ListNews.add(news2);
        ListNews.add(news3);
        ListNews.add(news4);
        ListNews.add(news5);
        ListNews.add(news6);
        ListNews.add(news7);
        ListNews.add(news8);
        ListNews.add(news9);
        ListNews.add(news10);
        ListNews.add(news11);
        ListNews.add(news12);



    }
    private void addEvents() {
        if (TrangThaiDangNhap == 0) {
            nav_head_btnLogin.setVisibility(View.VISIBLE);
        } else {
            nav_head_btnLogin.setVisibility(View.INVISIBLE);
        }
        nav_head_btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity2.this, Login_Activity.class));
            }
        });
    }

    private void addConTrols(NavigationView navigationView) {
        View hview = navigationView.inflateHeaderView(R.layout.nav_header_main2);
        imvUser = hview.findViewById(R.id.imvUser);
        //txtEmailUser = hview.findViewById(R.id.txtEmailUser);
        txtNameUser = hview.findViewById(R.id.txtNameUser);
        nav_head_btnLogin = hview.findViewById(R.id.nav_head_btnLogin);
        setDataUser();
    }

    public void getListTheLoai() {
        final ArrayList<TheLoai> list = new ArrayList<>();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        TheLoai theLoai = new TheLoai("123", "543", "123");
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
        // ListTinTuc = findViewById(R.id.list_tintuc);
        ListNews = new ArrayList<>();
        // adapter = new NewsAdapter(MainActivity2.this,R.layout.item_list_tintuc,ListNews);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new VP_MainAdapter(getSupportFragmentManager(), getApplicationContext()));
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
        });
        //getdata_tintuc();

    }

    private void setDataUser() {
        if (accessToken != null) {
            Users users = (Users) getIntent().getSerializableExtra("object_fb");
            String profilePicUrl = "https://graph.facebook.com/" + users.getId() + "/picture?type=large";
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
        if (id == R.id.mnu_search) {
            startActivity(new Intent(MainActivity2.this, SearchActivity.class));
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
            startActivity(new Intent(MainActivity2.this, MainActivity2.class));
        }
        if (id == R.id.nav_thongtincanhan) {
//            fragmentManager.beginTransaction()
//                    .replace(R.id.content_frame
//                            , new fm_ChiTietThongTin())
//                    .commit();

            startActivity(new Intent(MainActivity2.this, ThongTinCaNhanActivity.class));
        }
        if (id == R.id.nav_chonchuyenmuc) {
            startActivity(new Intent(MainActivity2.this, ChonChuyenMuc.class));
        }
        if (id == R.id.nav_setting) {
            startActivity(new Intent(MainActivity2.this, SettingsActivity2.class));
        }
        if (id == R.id.nav_history_new_save) {
            startActivity(new Intent(MainActivity2.this, HistoryRead_Activity.class));
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
