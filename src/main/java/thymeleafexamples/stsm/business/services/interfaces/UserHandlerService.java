package thymeleafexamples.stsm.business.services.interfaces;

import thymeleafexamples.stsm.business.entities.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author vvujcic on 23/06/2015.
 */
public abstract class UserHandlerService {

    private Map<Long, UserSession> userSessions = new HashMap<Long, UserSession>();

    /**
     * Handles the new logged in user. The user will be retrieved or saved to the DB.
     * @param loggedInUser
     * @return
     */
    public final User handleUserOnLogin(User loggedInUser) {
        User user = retrieve(loggedInUser);
        if (user == null) {
            user = add(loggedInUser);
            user.setId(5l); // TODO: this needs to be coming from an atomic incrementer
        }
        // now construct the session for the user
        UserSession session = new UserSession(new Date(), user);
        userSessions.put(user.getId(), session);

        return user;
    }

    public abstract User add(User newUser);

    /**
     * Retrieve the actual user
     * @param existingUser
     * @return
     */
    public abstract User retrieve(User existingUser);

    public User getCurrentSessionUser(Long userId) {
        UserSession session = userSessions.get(userId);
        if (session == null) {
            throw new IllegalArgumentException("Unknown user id " + userId);
        }
        return session.getUser();
    }

    /**
     * Represents a very simple user session class
     */
    private class UserSession {

        private Date sessionStart;
        private User user;

        public UserSession(Date sessionStart, User user) {
            this.sessionStart = sessionStart;
            this.user = user;
        }

        public Date getSessionStart() {
            return sessionStart;
        }

        public void setSessionStart(Date sessionStart) {
            this.sessionStart = sessionStart;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }
}
