package com.baculsoft.sample.realmmigration.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.baculsoft.sample.realmmigration.R;
import com.baculsoft.sample.realmmigration.model.User;
import com.baculsoft.sample.realmmigration.realm.UserData;

import io.realm.Realm;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class MainActivity extends AppCompatActivity {
    EditText etMainEmail;
    EditText etMainName;
    EditText etMainAge;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitle(getTitle());
        setSupportActionBar(toolbar);

        etMainEmail = (EditText) findViewById(R.id.et_main_email);
        etMainName = (EditText) findViewById(R.id.et_main_name);
        etMainAge = (EditText) findViewById(R.id.et_main_age);

        final Realm realm = Realm.getDefaultInstance();
        UserData data = realm.where(UserData.class).findFirst();

        if (null != data) {
            data = realm.copyFromRealm(data);

            final User user = new User();
            user.setEmail(data.getEmail());
            user.setName(data.getName());
            user.setAge(data.getAge());

            Toast.makeText(this, String.format("Email: %s, Name: %s and Age: %s", user.getEmail(), user.getName(), user.getAge()), Toast.LENGTH_LONG).show();
        }

        Button button = (Button) findViewById(R.id.btn_main);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final String email = etMainEmail.getText().toString();
                final String name = etMainName.getText().toString();
                final int age = Integer.parseInt(etMainAge.getText().toString());

                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(name)) {
                    final User user = new User();
                    user.setEmail(email);
                    user.setName(name);
                    user.setAge(age);

                    final Realm realm = Realm.getDefaultInstance();
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(final Realm realm) {
                            final UserData userData = new UserData();
                            userData.fill(user);

                            realm.insertOrUpdate(userData);
                        }
                    });

                    realm.close();

                    Toast.makeText(MainActivity.this, "Success save data...", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}