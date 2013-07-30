package pl.webdesignstudio.translator;

import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sebastian Owodziń on 7/29/13.
 * Copyright (c) 2013 WEB DESIGN STUDIO Sebastian Owodziń. All rights reserved.
 */

public class Translator extends BaseTranslator {

    private Translator(Context context) { this.context = context; }
    private static volatile Translator instance = null;

    public static Translator getInstance(Context context) {
        if (instance == null) {
            synchronized (Translator.class) {
                if (instance == null) {
                    instance = new Translator(context);
                }
            }
        }

        return instance;
    }

    public String translate(String text, String language) {
        this.loadTranslationsIfNeeded(language);

        String translation = text;
        if ( this.translationsCache.containsKey(language) ) {
            if ( this.translationsCache.get(language).containsKey(text) ) {
                translation = this.translationsCache.get(language).get(text);
            }
        }

        return translation;
    }

    public String translate(String text) {
        if ( null == this.translationsLanguageCode ) {
            throw new RuntimeException("Translations default language code is not set! You need to set it, to use this method.");
        }

        return this.translate(text, this.translationsLanguageCode);
    }

    public ArrayList<String> translateMany(ArrayList<String> texts, String language) {
        ArrayList<String> translations = new ArrayList<String>();

        if ( this.translationsCache.containsKey(language) ) {
            for ( String text : texts ) {
                if ( this.translationsCache.get(language).containsKey(text) ) {
                    translations.add(this.translationsCache.get(language).get(text));
                } else {
                    translations.add(text);
                }
            }
        }

        return translations;
    }

    public ArrayList<String> translateMany(ArrayList<String> texts) {
        if ( null == this.translationsLanguageCode ) {
            throw new RuntimeException("Translations default language code is not set! You need to set it, to use this method.");
        }

        return this.translateMany(texts, this.translationsLanguageCode);
    }

    public void translateTextView(TextView textView, String language) {
        textView.setText(this.translate(textView.getText().toString(), language));
    }

    public void translateTextView(TextView textView) {
        this.translateTextView(textView, this.translationsLanguageCode);
    }

    public void translateTextViews(ArrayList<TextView> textViews, String language) {
        ArrayList<String> texts = new ArrayList<String>();

        for ( TextView textView : textViews ) {
            texts.add(textView.getText().toString());
        }

        ArrayList<String> translatedTexts = this.translateMany(texts, language);

        int index = 0;
        for ( TextView textView : textViews ) {
            textView.setText(translatedTexts.get(index));
            index++;
        }
    }

    public void translateTextViews(ArrayList<TextView> textViews) {
        this.translateTextViews(textViews, this.translationsLanguageCode);
    }

    public void translateButton(Button button, String language) {

    }

    public void translateButton(Button button) {

    }

    public void translateButtons(ArrayList<Button> buttons, String language) {

    }

    public void translateButtons(ArrayList<Button> buttons) {

    }

}
