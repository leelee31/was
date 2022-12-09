# was
WAS 구조와 Spring MVC 동작 방식을 학습하면서 개발한 프로젝트  

![IMG_5009](https://user-images.githubusercontent.com/45748683/206144757-cb2aee20-ba05-40b4-b4ea-589b13fae8c7.JPG)

## servletcontainer
* HTTP 요청 받음
* ApplicationContext 생성
* DispatcherServlet 생성 및 실행
* HTTP 응답 전달

### ApplicationContext
* Spring 컨테이너 역할
* Bean 역할 객체 생성, 의존성 주입

## servlet
### DispatcherServlet
* HandlerMapping을 이용하여 적절한 컨트롤러 선정
* HandlerAdapter를 이용하여 선정된 컨트롤러에 작업을 위임하고 결과값(ModelAndView)을 받음
* ModelAndView의 View 정보를 이용하여 적절한 html 파일 바이트화
* View의 바이트화된 정보를 응답에 저장

### handler
* HandlerMapping
  * UrlControllerHandlerMapping: url과 컨트롤러 클래스 경로를 매핑
  * 실제 Spring엔 BeanNameUrlHandlerMapping과 RequestMappingHandlerMapping(3.1버전 이상)이 기본으로 등록되어 있음
* HandlerAdapter
  * SimpleControllerHandlerAdapter: Controller 타입의 컨트롤러 호출
  * 실제 Spring엔 SimpleControllerHandlerAdapter가 디폴트이며  
  가장 인기있는 전략은 애노테이션을 사용하는 RequestMappingHandlerAdapter(3.1버전 이상)

## http
* HTTP 메세지 객체

## controller
* 요청을 해석하여 서비스 계층에 작업 위임
* 작업 수행 후의 정보값인 모델과 클라이언트에게 보여줄 화면인 뷰 정보를 담은 ModelAndView 인스턴스를 반환
