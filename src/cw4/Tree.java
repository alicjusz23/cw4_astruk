package cw4;


public class Tree {
    Node root;
    int glebokosc =0;
    
    public Tree(){
        this.root = new Node();
    }
    
    public Tree(Node e){
        this.root = e;
    }
    
   
    public void insertT(int i, double v, Node e){
        if (e.item == null){
            e.item = new Node.Item();
            e.item.index = i;
            e.item.setValue(v);
        }else if (i < e.item.index){
                if (e.left == null)
                    e.left = new Node();
                insertT(i, v, e.left);
        }else if (i > e.item.index){
                if (e.right == null)
                    e.right = new Node();
                insertT(i, v, e.right);
        } else{
                e.item.setValue(v);
                e.item.index = i;
        }
    }
 
    public void insert(int i, double v){
        insertT(i, v, root);
    }

 
    double getT(int i, Node e){
        if(e == null)
            return 0;
        if(i < e.item.index){
            return getT(i, e.left);
        }else if (i > e.item.index){
            return getT(i, e.right);
        }else{
            return e.item.getValue();
        }
    }

     double get(int i){
         return getT(i, root);
     }

     
  
    void test() { 
        test(root, 0); 
    }

  
    void test(Node r, int gl) {
        if (r == null){  
            return;
        }
        test(r.left, glebokosc++);
        glebokosc--;
        System.out.print("indeks= " + r.item.index + " war= " + get(r.item.index) + " głębokość= " + glebokosc +"\n");
        test(r.right, glebokosc++);
        glebokosc--;
    }

}
