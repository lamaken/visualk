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
public class FormHtml5InputEmail extends FormHtml5InputTypeElement{
    
    public FormHtml5InputEmail(String name, String label, String help, String value) {
       
        setInputType(FormHtml5InputTypeElement.EMAIL_TYPE);
        this.prepare(name,label,help, value);
    }
}
