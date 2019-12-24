import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class assignment3{
    public static void main(String args[]) throws IOException, NotFoundException {
        String T=args[0];
        int capacity=Integer.parseInt(T);
        String method=args[1];

        if(args[1].equals("DH")) {
            MyHashTable table = new MyHashTable(capacity);
            File fle = new File(args[2]);
            BufferedReader br = new BufferedReader(new FileReader(fle));
            String lne;

            while ((lne = br.readLine()) != null) {
                String[] data = lne.split(" ", 6);
                if (data[0].equals("insert")) {
                    Pair<String, String> pair = new Pair<>(data[1], data[2]);
                    Student student = new Student(pair, data[3], data[4], data[5]);
                    if (table.contains(pair)) {
                        System.out.println("E");
                    }else{
                        System.out.println(table.insert(pair, student));
                    }

                } else if (data[0].equals("update")) {
                    Pair<String, String> pair = new Pair<>(data[1], data[2]);
                    Student student = new Student(pair, data[3], data[4], data[5]);
                    int k=table.update(pair,student);
                    if(k==0)
                        System.out.println("E");
                    else{
                        System.out.println(k);
                    }
                } else if (data[0].equals("delete")) {
                    Pair<String, String> pair = new Pair<>(data[1], data[2]);
                    int k = table.delete(pair);

                    if (k == 0) {
                        System.out.println("E");
                    } else {
                        System.out.println(k);
                    }
                } else if (data[0].equals("contains")) {
                    Pair<String, String> pair = new Pair<>(data[1], data[2]);
                    if(table.contains(pair)){
                        System.out.println("T");
                    }else{
                        System.out.println("F");
                    }

                } else if (data[0].equals("get")) {
                    Pair<String, String> pair = new Pair<>(data[1], data[2]);
                    try {
                        Student student = ((Student) table.get(pair));
                        String student1 = student.toString();
                        System.out.println(student1);
                    }
                    catch(NotFoundException e){
                        System.out.println("E");
                    }
                } else if (data[0].equals("address")) {
                    Pair<String, String> pair = new Pair<>(data[1], data[2]);
                    try {
                        System.out.println(table.address(pair));
                    } catch (NotFoundException e) {
                        System.out.println("E");
                    }

                }
            }
        }

        if(args[1].equals("SCBST")) {
            HashSCBST hash=new HashSCBST(capacity);
            File fle = new File(args[2]);
            BufferedReader br = new BufferedReader(new FileReader(fle));
            String lne;

            while ((lne = br.readLine()) != null) {
                String[] data = lne.split(" ", 6);
                if (data[0].equals("insert")) {
                    Pair<String, String> pair = new Pair<>(data[1], data[2]);
                    Student student = new Student(pair, data[3], data[4], data[5]);
                    if(hash.contains(pair)){
                        System.out.println("E");
                    }else{
                        System.out.println(hash.insert(pair, student));
                    }


                } else if (data[0].equals("update")) {
                    Pair<String, String> pair = new Pair<>(data[1], data[2]);
                    Student student = new Student(pair, data[3], data[4], data[5]);
                    int k=hash.update(pair,student);
                    if(k==0)
                        System.out.println("E");
                    else{
                        System.out.println(k);
                    }
                } else if (data[0].equals("delete")) {
                    Pair<String, String> pair = new Pair<>(data[1], data[2]);
                    int k = hash.delete(pair);

                    if (k == 0) {
                        System.out.println("E");
                    } else {
                        System.out.println(k);
                    }
                } else if (data[0].equals("contains")) {
                    Pair<String, String> pair = new Pair<>(data[1], data[2]);
                    if(hash.contains(pair)){
                        System.out.println("T");
                    }else{
                        System.out.println("F");
                    }

                } else if (data[0].equals("get")) {
                    Pair<String, String> pair = new Pair<>(data[1], data[2]);
                    try {
                        Student student = ((Student) hash.get(pair));
                        if(student!=null){
                            System.out.println(student);}
                        else{
                            System.out.println("E");
                        }
                    }
                    catch(NotFoundException e){
                        System.out.println("E");
                    }
                } else if (data[0].equals("address")) {
                    Pair<String, String> pair = new Pair<>(data[1], data[2]);
                    try {
                        System.out.println(hash.address(pair));
                    } catch (NotFoundException e) {
                        System.out.println("E");
                    }

                }
            }
        }

    }
}
