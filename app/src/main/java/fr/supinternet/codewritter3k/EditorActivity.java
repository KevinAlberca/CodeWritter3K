package fr.supinternet.codewritter3k;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import butterknife.BindView;

/**
 * Created by AwH on 19/06/16.
 */
public class EditorActivity extends AppCompatActivity {

    public String language;
    @BindView(R.id.editor_area)
    EditText userCode;
    private Integer lines = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editor_layout);

        this.language = getIntent().getExtras().getString("language");
        Log.d("LANGUAGE", this.language);

        userCode = (EditText)findViewById(R.id.editor_area);

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

            }
        });
    }
}
