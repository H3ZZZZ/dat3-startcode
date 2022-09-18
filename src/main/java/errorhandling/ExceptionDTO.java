/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package errorhandling;

/**
 *
 * @author jobe
 */
public class ExceptionDTO {
    private int code;
    private String message;

    private String stackTrace;
  public ExceptionDTO(int code, String description, String stackTrace){
      this.code = code;
      this.message = description;
      this.stackTrace = stackTrace;
  }
    
}
