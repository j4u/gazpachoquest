package net.sf.gazpachoquest.dto;

import net.sf.gazpachoquest.dto.support.LanguageSettingsContainerBuilder;
import net.sf.gazpachoquest.dto.support.LanguageSettingsDTO;

public class QuestionLanguageSettingsDTO implements LanguageSettingsDTO {
    private static final long serialVersionUID = 7670525018631065390L;

    private String title;

    public QuestionLanguageSettingsDTO() {
        super();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public static class BuilderImpl<C extends LanguageSettingsContainerBuilder<C>> {

        private String title;

        private final C container;

        public BuilderImpl(C builderImpl) {
            super();
            this.container = builderImpl;
        }

        public QuestionLanguageSettingsDTO build() {
            QuestionLanguageSettingsDTO questionLanguageSettingsDTO = new QuestionLanguageSettingsDTO();
            questionLanguageSettingsDTO.title = title;
            return questionLanguageSettingsDTO;
        }

        public C languageSettingsEnd() {
            return container.languageSettings(build());
        }

        public BuilderImpl<C> title(final String title) {
            this.title = title;
            return this;
        }
    }

    @Override
    public String toString() {
        return "QuestionLanguageSettingsDTO [title=" + title + "]";
    }

}