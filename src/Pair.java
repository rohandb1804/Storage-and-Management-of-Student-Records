public class Pair<A,B> implements Comparable<Pair<A,B>>{
    A fname;
    B lname;
    Pair(A fname, B lname){
        this.fname=fname;
        this.lname=lname;

    }
    @Override
    public String toString() {
        String fnme=fname.toString();
        String lnme=lname.toString();
        return fnme+lnme;
    }

    @Override
    public int compareTo(Pair<A, B> o) {
        String  a=this.fname.toString();
        String b=o.fname.toString();
        String c=this.lname.toString();
        String d =o.lname.toString();
        if(a.compareTo(b)==0 && c.compareTo(d)!=0){
            if(c.compareTo(d)<0){
                return -1;
            }else{
                return 1;
            }
        }
        else {
            return a.compareTo(b);
        }
    }
}