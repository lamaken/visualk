/**
 *
 */
package visualk.html5.forms;

import visualk.html5.ClassCSS;
import visualk.html5.DivHtml;

/**
 * @author lamaken
 *
 */
public class FormHtml5 {

    public static String AUTOCOMPLETE_ON = "on";
    public static String AUTOCOMPLETE_OFF = "off";
    private final String OPEN_FORM = "<form method=post ";
    private final String CLOSE_FORM = "</form>";
    private String formHtml = "";
    private String formElements = "";
    private String name;
    private String where;
    
    private String autocomplete;
    private ClassCSS css;

    public void addFormElement(FormHtml5Element element) {
        if (getFormElements().equals("")) {
            setFormElements("<tr><td>" + element.toHtml("</td><td>") + "</td></tr>");
        } else {
            setFormElements(getFormElements() + "<tr><td>" + element.toHtml("</td><td>") + "</td></tr>");
        }
    }

    private void genForm(String servletName) {

        setFormHtml(OPEN_FORM);
        setFormHtml(getFormHtml() + "action=\"" + servletName+ "\" name=\"" + getName() + "\" autocomplete=\"" + getAutocomplete() + "\">");
        
        /*
        //////////////// header form
        setFormHtml(getFormHtml()
                + "<input type=\"hidden\" name=\"from\" value=\""+CMCB.MODULE_LOGIN+"\"/>"
                + "<input type=\"hidden\" name=\"to\" value=\""+CMCB.MODULE_MANTENIMENT+"\"/>");
        //////////////// user form
        
        */
        
        setFormHtml(getFormHtml() + "<table cellspacing=1 cellpadding=4 style=\"background:rgb(210,210,210);\" width=100% height=100% border=1><tr><td>");
        setFormHtml(getFormHtml() + "<table cellspacing=1 cellpadding=4 style=\"background:rgb(230,230,230);\" width=100% height=100% border=0><tr><td>");

        setFormHtml(getFormHtml() + getFormElements());
        setFormHtml(getFormHtml() + "<tr><td colspan=2>" + "<input type=\"submit\"/></td></tr>");

        setFormHtml(getFormHtml() + "</td></tr></table></td></tr></table>");

        setFormHtml(getFormHtml() + CLOSE_FORM);


    }

    public FormHtml5( String name, String autocomplete, ClassCSS formCSS, String from, String to) {
        css = formCSS;
        setFormElements("");
        setName(name);
        setAutocomplete(autocomplete);
    }

    public String toHtml() {
        genForm(getName());
        return (new DivHtml(css.getId()).toHtml(getFormHtml()));

    }

    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param autocomplete the autocomplete to set
     */
    public void setAutocomplete(String autocomplete) {
        this.autocomplete = autocomplete;
    }

    /**
     * @return the autocomplete
     */
    public String getAutocomplete() {
        return autocomplete;
    }

    /**
     * @return the formHtml
     */
    public String getFormHtml() {
        return formHtml;
    }

    /**
     * @param formHtml the formHtml to set
     */
    public void setFormHtml(String formHtml) {
        this.formHtml = formHtml;
    }

    /**
     * @return the formElements
     */
    public String getFormElements() {
        return formElements;
    }

    /**
     * @param formElements the formElements to set
     */
    public void setFormElements(String formElements) {
        this.formElements = formElements;
    }
}

/*
 * 
<form action="demo_form.asp" autocomplete="on">
  First name:<input type="text" name="fname"><br>
  Last name: <input type="text" name="lname"><br>
  E-mail: <input type="email" name="email" autocomplete="off"><br>
  <input type="submit">
</form>
 * 
 */