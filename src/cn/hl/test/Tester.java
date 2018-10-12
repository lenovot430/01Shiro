package cn.hl.test;

import java.util.Scanner;

import org.junit.Test;

public class Tester {

	@Test
	public void test() {
		for(int i=0;i<5;i++) {
			for(int j=5;j>i;j--) {
				System.out.print("*");	
			}
			System.out.println();	
		}
	}
	
	@Test
	public void test1() {
		
		int a=2<<4;
		
		System.out.println(a);
	}
	

	public static void sort() {
		Scanner input=new Scanner(System.in);
		int sort[] =new int[10];
		int temp;
		
		System.out.println("请输入十个排序的数据：");
		
		for(int i=0;i<sort.length;i++) {
			sort[i]=input.nextInt();
		}
		for(int i=0;i<sort.length-1;i++) {
			for(int j =0;j<sort.length-i-1;j++) {
				if(sort[j]<sort[j+1]) {
					temp=sort[j];
					sort[j]=sort[j+1];
					sort[j+1]=temp;
				}
			}
		}
		System.out.println("排序后的选项：");
		
		for(int i=0;i<sort.length;i++) {
			System.out.println(sort[i]+"");
		}
	}
	
	public static void main(String[] args) {
		sort();
	}
	
}
