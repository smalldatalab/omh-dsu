package io.smalldata.ohmageomh.dsu.service;

import io.smalldata.ohmageomh.dsu.domain.Study;
import org.openmhealth.dsu.domain.EndUser;

import java.util.List;
import java.util.Optional;

/**
 * Service for Study-Participant management.
 * Created by Cheng-Kang Hsieh on 3/25/15.
 */

public interface StudyService {
    /**
     * Get the Study object by its name
     * @param studyName  the study name
     * @return A optional Study object
     */
    Optional<Study> getStudyByName(String studyName);

    /**
     * Get a list of the studies that this user is a participant of.
     * @param username The username from the DSU.
     * @return A list of Study objects.  List is empty if user is not a participant in any studies.
     */
    List<Study> getStudiesByUsername(String username);

    /**
     * Check if a user already belongs to a study
     * @param user  the EndUser object of the current user
     * @param study the study object
     * @return if the user has already enrolled the study
     */
    boolean isUserEnrolled(EndUser user, Study study);

    /**
     * Get the user's participant ID that is specific for this study.
     * This id will help the study coordinator to identify the user in the study
     * @param user  the EndUser object of the current user
     * @param study the study object
     * @return if the user has already enrolled the study
     */
    Optional<String> getParticipantId(EndUser user, Study study);

    /**
     * Enroll a user to a study, or do nothing if the user has already enrolled.
     * @param user  the EndUser object of the current user
     * @param study the study object
     */
    void enrollUser(EndUser user, Study study);
}
