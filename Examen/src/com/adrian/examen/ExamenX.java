package com.adrian.examen;

import java.util.Scanner;

public class ExamenX {
	
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingresa un número para el tamaño de las esquinas de la X: ");
        int size = scanner.nextInt();
        
        int xSize = size * 2 - 1; // Del numero ingresado, *2-1 que seria el caracter central
        
        dibujarX(size, xSize);
    }
    
    public static void dibujarX(int size, int xSize) {
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < xSize; j++) {
                if (esDiagonalPrincipal(i, j, xSize) || esDiagonalSecundaria(i, j, xSize)) {
                    if (esEsquinaSuperiorIzquierda(i, j, size) || esEsquinaSuperiorDerecha(i, j, size) ||
                        esEsquinaInferiorIzquierda(i, j, size) || esEsquinaInferiorDerecha(i, j, size)) {
                        System.out.print("X");
                    } else {
                        System.out.print(" ");
                    }
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
    
    public static boolean esDiagonalPrincipal(int i, int j, int xSize) {
        return i == j;
    }
    
    public static boolean esDiagonalSecundaria(int i, int j, int xSize) {
        return i + j == xSize - 1;
    }
    
    public static boolean esEsquinaSuperiorIzquierda(int i, int j, int size) {
        return i < size && j == i;
    }
    
    public static boolean esEsquinaSuperiorDerecha(int i, int j, int size) {
        return i < size && j == size * 2 - 2 - i;
    }
    
    public static boolean esEsquinaInferiorIzquierda(int i, int j, int size) {
        return i >= size && j == i;
    }
    
    public static boolean esEsquinaInferiorDerecha(int i, int j, int size) {
        return i >= size && j == size * 2 - 2 - i;
    }
}