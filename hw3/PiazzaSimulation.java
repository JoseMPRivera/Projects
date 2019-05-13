import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A simulation of a backend for the Piazza discussion software.
 */
public class PiazzaSimulation
{
   private List<User> users = new ArrayList<>();
   private List<Post> posts = new ArrayList<>();
   private User currentUser;

   /**
    * Starts the simulation
    * @param args ignored
    */
   public static void main(String[] args)
   {
      PiazzaSimulation simulation = new PiazzaSimulation();
      simulation.users.add(new User("horstmann", "secret", User.Type.INSTRUCTOR));
      simulation.run(new Scanner(System.in));

   }

   /**
    * Sign up a student.
    * @param request the incoming request
    * @return the reply
    */
   public Reply signup(Request request)
   {
      String username = request.get("username");
      String password = request.get("password");
      users.add(new User(username, password, User.Type.STUDENT));
      return new Reply();
   }

   /**
    * Log in a user.
    * @param request the incoming request
    * @return the reply
    */
   public Reply login(Request request)
   {
      String username = request.get("username");
      String password = request.get("password");
      for (User u : users)
      {
         if (u.authenticate(username, password)) currentUser = u;
      }
      if (currentUser == null)
         return new Reply("Authentication failed");
      else
         return new Reply();
   }

   /**
    * Add a new post.
    * @param request the incoming request
    * @return the reply
    */
   private Reply newpost(Request request)
   {
      Reply reply;
      int id = posts.size();
      Post p = new Post();
      p.update(currentUser, request.get("text"));
      posts.add(p);
      reply = new Reply("id", id);
      return reply;
   }
   
   /**
    * Edit an existing post.
    * @param request the incoming request
    * @return the reply
    */
   private Reply editpost(Request request)
   {      
      Reply reply;
      int id = request.getInt("id");
      if (0 <= id && id < posts.size())
      {
         posts.get(id).update(currentUser, request.get("text"));
         reply = new Reply();
      }
      else
         reply = new Reply("Invalid ID " + id);
      return reply;
   }

   /**
    * Mark a post, student response, or instructor response as good.
    * @param request the incoming request
    * @return the reply
    */
   private Reply good(Request request)
   {
      Reply reply;
      int id = request.getInt("id");
      if (0 <= id && id < posts.size())
      {
         reply = new Reply();
         Post p = posts.get(id);
         String type = request.get("type");
         if (type.equals("post")) p.setGood();
         else if (type.equals("instructorresponse")) p.getInstructorResponse().setGood();
         else if (type.equals("studentresponse")) p.getStudentResponse().setGood();
         else reply = new Reply("Illegal type " + type); 
      }
      else
         reply = new Reply("Invalid ID " + id);
      return reply;
   }

   /**
    * Get information about an existing post.
    * @param request the incoming request
    * @return the reply
    */
   private Reply getpost(Request request)
   {
      Reply reply;
      int id = request.getInt("id");
      if (0 <= id && id < posts.size())
      {
         Post p = posts.get(id);
         
         reply = new Reply("text", p.getText(), 
               "good", p.isGood(),
               "lastauthor", p.getLastAuthor(),
               "views", p.view(currentUser));
      }
      else
         reply = new Reply("Invalid ID " + id);
      return reply;
   }

   /**
    * Respond to a post.
    * @param request the incoming request
    * @return the reply
    */
   private Reply addresponse(Request request)
   {
      Reply reply;
      int id = request.getInt("id");
      if (0 <= id && id < posts.size())
      {
         Post p = posts.get(request.getInt("id"));
         if (currentUser.isInstructor())
         {
            p.getInstructorResponse().update(currentUser, request.get("text"));
         }
         else
         {
            p.getStudentResponse().update(currentUser, request.get("text"));
         }               
         reply = new Reply();
      }
      else
         reply = new Reply("Invalid ID " + id);
      return reply;
   }

   /**
    * Get information about a response.
    * @param request the incoming request
    * @return the reply
    */
   private Reply getresponse(Request request)
   {
      Reply reply;
      int id = request.getInt("id");
      if (0 <= id && id < posts.size())
      {
         Post p = posts.get(request.getInt("id"));
         Response response = null;
         String type = request.get("type"); 
         if (type.equals("instructor"))         
            response = p.getInstructorResponse();                     
         else if (type.equals("student"))
            response = p.getStudentResponse();
         if (response == null)
            reply = new Reply("Invalid type " + type);
         else
            reply = new Reply("text", response.getText(), 
               "good", response.isGood(),
               "lastauthor", response.getLastAuthor());
      }
      else
         reply = new Reply("Invalid ID " + id);
      return reply;
   }

   /**
    * Start a followup discussion.
    * @param request the incoming request
    * @return the reply
    */
   private Reply followup(Request request)
   {
      Reply reply;
      int id = request.getInt("id");
      if (0 <= id && id < posts.size())
      {
         Post p = posts.get(id);
         FollowupDiscussion followup = new FollowupDiscussion();
         followup.update(currentUser, request.get("text"));
         int followupid = p.add(followup);
         reply = new Reply("followupid", followupid); 
      }            
      else
         reply = new Reply("Invalid ID " + id);
      return reply;
   }

