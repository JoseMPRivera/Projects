import java.util.ArrayList;

public class Post {

    private ArrayList<FollowupDiscussion> discussions;
    private Response post;
    private int currentViewID;
    private Response instructorResponse;
    private Response studentResponse;
    private int views;

    Post(){
        discussions = new ArrayList<>();
        instructorResponse = new Response();
        studentResponse = new Response();
        views = 0;

    }

    public int add(FollowupDiscussion discussion){

        discussions.add(discussion);
        return discussions.size();
    }

    public int getFollowupCount(){
        return discussions.size();
    }

    public FollowupDiscussion getFollowup(int id){

        currentViewID = id;
        return discussions.get(id);
    }

    public void update(User user, String text) {

        views++;
        post = new Response(user, text);

    }

    public void setGood(){
        post.setGood();
    }

    public boolean isGood() {
        return post.isGood();
    }


    public Response getInstructorResponse(){

        return instructorResponse;

    }

    public Response getStudentResponse(){

        return studentResponse;

    }

    public String getText(){

        return post.getText();
    }

    public User getLastAuthor(){

        return post.getLastAuthor();
    }

    public int getCurrentViewID(){
        return currentViewID;
    }

    public Response getResponse(int id){

        return discussions.get(id).getResponse();
    }

    public int view(User user){

        return views;
    }

}
