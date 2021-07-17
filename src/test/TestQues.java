/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
import static java.lang.System.out;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.IntFunction;
/**
 *
 * @author USER
 */
public class TestQues 
{
	public static void main(String[] args){
		Map<Key, Integer> map = new HashMap<>();
		Key firstKey = new Key();
		for(int i=0; i<10;i++){
			map.put(new Key(), i);
		}
		
		map.get(firstKey);
		
		}
		
		static class Key{
		
			
                        @Override
			public int hashCode(){
				return 42;
			}
		}
		
		}
