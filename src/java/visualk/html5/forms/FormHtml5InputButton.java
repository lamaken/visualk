/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visualk.html5.forms;

import visualk.html5.LabelHtml;

/**
 *
 * @author alex
 */
public class FormHtml5InputButton extends  FormHtml5InputTypeElement{
    public FormHtml5InputButton(String name,String label, String value) {
        setInputType(FormHtml5InputTypeElement.BUTTON_TYPE);
        this.prepare(name,label,"",value); }
}
