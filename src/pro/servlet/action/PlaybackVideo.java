package pro.servlet.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pro.utils.PlaybackClient;
import pro.dao.entity.PlaybackInfo;
//import java.net.InetAddress;


/**
 * Servlet implementation class PlaybackVideo
 */
//@WebServlet("/PlaybackVideo")
public class PlaybackVideo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlaybackVideo() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("this is doGet");
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("this is doPost");
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/x-json");
		response.setCharacterEncoding("utf-8");
				
		Timestamp dateTime = Timestamp.valueOf(request.getParameter("datetime"));
		String stream = request.getParameter("stream");
		// String ip = request.getParameter("IP");
		String ip = request.getRemoteAddr();
		System.out.println("datetime is "+dateTime +", stream is "+stream+", get remote IP is "+ip);
		
		Timestamp StartTime = new Timestamp(dateTime.getTime()-7000);
		Timestamp StopTime = new Timestamp(dateTime.getTime()+8000);
		System.out.println("start time is "+StartTime.toString()+", stop time is "+StopTime.toString());
		
		// streamId=1表示上游，2表示下游
		int streamId=1;
		if(stream.equals("下游")) {
			streamId=2;
		}
		
		PlaybackInfo info = new PlaybackInfo(streamId,StartTime.toString(),StopTime.toString(),ip);		
		PlaybackClient playback = new PlaybackClient(info);
		playback.setSocket();
		playback.dataTransmit();
		playback.closeSocket();		
	}
}
