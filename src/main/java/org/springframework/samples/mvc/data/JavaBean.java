package org.springframework.samples.mvc.data;

public class JavaBean {

	private String param1;

	private String param2;

	private String param4;

	public String getParam1() {
		return param1;
	}

	public void setParam1(String param1) {
		System.out.println("setParam1");
		this.param1 = param1;
	}

	public String getParam2() {
		return param2;
	}

	public void setParam2(String param2) {
		this.param2 = param2;
	}

//	@Override
//	public String toString() {
//		return "JavaBean [param1=" + param1 + ", param2=" + param2 + ", param3=" + param3 + "]";
//	}

	public String getParam3() {
		return param4;
	}

	public void setParam3(String param3) {
		this.param4 = param3;
	}


}