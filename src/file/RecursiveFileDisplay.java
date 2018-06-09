package file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class RecursiveFileDisplay {

	static ArrayList<MyFile> arr = new ArrayList<MyFile>();
	static ArrayList<MyFile> Filetxt = new ArrayList<MyFile>();

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhap duong dan");
		String path = sc.nextLine();
		File currentDir = new File(path); // current directory
		displayDirectoryContents(currentDir);
		for (int i = 0; i < arr.size(); i++) {
			System.out.println(arr.get(i).name);
		}
		SelectionSort(arr);
		System.out.println("nhap lua chon");
		int choice;
		filetxt(arr);

		do {
			System.out.println(" _________________________________________________________");
			System.out.println("|_____________1.Sap xep theo Seclection Sort______________|");
			System.out.println("|_____________2.Sap xep theo Insertion Sort_______________|");
			System.out.println("|_____________3.Tim kiem file_____________________________|");
			System.out.println("|_____________4.EXIT______________________________________|");

			choice = sc.nextInt();
			switch (choice) {

			case 1:
				SelectionSort(Filetxt);
				break;
			case 2:
				InsertionSort(Filetxt);
				break;
			case 3:
				Scanner sc1 = new Scanner(System.in);
				System.out.println("Nhap Noi Dung");
				String str = sc1.nextLine();
				SearchFile(str);
				break;
			case 4:
				System.out.println("EXIT");
				break;

			}
		} while (choice != 4);
	}

	public static void displayDirectoryContents(File dir) {
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				displayDirectoryContents(files[i]);
			} else if (files[i].isFile()) {
				MyFile myfile = new MyFile();
				myfile.name = files[i].getName();

				myfile.Size = (int) files[i].length();
				myfile.FullPath = files[i].getAbsolutePath();
				arr.add(myfile);
			}
		}
	}

	public static void filetxt(ArrayList<MyFile> arr2) {
		for (int i = 0; i < arr2.size(); i++) {
			if (arr2.get(i).name.endsWith(".txt")) {
				Filetxt.add(arr2.get(i));
			}
		}
	}

	public static void InsertionSort(ArrayList<MyFile> arr3) {

		int n = arr3.size();
		for (int i = 1; i < n; i++) {
			MyFile tmp = arr3.get(i);
			int x = arr3.get(i).Size;
			int j = i;
			while (j > 0 && arr3.get(j - 1).Size > x) {

				arr3.set(j, arr3.get(j - 1));
				j--;
			}
			arr3.set(j, tmp);
		}
		DisplayArr(Filetxt);
	}

	public static void SelectionSort(ArrayList<MyFile> arr4) {
		int min, i, j;
		MyFile temp;
		for (i = 0; i < arr4.size() - 1; i++) {
			min = i;
			for (j = i + 1; j < arr4.size(); j++) {
				if (arr4.get(j).Size < arr4.get(min).Size) {
					min = j;
				}
			}
			temp = arr4.get(i);
			arr4.set(i, arr4.get(min));
			arr4.set(min, temp);
		}
		DisplayArr(Filetxt);
	}

	public static void DisplayArr(ArrayList<MyFile> arr5) {
		for (MyFile myFile : arr5) {
			System.out.printf("%-10s%-10d\n", myFile.name, myFile.Size);
		}
	}

	public static String readFile(String path) throws FileNotFoundException {
		String contentFile = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String contentLine = br.readLine();
			while (contentLine != null) {
				contentFile += contentLine;
				contentLine = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contentFile;
	}

	public static void SearchFile(String str) throws FileNotFoundException {
		boolean bool = false;
		for (MyFile myFile : Filetxt) {

			String content = readFile(myFile.FullPath);

			if (content.contains(str)) {
				System.out.println(myFile.name);
				bool = true;
			}
		}
		if (bool == false) {
			System.out.println("không có nội dung phù hợp");
		}
	}
}