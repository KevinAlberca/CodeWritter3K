package fr.supinternet.codewritter3k;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

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
    String lastText = "";
    int cursorPosition = 0;
    Map<String, Integer> types = new HashMap<String, Integer>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editor_layout);
        ButterKnife.bind(this);

        lineArea.setText("1");

        this.language = getIntent().getExtras().getString("language");
        Log.d("LANGUAGE", this.language);

        types.put("normal", Color.WHITE);
        types.put("balise", Color.YELLOW);
        types.put("text", Color.RED);
        types.put("css", Color.GRAY);
        types.put("color", Color.CYAN);

        userCode.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {

                if (!s.toString().equals(lastText)) {
                    changeLastText(s);
                    cursorPosition = userCode.getSelectionStart();
                    colorText(userCode, lastText);
                    userCode.setSelection(cursorPosition);
                }

            }

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
            }
        });
    }

    private void changeLastText(CharSequence c) {
        lastText = c.toString();
    }

    @OnClick(R.id.editor_save_btn)
    public void saveFile() {
        SaveActivity sA = new SaveActivity();
    }

    private void colorText(EditText editor, String text) {

        Integer startOfSpan = 0;
        Integer endOfSpan = 0;
        Boolean isNotNormal = false;
        String type = "normal";
        Character endOfString = ' ';
        Integer endOfQuote = 0;
        SpannableString spanText = new SpannableString(text);

        for (Integer i = 0; i < text.length(); i++) {
            Character character = text.charAt(i);

            switch (character) {
                case '<':
                    type = "balise";
                    endOfString = '>';
                    startOfSpan = i;
                    isNotNormal = true;
                    break;
                case '"':
                    if (!i.equals(endOfQuote)) {
                        System.out.println("ok");
                        type = "text";
                        isNotNormal = true;
                        startOfSpan = i;
                        endOfString = '"';
                    }
                    break;
                case '{':
                    type = "css";
                    isNotNormal = true;
                    startOfSpan = i;
                    endOfString = '}';
                    break;
                case '#':
                    type = "color";
                    isNotNormal = true;
                    startOfSpan = i;
                    endOfString = ';';
                    break;
                case 'v':
                    if (text.substring(i, i+4).equals("var ")) {
                        spanText.setSpan(new ForegroundColorSpan(Color.GREEN), i, i+3, 0);
                    }
                    break;
                case 'f':
                    if (text.substring(i, i+9).equals("function ")) {
                        spanText.setSpan(new ForegroundColorSpan(Color.MAGENTA), i, i+9, 0);
                    }
                    break;
            }

            if (isNotNormal) {
                endOfSpan = findNext(i + 1, text, endOfString);
                if (endOfString == '"') endOfQuote = endOfSpan - 1;
                spanText.setSpan(new ForegroundColorSpan(types.get(type)), startOfSpan, endOfSpan, 0);
                isNotNormal = false;
                type = "normal";
            }
        }

        userCode.setText(spanText, TextView.BufferType.SPANNABLE);
    }

    private Integer findNext(Integer start, String text, Character endOfString) {

        for (Integer i = start; i < text.length(); i++) {
            Character charact = text.charAt(i);
            if (charact == endOfString) {
                return i + 1;
            }
        }

        return text.length();
    }
}
