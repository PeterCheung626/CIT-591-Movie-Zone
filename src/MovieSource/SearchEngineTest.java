/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MovieSource;

/**
 *
 * @author yufancheng
 */

public class SearchEngineTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExactSearchEngine s = new ExactSearchEngine(9538);
		String res = s.connect();
		s.reset(9826);
		s.connect();
		System.out.println(s.getMovie().getPoster());
		System.out.println(res);
		//BlurSearchEngine b = new BlurSearchEngine(284053);
		//b.connect();
		

	}

}

