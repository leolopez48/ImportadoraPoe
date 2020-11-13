
package com.utils;

/**
 * NombreClase: ComboItem  
 * Fecha: 13/11/2020 
 * Versión: 1.0 
 * Copyright:ITCA-FEPADE
 * @author Leonel Antonio López Valencia - 040119 
 * Roberto Alejandro Armijo Jímenez - 046719 S
 * andra Natalia Menjívar Romero - 174218
 */
public class ComboItem {
    private int value;
    private String label;

    public ComboItem() {
    }

    public ComboItem(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
       return label;
    }
    
    
}
