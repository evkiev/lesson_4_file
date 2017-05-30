package lesson4_file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class _general {

	public static void main(String[] args) throws IOException {

		String dir = args[0];
		String file = args[1];

		// findFile(dir, file);
		// findfilecontain(dir, file);
		// copyfile(dir, file);

		copyallfile(dir, file);

	}

	static void findFile(String startPath, String fileNameToFind) throws IOException {
		if (startPath == null) {
			System.out.println("Error1 (check): Start path " + startPath + "  is empty");
			return;
		}

		File startCatalog = new File(startPath);
		if (!startCatalog.exists()) {
			System.out.println("Error2 (check): Start path " + startPath + " does not exist");
			return;
		}

		File[] listFiles = startCatalog.listFiles();

		if (listFiles == null) {
			System.out.println("Error3 (check): There are no files in start path");
			return;
		}

		for (File fileInCatalog : listFiles) {
			if (fileInCatalog.isDirectory()) {
				findFile(fileInCatalog.getAbsolutePath(), fileNameToFind); // recursive
			} else {
				if (fileNameToFind.equals(fileInCatalog.getName())) {
					System.out.println("file found [" + fileInCatalog.getCanonicalPath() + "]");
				}
			}

		}
	}

	static void findfilecontain(String startPath, String fileNameToFind) throws IOException {
		if (startPath == null) {
			System.out.println("Error1 (check): Start path " + startPath + "  is empty");
			return;
		}

		File startCatalog = new File(startPath);
		if (!startCatalog.exists()) {
			System.out.println("Error2 (check): Start path " + startPath + " does not exist");
			return;
		}

		File[] listFiles = startCatalog.listFiles();

		if (listFiles == null) {
			System.out.println("Error3 (check): There are no files in start path");
			return;
		}

		for (File fileInCatalog : listFiles) {
			if (fileInCatalog.isDirectory()) {
				findfilecontain(fileInCatalog.getAbsolutePath(), fileNameToFind); // recursive
			} else {
				if (fileInCatalog.getName().contains(fileNameToFind.substring(0, fileNameToFind.lastIndexOf("*")))) {
					System.out.println("file found [" + fileInCatalog.getCanonicalPath() + "]");
				}
			}

		}

	}

	static void copyfile(String dir, String filename) throws IOException {
		File source = new File(dir + filename);
		File dest = new File("d://Back/" + filename.substring(0, filename.lastIndexOf(".")) + ".bac");
		if (source.exists() & !dest.exists()) {

			System.out.println("source is " + source + " dest " + dest);
			Files.copy(source.toPath(), dest.toPath());
			System.out.println("file " + filename + " has copied successfully");
		} else {
			System.out.println("Error (check): There are no files in start path or dest file exists");
		}
	}

	static void copyallfile(String startdir, String filename) throws IOException {

		if (startdir == null) {
			System.out.println("Error1 (check): Start path " + startdir + "  is empty");
			return;
		}

		File startCatalog = new File(startdir);
		if (!startCatalog.exists()) {
			System.out.println("Error2 (check): Start path " + startdir + " does not exist");
			return;
		}

		File[] listFiles = startCatalog.listFiles();

		if (listFiles == null) {
			System.out.println("Error3 (check): There are no files in start path");
			return;
		}

		// copy files
		for (File fileInCatalog : listFiles) {
			if (fileInCatalog.isDirectory()) {
				copyallfile(fileInCatalog.getAbsolutePath(), filename); // recursive
			} else {
				if (fileInCatalog.getName().contains(filename.substring(0, filename.lastIndexOf("*")))) {

					File dest = new File("d://Back/"
							+ fileInCatalog.getName().substring(fileInCatalog.getName().lastIndexOf("/") + 1,
									fileInCatalog.getName().lastIndexOf("."))
							+ ".bac");

					System.out.println("source is " + fileInCatalog + " dest " + dest);
					Files.copy(fileInCatalog.toPath(), dest.toPath());
					System.out.println("file " + fileInCatalog + " has copied successfully");

					dest = null;
				}
			}

		}

	}
}
