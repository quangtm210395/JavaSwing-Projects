/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.entity;

import java.awt.Insets;

/**
 *
 * @author Tran Minh Quang
 */
public class Properties {
    private static final int screenWidth = 500;
    private static final int screenHeight = 300;
    public static final Insets screenBorder = new Insets(10, 15, 10, 15);

    public static int getScreenWidth() {
        return screenWidth;
    }

    public static int getScreenHeight() {
        return screenHeight;
    }
    
    
}
