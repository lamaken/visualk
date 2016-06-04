/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visualk.html5.forms;

/**
 *
 * @author alex
 */
public class FormHtml5InputText extends FormHtml5InputTypeElement {

    public FormHtml5InputText(String name, String label, String help, String value) {

        setInputType(FormHtml5InputTypeElement.TEXT_TYPE);
        this.prepare(name, label, help, value);
    }
}
