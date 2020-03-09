package com.nuparu.defuni.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.nuparu.defuni.province.Province;

public class CsvHelper {

	public static List<Province> loadDefinition(String file){
		List<Province> list = new ArrayList<Province>();
		BufferedReader br = null;
		try {
		br = new BufferedReader(new FileReader(file));
		String line;
		while ((line = br.readLine()) != null) {
			String[] words = line.split(";");
			if(words.length != 6 && words.length != 5) continue;
			try {
			long id = Integer.parseInt(words[0]);
			int r = Integer.parseInt(words[1]);
			int g = Integer.parseInt(words[2]);
			int b = Integer.parseInt(words[3]);
			
			String name = words[4];
			
			Province province = new Province(id,r,g,b,name);
			list.add(province);
			}
			catch(NumberFormatException e) {
				continue;
			}
		}
		}catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		
		return list;
	}
	
}
