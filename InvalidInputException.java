import java.util.*;
import java.lang.*;
import java.lang.Exception.*;

/** This class handles exceptions.
  * 
  * Author: Wilson Leung
  * Date: 3/18/2017
  *
  **/

public class InvalidInputException extends Exception
{
  //constructor	
  public InvalidInputException( String message ) {
    super(message); 
  }
}

