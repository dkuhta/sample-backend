package com.sample;

import com.google.inject.Singleton;
import com.sample.util.ApplicationUtils;
import com.softteco.toolset.i18n.AbstractI18nService;

/**
 *
 */
@Singleton
public class I18nServiceBean extends AbstractI18nService {

    public static final String DEFAULT_LANG = "en";
    public static final String[] AVAILABLE_LANGS = new String[]{"en"};

    public static final String BUNDLE_MAIL = "mail";
    public static final String BUNDLE_MESSAGES = "messages";

    @Override
    protected String[] getBundles() {
        return new String[]{BUNDLE_MAIL, BUNDLE_MESSAGES};
    }

    @Override
    protected String getPropertiesPath() {
        return ApplicationUtils.getAppPath() + "i18n";
    }
}
