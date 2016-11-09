package com.sample;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.sample.util.ApplicationUtils;
import com.softteco.toolset.i18n.AbstractI18nService;
import com.softteco.toolset.restlet.UserSession;

/**
 *
 */
@Singleton
public class I18nServiceBean extends AbstractI18nService {

    public static final String BUNDLE_MAIL = "mail";
    public static final String BUNDLE_MESSAGES = "messages";

    @Inject
    private UserSession userSession;

    @Override
    protected String[] getBundles() {
        return new String[]{BUNDLE_MAIL, BUNDLE_MESSAGES};
    }

    @Override
    protected String getPropertiesPath() {
        return ApplicationUtils.getAppPath() + "i18n";
    }

    @Override
    public String getMessage(final String bundle, final String key) {
        String lang = Constants.AVAILABLE_LANGS.contains(userSession.getLang()) ? userSession.getLang() : Constants.DEFAULT_LANG;
        return super.getMessage(lang, bundle, key);
    }
}
