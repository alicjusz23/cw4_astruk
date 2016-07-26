package cw4;


public class Node {
    Node.Item item;
    Node left ;
    Node right;
    
    
    public Node(){
    }
    
    
    public static class Item { 
        private double value;
        int index; 
        
        double getValue(){
            return value;
        }
        
        void setValue(double v){
            this.value = v;
        }
    }
}