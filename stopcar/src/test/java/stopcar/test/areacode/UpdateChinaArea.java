package stopcar.test.areacode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.park.bean.China_area;

/**
 * 通过高德地图地址编码更新china_area表
 * @author jingxiaohu
 *
 */
public class UpdateChinaArea {
	
	/**
	 * 读取文件组装表数据
	 * @throws Throwable 
	 */
	public static List<China_area> readFile(String path,String output_path) throws Throwable{
//		List<China_area> list = new ArrayList<China_area>();
		Map<String,String> map_province = new HashMap<String,String>();//省
		Map<String,String> map_city = new HashMap<String,String>();//市
		File file = new File(path);
		Reader read = new FileReader(file);
		BufferedReader readbuf = new BufferedReader(read);
		
		File file_out = new File(output_path);
		Writer write = new FileWriter(file_out);
		BufferedWriter writebuf = new BufferedWriter(write);
		
		String str = null;
		int id = 1;
		while((str = readbuf.readLine()) != null){
			if(str.contains("adcode") || str.contains("中华人民共和国")){
				continue;
			}
			String[] array = str.split("	");
			if(array == null){
				continue;
			}
			if(array.length == 2 ){
				//省或者直辖市
				map_province.put(array[1], array[0]);
				continue;
			}
			String provice_code = array[1].substring(0, 2)+"0000";
			String city_code = array[2];
			if(!map_city.containsKey(city_code)){
				map_city.put(city_code, array[0]);
			}
			
			
			China_area china_area = new China_area();
			china_area.setArea_name(array[0]);
			china_area.setArea_code(array[1]);
			
			if(map_province.get(provice_code) == null){
				//直辖市
				map_province.put(array[1], array[0]);
			}
			china_area.setProvince_name(map_province.get(provice_code));
			china_area.setProvince_code(provice_code);
			
			china_area.setCity_code(array[2]);
			china_area.setCity_name(map_city.get(city_code));
			
			china_area.setFather(provice_code);
//			System.out.println(china_area.toMap().toString());
			String sql = "INSERT INTO `china_area` VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');";
			sql = String.format(sql, id,china_area.getArea_code(),china_area.getArea_name(),
			china_area.getProvince_code(),china_area.getProvince_name(),
			china_area.getCity_code(),china_area.getCity_name(),china_area.getFather());
			System.out.println(sql);
			writebuf.write(sql);
			writebuf.newLine();
			//list.add(china_area);
			id++;
		}
		readbuf.close();
		writebuf.flush();
		writebuf.close();
		return null;
	}

	public static void main(String[] args) throws Throwable {
		String path = "D://temp/new 2.txt";
		String path_out = "D://temp/out.txt";
		readFile(path,path_out);
	}
}
