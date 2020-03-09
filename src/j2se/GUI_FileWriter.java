package j2se;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.BufferedReader;

public class GUI_FileWriter {
	public static void read(int[] place){
		File file1=new File("e:/h.txt");            //这个程序可以保存关闭时候的位置
		try{
			new FileReader(file1);
		}catch(FileNotFoundException e){
			
		}
		try {
			file1.createNewFile();
			System.out.println("不存在文件，创建成功");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try(
				FileReader fr=new FileReader(file1);
				BufferedReader br=new BufferedReader(fr);
			){
			for(int i=0;;i++){
				String line=br.readLine();
				if(line==null)
					break;
				place[i]=Integer.valueOf(line);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public static void write(int[] place){
		File file1=new File("e:/h.txt");
		try(
				FileWriter fw=new FileWriter(file1);
				PrintWriter pw=new PrintWriter(fw);
			){
			pw.println(place[0]);
			pw.println(place[1]);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public static void gui(int[] place){
		JFrame f=new JFrame("嘤嘤怪冲鸭");
		f.setSize(500,400);
		f.setLocation(place[0],place[1]);
		f.setLayout(null);
		JButton b=new JButton("这个程序会在上次关闭的地方打开!");
		b.setBounds(100, 50, 300, 30);
		f.add(b);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		f.setVisible(true);
		for(;;){
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		place[0]=f.getX();
		place[1]=f.getY();
		}
	}
	public static void main(String[] args) {
		int[] place=new int[]{200,300};
		read(place);
		Thread thread1=new Thread(){
			public void run(){
				gui(place);
			}
		};
		Thread thread2=new Thread(){
			public void run(){
				for(;;){
					write(place);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		thread2.start();
		thread1.start();
	}
}
