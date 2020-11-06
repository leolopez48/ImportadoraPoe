
package com.utils;

import java.awt.TextField;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;

/**
 *
 * @author Natalia
 */
public class ValidarCampos {

    public ValidarCampos() {
    }
    
    //Validar solo numeros
    public void numbersOnly(KeyEvent evt)
    {
        if(!Character.isDigit(evt.getKeyChar())){
            evt.consume();
        }
    }
    
    //Validar solo palabras
    public void wordsOnly(KeyEvent evt)
    {
        if(!Character.isLetter(evt.getKeyChar()) && evt.getKeyChar() != KeyEvent.VK_SPACE){
            evt.consume();
        }
    }
    
    //validar solo numeros y punto
    public void numberAndPoint(KeyEvent evt,JTextField textField)
    {
        if(!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.'){
            evt.consume();
        }
        if(evt.getKeyChar()=='.'&& textField.getText().contains("."))
        {
            evt.consume();
        }
    }
}
