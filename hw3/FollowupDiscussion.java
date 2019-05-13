public class FollowupDiscussion extends Post{

    private User currentUser;
    private String text;
    private boolean resolved;
    private Response response;

    public FollowupDiscussion() {
        resolved = false;
    }

    public void update(User currentUser, String text) {
        this.currentUser = currentUser;
        this.text = text;
    }

    public void resolve(){
        resolved = true;
    }

    public String getText(){
        return text;
    }

    public User getAuthor(){
        return currentUser;
    }

    public boolean isResolved(){
        return resolved;
    }

    public int getResponseCount(){

        return super.getFollowupCount();
    }

    public int addResponse(Response response){

        this.response = response;
        return super.getCurrentViewID();
    }

    public Response getResponse(int id){


        return super.getResponse(id);
    }

    public Response getResponse(){
        return response;
    }

}
