public class User {
    private String name;
    private String password;
    private Type user;

    public enum Type{INSTRUCTOR,STUDENT}

    public User(String studentName, String password, Type eType) {
        this.name = studentName;
        this.password = password;
        user = eType;

    }

    public boolean isInstructor(){
        return user.equals(Type.INSTRUCTOR);
    }

    public boolean isStudent(){
        return user.equals(Type.STUDENT);
    }

    public boolean authenticate(String name, String pass){

        if(name.equals(this.name) && pass.equals(this.password)) return true;

        else return false;
    }




}
