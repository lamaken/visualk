/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visualk.html5.forms;

/**
 *
 * @author alex
 */
public class FormHtml5Element {
    
    
   public static final String INPUT_TYPE="input";
   public static final String BUTTON_TYPE="button";
   public static final String PROGRESS_TYPE="progress";
   public static final String LABEL_TYPE="label";
   public static final String TEXTAREA_TYPE="textarea";
   public static final String OUTPUT_TYPE="output";
    
    

    
    private String HTMLElementLabel="";
    private String HTMLElementControl="";
   
    private String name="";
    private String ElementType="";
    
   private static int taborder=0;
   
   public void incTabOrder(){
       taborder++;
   }
   public int getTabOrder(){
       return(taborder);
   }
    
    String toHtml(String concat) {
        return getHTMLElementLabel()+concat+getHTMLElementControl();
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
     * @return the ElementType
     */
    public String getElementType() {
        return ElementType;
    }

    /**
     * @param ElementType the ElementType to set
     */
    public void setElementType(String ElementType) {
        this.ElementType = ElementType;
    }

    /**
     * @return the HTMLElementLabel
     */
    public String getHTMLElementLabel() {
        return HTMLElementLabel;
    }

    /**
     * @param HTMLElementLabel the HTMLElementLabel to set
     */
    public void setHTMLElementLabel(String HTMLElementLabel) {
        this.HTMLElementLabel = HTMLElementLabel;
    }

    /**
     * @return the HTMLElementControl
     */
    public String getHTMLElementControl() {
        return HTMLElementControl;
    }

    /**
     * @param HTMLElementControl the HTMLElementControl to set
     */
    public void setHTMLElementControl(String HTMLElementControl) {
        this.HTMLElementControl = HTMLElementControl;
    }
    
}
