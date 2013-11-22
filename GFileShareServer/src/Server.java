import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


class Server {
	ServerSocket ss;
	public Server(){
		try{
			ss=new ServerSocket(9987);
			System.out.println("Server started");
			Socket s=ss.accept();			
						
			AppZip appZip = new AppZip("/home/gaurav/workspace","/home/gaurav/trial.zip");
	    	appZip.generateFileList(new File("/home/gaurav/workspace"));
	    	appZip.zipIt("/home/gaurav/trial.zip");
			
	    	File file = new File("/home/gaurav/trial.zip");
	    	InputStream in = new FileInputStream(file);
	        OutputStream out = s.getOutputStream();	        
			copy(in, out);
	        out.close();
	        in.close();
	        
			System.out.println(file.length());
			System.out.println("Client Accepted");
			ss.close();
		}catch(Exception e){
			System.out.println(e);
		}		
	}
	
	void copy(InputStream in, OutputStream out){
        byte[] buf = new byte[8192];
        int len = 0,tot = 0;
        try{
	        while ((len = in.read(buf)) != -1) {
	            out.write(buf, 0, len);
	            tot+=len;
	            System.out.println("Read "+((double)(tot)/1000000)+" MB");
	        }
        }catch(Exception e){
        	System.out.println(e);
        }
    }
}

class Main{
	public static void main(String[] args){
		new Server();
	}
}