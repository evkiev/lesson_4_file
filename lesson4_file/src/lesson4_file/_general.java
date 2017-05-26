package lesson4_file;

import java.io.File;
import java.io.IOException;


public class _general {

	public static void main(String[] args) throws IOException {

		String dir = args[0];
		String file = args[1];
		
		findFile(dir, file);
		
		
	}

	static void findFile(String startPath, String fileNameToFind) throws IOException {
		if (startPath == null) {
			System.out.println("Error: Start path " + startPath + "  is empty");
			return;
		}

		File startCatalog = new File(startPath);
		if (!startCatalog.exists()) {
			System.out.println("Error: Start path " + startPath + " does not exist");
			return;
		}

		// String[] list = f.list(); // список имён файлов в текущей папке
		File[] listFiles = startCatalog.listFiles();

		if (listFiles == null) {
			System.out.println("Error: There are no files in start path");
			return;
		}

		for (File fileInCatalog : listFiles) {
			if (fileInCatalog.isDirectory()) {
				findFile(fileInCatalog.getAbsolutePath(), fileNameToFind); // recursive
			} else {
				if (fileNameToFind.equals(fileInCatalog.getName().substring(0, fileNameToFind.lastIndexOf("*")))) {
					System.out.println("file found [" + fileInCatalog.getCanonicalPath() + "]");
				}
			}
		}
	}

	// if file.lastIndex("*")
	//fileInCatalog.getName()
	//метод contain
}
