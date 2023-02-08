package org.springframework.samples.mvc.mapping;

import javax.xml.bind.annotation.XmlRootElement;

//시작하기 전에 알아두기!

//자바 객체를 xml로 변환하는 작업을 직렬화(마샬링(Marshalling)),
//그 반대를 역직렬화(언마샬링(UnMarshalling)) 이라고 한다.
//이러한 자바 객체 <-> xml 간의 변환을 편하게 도와주는것이 JAXB api이다.
//JAXB(Java Architecture for XML Binding)

//@XmlRootElement 어노테이션은 자바Class에 사용하는 annotation으로,
//해당 클래스가 xml root라는 의미이다.
//name을 사용하여 특정 xml노드의 root임을 명시할 수 있다.

@XmlRootElement
public class JavaBean {

	private String foo ;

	private String fruit;

	public JavaBean() {
		// TODO Auto-generated constructor stub
	}




	public JavaBean(String foo, String fruit) {
		super();
		this.foo = foo;
		this.fruit = fruit;
	}




	public String getFoo() {
		return foo;
	}

	public void setFoo(String foo) {
		System.out.println("foo호출");
		this.foo = foo;
	}

	public String getFruit() {
		return fruit;
	}

	public void setFruit(String fruit) {
		System.out.println("fruit호출");
		this.fruit = fruit;
	}

	@Override
	public String toString() {
		return "HAHAHAHAHAHAHA JavaBean {foo=[" + foo + "], fruit=[" + fruit + "]}";
	}

}