   /**
    * Mark a followup discussion as resolved.
    * @param request the incoming request
    * @return the reply
    */
   private Reply resolve(Request request)
   {
      Reply reply;
      int id = request.getInt("id");
      if (0 <= id && id < posts.size())
      {
         Post p = posts.get(id);
         int followupid = request.getInt("followupid");
         if (0 <= followupid && id < p.getFollowupCount())
         {
            p.getFollowup(followupid).resolve();
            reply = new Reply();
         }
         else
            reply = new Reply("Invalid followup ID " + followupid);                           
      }            
      else
         reply = new Reply("Invalid ID " + id);
      return reply;
   }

   /**
    * Get information about a followup discussion.
    * @param request the incoming request
    * @return the reply
    */
   private Reply getfollowup(Request request)
   {
      Reply reply;
      int id = request.getInt("id");
      if (0 <= id && id < posts.size())
      {
         Post p = posts.get(id);
         int followupid = request.getInt("followupid");
         if (0 <= followupid && followupid < p.getFollowupCount())
         {
            FollowupDiscussion followup = p.getFollowup(followupid);
            reply = new Reply("text", followup.getText(), 
                  "author", followup.getAuthor(),
                  "resolved", followup.isResolved());            
         }
         else
            reply = new Reply("Invalid followup ID " + id + " " + followupid);
      }            
      else
         reply = new Reply("Invalid ID " + id);
      return reply;
   }

   /**
    * Add a reply in a followup discussion.
    * @param request the incoming request
    * @return the reply
    */
   private Reply followupresponse(Request request)
   {
      Reply reply;
      int id = request.getInt("id");
      if (0 <= id && id < posts.size())
      {
         Post p = posts.get(id);
         int followupid = request.getInt("followupid");
         if (0 <= followupid && id < p.getFollowupCount())
         {
            Response r = new Response(currentUser, request.get("text"));
            int followupresponseid = p.getFollowup(followupid).addResponse(r);
            reply = new Reply("followupresponseid", followupresponseid);
         }
         else
            reply = new Reply("Invalid followup ID " + id + " " + followupid);                           
      }
      else
         reply = new Reply("Invalid ID " + id);
      return reply;
   }

   /**
    * Get information about a reply in a followup discussion.
    * @param request the incoming request
    * @return the reply
    */   
   private Reply getfollowupresponse(Request request)
   {
      Reply reply;
      int id = request.getInt("id");
      if (0 <= id && id < posts.size())
      {
         Post p = posts.get(id);
         int followupid = request.getInt("followupid");
         if (0 <= followupid && id < p.getFollowupCount())
         {
            FollowupDiscussion followup = p.getFollowup(followupid);
            int followupresponseid = request.getInt("followupresponseid");
            if (0 <= followupresponseid && followupresponseid < followup.getResponseCount()) {               
               Response r = followup.getResponse(followupresponseid);
               reply = new Reply("text", r.getText(), "author", r.getAuthor());
            }
            else 
               reply = new Reply("Invalid followup reply ID " + id + " " + followupid + " " + followupresponseid);
         }
         else
            reply = new Reply("Invalid followup ID " + id + " " + followupid);                           
      }
      else
         reply = new Reply("Invalid ID " + id);
      return reply;
   }
   
   /**
    * Runs the simulation.
    * @param in the scanner from which to read input
    */
   public void run(Scanner in)
   {
      while (in.hasNextLine())
      {
         Request request = Request.read(in);
         Reply reply;
         try
         {
            if (request.getCommand().equals("signup"))
            {
               reply = signup(request); 
            }
            else if (request.getCommand().equals("login"))
            {
               reply = login(request);
            }
            else if (request.getCommand().equals("newpost"))
            {
               reply = newpost(request);
            }
            else if (request.getCommand().equals("editpost"))
            {
               reply = editpost(request);
            }
            else if (request.getCommand().equals("getpost"))
            {
               reply = getpost(request);            
            }
            else if (request.getCommand().equals("response"))
            {
               reply = addresponse(request);            
            }
            else if (request.getCommand().equals("good"))
            {
               reply = good(request);
            }
            else if (request.getCommand().equals("getresponse"))
            {
               reply = getresponse(request);            
            }
            else if (request.getCommand().equals("followup"))
            {
               reply = followup(request);            
            }
            else if (request.getCommand().equals("resolve"))
            {
               reply = resolve(request);                        
            }
            else if (request.getCommand().equals("getfollowup"))
            {
               reply = getfollowup(request);
            }
            else if (request.getCommand().equals("followupresponse"))
            {
               reply = followupresponse(request);                        
            }
            else if (request.getCommand().equals("getfollowupresponse"))
            {
               reply = getfollowupresponse(request);                                    
            }
            else
            {
               reply = new Reply("Unknown command: " + request.getCommand());
            }
         }
         catch (Exception ex)
         {
            reply = new Reply(ex.getMessage());
         }
         System.out.println(reply);
      }
   }
}
