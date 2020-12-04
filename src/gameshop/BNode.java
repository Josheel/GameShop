package gameshop;
/**
 *
 * @author Josheel
 */
public class BNode {
    ShopItem data;
    BNode left,right;
    
    public BNode(ShopItem data){
        this.data = data;
        left = right = null;
    }
}
