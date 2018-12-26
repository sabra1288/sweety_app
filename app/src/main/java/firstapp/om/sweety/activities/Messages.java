package firstapp.om.sweety.activities;

public class Messages { //this class will hold all the needed info to render a single message.

    private String text; // message body
    private MemberData data; // data of the user that sent this message
    private boolean belongsToCurrentUser; // is this message sent by us?

    public Messages(String text, MemberData data, boolean belongsToCurrentUser) {
        this.text = text;
        this.data = data;
        this.belongsToCurrentUser = belongsToCurrentUser;
    }

    public String getText() {
        return this.text;
    }

    public MemberData getData() {
        return this.data;
    }

    public boolean isBelongsToCurrentUser() {
        return this.belongsToCurrentUser;
    }
}
