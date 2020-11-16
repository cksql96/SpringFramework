package com.zerock.ex;

//POJO : Plain Old Java Object(아주 평범한 클래스)

//JavaBeans Class		-->> 여기서 만든 객체 : 'Bean' 객체.
//Get/Set + "이름"---> 프로퍼티(properties,속성)가 생성된다.
//필드의 이름과 프로퍼티의 이름은 달라도 된다.
//따라서 필드의 이름을 숨길때, private을 넣어주고, 필드의 이름과 프로퍼티를 다르게 해줘서 숨길수 있다.
public class Bean {

	//Bean 클래스는, 생성자가 여러개 있을수 있어도,
	//반드시 default constructor가 필요하다. 
	//또한 각 필드에 대해서, getter/setter가 필요하다.
	
	String name;
	int age;
	
	
	public Bean() {
		
	}	//default constructor
	

	public String getName() {
		return name;
	}	//getName

	public void setName(String name) {
		this.name = name;
	}	//setName

	public int getAge() {
		return age;
	}	//getAge

	public void setAge(int age) {
		this.age = age;
	}	//setAge

}	//end class

