package com.uninpahu.devops;

/**
 * Clase de ejemplo utilizada en el laboratorio de Integración Continua y
 * Despliegue Continuo (CD - Taller 3).
 * Su propósito es tener lógica sencilla para validar con pruebas
 * unitarias dentro del pipeline de Jenkins (7 etapas).
 */
public class Calculadora {

    public int sumar(int a, int b) {
        return a + b;
    }

    public int restar(int a, int b) {
        return a - b;
    }

    public int multiplicar(int a, int b) {
        return a * b;
    }

    public double dividir(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("No es posible dividir por cero");
        }
        return (double) a / b;
    }

    public boolean esPrimo(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Calcula la potencia de base elevada a exponente.
     * Taller 3 - nueva funcionalidad para validar CD.
     * Ejecute Cambio de C a B
     */
    public long potencia(int base, int exponente) {
        if (exponente < 0) {
            throw new IllegalArgumentException("Exponente no puede ser negativo");
        }
        long resultado = 1;
        for (int i = 0; i < exponente; i++) {
            resultado *= base;
        }
        return resultado;
    }

    /**
     * Calcula el módulo (residuo) de la división.
     */
    public int modulo(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("No es posible calcular módulo por cero");
        }
        return a % b;
    }
}