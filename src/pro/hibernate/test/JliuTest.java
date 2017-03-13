package pro.hibernate.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

public class JliuTest {

	@Test
	public void test() {
		Map<String, Integer> map = new HashMap<String, Integer>();  
		  
		map.put("lisi", 5);   
		map.put("lisi1", 1);   
		map.put("lisi2", 3);   
		map.put("lisi3", 9);   
		  
		List<Map.Entry<String, Integer>> infoIds = new ArrayList<Map.Entry<String, Integer>>(  
		        map.entrySet());  
		System.out.println("--------------排序前--------------");  
		for (int i = 0; i < infoIds.size(); i++) {  
		    String id = infoIds.get(i).toString();  
		    System.out.println(id);  
		}  
		// 排序  
		Collections.sort(infoIds, new Comparator<Map.Entry<String, Integer>>() {  
		    public int compare(Map.Entry<String, Integer> o1,  
		            Map.Entry<String, Integer> o2) {  
		        return ( o1.getValue()-o2.getValue());  
		    }  
		});  
		System.out.println("--------------排序后--------------");  
		for (int i = 0; i < infoIds.size(); i++) {  
		    Entry<String,Integer> ent=infoIds.get(i);  
		    System.out.println(ent.getKey()+"="+ent.getValue());
	}

}
}
