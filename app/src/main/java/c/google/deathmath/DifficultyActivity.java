package c.google.deathmath;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;

public class DifficultyActivity extends AppCompatActivity {

    GridLayout gr_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);

        gr_layout = findViewById(R.id.gr_layout);
        setGridElement(gr_layout);


    }

    public void setGridElement(GridLayout gr_element) {
        for (int i = 0;i<gr_element.getChildCount();i++){
            CardView cardView = (CardView)gr_element.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view) {
                    setIntent(finalI);
                }
            });
        }
    }
    public void setIntent(int value){
        Intent intent = new Intent(getApplicationContext(),GameActivity.class);
        if (value==0){
            intent.putExtra("result",0);
        }else if (value==1){
            intent.putExtra("result",1);
        }else if (value==2) {
            intent.putExtra("result", 2);
        }
        startActivity(intent);
    }
}
