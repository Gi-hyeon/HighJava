package kr.or.ddit.basic;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {
	public static void main(String[] args) throws UnknownHostException {
		// InetAddress 클래스 -> IP 주소를 다루기 위한 클래스
		
		// www.naver.com
		InetAddress ip = InetAddress.getByName("www.naver.com");
		
		System.out.println("HostName : " + ip.getHostName());
		// 똑같은 naver인데 ip가 다르다 -> ip가 부족하면 네이버의 다른 ip가 접속된다. 
		System.out.println("HostAddress : " + ip.getHostAddress());
		System.out.println("toString : " + ip.toString());
		System.out.println();
		
		// 자신 컴퓨터의 IP정보 가져오기
		InetAddress localIp = InetAddress.getLocalHost();
		System.out.println("HostName : " + localIp.getHostName());
		System.out.println("HostAddress : " + localIp.getHostAddress());
		System.out.println("toString : " + localIp.toString());
		System.out.println();
		
		// IP주소가 여러개인 호스트의 정보 가져오기 -> 더 많은 IP가 존재한다.
		InetAddress[] ipArr = InetAddress.getAllByName("www.naver.com");
		for (InetAddress inetAddress : ipArr) {
			System.out.println("HostName : " + inetAddress.getHostName());
			System.out.println("HostAddress : " + inetAddress.getHostAddress());
			System.out.println("toString : " + inetAddress.toString());
		}
		System.out.println();
		
		// 
	}
}
