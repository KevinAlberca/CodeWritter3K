package fr.supinternet.codewritter3k;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;

import butterknife.BindView;

/**
 * Created by AwH on 19/06/16.
 */
public class EditorActivity extends AppCompatActivity {

    public String language;
    @BindView(R.id.editText)
    EditText userCode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editor_layout);

        this.language = getIntent().getExtras().getString("language");
        Log.d("LANGUAGE", this.language);
    }
}
