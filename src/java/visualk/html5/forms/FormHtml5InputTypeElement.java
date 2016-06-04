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
public class FormHtml5InputTypeElement extends FormHtml5Element {

    public static final String TEXT_TYPE = "text";
    public static final String CHECKBOX_TYPE = "checkbox";
    public static final String FILE_TYPE = "file";
    public static final String HIDDEN_TYPE = "hidden";
    public static final String IMAGE_TYPE = "image";
    public static final String PASSWORD_TYPE = "password";
    public static final String RADIO_TYPE = "radio";
    public static final String RESET_TYPE = "reset";
    public static final String SUBMIT_TYPE = "submit";
    public static final String COLOR_TYPE = "color";
    public static final String DATE_TYPE = "date";
    public static final String DATETIME_TYPE = "datetime";
    public static final String DATETIME_LOCAL_TYPE = "datetime-local";
    public static final String EMAIL_TYPE = "email";
    public static final String MONTH_TYPE = "month";
    public static final String NUMBER_TYPE = "number";
    public static final String RANGE_TYPE = "range";
    public static final String SEARCH_TYPE = "search";
    public static final String TEL_TYPE = "tel";
    public static final String TIME_TYPE = "time";
    public static final String URL_TYPE = "url";
    public static final String WEEK_TYPE = "week";
    private String inputType = "";

    
    public void prepare(String name, String label, String help,String value){
        //TODO: id:unique automatic
        String id;
        id = new UniqueName(8).getName();
        incTabOrder();
        
        setName(name);
        setElementType(FormHtml5Element.INPUT_TYPE);
        setHTMLElementControl("<" + FormHtml5Element.INPUT_TYPE + " value=\""+value+"\" tabindex=\""+getTabOrder()+"\" id=\"" + id + "\" name=\"" + getName() + "\" type=\"" + getInputType() + "\"/>");
        setHTMLElementLabel(new LinkHtml(new UniqueName(8).getName(), label, "#", "document.getElementById('" + id + "').focus();", help).toHtml());
    }

    /**
     * @return the inputType
     */
    public String getInputType() {
        return inputType;
    }

    /**
     * @param inputType the inputType to set
     */
    public void setInputType(String inputType) {
        this.inputType = inputType;
    }
}
