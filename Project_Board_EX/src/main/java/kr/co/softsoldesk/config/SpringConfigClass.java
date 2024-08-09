package kr.co.softsoldesk.config;

import javax.servlet.FilterRegistration;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.apache.jasper.runtime.ProtectedFunctionMapper;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class SpringConfigClass implements WebApplicationInitializer{
//WebApplicationInitializer: web.xml 없이 자바 클래스를 통해 웹 애플리케이션 설정, 애플리케이션 시작 시 자동 실행

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		AnnotationConfigWebApplicationContext servletAppContext = new AnnotationConfigWebApplicationContext();
		servletAppContext.register(ServletAppContext.class);
		
		DispatcherServlet dispatcherServlet = new DispatcherServlet(servletAppContext);
		//DispatcherServlet: 요청 발생 시 요청을 처리하는 서블릿을 DispatcherServlet으로 설정
		ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", dispatcherServlet);
		
		servlet.setLoadOnStartup(1);//서블릿의 로드 순서, 서버가 시작될 때 바로 로드
		servlet.addMapping("/");//모든 요청을 해당 서블릿이 처리
		
//=================================================================================================================
		
		AnnotationConfigWebApplicationContext rootAppContext = new AnnotationConfigWebApplicationContext();
		rootAppContext.register(RootAppContext.class);
		
		ContextLoaderListener listener = new ContextLoaderListener(rootAppContext);
		servletContext.addListener(listener);
		//웹에서 발생되는 이벤트 처리 설정
		
//=================================================================================================================
		
		FilterRegistration.Dynamic filter = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
		//파라미터 인코딩 설정
		filter.setInitParameter("encoding", "utf-8");
		filter.addMappingForServletNames(null, false, "dispatcher");
		
//=================================================================================================================
		
		MultipartConfigElement multipartConfig = new MultipartConfigElement(null, 52428800, 524288000, 0);
		//null: 사용자 입력한 내용을 임시기억할 톰켓에서 제공하는 서버의 임시 기억장소
		//52428800: 업로드 파일 용량을 50MB로 설정
		//524288000: 전체용량 500MB로 설정
		//0: 파일의 임계값(자동저장)
		servlet.setMultipartConfig(multipartConfig);
	}	

}
