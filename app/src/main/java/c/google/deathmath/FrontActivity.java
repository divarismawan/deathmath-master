package c.google.deathmath;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FrontActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);
    }

    public void btn_start(View view) {
        Intent intent = new Intent(getApplicationContext(),DifficultyActivity.class);
        startActivity(intent);
    }
}
