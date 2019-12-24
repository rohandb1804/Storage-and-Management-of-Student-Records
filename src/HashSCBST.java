class Node<K,T> {
    K key;
    T data;
    Node<K,T> left;
    Node<K,T> right;
    Node(K key, Node left, Node right, T data){
        this.key=key;
        this.left=left;
        this.right=right;
        this.data=data;
    }

}
class SCBST<K extends Comparable<K>,T >{

    Node root;
    SCBST(){
        this.root = null;
    }

    int  insert(K key, T elem){
        root=add(root,key,elem);
        return getcount(key);
    }
    Node add(Node node, K key, T elem){
        if(node==null){
            node=new Node(key, null,null,elem);
        }
        else{
            if(key.compareTo((K) node.key)<0){
                node.left = add(node.left, key,elem);
            }else{
                node.right=add(node.right,key,elem);
            }
        }
        return node;
    }

    Node updateutil(Node node,K key,T elem){
        if(node==null){
            return null;
        }
        if (key.compareTo((K) node.key) == 0) {
            node.data=elem;
            return node;
        }
        if(key.compareTo((K)node.key)<0) {
            Node child = updateutil(node.left, key, elem);
            return child;
        }else{
            Node child = updateutil(node.right, key, elem);
            return child;
        }

    }
    int update(K key, T elem){
        int count=getcount(key);
        updateutil(root,key,elem);
        return count;
    }

    boolean contains(K key){
        if(getcount(key)==0){
            return false;
        }
        else
            return true;
    }


    Node getutil(Node node,K key){
        if(node==null){
            return null;
        }
        int cmp=key.compareTo((K)node.key);
        if(cmp<0){
            Node child=getutil(node.left,key);
            node=child;
        }
        else if(cmp>0){
            // System.out.print("R");
            Node child=getutil(node.right,key);
            node= child;
        }

        return node;
    }
    T  get(K key){
        if(contains(key)){

            return (T)getutil(root,key).data;
        }
        return null;
    }

    Node getNode(K key){
        return getutil(root,key);
    }


    Node minfind(Node node){
        while(node.left!=null){
            node=node.left;
        }
        return node;
    }
    public int  delete(K key) {

        if (contains(key)) {
            Node getnode=getNode(key);
            int count=getcount(key);
            if(getnode.left==null && getnode.right==null){
                count=getcount(key);
            }
            else if(getnode.left==null || getnode.right==null){
                count=getcount(key)+1;
            }else{

                count=getcount((K)minfind(getnode.right).key);
            }
            root = remove(root, key);

            return count;
        }
        else{
            return 0;
        }
    }
    Node remove(Node node, K key) {

        if (node == null) {
            return null;
        }

        int cmp = key.compareTo((K)node.key);

        if (cmp < 0) {
            node.left = remove(node.left, key);
        } else if (cmp > 0) {
            node.right = remove(node.right, key);

        } else {
            if (node.left == null) {

                Node rightChild = node.right;

                node.data = null;
                node.key=null;
                node = null;

                return rightChild;


            } else if (node.right == null) {

                Node leftChild = node.left;

                node.data = null;
                node.key=null;
                node = null;

                return leftChild;

            } else {


                Node tmp =minfind(node.right);

                node.key = tmp.key;
                node.data=tmp.data;

                node.right = remove(node.right, (K)tmp.key);


            }
        }

        return node;
    }


    Node AddressHelper(Node node,K key){
        if(node==null){
            return null;
        }
        int cmp=key.compareTo((K)node.key);
        if(cmp<0){
            System.out.print("L");
            AddressHelper(node.left,key);
        }
        else if(cmp>0){
            System.out.print("R");
            AddressHelper(node.right,key);
        }




        return node;
    }

    String Address(K key){
        if(contains(key)){
            AddressHelper(root,key);
            return "";
        }
        return "E";
    }






    int getcountUtil(Node node, K key, int level)
    {
        if (node == null)
            return 0;

        if (key.compareTo((K) node.key) == 0)
            return level;

        int child = getcountUtil(node.left, key, level + 1);
        if (child != 0)
            return child;

        child = getcountUtil(node.right, key, level + 1);
        return child;
    }


    int getcount( K  key)
    {
        return getcountUtil(root, key, 1);
    }


}

public class HashSCBST<K extends Comparable<K>,T> implements MyHashTable_<K,T>{
    int capacity;
    SCBST[] table;

    HashSCBST(int size) {
        capacity = size;
        table = new SCBST[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = new SCBST();
        }

    }

    public int insert(K key, T obj) {

        int index=(int)djb2(key.toString(),capacity);
        return table[index].insert(key,obj);




    }


    public int update(K key, T obj) {
        int index=(int)djb2(key.toString(),capacity);
        return table[index].update(key,obj);
    }


    public int delete(K key) {
        int index=(int)djb2(key.toString(),capacity);
        return table[index].delete(key);
    }


    public boolean contains(K key) {
        int index=(int)djb2(key.toString(),capacity);
        return table[index].contains(key);
    }


    public T get(K key) throws NotFoundException {
        int index=(int)djb2(key.toString(),capacity);

        if((T)table[index]!=null){
            if (table[index].contains(key)) {
                return (T)table[index].get(key);
            }else{
                return null;
            }
        }
        else{
            throw new NotFoundException("E");
        }
    }


    public String address(K key) throws NotFoundException {
        int index=(int)djb2(key.toString(),capacity);
        if(contains(key)){
            System.out.print(index +"-");
        }

        return table[index].Address(key);
    }

    public static long djb2(String str, int hashtableSize) {
        long hash = 5381;
        for (int i = 0; i < str.length(); i++) {
            hash = ((hash << 5) + hash) + str.charAt(i);
        }
        return Math.abs(hash) % hashtableSize;
    }

}