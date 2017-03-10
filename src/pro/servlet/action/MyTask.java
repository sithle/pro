package pro.servlet.action;
import java.util.*; 
import javax.servlet.ServletContextEvent; 
import javax.servlet.ServletContextListener; 
import javax.servlet.ServletException; 
import javax.servlet.ServletRequest; 
import javax.servlet.ServletResponse; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.*; 

public class MyTask extends TimerTask { 
 // private static final int C_SCHEDULE_HOUR = 0; 
  private static boolean isRunning = false; 
  private ServletContext context = null; 

  public MyTask() { 
  } 
  public MyTask(ServletContext context) { 
    this.context = context; 
  } 

  public void run() { 
    //Calendar cal = Calendar.getInstance(); 
    if (!isRunning) { 
      
        isRunning = true; 
        context.log("开始执行指定任务"); 
        //TODO 添加自定义的详细任务
        System.out.println("Test Test" );

        isRunning = false; 
        context.log("指定任务执行结束"); 
      
    } 
    else { 
      context.log("上一次任务执行还未结束"); 
    } 
  } 

}