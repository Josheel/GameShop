package gameshop;

/**
 *
 * @author Josheel
 */
public class BinaryTree {
    BNode root;
    boolean itemExists;
    
    public BinaryTree(){
        root = null;
    }
    
    public boolean isEmpty(){
        return root == null;
    }
    public void insert(Weapon wep, int quantity){
        root = insertRec(root,wep,quantity);
    }
    
    BNode insertRec(BNode root, Weapon wep, int quantity){
        if(root == null){
                root = new BNode(new ShopItem(wep,quantity));
                return root;
        }

        else if (root.data.item.weaponName.compareToIgnoreCase(wep.weaponName) > 0) 
            root.left = insertRec(root.left, wep, quantity); 
        else  
            root.right = insertRec(root.right, wep,quantity); 

        return root; 
    }
    
    void printData()  
    {  
         inorderRec(root);  
    } 
  
    void inorderRec(BNode root) 
    { 
        if (root != null) { 
            inorderRec(root.left); 
            
            System.out.println("Weapon Name: " +  rightPadding(root.data.item.weaponName,12) + 
                    " Cost: " + rightPadding(Double.toString(root.data.item.cost),7) +
                    " Damage: " + rightPadding(Integer.toString(root.data.item.damage),6)  +
                    " Range: " + rightPadding(Integer.toString(root.data.item.range), 4) +  
                    " Weight: " + rightPadding(Double.toString(root.data.item.weight),5) +
                    " Stock: " + root.data.numberInStock);
            inorderRec(root.right); 
        } 
    }
    
    public static String rightPadding(String str, int num) {
        return String.format("%1$-" + num + "s", str);
    }
    
    public boolean delete(String key){
        itemExists = false;
        root = deleteRec(root, key);
        return itemExists;
    }
    
    private BNode deleteRec(BNode curr, String key){
        if(curr == null) return curr;
        if(curr.data.item.weaponName.compareToIgnoreCase(key) > 0) curr.left = deleteRec(curr.left,key);
        if(curr.data.item.weaponName.compareToIgnoreCase(key) < 0) curr.right = deleteRec(curr.right,key);
        if(curr.data.item.weaponName.equals(key)){
           itemExists = true;
           if(curr.left == null) return curr.right;
           if(curr.right == null) return curr.left;
           BNode successor = curr.right;
           
           while(successor.left != null)
               successor = successor.left;
           
           curr.data = successor.data;
           curr.right = deleteRec(curr.right,successor.data.item.weaponName);
        }
        return curr;
    }
    
    public ShopItem searchItem(String name){
            return searchRec(root, name);
    }

    private ShopItem searchRec(BNode curr,String key){
        ShopItem exists = null;
        
        if (curr == null)
            return null;
        
        else if (curr.data.item.weaponName.equals(key) && curr != null)
            return curr.data;
        
        else if(curr.data.item.weaponName.compareToIgnoreCase(key) > 0 && curr != null)
            exists = searchRec(curr.left,key);
        
        else if(curr.data.item.weaponName.compareToIgnoreCase(key) < 0 && curr != null)
            exists = searchRec(curr.right, key);
            
        return exists;
    }
}
