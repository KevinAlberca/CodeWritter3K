package fr.supinternet.codewritter3k;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.main_css_btn)
    Button css_btn;
    @BindView(R.id.main_js_button)
    Button js_btn;
    @BindView(R.id.main_php_btn)
    Button php_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        css_btn.setEnabled(false);
        js_btn.setEnabled(false);
        php_btn.setEnabled(false);
    }

    @OnClick(R.id.main_html_btn) public void goToHTMLAndCSS() {
        Intent editorIntent = new Intent(this, EditorActivity.class);
        editorIntent.putExtra("language", "HTML");
        startActivity(editorIntent);
    }
}
