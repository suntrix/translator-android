package pl.webdesignstudio.exampleapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

import java.util.Arrays;

import pl.webdesignstudio.translator.Translator;

import static pl.webdesignstudio.exampleapp.R.*;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        Translator translator = Translator.getInstance(this);
        translator.translationsLanguageCode = "fr";
//        translator.loadTranslations(Arrays.asList("fr"));

        translator.translateTextView((TextView)findViewById(id.translatedTextView));
//
//        TextView translatedTextView = (TextView)findViewById(id.translatedTextView);
//        translatedTextView.setText(translator.translate(translatedTextView.getText().toString()));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
