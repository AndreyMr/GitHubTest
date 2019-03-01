package ru.javabegin.training.spring.aop.objects;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Component;

import ru.javabegin.training.spring.aop.annotations.ShowResults;
import ru.javabegin.training.spring.aop.annotations.ShowTime;
import ru.javabegin.training.spring.aop.interfaces.Manager;

@Component
public class FileManager implements Manager {
	@ShowTime
	@ShowResults
	@Override
	public Set<String> getExtensionList(String folder) {
		File dir = new File(folder);

		Set<String> extList = new TreeSet<>();

		for (String fileName : dir.list()) {

			File file = new File(dir.getAbsolutePath() + "\\" + fileName);

			int i = fileName.lastIndexOf(".");
			if (file.isFile() && i != -1) {
				extList.add(fileName.substring(i + 1, fileName.length()).toLowerCase());
			}
		}

		return extList;

	}

	@ShowTime
	@ShowResults
	@Override
	public Map<String, Integer> getExtensionCount(String folder) {

		File dir = new File(folder);

		Map<String, Integer> map = new HashMap<>();

		for (String ext : getExtensionList(folder)) {
			FilenameFilter filter = new CustomFileFilter(ext);
			map.put(ext, dir.listFiles(filter).length);
		}

		return map;

	}

}
