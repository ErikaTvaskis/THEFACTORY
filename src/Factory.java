
//IMPORTS
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.ThreadLocalRandom;

public class Factory 
{ //Begin class
    
//COOL TEXT
public static final String WHITEBACKGROUND = "\u001B[47m";
public static final String BLUETEXT = "\u001B[34m";
public static final String PURPLETEXT = "\u001B[35m";
public static final String GREENTEXT = "\u001B[32m";
public static final String RESET = "\u001B[0m";
  
public static void main(String[] args) throws InterruptedException 
{ //Begin main
 
//VARIABLES
ArrayList<Integer> list = new ArrayList<Integer>();
Semaphore semaphore = new Semaphore(5);
final int MIN = 1;
final int MAX = 100;
final int BIGMIN = 100000000;
final int BIGMAX = 199999900;
int a = 0;
int b = 0;
int c = 0;
  
Runnable SHELF = new Runnable() {
    @Override
    public void run() {
        while (a == 0) { 
            try {
                if(semaphore.availablePermits()==0){
                    System.out.println("Blocked");
                }//if no permits

                semaphore.acquire();

                if(list.isEmpty()){
                    for (int i = 0; i < 5; i++) {
                        int n = ThreadLocalRandom.current().nextInt(MIN, MAX + 1);
                        //array[i] = n;
                        //list.set(i, n);
                        list.add(n);
                        System.out.println("Restocking the shelf: " + Arrays.toString(list.toArray()) + " Has " + list.size() + " Integers");
                    }//end of while
                        
                    System.out.println("The shelf has been reloaded: " + Arrays.toString(list.toArray()));
                }//end of if less then five products
                else{
                    System.out.println("Shelf is not empty, contains: " + Arrays.toString(list.toArray()));
                }//end of else full product

                semaphore.release();
                Thread.sleep(2500);
            } //end of try
            catch (InterruptedException ex) {
                System.out.println(Arrays.toString(list.toArray()) + ": Interrupted");
            }//end of catch
            
        }//end of while loop
} //End of run() method
}; //End of SHELF
   
Runnable CUSTOMER1 = new Runnable() {
    @Override
    public void run() {
        while (b == 0) {
            try {
                Thread.sleep(500);
            } //End try statement 
            catch (InterruptedException ex) {
                Logger.getLogger(Factory.class.getName()).log(Level.SEVERE, null, ex);
            } //End catch statement
        
            try {
                if(semaphore.availablePermits()==0) {
                    System.out.println("Not permitted: " + Arrays.toString(list.toArray()));
                } //If no permits are available

                semaphore.acquire();

                if(!(list.isEmpty())){
                    int n = list.get(0);
                    list.remove(list.get(0));
                    //int answer1 = getPowered(2, n);
                    double answer1 = getthepower(2.0, n);

                    System.out.println(WHITEBACKGROUND + BLUETEXT + "****************CUSTOMER ONE****************" + RESET);
                    System.out.println("CONSUMER ONE TOOK: " + n);
                    System.out.println("THE SHELF NOW: " + Arrays.toString(list.toArray()));
                    System.out.println("ONLY " + list.size() + " ITEM(S) LEFT NOW"); 
                    System.out.println("THE CALCULATION: 2^" + n + " = " + answer1);
                    System.out.println(WHITEBACKGROUND + BLUETEXT + "********************************************" + RESET);
                    System.out.println();
                }//End if 
                else{
                    System.out.println("Shelf does not contain enough items for Customer 1: " + Arrays.toString(list.toArray()));
                }//end of else no product
                        
                semaphore.release();
                        
            } //End try statement
            catch (InterruptedException ex) {
                System.out.println("Interrupted Exception: " + Arrays.toString(list.toArray()));
            }//End catch statement
               
        }//End of while loop 
    } //End run() method
}; //END CUSTOMER1

Runnable CUSTOMER2 = new Runnable() {
    @Override
    public void run() {
        while (c == 0) {
            try {
                Thread.sleep(250);
            } //End try statement 
            catch (InterruptedException ex) {
                Logger.getLogger(Factory.class.getName()).log(Level.SEVERE, null, ex);
            } //End catch statement
        
            try {
                if(semaphore.availablePermits()==0) {
                    System.out.println("Not Permitted: " + Arrays.toString(list.toArray()));
                } //If no permits are available

                semaphore.acquire();

                if(list.size() > 1){
                    int p = list.get(0);
                    list.remove(list.get(0));
                    int q = list.get(0);
                    list.remove(list.get(0));
                    int bigp = ThreadLocalRandom.current().nextInt(BIGMIN, BIGMAX + 1) + p;
                    int bigq = ThreadLocalRandom.current().nextInt(BIGMIN, BIGMAX + 1) + q;
                    int answer2 = getGreatestCommonFactor(bigp,bigq);

                    System.out.println(WHITEBACKGROUND + PURPLETEXT + "****************CUSTOMER TWO****************" + RESET);
                    System.out.println("CONSUMER TWO TOOK: " + p + " AND " + q);
                    System.out.println("THE SHELF NOW: " + Arrays.toString(list.toArray()));
                    System.out.println("ONLY " + list.size() + " ITEM(S) LEFT NOW"); 
                     System.out.println("MAKING THE TWO NUMBERS BIG"); 
                    System.out.println("THE GREATEST COMMON FACTOR OF: ");
                    System.out.println(bigp + " AND " + bigq + " = " + answer2);
                    System.out.println(WHITEBACKGROUND + PURPLETEXT + "********************************************" + RESET);
                    System.out.println();
                }//End if 
                else{
                    System.out.println("Shelf does not contain enough items for Customer 2: " + Arrays.toString(list.toArray()));
                }//end of else no product
                        
                semaphore.release();
                        
            } //End try statement
            catch (InterruptedException ex) {
                System.out.println(Arrays.toString(list.toArray()) + ": Interrupted");
            }//End catch statement
               
        }//End of while loop 
    } //End run() method
}; //END CUSTOMER2

Runnable CUSTOMER3 = new Runnable() {
    @Override
    public void run() {
        while (c == 0) {
            try {
                Thread.sleep(1000);
            } //End try statement 
            catch (InterruptedException ex) {
                Logger.getLogger(Factory.class.getName()).log(Level.SEVERE, null, ex);
            } //End catch statement
        
            try {
                if(semaphore.availablePermits()==0) {
                    System.out.println("Not Permitted: " + Arrays.toString(list.toArray()));
                } //If no permits are available

                semaphore.acquire();

                if(!(list.isEmpty())){
                    int r = list.get(0);
                    list.remove(list.get(0));

                    System.out.println(WHITEBACKGROUND + GREENTEXT + "****************CUSTOMER THREE****************" + RESET);
                    System.out.println("CONSUMER THREE TOOK: " + r);
                    System.out.println(WHITEBACKGROUND + GREENTEXT + "********************************************" + RESET);
                    System.out.println();
                }//End if 
                else{
                    System.out.println("Shelf does not contain enough items for Customer 3: " + Arrays.toString(list.toArray()));
                }//end of else no product
                        
                semaphore.release();
                        
            } //End try statement
            catch (InterruptedException ex) {
                System.out.println(Arrays.toString(list.toArray()) + ": Interrupted");
            }//End catch statement
               
        }//End of while loop 
    } //End run() method
}; //END CUSTOMER3


//MAKING THREADS
Thread S = new Thread(SHELF);
Thread C1 = new Thread(CUSTOMER1);
Thread C2 = new Thread(CUSTOMER2);
Thread C3 = new Thread(CUSTOMER3);

//STARTING THREADS
S.start();
C1.start();
C2.start();
C3.start();

} //End main

/******************************************************
Purpose: calculates double to the power of an integer
In: x (double), n (integer)
Out: calculation
/******************************************************/ 
public static double getthepower(double x, int n){
    if(n==0)
        return 1;

    double ret = getthepower(x,n/2);
    ret = ret * ret;
    if(n%2!=0)
        ret = ret * x;
    return ret;
} //End getthepower method

/******************************************************
Purpose: Get the greatest common factor of two integers
In: a, b (the two integers)
Out: the greatest common factor (integer)
/******************************************************/ 
public static int getGreatestCommonFactor(int a, int b) {  
    if (b==0) {
        return a;
    } //End if
   return getGreatestCommonFactor(b,a%b);
} //End getGreatestCommonFactor method

} //End class