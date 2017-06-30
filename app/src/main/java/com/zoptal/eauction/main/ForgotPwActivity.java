package com.zoptal.eauction.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.zoptal.eauction.R;

public class ForgotPwActivity extends AppCompatActivity {

    private Button btn_cncl;
    private TextView tv_terms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pw);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        btn_cncl=(Button)findViewById(R.id.btn_cncl);

        tv_terms=(TextView)findViewById(R.id.tv_terms);

        SpannableString terms = new SpannableString("Terms of Use & Privacy Policy");
        terms.setSpan(new UnderlineSpan(), 0, terms.length(), 0);
        tv_terms.setText(terms);

        btn_cncl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intnt=new Intent(ForgotPwActivity.this,LoginActivity.class);
                startActivity(intnt);

            }
        });

        tv_terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent terms=new Intent(ForgotPwActivity.this,TermsActivity.class);
                startActivity(terms);
            }
        });
    }
}
