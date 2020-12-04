/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameshop;

class Player
    {
        public String name;
        public Backpack firstItem,lastItem;
        public int numItems;
        public double maxWeight;
        public double money;

        public Player(String n, double m)
        {
            name = n;
            money = m;
            numItems = 0;
            lastItem=firstItem = null;
            maxWeight = 50.0;
        }

        public boolean buy(Weapon w)
        {             
                        if(firstItem != null){
                                if(firstItem.presentWeight + w.weight <= maxWeight && w.weight <= maxWeight){
                                   lastItem.nextItem = new Backpack(w, maxWeight);
                                   lastItem = lastItem.nextItem;
                                   System.out.println(w.weaponName+" bought...");
                                   firstItem.presentWeight += w.weight;
                                   numItems++;
                                   return true;
                                }
                                else
                                    System.out.println("\nYou cannot purchase this item, it is too heavy for your backpack to carry\n");
                        }                     
                        else{
                            if(w.weight <= maxWeight){
                                System.out.println(w.weaponName+" bought...");
                                lastItem = firstItem = new Backpack(w,maxWeight);
                                firstItem.presentWeight += w.weight;
                                numItems++;
                                return true;
                            }
                            else
                                System.out.println("\nYou cannot purchase this item, it is too heavy, it is too heavy for your backpack to carry\n");
                        }               
            return false;
        }
        
        public void withdraw(double amt)
        {
            money = money - amt;
        }

        public boolean inventoryFull()
        {
            return (numItems == 10) ;
        }

 

        public void printCharacter()
        {
            double weight;
            if(firstItem != null)
                weight = firstItem.presentWeight;
            else 
                weight = 0.0;
            
            System.out.println(" Name:" + name + "\n Money:" + money + "\n Weight of Backpack: " + weight+ " LB" + "\tWeapons Owned: " + numItems);
            System.out.println("");
        }

        public void printBackpack()
        {
            if(numItems != 0){
                Backpack currentItem = firstItem;
                System.out.println(" "+name);
                while(currentItem != null)
                {
                     System.out.println(" "+rightPadding(currentItem.wep.weaponName,15) + 
                             " Damage: " + currentItem.wep.damage +
                             " Range: "+ currentItem.wep.range + 
                             " Weight: "+ rightPadding(Double.toString(currentItem.wep.weight),3));
                     currentItem = currentItem.nextItem;
                }
                System.out.println();
            }
            else
                System.out.println("Backpack is empty!\n");
        }
        
        public static String rightPadding(String str, int num) {
            return String.format("%1$-" + num + "s", str);
        }
    }
