/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.SortedSet;

/**
 *
 * @author USER
 */
public class TestList {

    public static void main(String args[]) {

        List l = new ArrayList();
        Set s = new HashSet();
        l.add(3);
        l.add(1);
        l.add(4);
        l.add(2);
        
        s.add(3);
        s.add(1);
        s.add(4);
        s.add(2);
        
        System.out.println(l);
        System.out.println(s);
        
        SortedSet ss;
        NavigableSet  ns;
    }

}
