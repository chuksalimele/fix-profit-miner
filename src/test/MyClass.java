/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

class MyClass<A> {

    <B> MyClass(B b) {
        //...
    }
    
    void test(){
        Integer t;
       // new MyClass<Integer>();
       MyClass<Integer> myObject = new MyClass<>("");
       //MyClass<Integer> myObject = new <String> MyClass<>();
       //MyClass<Integer> myObject = new <> MyClass("");
       new MyClass<Integer>("");
       
    }
}
