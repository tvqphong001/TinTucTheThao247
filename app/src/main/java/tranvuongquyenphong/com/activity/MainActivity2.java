package tranvuongquyenphong.com.activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tranvuongquyenphong.com.R;
import tranvuongquyenphong.com.adapter.VP_MainAdapter;
import tranvuongquyenphong.com.adapter.NewsAdapter;
import tranvuongquyenphong.com.model.News;
import tranvuongquyenphong.com.model.TheLoai;
import tranvuongquyenphong.com.model.TinTuc;
import tranvuongquyenphong.com.model.Users;
import tranvuongquyenphong.com.util.FormatHelper;
import tranvuongquyenphong.com.util.GetDataBase;
import tranvuongquyenphong.com.util.XMLDOMParser;

public class MainActivity2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static ArrayList<News> ListNews;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    ImageView imvUser;
    TextView txtNameUser;
    public static ArrayList<TheLoai> listTheLoai;
    Button nav_head_btnLogin;
    int TrangThaiDangNhap;
    TabLayout tabLayout;
    ViewPager viewPager;
    public static ArrayList<TinTuc> listTrangChu;
    public static ArrayList<TinTuc> listThoiSu;
    public static ArrayList<TinTuc> listGiaiTri;
    public static ArrayList<TinTuc> listGiaoDuc;
    public static ArrayList<TinTuc> listTheThao;
    public static ArrayList<TinTuc> listSucKhoe;
    public static ArrayList<TinTuc> listCongNghe;
    public static ArrayList<TinTuc> listVideo;
    ArrayList<TinTuc> lisTrungGian;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        addConTrolss();

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
//        View hview = navigationView.inflateHeaderView(R.layout.nav_header_main2);
//        nav_head_btnLogin = hview.findViewById(R.id.nav_head_btnLogin);
//        imvUser = hview.findViewById(R.id.imvUser);
//        txtNameUser = hview.findViewById(R.id.txtNameUser);

        setViewPager();
        addEvents();
    }

    private void setViewPager() {
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
    }

    public ArrayList<TinTuc> setDataApp(String TheLoai) {
        lisTrungGian.clear();
        for (int i = 0; i < listTheLoai.size(); i++) {
            if (listTheLoai.get(i).getTenLoai().equals(TheLoai)) {
                if (listTheLoai.get(i).getNguontin().equals("Thanh Niên")) {
                    final String link = listTheLoai.get(i).getLink();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            new ReadDataThanhNien().execute(link);
                        }
                    });
                }
                if (listTheLoai.get(i).getNguontin().equals("VnExpress")) {
                    final String link = listTheLoai.get(i).getLink();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            new ReadDataVNEpress().execute(link);
                        }
                    });
                }
                if (listTheLoai.get(i).getNguontin().equals("VietNamNet")) {
                    final String link = listTheLoai.get(i).getLink();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            new ReadDataVietNamNet().execute(link);
                        }
                    });
                }
            }
        }
        return lisTrungGian;
    }

    private void setDataTheLoai() {

        TheLoai theLoai = new TheLoai("001", "Trang Chủ", "https://vnexpress.net/rss/tin-moi-nhat.rss", "VnExpress");
        TheLoai theLoai1 = new TheLoai("002", "Trang Chủ", "https://thanhnien.vn/rss/home.rss", "Thanh Niên");
        TheLoai theLoai2 = new TheLoai("003", "Trang Chủ", "http://vietnamnet.vn/rss/home.rss", "VietNamNet");

        TheLoai theLoai3 = new TheLoai("004", "Thời Sự", "https://vnexpress.net/rss/thoi-su.rss", "VnExpress");
        TheLoai theLoai4 = new TheLoai("005", "Thời Sự", "https://thanhnien.vn/rss/viet-nam.rss", "Thanh Niên");
        TheLoai theLoai5 = new TheLoai("006", "Thời Sự", "http://vietnamnet.vn/rss/thoi-su.rss", "VietNamNet");

        TheLoai theLoai6 = new TheLoai("007", "Giải Trí", "https://vnexpress.net/rss/giai-tri.rss", "VnExpress");
        TheLoai theLoai7 = new TheLoai("008", "Giải Trí", "https://game.thanhnien.vn/rss/home.rss", "Thanh Niên");
        TheLoai theLoai8 = new TheLoai("009", "Giải Trí", "http://vietnamnet.vn/rss/giai-tri.rss", "VietNamNet");

        TheLoai theLoai9 = new TheLoai("010", "Giáo Dục", "https://vnexpress.net/rss/giao-duc.rss", "VnExpress");
        TheLoai theLoai10 = new TheLoai("011", "Giáo Dục", "https://thanhnien.vn/rss/giao-duc.rss", "Thanh Niên");
        TheLoai theLoai11 = new TheLoai("012", "Giáo Dục", "http://vietnamnet.vn/rss/giao-duc.rss", "VietNamNet");

        TheLoai theLoai12 = new TheLoai("012", "Thể Thao", "https://vnexpress.net/rss/the-thao.rss", "VnExpress");
        TheLoai theLoai13 = new TheLoai("013", "Thể Thao", "https://thethao.thanhnien.vn/rss/home.rss", "Thanh Niên");
        TheLoai theLoai14 = new TheLoai("014", "Thể Thao", "http://vietnamnet.vn/rss/the-thao.rss", "VietNamNet");

        TheLoai theLoai15 = new TheLoai("015", "Sức Khỏe", "https://vnexpress.net/rss/suc-khoe.rss", "VnExpress");
        TheLoai theLoai16 = new TheLoai("016", "Sức Khỏe", "https://thanhnien.vn/rss/doi-song/suc-khoe.rss", "Thanh Niên");
        TheLoai theLoai17 = new TheLoai("017", "Sức Khỏe", "http://vietnamnet.vn/rss/suc-khoe.rss", "VietNamNet");

        TheLoai theLoai18 = new TheLoai("018", "Công Nghệ", "https://vnexpress.net/rss/so-hoa.rss", "VnExpress");
        TheLoai theLoai19 = new TheLoai("019", "Công Nghệ", "https://thanhnien.vn/rss/cong-nghe-thong-tin.rss", "Thanh Niên");
        TheLoai theLoai20 = new TheLoai("020", "Công Nghệ", "http://vietnamnet.vn/rss/cong-nghe.rss", "VietNamNet");

        TheLoai theLoai21 = new TheLoai("021", "Video", "https://video.thanhnien.vn/rss/home.rss", "Thanh Niên");
        TheLoai theLoai22 = new TheLoai("022", "Video", "https://xe.thanhnien.vn/rss/clip.rss", "Thanh Niên");
        //TheLoai theLoai23 = new TheLoai("023","Video","http://vietnamnet.vn/rss/suc-khoe.rss","VietNamNet");
        listTheLoai.add(theLoai);
        listTheLoai.add(theLoai1);
        listTheLoai.add(theLoai2);
        listTheLoai.add(theLoai3);
        listTheLoai.add(theLoai4);
        listTheLoai.add(theLoai5);
        listTheLoai.add(theLoai6);
        listTheLoai.add(theLoai7);
        listTheLoai.add(theLoai8);
        listTheLoai.add(theLoai9);
        listTheLoai.add(theLoai10);
        listTheLoai.add(theLoai11);
        listTheLoai.add(theLoai12);
        listTheLoai.add(theLoai13);
        listTheLoai.add(theLoai14);
        listTheLoai.add(theLoai15);
        listTheLoai.add(theLoai16);
        listTheLoai.add(theLoai17);
        listTheLoai.add(theLoai18);
        listTheLoai.add(theLoai19);
        listTheLoai.add(theLoai20);
        listTheLoai.add(theLoai21);
        listTheLoai.add(theLoai22);

    }

    public void setDataTab() {


        String p1 = "Trang Chủ";
        String p2 = "Thời Sự";
        String p3 = "Giải Trí";
        String p4 = "Giáo Dục";
        String p5 = "Thể Thao";
        String p6 = "Sức Khỏe";
        String p7 = "Công Nghệ";
        String p8 = "Video";
        VP_MainAdapter.listFragment = new ArrayList<>();
        VP_MainAdapter.listFragment.add(p1);
        VP_MainAdapter.listFragment.add(p2);
        VP_MainAdapter.listFragment.add(p3);
        VP_MainAdapter.listFragment.add(p4);
        VP_MainAdapter.listFragment.add(p5);
        VP_MainAdapter.listFragment.add(p6);
        VP_MainAdapter.listFragment.add(p7);
        VP_MainAdapter.listFragment.add(p8);
    }


    private void addEvents() {
//        if (TrangThaiDangNhap == 0) {
//            nav_head_btnLogin.setVisibility(View.VISIBLE);
//        } else {
//            nav_head_btnLogin.setVisibility(View.INVISIBLE);
//        }
//        nav_head_btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity2.this, Login_Activity.class));
//            }
//        });
//        final SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.layout_Refresh);
//        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,R.color.colorPrimaryDark,R.color.colorAccent);
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                swipeRefreshLayout.setRefreshing(true);
//                (new Handler()).postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        swipeRefreshLayout.setRefreshing(false);
//                        setDataTrangChu();
//                        setViewPager();
//                    }
//                },300);
//            }
//        });
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
        //TheLoai theLoai = new TheLoai("123", "543", "123");
        //mDatabase.child("TheLoai").push().setValue(theLoai);
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
        setDataUser();
        listTheLoai = new ArrayList<>();

        lisTrungGian = new ArrayList<>();

        listTrangChu = new ArrayList<>();
        listThoiSu = new ArrayList<>();
        listGiaiTri = new ArrayList<>();
        listGiaoDuc = new ArrayList<>();
        listTheThao = new ArrayList<>();
        listSucKhoe = new ArrayList<>();
        listCongNghe = new ArrayList<>();
        listVideo = new ArrayList<>();

        TrangThaiDangNhap = 0;
        setDataTab();
        setDataTheLoai();
        listTrangChu = setDataApp("Trang Chủ");
        listThoiSu = setDataApp("Thời Sự");
        listGiaiTri = setDataApp("Giải Trí");
        listGiaoDuc = setDataApp("Giáo Dục");
        listTheThao= setDataApp("Thể Thao");
        listSucKhoe = setDataApp("Sức Khỏe");
        listCongNghe = setDataApp("Công Nghệ");
        listVideo = setDataApp("Video");

    }

    private void setDataUser() {
        Intent intent = getIntent();
        TrangThaiDangNhap = intent.getIntExtra("Trangthaidangnhap", 0);
        if (TrangThaiDangNhap == 1) {
            Users users = (Users) getIntent().getSerializableExtra("object_fb");
            String profilePicUrl = "https://graph.facebook.com/" + users.getId() + "/picture?type=large";
            Picasso.get().load(profilePicUrl).into(imvUser);
            txtNameUser.setText(users.getName());
            //txtEmailUser.setText(users.getEmail());
            //Toast.makeText(this, users.getEmail() + users.getName(), Toast.LENGTH_SHORT).show();
        }
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

    class ReadDataThanhNien extends AsyncTask<String, Integer, String> {

        protected String doInBackground(String... strings) {
            return docNoiDung_Tu_URL(strings[0]);
        }

        // vn express
        protected void onPostExecute(String s) {
            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);
            NodeList nodeList = document.getElementsByTagName("item");
            NodeList nodeList1Desciption = document.getElementsByTagName("description");
            NodeList nodeListTitle = document.getElementsByTagName("title");
            // NodeList nodeListLink = document.getElementsByTagName("link");
            String title = "";
            String link = "";
            String hinhanh = "";
            String NguonBao = nodeListTitle.item(1).getTextContent();
            String Ngay = "";
            for (int i = 0; i < nodeList.getLength(); i++) {
                String cdata = nodeList1Desciption.item(i + 1).getTextContent();
                int flag = 0;
//                Element line = (Element) nodeList1Desciption.item(i+1);
//                String cdata = getCharacterDataFromElement(line);
                Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Pattern p1 = Pattern.compile("<img[^>]+data-original\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Matcher matcher = p.matcher(cdata);
                Matcher matcher1 = p1.matcher(cdata);
                //Matcher matcher = Pattern.compile("<img src=\"([^\"]+)").matcher(getCharacterDataFromElement(line));

                if (matcher1.find()) {
                    hinhanh = matcher1.group(1);
                    flag = 1;

                }
                if (matcher.find() && flag == 0) {

                    hinhanh = matcher.group(1);
                    //Log.d("hinhanh",hinhanh + ".........." +i);
                }

                Element element = (Element) nodeList.item(i);
                title = nodeListTitle.item(i + 2).getTextContent();
                link = parser.getValue(element, "link");
                Ngay = parser.getValue(element, "pubDate");

//                try {
//                    NgayDang = FormatHelper.formatPubDate(Ngay.trim());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                Date NgayDang = FormatHelper.parseDate(Ngay);

                //Toast.makeText(MainActivity.this, title, Toast.LENGTH_SHORT).show();
                TinTuc tinTuc = new TinTuc(title, link, hinhanh, NguonBao, NgayDang);
                lisTrungGian.add(tinTuc);

            }
            super.onPostExecute(s);

        }
    }

    private String docNoiDung_Tu_URL(String theUrl) {
        StringBuilder content = new StringBuilder();
        try {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    class ReadDataVNEpress extends AsyncTask<String, Integer, String> {

        protected String doInBackground(String... strings) {
            return docNoiDung_Tu_URL(strings[0]);
        }

        // vn express
        protected void onPostExecute(String s) {
            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);
            NodeList nodeList = document.getElementsByTagName("item");
            NodeList nodeList1Desciption = document.getElementsByTagName("description");
            String title = "";
            String link = "";
            String hinhanh = "";
            String nguonBao = "VnExpress";
            for (int i = 0; i < nodeList.getLength(); i++) {
                String cdata = nodeList1Desciption.item(i + 1).getTextContent();
                int flag = 0;
                Element line = (Element) nodeList1Desciption.item(i + 1);
                //String cdata = getCharacterDataFromElement(line);
                Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Pattern p1 = Pattern.compile("<img[^>]+data-original\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Matcher matcher = p.matcher(cdata);
                Matcher matcher1 = p1.matcher(cdata);
                //Matcher matcher = Pattern.compile("<img src=\"([^\"]+)").matcher(getCharacterDataFromElement(line));

                if (matcher1.find()) {
                    hinhanh = matcher1.group(1);
                    flag = 1;

                }
                if (matcher.find() && flag == 0) {

                    hinhanh = matcher.group(1);
                    //Log.d("hinhanh",hinhanh + ".........." +i);
                }

                Element element = (Element) nodeList.item(i);
                title = parser.getValue(element, "title");
                link = parser.getValue(element, "link");
                TinTuc tinTuc = new TinTuc(title, link, hinhanh, nguonBao, null);
                lisTrungGian.add(tinTuc);
                //Toast.makeText(MainActivity.this, title, Toast.LENGTH_SHORT).show();
//                TinTuc tinTuc = new TinTuc(title,link,hinhanh);
//                listtin.add(docBao);

            }
            super.onPostExecute(s);

        }
    }

    class ReadDataVietNamNet extends AsyncTask<String, Integer, String> {

        protected String doInBackground(String... strings) {
            return docNoiDung_Tu_URL(strings[0]);
        }

        // vn express
        protected void onPostExecute(String s) {
            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);
            NodeList nodeList = document.getElementsByTagName("item");
            NodeList nodeList1Desciption = document.getElementsByTagName("description");
            String title = "";
            String link = "";
            String hinhanh = "";
            String nguonbao = "Vietnamnet";
            for (int i = 0; i < nodeList.getLength(); i++) {
                String cdata = nodeList1Desciption.item(i + 1).getTextContent();
                int flag = 0;
                Element line = (Element) nodeList1Desciption.item(i + 1);
                Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Pattern p1 = Pattern.compile("<img[^>]+data-original\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Matcher matcher = p.matcher(cdata);
                Matcher matcher1 = p1.matcher(cdata);
                //Matcher matcher = Pattern.compile("<img src=\"([^\"]+)").matcher(getCharacterDataFromElement(line));

                if (matcher1.find()) {
                    hinhanh = matcher1.group(1);
                    flag = 1;

                }
                if (matcher.find() && flag == 0) {

                    hinhanh = matcher.group(1);
                    //Log.d("hinhanh",hinhanh + ".........." +i);
                }

                Element element = (Element) nodeList.item(i);
                title = parser.getValue(element, "title");
                link = parser.getValue(element, "link");
                TinTuc tinTuc = new TinTuc(title, link, hinhanh, nguonbao, null);
                lisTrungGian.add(tinTuc);

            }
            super.onPostExecute(s);

        }
    }
}
