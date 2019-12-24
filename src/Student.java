public class Student implements Student_ {
    String fname;
    String lname;
    String hostel;
    String department;
    String cgpa;
    Student(Pair<String, String > pair,String hostel, String department, String cgpa){
        this.fname=pair.fname;
        this.lname=pair.lname;
        this.hostel=hostel;
        this.department=department;
        this.cgpa=cgpa;

    }
    public String fname(){
        return fname;
    }
    public String lname(){
        return lname;
    }
    public String hostel(){
        return hostel;
    }
    public String department(){
        return department ;
    }
    public String cgpa(){
        return cgpa;
    }

    @Override
    public String toString() {
        return fname+ " "+ lname + " "+ hostel + " "+ department+ " "+ cgpa;
    }
}