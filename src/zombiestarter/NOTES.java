
package zombiestarter;

import java.util.Scanner;

public class NOTES {

    public static int div(int n1, int n2){
        
        if (n2 == 0){
            throw new ArithmeticException("boo");
            //System.out.println("Can't div by zero");
            //System.exit(0);
            //return 0;
        }
       return n1/n2;
    }
    
    public static void main(String[] args) {
        
        //String Array exeptions
        String names[] = {"jo0", "poppy", "mary"};
        
        for(int i = 0; i < 3; i++)
        {
            System.out.println(names[i]);
        }
        
        //Scanner arithmetic exeptions
        Scanner scan = new Scanner(System.in);
        
        int number1 = scan.nextInt();
        int number2 = scan.nextInt();
        
        try{
            System.out.println(div(number1, number2));
        }
        catch(ArithmeticException ex){
            System.out.println("Div by zero");
        }
        finally{
            //will always happen outside of the catchment of expesion, it is always run.
        }

        System.out.println("After try/catch");

        
    }
    
}
    
      //  private Room findRoom(String start) {
        //loop over array to find room we are in
////        for (int i = 0; i < rooms.size(); i++) {
////           
////                System.out.println(rooms.get(i));
////        
////        }
////        return rooms;
   //     return;
    //}
    
    //        for (Room room : worldLoader) {
//            Room r = new Room(room);
//            rooms.add(r);

           // currentRoom = findRoom(worldLoader.getStart());
    
        //ITEMS
    //Get Room Description
    //Get entrances
    //Check locked
    //Get Items
    //Get zombie count
    //Get Endroom
    //Make inventory



    //should have attribute current room, string or int(like index) but use Room class
    //make entrance classes
    //has two fields and two methods
    //look
    //zombiebot calls look in world
    //world calls current room
    //add room method possible instead of contrcuteing them all