package net.sf.gazpachosurvey.domain.core;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("Q")
public class BrowsedQuestion extends BrowsedElement {

    private static final long serialVersionUID = 4401342736300174568L;

    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;

    public BrowsedQuestion() {
        super();
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public static Builder with() {
        return new Builder();
    }
    public static class Builder {
        private Integer id;
        
        private Question question;

        private Respondent respondent;

        public Builder respondent(Respondent respondent) {
            this.respondent = respondent;
            return this;
        }
        public Builder id(Integer id) {
            this.id = id;
            return this;
        }
        public Builder question(Question question) {
            this.question = question;
            return this;
        }

        public BrowsedQuestion build() {
            BrowsedQuestion browsedQuestion = new BrowsedQuestion();
            browsedQuestion.question = question;
            browsedQuestion.respondent = respondent;
            browsedQuestion.setId(id);
            return browsedQuestion;
        }
    }
}
