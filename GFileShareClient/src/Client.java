
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

class Client {
	public Client(){
		try {
			Socket s=new Socket("117.204.171.210",9987);
			File file=new File("/home/gaurav/mnt/trial.zip");
			InputStream in = s.getInputStream();
	        OutputStream out = new FileOutputStream(file);
	        copy(in, out);
	        out.close();
	        in.close();
			System.out.println(file.getName()+" "+file.length());
			UnZip unZip = new UnZip();
	    	unZip.unZipIt("/home/gaurav/mnt/"+file.getName(),"/home/gaurav/mnt");
			//System.out.println(file.getName());
	    	out.close();
	    	in.close();
			file.delete();
		} catch (Exception e) {
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
		new Client();
	}
}
