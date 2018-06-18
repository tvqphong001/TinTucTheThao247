package tranvuongquyenphong.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import tranvuongquyenphong.com.R;
import tranvuongquyenphong.com.model.News;
import tranvuongquyenphong.com.model.Users;

public class Login_Activity extends AppCompatActivity {
    EditText username,password;
    Button btnLogin;
    CallbackManager callbackManager;
    LoginButton btnLoginButton;
    private DatabaseReference mDatabase;
    public static LoginResult login_Result;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //if differen null go to main

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if (accessToken!=null)
        {
            GraphRequest request = GraphRequest.newMeRequest(
                    accessToken,
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject object, GraphResponse response) {
                            Log.d("Json",response.getJSONObject().toString());
                            Users users = null;
                            try {
                                String email = object.getString("email");
                                String birthday = object.getString("birthday");
                                String name = object.getString("name");
                                String id = object.getString("id");
                                users = new Users(email,birthday,name,id);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Intent intent = new Intent(Login_Activity.this,MainActivity2.class);
                            intent.putExtra("object_fb",users);
                            startActivity(intent);
                        }
                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "email,birthday,name,id");
            request.setParameters(parameters);
            request.executeAsync();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);

        //
        addControls();
        addEvents();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);


        callbackManager = CallbackManager.Factory.create();


        // If using in a fragment
//        btnLoginButton.setFragment(this);
        btnLoginButton.setReadPermissions(Arrays.asList("public_profile","email","user_birthday"));

        btnLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.d("Json",response.getJSONObject().toString());
                        Users users = null;
                        try {
                            String email = object.getString("email");
                            String birthday = object.getString("birthday");
                            String name = object.getString("name");
                            String id = object.getString("id");
                            users = new Users(email,birthday,name,id);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Intent intent = new Intent(Login_Activity.this,MainActivity.class);
                        intent.putExtra("object_fb",users);
                        startActivity(intent);
                    }
                });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "email,birthday,name,id");
            request.setParameters(parameters);
            request.executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
        //setdataTinTuc();

    }

    private void addEvents() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get data user
                Toast.makeText(Login_Activity.this, "DangNhap", Toast.LENGTH_SHORT).show();
                mDatabase.child("User").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        Users users = dataSnapshot.getValue(Users.class);
                        if (username.getText().toString().equals(users.getUsername())&&password.getText().toString().equals(users.getPassword()))
                        {
                            Intent intent = new Intent(Login_Activity.this,MainActivity2.class);
                            startActivity(intent);
                        }
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
        });
    }

    private void addControls() {
        btnLoginButton = (LoginButton) findViewById(R.id.facebook_login);
        password = findViewById(R.id.password);
        username = findViewById(R.id.username);
        btnLogin = findViewById(R.id.btnlogin);

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
        mDatabase.child("News").push().setValue(news1);
        mDatabase.child("News").push().setValue(news2);
        mDatabase.child("News").push().setValue(news3);
        mDatabase.child("News").push().setValue(news4);
        mDatabase.child("News").push().setValue(news5);
        mDatabase.child("News").push().setValue(news6);
        mDatabase.child("News").push().setValue(news7);
        mDatabase.child("News").push().setValue(news8);
        mDatabase.child("News").push().setValue(news9);
        mDatabase.child("News").push().setValue(news10);
        mDatabase.child("News").push().setValue(news11);
        mDatabase.child("News").push().setValue(news12);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
