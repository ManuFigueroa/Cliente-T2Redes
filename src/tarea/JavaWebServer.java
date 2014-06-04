package tarea;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
//import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
//import java.net.UnknownHostException;
//import java.nio.charset.Charset;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.awt.Desktop;
import java.net.URI;








import org.apache.commons.io.IOUtils;

public class JavaWebServer 
{   
	private static final int fNumberOfThreads = 100;
	private static final Executor fThreadPool = Executors.newFixedThreadPool(fNumberOfThreads);
	private static int port = 8080; /* port to connect to  TCP*/
<<<<<<< HEAD
	private static String host = "localhost"; /* host to connect to TCP */
=======
	private static String host = "192.168.1.161"; /* host to connect to TCP */
>>>>>>> FETCH_HEAD
	
	//private static BufferedReader stdIn;

	//private static String nick;

	/**
	 * Read in a nickname from stdin and attempt to authenticate with the 
	 * server by sending a NICK command to @out. If the response from @in
	 * is not equal to "OK" go bacl and read a nickname again
	 */
	
	
	

	public static void main(String[] args) throws IOException 
	{ 

		ServerSocket socket = null;
		final Socket server = new Socket(host,port);
		
		try
		{
			
			int puerto = 8081;
			socket = new ServerSocket(puerto);
			

		    if(Desktop.isDesktopSupported())
		    {
		    	Desktop.getDesktop().browse(new URI("http://localhost:"+Integer.toString(puerto)));
		    }

			
			//ServerConn sc = new ServerConn(server);
		    //Thread t = new Thread(sc);
		    //t.start();
			
			
	  		while (true) 
	  		{ 
	  			final Socket connection = socket.accept();
	  			Runnable task = new Runnable() 
	  			{ 
	  				//@Override 
	  				public void run() 
	  				{ 
	  					
	  					//System.out.println("Socket : "+ server );
	  					HandleRequest(connection, server);
	  				} 
	  			};
				fThreadPool.execute(task);
			}
		}
		catch (IOException e)
		{			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		finally
		{
			try 
	        {
	        	socket.close();
	        } 
	        catch (IOException e1)
	        {
	            e1.printStackTrace(System.err);
	        }
		}
    }
   

	private static void HandleRequest(Socket s, Socket server) 
	{ 
		BufferedReader inHTTP;
		PrintWriter outHTTP;
		String request;
		
    
	    /* obtain an output stream to the server... */    
	    
 		try 
 		{
 			 	
 			PrintWriter out = new PrintWriter(server.getOutputStream(), true);
 			
		    /* ... and an input stream */
		    BufferedReader in = new BufferedReader(new InputStreamReader(
		                server.getInputStream()));
		    			
 			//String webServerAddress = s.getInetAddress().toString();
 			inHTTP = new BufferedReader(new InputStreamReader(s.getInputStream()));
 			
 			request = inHTTP.readLine();
 			
 			StringTokenizer st = new StringTokenizer(request);
 			//ESTO (method) DEFINE SI ES GET O POST
 			String method = st.nextToken();
 			
 	        String uri = decodePercent(st.nextToken());
 	        
 	        Properties parms = new Properties();
	        int qmi = uri.indexOf('?');
	        if (qmi >= 0) 
	        {
	        	decodeParms(uri.substring(qmi + 1), parms);
	        	uri = decodePercent(uri.substring(0, qmi));
	        }
 	        
	        Properties header = new Properties();
 			
 			if (st.hasMoreTokens()) 
 			{
 		        String line = inHTTP.readLine();
 		          while (line.trim().length() > 0) 
 		          {
 		        	  int p = line.indexOf(':');
 		        	  header.put(line.substring(0, p).trim().toLowerCase(), line.substring(p + 1).trim());
 		        	  line = inHTTP.readLine();
 		          }
 		    }
 			
 			//Si es POST entra, en caso contrario (GET) hace solo el resto
 			if (method.equalsIgnoreCase("POST")) 
 			{
 				long size = 0x7FFFFFFFFFFFFFFFl;
 		         
 		        String contentLength = header.getProperty("content-length");
 		        
 		        if (contentLength != null)
 		              size = Integer.parseInt(contentLength);
 		        

				String postLine = "";
				char buf[] = new char[512];
				int read = inHTTP.read(buf);
				while (read >= 0 && size > 0 && !postLine.endsWith("\r\n")) 
				{
				size -= read;
				postLine += String.valueOf(buf, 0, read);
				if (size > 0)
					read = inHTTP.read(buf);
 		        }
 		         
					postLine = postLine.trim();
 		          
					decodeParms(postLine, parms);
					
					String name = parms.getProperty("username");
					String dest = parms.getProperty("dest");
					String msg = parms.getProperty("msg");
					String arch = parms.getProperty("arch");
					if (name != null)
		 			{
						out.println("NICK " + name);
		 			}
					else if (dest != null && msg != null){
						out.println("MSG "+ dest +" " + msg);
		 			}
					else if (dest != null && arch != null){
						out.println("FILE "+ dest +" " + arch);
						 //DataInputStream input;
						BufferedInputStream bis;
						BufferedOutputStream bos;
						
						 int in1;
						 byte[] byteArray;
						 //Fichero a transferir						 
						try{
<<<<<<< HEAD
						 final File localFile = new File(arch);
=======
						 final File localFile = new File( arch );
						 @SuppressWarnings("resource")
						 Socket bla = new Socket(host,port);
>>>>>>> FETCH_HEAD
						 bis = new BufferedInputStream(new FileInputStream(localFile));
						 bos = new BufferedOutputStream(bla.getOutputStream());
						 //Enviamos el nombre del fichero
						 //DataOutputStream dos=new DataOutputStream(server.getOutputStream());
						 //dos.writeUTF(localFile.getName());
						 //Enviamos el fichero
						 byteArray = new byte[8192];
						 while ((in1 = bis.read(byteArray)) != -1){
						 bos.write(byteArray,0,in1);
						 }
<<<<<<< HEAD
						 if(server.isClosed()){
							System.out.println("ANTES DE CERRAR");
						 }
						 
=======
						
>>>>>>> FETCH_HEAD
						bis.close();
						//out.println("Cerre bis");
						bos.close();
<<<<<<< HEAD
						if(server.isClosed()){
							System.out.println("DPS DE CERRAR");
						}
						 
						 
=======
						//System.out.println("Cerre bos");
						//dos.close();
						//out.println("Cerre dos");
>>>>>>> FETCH_HEAD
						}catch ( Exception e ) {
							 System.err.println(e);
							
						}
						
						//System.out.println("Se llamaaaaa  "+ arch +" para:" + dest);
		 			}										
					
 		    }
 	        
 			
 			outHTTP = new PrintWriter(s.getOutputStream(), true);
 		 			
 			if (uri.equals("/"))
 			{
 				InputStream archivo = new FileInputStream ("login.html");
 	 			String form = IOUtils.toString(archivo, "UTF-8");
 				outHTTP.println(form);
 			}
 			else if (uri.equals("/chat")){
 				//System.out.println("cargue: "+uri );
 				InputStream archivo = new FileInputStream ("chat.html");
 	 			String form = IOUtils.toString(archivo, "UTF-8");
 				outHTTP.println(form);
 			}
 			if (uri.equals("/textochat"))
 			{
 				//System.out.println("cargue: "+uri );
 				out.println("PEDIR mensajes");
 				
 				String entremedio = in.readLine();
 				
 				InputStream archivo = new FileInputStream ("subchat.html");
 	 			String form = IOUtils.toString(archivo, "UTF-8");
 	 			form = form.replace("#REEMPLAZAR#", entremedio);
 				outHTTP.println(form);
 			}
 			if (uri.equals("/enviarchat"))
 			{
 				//System.out.println("cargue: "+uri );
 				
 				InputStream archivo = new FileInputStream ("msg.html");
 	 			String form = IOUtils.toString(archivo, "UTF-8");
 				outHTTP.println(form);
 			}
 			if (uri.equals("/subirarchivo"))
 			{
 				//System.out.println("cargue: "+uri );
 				
 				InputStream archivo = new FileInputStream ("archivos.html");
 	 			String form = IOUtils.toString(archivo, "UTF-8");
 				outHTTP.println(form);
 			}
 			
 			outHTTP.flush();
 			outHTTP.close();
 			s.close();
 		} 
 		catch (IOException e) 
 		{ 
 			System.out.println("Failed respond to client request: " + e.getMessage());
 		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
 		finally 
 		{ 
 			if (s != null) 
 			{ 
 				try 
 				{ 
 					s.close();
 				} 
 				catch (IOException e) 
 				{ 
 					e.printStackTrace();
 				} 
 			} 
 		} 
 		return;
 		
 	}
	
	private static String decodePercent(String str) {
	      try {
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < str.length(); i++) {
	          char c = str.charAt(i);
	          switch (c) {
	          case '+':
	            sb.append(' ');
	            break;
	          case '%':
	            sb.append((char) Integer.parseInt(str.substring(i + 1, i + 3), 16));
	            i += 2;
	            break;
	          default:
	            sb.append(c);
	            break;
	          }
	        }
	        return new String(sb.toString().getBytes());
	      } catch (Exception e) {
	        
	      }
		return str;
	}
	
	private static void decodeParms(String parms, Properties p) throws InterruptedException {
	      if (parms == null)
	        return;

	      StringTokenizer st = new StringTokenizer(parms, "&");
	      while (st.hasMoreTokens()) {
	        String e = st.nextToken();
	        int sep = e.indexOf('=');
	        if (sep >= 0)
	          p.put(decodePercent(e.substring(0, sep)).trim(), decodePercent(e.substring(sep + 1)));
	      }
	}

 }

