package com.kreitek.refactor.mal;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DNI {

    public TIPODNI tipodni;
    public String numDNI;
    public Date fchValidez;


    public DNI(TIPODNI tipo, String numDNI, Date fchValidez) {
        this.tipodni = tipo;
        this.numDNI = numDNI;
        this.fchValidez = fchValidez;
    }

    public Boolean validarDNI() {
        String dniChars = "TRWAGMYFPDXBNJZSQVHLCKE";
        String intPartDNI = this.numDNI.trim().replaceAll(" ", "").substring(0, 8);
        char ltrDNI = this.numDNI.charAt(8);
        int valNumDni = Integer.parseInt(intPartDNI) % 23;
        return this.numDNI.length() == 9 && isNumeric(intPartDNI) && dniChars.charAt(valNumDni) == ltrDNI;

    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}