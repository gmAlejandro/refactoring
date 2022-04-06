package com.kreitek.refactor.mal;

class  Main
{
    public static void main(String args[])
    {
        System.out.println("=====================");
        System.out.println("Vamos a refactorizar!");
        System.out.println("=====================");
        Boolean valido;

        // creamos un DNI correcto
        DNI dniCorrecto = new DNI(TIPODNI.DNI, "11111111H", null);
        valido = dniCorrecto.validarDNI();
        System.out.println( "DNI " + dniCorrecto.numDNI + " es: " + valido.toString());

        // creamos un DNI incorrecto
        DNI dniIncorrecto01 = new DNI(TIPODNI.DNI, "24324356A", null);
        valido = dniIncorrecto01.validarDNI();
        System.out.println( "DNI " + dniIncorrecto01.numDNI + " es: " + valido.toString());

        // creamos un NIE correcto
        NIE nieCorrecto = new NIE(TIPODNI.NIE, "X0932707B", null);
        valido = nieCorrecto.validarNIE();
        System.out.println( "NIE " + nieCorrecto.numDNI + " es: " + valido.toString());

        // creamos un NIE incorrecto
        NIE nieIncorrecto = new NIE(TIPODNI.NIE, "Z2691139Z", null);
        valido = nieIncorrecto.validarNIE();
        debug( "NIE " + nieIncorrecto.numDNI + " es: " + valido.toString());

        // creamos un CIF correcto
        CIF cifCorrecto = new CIF(TIPODNI.CIF, "W9696294I", null);
        valido = cifCorrecto.validarCIF();
        debug( "CIF " + cifCorrecto.numDNI + " es: " + valido.toString());

        // creamos un CIF incorrecto
        CIF cifIncorrecto = new CIF(TIPODNI.CIF, "W9696294A", null);
        valido = cifIncorrecto.validarCIF();
        debug( "NIE " + cifIncorrecto.numDNI + " es: " + valido.toString());
    }

    public static void debug(String msg){
        System.out.println(msg);
    }
}