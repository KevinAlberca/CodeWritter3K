package fr.supinternet.codewritter3k;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.main_activity_html_css_btn) public void goToHTMLAndCSS() {
        Intent editorIntent = new Intent(this, EditorActivity.class);
        editorIntent.putExtra("language", "HTML/CSS");
        startActivity(editorIntent);
    }
}
