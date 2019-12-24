class HashElem<K,T>{
        K key;
        T obj;
        HashElem(K key, T obj){
            this.key=key;
            this.obj=obj;
        }

    }


        public class MyHashTable<K,T> implements MyHashTable_<K,T>{

            int capacity;
            HashElem<K,T>[] table;
            HashElem<K,T> deactivated=new HashElem<>(null,null);
            int   currentsize;

            MyHashTable(int size){
                capacity=size;
                currentsize=0;
                table=new HashElem[capacity];
                for(int i=0;i<capacity;i++){
                    table[i]=null;
                }
            }
            public int insert(K key, T obj){
                if(currentsize==capacity){
                    return 0;
                }
                int count=0;
                int initindex=(int)djb2(key.toString(),capacity);
                //collision occurs
                if(table[initindex]!=(null)){
                    count++;
                    if(table[initindex]==deactivated){
                        table[initindex]=new HashElem<>(key, obj);
                        return count;
                    }
                    int index2=(int)sdbm(key.toString(),capacity);
                    while(true){
                        int newIndex=(initindex+(count*index2))%capacity;
                        count++;
                        if(table[newIndex]==null  || table[newIndex]==deactivated){
                            table[newIndex]=new HashElem<>(key, obj);
                            return count;
                        }
                    }
                }else{
                    count++;
                    table[initindex]=new HashElem<>(key,obj);
                    return count;

                }
            }

            // Update object for given key
            public int update(K key, T obj){
                int count=0;
                int initindex=(int)djb2(key.toString(),capacity);
                if(table[initindex]==null){

                    return count;
                }
                if(table[initindex]!=(null)){
                    count++;
                    if(table[initindex]!=deactivated) {
                /*String fname = ((Pair<String, String>) key).fname;
                String comp = ((Pair<String, String>) table[initindex].key).fname;*/
                        if (table[initindex].key.toString().equals(key.toString())) {
                            table[initindex].obj=obj;
                            return count;
                        }
                    }
                    int index2=(int)sdbm(key.toString(),capacity);
                    int newIndex=(initindex+(count*index2))%capacity ;
                    while(initindex!=newIndex){
                        newIndex=(initindex+(count*index2))%capacity;
                        count++;

                        if(table[newIndex]==null){
                            return 0;
                        }

                        if(table[newIndex]!=(null) ){
                            if(table[newIndex]!=deactivated) {
                       /* String fname1 = ((Pair<String, String>) key).fname;
                        String comp1 = ((Pair<String, String>) table[newIndex].key).fname;*/
                                if (table[newIndex].key.toString().equals(key.toString())) {
                                    table[newIndex].obj=obj;
                                    return count;
                                }

                            }
                        }
                    }
                }
                return count;
            }

            // Delete object for given key
            public int delete(K key){
                int count=0;
                int initindex=(int)djb2(key.toString(),capacity);
                if(table[initindex]==null){
                    return 0;
                }
                if(table[initindex]!=(null)){
                    count++;
                    if(table[initindex]!=deactivated) {
                /*String fname = ((Pair<String, String>) key).fname;
                String comp = ((Pair<String, String>) table[initindex].key).fname;*/
                        if (table[initindex].key.toString().equals(key.toString()))  {
                            table[initindex] = deactivated;
                            return count;
                        }

                    }
                    int index2=(int)sdbm(key.toString(),capacity);
                    int newIndex=(initindex+(count*index2))%capacity ;
                    while(initindex!=newIndex){
                        newIndex=(initindex+(count*index2))%capacity;
                        count++;

                        if(table[newIndex]==null){
                            return 0;
                        }

                        if(table[newIndex]!=(null) ){
                            if(table[newIndex]!=deactivated) {
                       /* String fname1 = ((Pair<String, String>) key).fname;
                        String comp1 = ((Pair<String, String>) table[newIndex].key).fname;*/
                                if (table[newIndex].key.toString().equals(key.toString())) {
                                    table[newIndex] = deactivated;
                                    return count;
                                }
                            }



                        }
                    }
                }
                return count;

            }

            // Does an object with this key exist?
            public boolean contains(K key){
                int count=0;
                int initindex=(int)djb2(key.toString(),capacity);
                if(table[initindex]==null){

                    return false;
                }
                if(table[initindex]!=(null)){
                    count++;
                    if(table[initindex]!=deactivated) {
               /* String fname = ((Pair<String, String>) key).fname;
                String comp = ((Pair<String, String>) table[initindex].key).fname;*/
                        if (table[initindex].key.toString().equals(key.toString())) {
                            return true;
                        }
                    }
                    int index2=(int)sdbm(key.toString(),capacity);
                    int newIndex=(initindex+(count*index2))%capacity ;
                    while(initindex!=newIndex){
                        newIndex=(initindex+(count*index2))%capacity;
                        count++;

                        if(table[newIndex]==null){
                            return false;
                        }

                        if(table[newIndex]!=(null) ){
                            if(table[newIndex]!=deactivated) {
                        /*String fname1 = ((Pair<String, String>) key).fname;
                        String comp1 = ((Pair<String, String>) table[newIndex].key).fname;*/
                                if (table[newIndex].key.toString().equals(key.toString())) {
                                    return true;
                                }

                            }
                        }
                    }
                }
                return false;
            }

            // Return the object with given key
            public T get(K key) throws NotFoundException{
                int count = 0;
                int initindex = (int) djb2(key.toString(), capacity);
                //collision occurs
                if (table[initindex] == null) {
                    throw new NotFoundException("E");
                }
                if (table[initindex] != (null)) {
                    count++;
                    if (table[initindex] != deactivated) {
                /*String fname = ((Pair<String, String>) key).fname;
                String comp = ((Pair<String, String>) table[initindex].key).fname;*/
                        if (table[initindex].key.toString().equals(key.toString())) {
                            return table[initindex].obj;
                        }
                    }
                    int index2 = (int) sdbm(key.toString(), capacity);
                    int newIndex = (initindex + (count * index2)) % capacity;
                    while (initindex != newIndex) {
                        newIndex = (initindex + (count * index2)) % capacity;
                        count++;

                        if (table[newIndex] == null) {
                            throw new NotFoundException("E");
                        }

                        if (table[newIndex] != (null)) {
                            if (table[newIndex] != deactivated) {
                      /*  String fname1 = ((Pair<String, String>) key).fname;
                        String comp1 = ((Pair<String, String>) table[newIndex].key).fname;*/
                                if (table[newIndex].key.toString().equals(key.toString())) {
                                    return table[newIndex].obj;
                                }

                            }
                        }
                    }


                }

                throw new NotFoundException("E");
            }
            public String address(K key) throws NotFoundException{
        int count=0;
        int initindex=(int)djb2(key.toString(),capacity);
        //collision occurs
        if(table[initindex]==null){

            throw new NotFoundException("E");
        }
        if(table[initindex]!=(null)){
            count++;
            if(table[initindex]!=deactivated) {
                /*String fname = ((Pair<String, String>) key).fname;
                String comp = ((Pair<String, String>) table[initindex].key).fname;*/
                if (table[initindex].key.toString().equals(key.toString())) {
                    String s1=Integer.toString(initindex);
                    return s1;
                }
            }
            int index2=(int)sdbm(key.toString(),capacity);
            int newIndex=(initindex+(count*index2))%capacity ;
            while(initindex!=newIndex){
                newIndex=(initindex+(count*index2))%capacity;
                count++;

                if(table[newIndex]==null){
                    throw new NotFoundException("E");
                }

                if(table[newIndex]!=(null) ){
                    if(table[newIndex]!=deactivated) {
                       /* String fname1 = ((Pair<String, String>) key).fname;
                        String comp1 = ((Pair<String, String>) table[newIndex].key).fname;*/
                        if (table[newIndex].key.toString().equals(key.toString())) {
                            String s2=Integer.toString(newIndex);
                            return s2;
                        }

                    }
                }
            }
        }
        throw new NotFoundException("e");
    }



    public static long djb2(String str, int hashtableSize) {
        long hash = 5381;
        for (int i = 0; i < str.length(); i++) {
            hash = ((hash << 5) + hash) + str.charAt(i);
        }
        return Math.abs(hash) % hashtableSize;
    }
    public static long sdbm(String str, int hashtableSize) {
        long hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = str.charAt(i) + (hash << 6) + (hash << 16) - hash;
        }
        return Math.abs(hash) % (hashtableSize - 1) + 1;
    }


}