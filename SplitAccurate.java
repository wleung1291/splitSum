/** Filename: SplitAccurate.java
  * Author: Wilson Leung
  * Date: 3/19/2017
  *
  * Description: A class that takes a single list of integers and divides it 
  *              into two separate lists where the sum of the integers in both 
  *              lists are equal or close to equal as accurrately as possible.
  **/

import java.util.*;
import java.lang.*; 
import java.io.*;


public class SplitAccurate
{

  /** Description: This method will take in an int array and find the sum of 
    *              equal or close to equal value between two arrays as 
    *              accurrately as possible.
    * Parameters: 
    *    integerList - The array to split.
    *
    * Return Value: A 2D array.
    **/ 
  public int[][] splitAccurately(int[] integerList) throws InvalidInputException{
    int arrLen = integerList.length; //size of the array passed in
    int revArr[] = reverse(integerList); // the reversed array. High to low.
    int arr1[] = new int[arrLen];
    int arr2[] = new int[arrLen];
    int sumArr1 = 0; // the sum of arr1 array
    int sumArr2 = 0; // the sum of arr2 array
    int i = 0; // to increment to the next int for processing

    // check if array is null
    if (integerList == null) {
      throw new InvalidInputException("Array is null");
    }
    // check if array is empty
    if (integerList.length == 0) {
      throw new InvalidInputException("Array is empty");
    }

    // places the next largest int into the array with the lower sum total
    while( i < revArr.length){ 
      for( int j = 0; j < arr1.length; j++){ 
        // has to iterate thgough
        if( Math.abs(sumArr1) < Math.abs(sumArr2) || sumArr1 == sumArr2){   
          arr1[j] = revArr[i]; // store value of revArr[i] into arr1
          sumArr1  = sumArr1 + arr1[j];
          i++; 
          //System.out.println("arr1 " + Arrays.toString(arr1));
        }
        else{
          arr2[j] = revArr[i]; 
          sumArr2  = sumArr2 + arr2[j];
          i++;
          //System.out.println("arr2 " + Arrays.toString(arr2));
        }
      }
    }

    // the difference between the sum of the two arrays
    int diff = Math.abs(Arrays.stream(arr1).sum() - Arrays.stream(arr2).sum());

    /* loops through arr1 and arr2 to determine which pair of ints have a 
     * difference of less than diff, then switch to mimimize difference.
     */  
    int tmpv = 0; // temp var to hold the int to swap
    for(int k = 0; k < arr1.length; k++){
      for(int l = 0; l < arr2.length; l++){
        diff = Math.abs(Arrays.stream(arr1).sum() - Arrays.stream(arr2).sum());
        //if difference between indices is less than the diff, switch
        if( Math.abs(arr1[k] - arr2[l]) < diff){
          tmpv = arr1[k];
          arr1[k] = arr2[l];
          arr2[l] = tmpv;
        }
      }
    }

    System.out.println(Arrays.stream(arr1).sum()); // the sum of arr1
    System.out.println(Arrays.stream(arr2).sum()); // the sum of arr2

    arr1 = removeZeros(arr1);
    arr2 = removeZeros(arr2);
    int splitArr[][] = {arr1,arr2}; //combine the two arrays into a 2D array
    System.out.println(Arrays.deepToString(splitArr)); //print 2D array
    return splitArr;
  }


  /** Description: This method will take in an int array and sort it from 
    *              least to greatest. It will then reverse that order.
    * Parameters: 
    *    array - The array to sort and reverse.
    *
    * Return Value: A sorted array.
    **/ 
  public int[] reverse(int[] array) {
    int revArr[] = array; //the array to return
    Arrays.sort(revArr);  // sort array from least to greatest
    int j = revArr.length - 1; //the last position in the array
    int tmp; //hold the greater value
    for (int i = 0; i < j; i++) { //loops switches primitives between i and j
      tmp = revArr[j];
      revArr[j] = revArr[i];
      revArr[i] = tmp;
      j--;
    }
    return revArr; // the sorted array from greatest to least
  }


  /** Description: This method will take in an int array and count the number
    *              of zeros, then create a copy of the array with non-zero ints.
    * Parameters: 
    *    array - The array to process.
    *
    * Return Value: An array without zeros.
    **/
  public int[] removeZeros(int[] array){
    int count =0;
    for(int i = 0; i < array.length; i++){ //get count of non-zero ints
      if(array[i] != 0){
        count++;
      }
    }
    int tmpArr[] = new int[count]; // array size without the zeros
    int index = 0; // the index to store the non-zero int
    for(int j = 0; j <array.length; j++){ //store non-zero ints into array
      if(array[j] != 0){
        tmpArr[index] = array[j];
        index++;
      }
    }//System.out.println("arr1" + Arrays.toString(tmpArr));
    return tmpArr;
  }

////////////////////////////////////////////////////////////////////////////////////
  public static void main( String[] args ) throws Exception
  {
    SplitAccurate f = new SplitAccurate();

    int intArr[] = {65,31,72,2,57,6,65,50};
    //int intArr[] = {200, 50,40,60,20,5};
    //int intArr[] = {200, 50,40,60,20,5,25};
    //int intArr[] = {9,8,7,6,5,4,3,2,1};
    //int intArr[] = {40,68,88,15,24,14,88,7,64};
    //int intArr[] = {12,64,10,67,99,92,78,16,2,66,90,32,21,15,81,14};
    //int intArr[] = {-2,-5,-8,-3,-4,-9};
    //int intArr[] = {-2,-50,14,78,-8,-3,40,-4,-9};
    //int intArr[] = {}; //empty array
    //int intArr[] = null;

    f.splitAccurately(intArr);
  }
}

