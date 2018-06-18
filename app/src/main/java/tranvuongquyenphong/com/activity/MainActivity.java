package tranvuongquyenphong.com.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.login.widget.ProfilePictureView;

import tranvuongquyenphong.com.R;
import tranvuongquyenphong.com.model.Users;

public class MainActivity extends AppCompatActivity {

    ProfilePictureView profilePictureView;
    Button ListTinTuc;
    TextView idfb,name,email,birthday;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        getInformation();
        ListTinTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity2.class));
            }
        });
    }

    private void addControls() {
        profilePictureView = findViewById(R.id.profile);
        idfb = findViewById(R.id.idfb);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        birthday = findViewById(R.id.birthday);
        ListTinTuc = findViewById(R.id.goto_main);

    }

    private void getInformation() {
        Users users = (Users) getIntent().getSerializableExtra("object_fb");
        profilePictureView.setProfileId(users.getId());
        idfb.setText(users.getId());
        name.setText(users.getName());
        email.setText(users.email);
        birthday.setText(users.getBirthday());
    }
}
