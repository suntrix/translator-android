package pl.webdesignstudio.translator;

import android.content.Context;
import android.widget.TextView;

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

//    public List<String> translateMany(List<String> texts, String language) {
//
//    }
//
//    public List<String> translateMany(List<String> texts) {
//        if ( null == this.translationsLanguageCode ) {
//            throw new RuntimeException("Translations default language code is not set! You need to set it, to use this method.");
//        }
//
//        return this.translateMany(texts, this.translationsLanguageCode);
//    }

    public void translateTextView(TextView textView, String language) {
        textView.setText(this.translate(textView.getText().toString(), language));
    }

    public void translateTextView(TextView textView) {
        textView.setText(this.translate(textView.getText().toString()));
    }
}
