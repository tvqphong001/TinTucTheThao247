package tranvuongquyenphong.com.activity;

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
    public static ArrayList<TheLoai> listTheLoai = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listTheLoai = GetDataBase.getListTheLoai();
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
        addConTrols();
    }

    private void addConTrols() {
        ListTinTuc = findViewById(R.id.list_tintuc);
        ListNews = new ArrayList<>();
        adapter = new NewsAdapter(MainActivity2.this,R.layout.item_list_tintuc,ListNews);
        getdata_tintuc();
        imvUser = findViewById(R.id.imvUser);
        txtEmailUser = findViewById(R.id.txtNameUser);
        txtNameUser = findViewById(R.id.txtNameUser);
        setDataUser();
    }

    private void setDataUser() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken !=null)
        {
//            GraphRequest request = GraphRequest.newMeRequest(
//                    accessToken,
//                    new GraphRequest.GraphJSONObjectCallback() {
//                        @Override
//                        public void onCompleted(JSONObject object, GraphResponse response) {
//                            Users users = null;
//                            try {
//                                String email = object.getString("email");
//                                String birthday = object.getString("birthday");
//                                String name = object.getString("name");
//                                String id = object.getString("id");
//                                users = new Users(email,birthday,name,id);
//                                String profilePicUrl = "https://graph.facebook.com/" + users.getId() +"/picture?type=large";
//                                Picasso.get().load(profilePicUrl).into(imvUser);
//                                txtNameUser.setText(name);
//                                txtEmailUser.setText(email);
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    });
//            Bundle parameters = new Bundle();
//            parameters.putString("fields", "email,birthday,name,id");
//            request.setParameters(parameters);
//            request.executeAsync();
            Users users = (Users) getIntent().getSerializableExtra("object_fb");
//            String profilePicUrl = "https://graph.facebook.com/" + users.getId() +"/picture?type=large";
//            Picasso.get().load(profilePicUrl).into(imvUser);
//            txtNameUser.setText(users.getName());
//            txtEmailUser.setText(users.getEmail());
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
