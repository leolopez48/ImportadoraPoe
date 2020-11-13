
package com.utils;

import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextField;

/**
 * NombreClase: ValidarCampos
 * Fecha: 13/11/2020 
 * Versión: 1.0 
 * Copyright:ITCA-FEPADE
 * @author Leonel Antonio López Valencia - 040119 
 * Roberto Alejandro Armijo Jímenez - 046719 S
 * andra Natalia Menjívar Romero - 174218
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
    
    public boolean isEmail(String correo)
    {
    Pattern pat=null;
    Matcher mat=null;
    //pat = Pattern.compile("^[\\w\\-\\_\\+]+(\\.[\\w\\-\\_]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z](2,4)$");
    //pat= Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    pat = Pattern.compile("^[\\w\\\\\\+]+(\\.[\\w\\\\]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$");
    mat=pat.matcher(correo);
    if(mat.find())
    {
        //System.out.println(correo);
        return true;
    } else {
    return false;
    }

    }
}
