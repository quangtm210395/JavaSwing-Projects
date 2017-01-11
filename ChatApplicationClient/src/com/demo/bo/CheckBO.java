/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.bo;

import com.demo.entity.User;
import java.util.ArrayList;

/**
 *
 * @author Tran Minh Quang
 */
public class CheckBO {
    public static boolean isUserName(String username) {
        if (username.length() < 6 || username.length() >= 16) {
            return false;
        }
        for (int i = 0; i < username.length(); i++) {
            if (!Character.isLetter(username.charAt(i)) && !Character.isDigit(username.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isPassWord(String pass) {
        String REGEX = "!@";
        int upLetter = 0;
        int specLetter = 0;
        int digit = 0;
        for (int i = 0; i < pass.length(); i++) {
            if (pass.charAt(i) == Character.toUpperCase(pass.charAt(i))) {
                upLetter++;
            }
            if (REGEX.contains(pass.charAt(i)+"")) {
                specLetter++;
            }
            if (Character.isDigit(pass.charAt(i))) {
                digit++;
            }
        }
        System.out.println(upLetter +" "+specLetter + " " + digit);
        return upLetter != 0 && specLetter != 0 && digit != 0 && pass.length() >= 8;
    }
    
    public static boolean isName(String name) {
        if (name.length() < 6) {
            return false;
        }
        for (int i = 0; i < name.length(); i++) {
            if (!Character.isLetter(name.charAt(i)) && name.charAt(i) != ' ') {
                return false;
            }
        }
        return true;
    }
    
    public static String normalForm(String name) {
        name = name.trim().replaceAll("//s+"," ");
        char[] c = name.toCharArray();
        c[0] = Character.toUpperCase(c[0]);
        for (int i = 1; i < c.length-1; i++) {
            if (c[i]==' ') {
                c[i+1] = Character.toUpperCase(c[i+1]);
            }
        }
        name = new String(c);
        return name;
    }
    
    public static boolean exists(ArrayList<User> list, User user) {
        if (list.isEmpty()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUsername().equals(user.getUsername())) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean isMatch(ArrayList<User> list, User user) {
        if (!exists(list, user)) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUsername().equals(user.getUsername())) {
                if(list.get(i).getPassword().equals(user.getPassword())){
                    user.setName(list.get(i).getName());
                    return true;
                } else return false;
            }
        }
        return false;
    }
    
}
