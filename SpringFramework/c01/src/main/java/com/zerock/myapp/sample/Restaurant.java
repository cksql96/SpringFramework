package com.zerock.myapp.sample;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import lombok.Data;



@Component			//Bean 객체로 등록할 클래스이면, 이 어노테이션을 준다.

@Data
//@AllArgsConstructor
public class Restaurant {
	
	//주입신호방법(시그널)		-스프링 콘테이너에다가 주입함. Restaurant가 Chef라는 클래스를 의존한다는것을.
//	@Resource								//3번째 	방법
//	@Resource(type=FQCN)					//3.1번째방법
	@Inject									//2번째 	방법
//	@Autowired								//1번째 	방법
//	@Setter(onMethod_ = {@Autowired})		//4번째 	방법
//	@Setter				//-이걸로 하면, 실행할때, Test에서는, null값이 들어간다.

	private Chef chef;

}	//end class
