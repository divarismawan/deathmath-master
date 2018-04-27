package c.google.deathmath;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.TimerTask;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class GameActivity extends AppCompatActivity {

    Random random = new Random();
    int pointA, pointB;
    int pointC, pointD;
    int randMath;
    int result;
    int newResult;
    int point = 0;
    int value;

    TextView tv_math;
    TextView tv_result;
    TextView tv_point;
    TextView tv_pointA;
    TextView tv_pointB;
    TextView tv_timer;


    Button btn_zero;
    Button btn_one;
    Button btn_two;
    Button btn_three;
    Button btn_four;
    Button btn_five;
    Button btn_six;
    Button btn_seven;
    Button btn_eight;
    Button btn_nine;
    Button btn_submit;
    Button btn_change;
    Button btn_del;
    Button btn_reset;
    CountDownTimer restart;
    boolean timerrunning = FALSE;
    long sisawaktuMilisecond = 15000;//15 detik
    CountDownTimer myTimer;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent myIntent = getIntent();
        value = myIntent.getIntExtra("result",0 );


        tv_math   = (TextView)findViewById(R.id.tv_math);
        tv_result = (TextView)findViewById(R.id.tv_result);
        tv_pointA = (TextView)findViewById(R.id.tv_pointA);
        tv_pointB = (TextView)findViewById(R.id.tv_pointB);
        tv_point  = (TextView)findViewById(R.id.tv_point);
        tv_timer  = (TextView) findViewById(R.id.tv_timer);

        btn_zero   = (Button)findViewById(R.id.btn_zero);
        btn_one    = (Button)findViewById(R.id.btn_one);
        btn_two    = (Button)findViewById(R.id.btn_two);
        btn_three  = (Button)findViewById(R.id.btn_three);
        btn_four   = (Button)findViewById(R.id.btn_four);
        btn_five   = (Button)findViewById(R.id.btn_five);
        btn_six    = (Button)findViewById(R.id.btn_six);
        btn_seven  = (Button)findViewById(R.id.btn_seven);
        btn_eight  = (Button)findViewById(R.id.btn_eight);
        btn_nine   = (Button)findViewById(R.id.btn_nine);
        btn_submit = (Button)findViewById(R.id.btn_submit);
        btn_change = (Button)findViewById(R.id.btn_change);
        btn_del    = (Button)findViewById(R.id.btn_del);
        btn_reset  = (Button)findViewById(R.id.btn_reset);
        setValue(btn_zero,0);
        setValue(btn_one,1);
        setValue(btn_two,2);
        setValue(btn_three,3);
        setValue(btn_four,4);
        setValue(btn_five,5);
        setValue(btn_six,6);
        setValue(btn_seven,7);
        setValue(btn_eight,8);
        setValue(btn_nine,9);
        tv_point.setText("Point : "+point);

        getDataIntent();
        setBtn_change();
        setBtn_reset();
        setBtn_del();

       restarttimer(0);
    }
    public void getDataIntent(){
//        startTimer();
        Intent myIntent = getIntent();
        value = myIntent.getIntExtra("result", 0);
        int setValue;
        if (value==0){
            setValue = diffEasy();
            valButton(String.valueOf(setValue));
        }else if (value==1){
            setValue = diffNormal();
            valButton(String.valueOf(setValue));
        }else if (value==2){
            setValue = diffHard();
            valButton(String.valueOf(setValue));
        }
    }

    public int diffEasy(){
        pointA = random.nextInt(25); // random pertama
        pointB = random.nextInt(15); // random kedua
        pointC = random.nextInt(15); // random ketiga
        pointC = random.nextInt(10); // random keempat
        newResult = mathType(pointA,pointB,pointC,pointD);
        return newResult;
       }
    public int diffNormal(){
        pointA = random.nextInt(100)+20; // random pertama
        pointB = random.nextInt(100)+20; // random kedua
        pointC = random.nextInt(20);
        pointD = random.nextInt(15);
        newResult = mathType(pointA,pointB,pointC,pointD);
        return newResult;
    }
    public int diffHard(){
        pointA = random.nextInt(250)+30; // random pertama
        pointB = random.nextInt(250)+30; // random kedua
        pointC = random.nextInt(25);
        pointD = random.nextInt(20);
        newResult = mathType(pointA,pointB,pointC,pointD);
        return newResult;
    }
    public int mathType(int pointA, int pointB, int pointC, int pointD){
        randMath = random.nextInt(3); //random jenis perhitungan
        if (randMath==0){
            tv_math.setText("x");
            tv_pointA.setText(""+pointC);
            tv_pointB.setText(""+pointD);
            result = pointC*pointD;
        }else if (randMath==1){
            tv_math.setText("+");
            tv_pointA.setText(""+pointA);
            tv_pointB.setText(""+pointB);
            result = pointA+pointB;
        }else  if (randMath==2){
            tv_math.setText("-");
            tv_pointA.setText(""+pointA);
            tv_pointB.setText(""+pointB);
            result = pointA-pointB;
        }
        return result;
    }

    public void setValue(Button button, final int val){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_result.setText(tv_result.getText()+""+val);
            }
        });
    }

    public void valButton(final String result){
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tv_result.getText().equals(result)){
                    String myval = tv_timer.getText().toString();
                    int timevalue = Integer.parseInt(myval);
                    point +=timevalue;
                    tv_point.setText("Point : "+point);
                    myTimer.cancel();
                    restarttimer(5000);
                    tv_result.setText("");
                    getDataIntent();
                }else {
                    Toast.makeText(GameActivity.this, "Jawaban Salah",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void setBtn_change(){
        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String st = tv_result.getText().toString();
                String newVal;

                if(st.isEmpty()){
                    tv_result.setText("-");
                }else{
                    if(st.substring(0,1).equals("-")){
                        newVal = st.replace("-","");
                        tv_result.setText(""+newVal);
                    }else if (!st.equals("")){
                        tv_result.setText("-"+st);
                    }
                }
            }
        });
    }

    public void setBtn_reset(){
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDataIntent();
                tv_result.setText("");
            }
        });
    }

    public void setBtn_del(){
        if (tv_result.equals("")){
            btn_del.setEnabled(false);
        }else {
            btn_del.setEnabled(true);
            btn_del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String str = tv_result.getText().toString();
                    if (!str.equals("")) {
                        String newValue = str.substring(0, str.length() - 1);
                        tv_result.setText("" + newValue);
                    }
                }
            });
        }
    }


    public void restarttimer(int addtime){
        myTimer = new CountDownTimer(sisawaktuMilisecond+addtime,1000){
            public void onTick(long l) {
                sisawaktuMilisecond = l;
                updateTimer();
            }
            public void onFinish() {
                String displayPoint;
                //tv_timer.setText("0");
                btn_submit.setEnabled(false);
                displayPoint = String.valueOf(point);
                Intent intent = new Intent(getApplicationContext(), PopUpActivity.class);
                intent.putExtra("point", displayPoint);
                if (value==0){
                    intent.putExtra("result",0);
                }else if (value==1){
                    intent.putExtra("result",1);
                }else if (value==2) {
                    intent.putExtra("result", 2);
                }
                startActivity(intent);
                Toast.makeText(GameActivity.this, "Waktu Habis", Toast.LENGTH_SHORT).show();
                sisawaktuMilisecond = 15000;
            }
        }.start();
        timerrunning = true;
    }

    public void updateTimer(){
        int second = (int) (sisawaktuMilisecond /1000);
        tv_timer.setText("" +second);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(GameActivity.this, PopUpBackActivity.class);
        startActivity(intent);

    }

}
