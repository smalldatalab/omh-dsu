package io.smalldata.ohmageomh.surveys.domain.survey.prompt;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import name.jenkins.paul.john.concordia.schema.Schema;
import name.jenkins.paul.john.concordia.schema.StringSchema;
import io.smalldata.ohmageomh.surveys.domain.exception.InvalidArgumentException;
import io.smalldata.ohmageomh.surveys.domain.survey.condition.Condition;
import org.springframework.data.annotation.PersistenceConstructor;

import java.util.List;

/**
 * <p>
 * A prompt for the user to make one choice among a list of choices where the
 * response will always be a string.
 * </p>
 *
 * @author John Jenkins
 */
public class StringSingleChoicePrompt extends SingleChoicePrompt<String> {
    /**
     * The string type of this survey item.
     */
    public static final String SURVEY_ITEM_TYPE =
        "string_single_choice_prompt";

    /**
     * Creates a new single-choice prompt whose choices and response should be
     * a string.
     *
     * @param surveyItemId
     *        The survey-unique identifier for this prompt.
     *
     * @param condition
     *        The condition on whether or not to show this prompt.
     *
     * @param displayType
     *        The display type to use to visualize the prompt.
     *
     * @param text
     *        The text to display to the user.
     *
     * @param displayLabel
     *        The text to use as a short name in visualizations.
     *
     * @param skippable
     *        Whether or not this prompt may be skipped.
     *
     * @param defaultResponse
     *        The default response for this prompt or null if a default is not
     *        allowed.
     *
     * @param choice_list
     *        The list of choices.
     *
     * @throws InvalidArgumentException
     *         A parameter was invalid.
     */
    @JsonCreator
    @PersistenceConstructor
    public StringSingleChoicePrompt(
        @JsonProperty(JSON_KEY_SURVEY_ITEM_ID) final String surveyItemId,
        @JsonProperty(JSON_KEY_CONDITION) final Condition condition,
        @JsonProperty(JSON_KEY_DISPLAY_TYPE) final DisplayType displayType,
        @JsonProperty(JSON_KEY_TEXT) final String text,
        @JsonProperty(JSON_KEY_DISPLAY_LABEL) final String displayLabel,
        @JsonProperty(JSON_KEY_SKIPPABLE) final boolean skippable,
        @JsonProperty(JSON_KEY_DEFAULT_RESPONSE) final String defaultResponse,
        @JsonProperty(JSON_KEY_CHOICES)
            final List<? extends Choice<? extends String>> choiceList)
        throws InvalidArgumentException {

        super(
            surveyItemId,
            condition,
            displayType,
            text,
            displayLabel,
            skippable,
            defaultResponse,
                choiceList);

        if(!
            (
                DisplayType.LIST.equals(displayType) ||
                DisplayType.SLIDER.equals(displayType))) {

            throw
                new InvalidArgumentException(
                    "The display type '" +
                        displayType.toString() +
                        "' is not valid for the prompt, which must be '" +
                        DisplayType.LIST.toString() +
                        "' or '" +
                        DisplayType.SLIDER.toString() +
                        "': " +
                        getSurveyItemId());
        }
    }

    /*
     * (non-Javadoc)
     * @see io.smalldata.ohmageomh.surveys.domain.survey.Respondable#getResponseSchema()
     */
    @Override
    public Schema getResponseSchema() {
        return
            new StringSchema(
                getText(),
                (skippable() || (getCondition() != null)),
                getSurveyItemId());
    }
}