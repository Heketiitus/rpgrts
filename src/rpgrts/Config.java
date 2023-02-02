package rpgrts;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Config {
	
	int width = 1920;
	int height = 1080;
	int tps = 60;
	
	public int getWidth() {return width;};
	public int getHeight() {return height;};
	public int getTps() {return tps;};
	
	public static Config loadConfig(String src) {
		System.out.println("config");
		File file = new File(src);
		Config config;
		if(file.exists()) {
			
				try {
					config = new Config();
					Scanner sc = new Scanner(file);
					while(sc.hasNext()) {
						String next = sc.next();
						System.out.println("next " + next);
						switch(next) {
						case "width":
							config.width = sc.nextInt();
							break;
						case "height":
							config.height = sc.nextInt();
							break;
						case "tps":
							config.tps = sc.nextInt();
							break;
						default:
							System.out.println("default");
						}
					}
					sc.close();
					return config;
				} catch (FileNotFoundException e) {
					
					e.printStackTrace();
				}
			
		}else {
			System.out.println("config doesnt exist");
			config = new Config();
			config.write(src);
			return config;
		}
		return null;
	}
	
	void write(String dst) {
		try {
			FileWriter fw = new FileWriter(new File(dst));
			fw.write("width " + getWidth()+"\n");
			fw.write("height " + getHeight()+"\n");
			fw.write("tps " + getTps()+"\n");
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	Config() {
		
	}
}
