package fr.supinternet.codewritter3k;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by AwH on 19/06/16.
 */
public class EditorActivity extends AppCompatActivity {

    public String language;
    @BindView(R.id.editor_area)
    EditText userCode;
    @BindView(R.id.lines_area)
    TextView lineArea;
    private Integer lines = 1;
    CharSequence lastText = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editor_layout);
        ButterKnife.bind(this);

        lineArea.setText("1");

        this.language = getIntent().getExtras().getString("language");
        Log.d("LANGUAGE", this.language);


        userCode.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                lines = userCode.getLineCount();
                String numberOfLines = "";
                for (Integer i = 1; i <= lines; i++) {
                    numberOfLines += i + "\n";
                }
                lineArea.setText(numberOfLines);

                /*if (s != lastText) {
                    System.out.println(s);

                    lastText = s;
                    //userCode.setText(lastText);
                }*/

            }
        });
    }

    @OnClick(R.id.editor_save_btn)
    public void saveFile() {
        SaveActivity sA = new SaveActivity();
    }
}
