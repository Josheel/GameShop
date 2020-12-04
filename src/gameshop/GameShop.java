package gameshop;

import java.util.Scanner;

/**
 *
 * @author Adder
 */
public class GameShop {

        public static int getInteger(Scanner sc,String message){
            System.out.print(message);
            while (!sc.hasNextInt()) 
            {
                sc.nextLine(); //clear the invalid input ...
                System.out.print(message);
            }
            return sc.nextInt();
        }
        
        public static double getDouble(Scanner sc,String message){
            System.out.print(message);
            while (!sc.hasNextDouble()) 
            {
                sc.nextLine(); //clear the invalid input ...
                System.out.print(message);
            }
            return sc.nextDouble();
        }
        
    
        public static void addWeapons(BinaryTree bt,Scanner sc)
        {
            System.out.println("***********WELCOME TO THE WEAPON ADDING MENU*********");
            String weaponName; int weaponRange; int weaponDamage; double weaponWeight; double weaponCost;
            int quantity;
            System.out.print("Please enter the NAME of the Weapon ('end' to quit):");
            weaponName=sc.next();
            while (weaponName.compareTo("end") != 0)
            {
                weaponRange= getInteger(sc,"Please enter the Range of the Weapon (0-10):"); 
                weaponDamage=getInteger(sc,"Please enter the Damage of the Weapon:"); 
                weaponWeight= getDouble(sc,"Please enter the Weight of the Weapon (in pounds):");
                weaponCost=getDouble(sc,"Please enter the Cost of the Weapon:");
                Weapon w = new Weapon(weaponName, weaponRange, weaponDamage, weaponWeight, weaponCost);
                quantity=getInteger(sc,"Please enter the quantity in stock:"); 
                bt.insert(w,quantity);
                System.out.print("\nPlease enter the NAME of another Weapon ('end' to quit):");
                weaponName = sc.next();
            }
            
            /*
                h.insert(w , quantity);
            
                INSIDE BINARY TREE INSERT FUNCTION
                    root.w.wepName > w.wepName
                    new ShopItem(item,quantity);
            
            */
        }

        public static void showRoomMenu(BinaryTree bt,Player p){
                System.out.println("WELCOME TO THE SHOWROOM!!!!");
                bt.printData(); //display everything in binary tree
                System.out.println("You have "+p.money+" money.");
                System.out.println("Please select a weapon to buy('end' to quit):");           
        }
        
        public static void showRoom(BinaryTree bt, Player p,Scanner sc)
        {
            String choice;
            
            if(!bt.isEmpty())
                showRoomMenu(bt,p);
            else{
                System.out.println("The shop is empty");
                return;
            }
                
            choice=sc.next();
            while (choice.compareTo("end") != 0 && !p.inventoryFull())
            {
                ShopItem si = bt.searchItem(choice); //search in binary tree
                if (si != null)
                {
                    if (si.item.cost > p.money)
                    {
                        System.out.println("Insufficient funds to buy "+si.item.weaponName );
                    }
                    else
                    {
                        if(p.buy(si.item)){
                            p.withdraw(si.item.cost);
                            si.numberInStock--;
                            if(si.numberInStock == 0)
                                bt.delete(si.item.weaponName);
                        }
                    }
                }
                else
                {
                    System.out.println(" ** "+choice+" not found!! **" );
                }
                if(!bt.isEmpty())
                showRoomMenu(bt,p);
                else{
                    System.out.println("The shop is empty");
                    return;
                }
                choice = sc.next();
            }
            if(p.inventoryFull()){
                System.out.println("Back Pack is Full!!!");
}
            System.out.println("");
        }
        
        public static void showRoomDelete(BinaryTree bt){
                System.out.println("\nSHOP ITEMS LIST");
                bt.printData(); //display everything in binary tree
                System.out.println("Please select a weapon to delete('end' to quit):");           
        }
        
        public static void deleteWeapon(BinaryTree bt, Scanner sc){
            String choice;
                   
            if(!bt.isEmpty())
            {
                showRoomDelete(bt);
            }else{
                System.out.println("The shop is empty");
                return;
            }  
            
            choice = sc.next();

            while(choice.compareTo("end") != 0 && !bt.isEmpty()){
                
                if(bt.delete(choice)) 
                    System.out.println(choice + " sucessfully deleted form shop\n");
                
                else System.out.println("Item does not exist in shop\n");
                
                if(!bt.isEmpty())
                {
                    showRoomDelete(bt);
                }
                else
                {
                    System.out.println("The shop is empty");
                    return;
                }  
                choice = sc.next();
            }

        }
        
        
        
        public static void main(String[] args)
        {
            Scanner sc = new Scanner(System.in);
            String pname;
            System.out.println("Please enter Player name:");
            pname=sc.next();
            BinaryTree bt = new BinaryTree();
            Player p1= new Player(pname,45);
      
            String choice;
            boolean end = false;
            System.out.print("\nEnter Operation:");
            do{
              System.out.println("\nWhat would you like to do?:");
              System.out.println("***** To add weapons to shop:         Enter \"add\" *****");
              System.out.println("***** To delete weapons from shop:    Enter \"delete\" *****");
              System.out.println("***** To buy item:                    Enter \"buy\" *****");
              System.out.println("***** To showCharacter:               Enter \"showCharacter\" *****");
              System.out.println("***** To showBackpack:                Enter \"showBackpack\" *****");
              System.out.println("***** To terminate program:           Enter \"end\" *****");
              System.out.print(">");
              choice = sc.next().toLowerCase();
               
              switch (choice) 
              {
                case "add":
                  addWeapons(bt,sc);
                  System.out.println("What else would you like to do?");
                  break;
                case "delete":
                  deleteWeapon(bt,sc);
                  break;
                case "buy":
                  showRoom(bt,p1,sc);
                  System.out.println("What else would you like to do?");
                  break;
                case "showcharacter":
                  System.out.println("WELCOME TO THE SHOW CHARACTER");  
                  p1.printCharacter();
                  System.out.println("What else would you like to do?");
                  break;   
                case "showbackpack":
                  System.out.println("WELCOME TO THE BACKPACK");  
                  p1.printBackpack();
                  System.out.println("What else would you like to do?");
                  break;   
                case "end":
                  System.out.println("\nYOU HAVE SUCCESFULLY ENDED THE PROGRAM");  
                  end = true;
                  break;
                default:
                    System.out.println("\nInvalid Command");
              }
            }while(end == false);
        
        }

}
