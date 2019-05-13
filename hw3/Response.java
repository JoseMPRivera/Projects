public class Response {

    User lastAuthor;
    String text;
    boolean good;

    public Response(){
        good = false;
    }

    public Response( User lastAuthor, String text) {
        this.lastAuthor = lastAuthor;
        this.text = text;
        this.good = false;
    }

    public User getLastAuthor() {
        return lastAuthor;
    }

    public String getText() {
        return text;
    }

    public boolean isGood() {
        return good;
    }

    public void setGood(){
        good = true;
    }

    public User getAuthor(){
        return lastAuthor;
    }

    public void update(User user, String text){

        lastAuthor = user;
        this.text = text;
    }


}
