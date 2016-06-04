/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visualk.html5.forms;

import visualk.html5.LinkHtml;
import visualk.html5.UniqueName;

/**
 *
 * @author alex
 */
public class FormHtml5InputPassword extends FormHtml5InputTypeElement {

   
    public FormHtml5InputPassword(String name, String label, String help,String value) {
  
        setInputType(FormHtml5InputTypeElement.PASSWORD_TYPE);
        this.prepare(name,label,help,value);
    }
    
}


