package org.springframework.samples.mvc.convert;

public final class SocialSecurityNumber {


	@MaskFormat("###-##-####")
	private final String value;


	public SocialSecurityNumber(String value) {
		this.value = value;
	}

	//@MaskFormat("###-##-####")
	public String getValue() {
		return value;
	}

	public static SocialSecurityNumber valueOf(@MaskFormat("###-##-####") String value) {
		return new SocialSecurityNumber(value);
	}

	/*
	 * @Override public String toString() { return "SocialSecurityNumber [value=" +
	 * value + "]"; }
	 */
	/*
	@Override
	public String toString()  {
		String retVal= null ;
		try {
			retVal = new MaskFormatter("###-##-####").valueToString("123-45-6789");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retVal;
	}
	*/

}