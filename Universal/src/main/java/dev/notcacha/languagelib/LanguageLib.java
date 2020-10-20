package dev.notcacha.languagelib;

import dev.notcacha.languagelib.i18n.I18n;
import dev.notcacha.languagelib.loader.FileLoader;
import dev.notcacha.languagelib.manageable.FileManageable;
import dev.notcacha.languagelib.managers.TranslationManager;

public interface LanguageLib {

    /**
     * @return instance from {@link TranslationManager} from get new {@link dev.notcacha.languagelib.message.TranslatableMessage}
     */

    TranslationManager getTranslationManager();

    /**
     * @return instance from {@link FileManageable} from manageable files
     */

    FileManageable getFileManageable();

    /**
     * @return instance from {@link FileLoader} from load files
     */

    FileLoader getFileLoader();

    /**
     * @return an instance {@link I18n} to access that class
     */

    I18n getI18n();

    interface Builder {

        /**
         * Set {@link TranslationManager} instance from use
         *
         * @param translationManager has been set
         */

        Builder setTranslationManager(TranslationManager translationManager);

        /**
         * Set {@link FileManageable} instance from use
         *
         * @param filesManageable has been set
         */

        Builder setFilesManageable(FileManageable filesManageable);

        /**
         * Set {@link FileLoader} instance from use
         *
         * @param fileLoader has been set
         */

        Builder setFileLoader(FileLoader fileLoader);

        /**
         * Set {@link I18n} instance from use
         *
         * @param i18n has been set
         */

        Builder setI18n(I18n i18n);

        /**
         * @return new {@link LanguageLib} with all features assigned!
         */

        LanguageLib build();

    }

}
