package pl.webdesignstudio.exampleapp;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

import pl.webdesignstudio.translator.Translator;

import static pl.webdesignstudio.exampleapp.R.*;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void frButtonTouched(View view) {
        Translator translator = Translator.getInstance(this);
        translator.translationsLanguageCode = "fr";

        Intent intent = new Intent(this, ExampleUITranslationsActivity.class);
        startActivity(intent);
    }

    public void plButtonTouched(View view) {
        Translator translator = Translator.getInstance(this);
        translator.translationsLanguageCode = "pl";

        Intent intent = new Intent(this, ExampleUITranslationsActivity.class);
        startActivity(intent);
    }
    
}
