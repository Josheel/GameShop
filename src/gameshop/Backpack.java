
package gameshop;
// This class should implement a backpack as a linked list
    // The backpack can hold any number of weapons as long as maxWeight is not crossed.
    // If an attempt to add a weapon to backpack is rejected due to weight
    class Backpack
    {
        double maxWeight;
        double presentWeight;
        Backpack nextItem;
        Weapon wep;
                
        // add / complete your list
        public Backpack(Weapon wep, double maxWeight){
            this.maxWeight = maxWeight;
            Backpack nextItem = null;
            this.wep = wep;
            this.presentWeight = 0;
        }
    }
