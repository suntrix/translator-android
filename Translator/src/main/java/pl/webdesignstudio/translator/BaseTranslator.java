package pl.webdesignstudio.translator;

import android.content.Context;

import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import pl.webdesignstudio.util.JSONUtil;

/**
 * Created by Sebastian Owodziń on 7/29/13.
 * Copyright (c) 2013 WEB DESIGN STUDIO Sebastian Owodziń. All rights reserved.
 */

public abstract class BaseTranslator {

    protected static String translatorTranslationsFilenamePattern = "translations_%s";

    protected Context context = null;

    protected HashMap<String, HashMap<String, String>> translationsCache = new HashMap<String, HashMap<String, String>>();

    public String translationsLanguageCode = null;

    public void setTranslationsLanguageCodeFromLocale(Locale locale) {
        this.translationsLanguageCode = locale.getLanguage();
    }

    public void loadTranslations(List<String> languages) {
        for ( String language : languages ) {
            this.loadTranslationsIfNeeded(language);
        }
    }

    public void clearCache(Boolean all) {
        if ( all ) {
            this.translationsCache.clear();
        } else {
            Iterator<String> keys = this.translationsCache.keySet().iterator();
            while ( keys.hasNext() ) {
                String key = keys.next();
                if ( !key.equalsIgnoreCase(this.translationsLanguageCode) ) {
                    this.translationsCache.remove(key);
                }
            }
        }
    }

    protected void loadTranslationsIfNeeded(String language) {
        if ( !this.translationsCached(language) ) {
            this.loadTranslationsFromFile(language);
        }
    }

    private Boolean translationsCached(String language) {
        return this.translationsCache.containsKey(language);
    }

    private void loadTranslationsFromFile(String language) {
        String filename = String.format(this.translatorTranslationsFilenamePattern, language);

        int resourceId =  context.getResources().getIdentifier(filename, "raw", context.getPackageName());

        try {
            this.translationsCache.put(language, JSONUtil.jsonToHashMap(JSONUtil.getJsonString(context.getResources().openRawResource(resourceId))));
        } catch (JSONException e) {
            // TODO: add plist support
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(String.format("Translations file for language %s not found.", language));
        }
    }

}
