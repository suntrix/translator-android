package pl.webdesignstudio.exampleapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

import pl.webdesignstudio.translator.Translator;

public class ExampleUITranslationsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exampleuitranslations);

        Translator translator = Translator.getInstance(this);

        translator.translateTextView((TextView)findViewById(R.id.translatedTextView));

        Button translatedButton = (Button)findViewById(R.id.translatedButton);
        translatedButton.setText(translator.translate(translatedButton.getText().toString()));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
