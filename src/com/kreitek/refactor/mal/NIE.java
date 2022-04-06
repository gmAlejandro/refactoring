package com.kreitek.refactor.mal;

import java.util.Date;

public class NIE {

    public TIPODNI tipodni;
    public String numDNI;
    public Date fchValidez;


    public NIE(TIPODNI tipo, String numDNI, Date fchValidez) {
        this.tipodni = tipo;
        this.numDNI = numDNI;
        this.fchValidez = fchValidez;
    }



    public boolean validarNIE() {
                boolean esValido = false;
                int i = 1;
                int caracterASCII = 0;
                char letra = ' ';
                int miNIE = 0;
                int resto = 0;
                char[] asignacionLetra = {
                        'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'
                };

                if (this.numDNI.length() == 9 && Character.isLetter(this.numDNI.charAt(8)) &&
                        this.numDNI.substring(0, 1).toUpperCase().equals("X") ||
                        this.numDNI.substring(0, 1).toUpperCase().equals("Y") ||
                        this.numDNI.substring(0, 1).toUpperCase().equals("Z")) {

                    do {
                        caracterASCII = this.numDNI.codePointAt(i);
                        esValido = (caracterASCII > 47 && caracterASCII < 58);
                        i++;
                    } while (i < this.numDNI.length() - 1 && esValido);
                }

                if (esValido && this.numDNI.substring(0, 1).equalsIgnoreCase("X")) {
                    this.numDNI = "0" + this.numDNI.substring(1, 9);
                } else if (esValido && this.numDNI.substring(0, 1).equalsIgnoreCase("Y")) {
                    this.numDNI = "1" + this.numDNI.substring(1, 9);
                } else if (esValido && this.numDNI.substring(0, 1).equalsIgnoreCase("Z")) {
                    this.numDNI = "2" + this.numDNI.substring(1, 9);
                }

                if (esValido) {
                    letra = Character.toUpperCase(this.numDNI.charAt(8));
                    miNIE = Integer.parseInt(this.numDNI.substring(1, 8));
                    resto = miNIE % 23;
                    esValido = (letra == asignacionLetra[resto]);
                }

                if (esValido) {
                    return true;
                } else {
                    return false;
                }

    }


}