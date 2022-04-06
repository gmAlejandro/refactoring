package com.kreitek.refactor.mal;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CIF {

    public TIPODNI tipodni;
    public String numDNI;
    public Date fchValidez;


    public CIF(TIPODNI tipo, String numDNI, Date fchValidez) {
        this.tipodni = tipo;
        this.numDNI = numDNI;
        this.fchValidez = fchValidez;
    }


    public boolean validarCIF() {

                if (this.numDNI != null) {
                    final String cifUP = this.numDNI.toUpperCase();

                    if ("ABCDEFGHJKLMNPQRSUVW".indexOf(cifUP.charAt(0)) == -1) {
                        return false;
                    }

                    final Pattern mask = Pattern
                            .compile("[ABCDEFGHJKLMNPQRSUVW][0-9]{7}[A-Z[0-9]]{1}");
                    final Matcher matcher = mask.matcher(cifUP);
                    if (!matcher.matches()) {
                        return false;
                    }

                    final char primerCar = cifUP.charAt(0);
                    final char ultimoCar = cifUP.charAt(cifUP.length() - 1);

                    TipoUltCaracter tipUltCar;

                    tipUltCar = getTipoUltCaracter(primerCar, ultimoCar);
                    if (tipUltCar == null) return false;
                    final String digitos = cifUP.substring(1, cifUP.length() - 1);
                    Integer pares = sumarPares(digitos);
                    Integer impares = sumarImpares(digitos);
                    final int total = pares + impares;
                    int numControl = 10 - (total % 10);

                    int pos = numControl == 10 ? 0 : numControl;
                    final char carControl = "JABCDEFGHI".charAt(pos);

                    if (tipUltCar == TipoUltCaracter.NUMERO) {

                        final int ultCar = Integer.parseInt(Character
                                .toString(ultimoCar));
                        return pos == ultCar;

                    } else if (tipUltCar == TipoUltCaracter.LETRA) {
                        return carControl == ultimoCar;

                    } else {
                        int ultCar = "JABCDEFGHI".indexOf(ultimoCar);

                        if (ultCar < 0) {
                            ultCar = Integer.parseInt(Character.toString(ultimoCar));
                            if (pos != ultCar) {
                                return false; // NOK
                            }
                        }
                        return (pos == ultCar) || (carControl == ultimoCar); // NOK
                    }
                }
                return false;

    }

    private TipoUltCaracter getTipoUltCaracter(char primerCar, char ultimoCar) {
        TipoUltCaracter tipUltCar;
        if (primerCar == 'P' || primerCar == 'Q' || primerCar == 'S' || primerCar == 'K' || primerCar == 'W') {
            tipUltCar = TipoUltCaracter.LETRA;
            if (!(ultimoCar >= 'A' && ultimoCar <= 'Z')) {
                return null;
            }

        } else if (primerCar == 'A' || primerCar == 'B' || primerCar == 'E' ||
                primerCar == 'H') {
            tipUltCar = TipoUltCaracter.NUMERO;
            if (!(ultimoCar >= '0' && ultimoCar <= '9')) {
                return null;
            }

        } else {
            tipUltCar = TipoUltCaracter.AMBOS;
        }
        return tipUltCar;
    }

    private Integer sumarImpares(String digitos) {
        int suma = 0;
        for (int i = 0; i <= digitos.length() - 1; i = i + 2) {
            int cal = Integer.parseInt(digitos.substring(i, i + 1)) * 2;
            if (Integer.toString(cal).length() > 1) {
                cal = Integer.parseInt(Integer.toString(cal).substring(0, 1)) +
                        Integer.parseInt(Integer.toString(cal).substring(1, 2));
            }
            suma += cal;
        }
        return suma;
    }

    private Integer sumarPares(String digitos) {
        int suma = 0;
        for (int i = 1; i <= digitos.length() - 1; i = i + 2) {
            suma += Integer.parseInt(digitos.substring(i, i + 1));

        }
        return suma;
    }


}