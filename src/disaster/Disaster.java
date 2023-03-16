   // //                                         //
package disaster;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.RandomAccess;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author yo
 */
public class Disaster {
        
    static int compareFiles(File f1, File f2) throws IOException {
        try(BufferedInputStream fis1 = new BufferedInputStream(new FileInputStream(f1));
            BufferedInputStream fis2 = new BufferedInputStream(new FileInputStream(f2));) {
            
            while(true) {
                int b1 = fis1.read();
                int b2 = fis2.read();

                if(b1==-1 && b2==-1) {
                    return 0;
                }

                if(b1 != b2) return b1 - b2;
            }
        }
    }
    
    public static List<File> getAllFiles(File f){
        ArrayList files = new ArrayList();
        File[] folders = f.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        
        for(File folder:folders){
            files.addAll(getAllFiles(folder));
        }
        
        File[] files1 = f.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile();
            }
        });
        
        files.addAll(new ArrayList(Arrays.asList(files1)));
        
        return files;
    }
        
    public static void main(String[] args) throws IOException {
        
        List<File> files = getAllFiles(new File("D:\\rto\\"));
    
        TreeSet<File> ts = new TreeSet<File>(new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                try {
                    return compareFiles(o1, o2);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        try {
            for(File f : files){
                if(!ts.contains(f)) {
                    ts.add(f);
                } else {
                    File old = ts.floor(f);
                    System.out.println("Файл " + f + " такой же как " + old);
                }
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        
//        for(int i = 0; i < allFiles.size(); i++){
//            for(int j = i + 1;j < allFiles.size(); j++) {
//                if(compare(allFiles.get(i), allFiles.get(j)) == 0) {
//                    System.out.println("Файлы " + allFiles.get(i) + " и " + allFiles.get(j) + " одинаковы");
//                }
//            }
//        }
        
        
        //int q = compare(f1, f2);
        //System.out.println(q);
        
//            if(q.length() == f.length()) {
//            System.out.println("equals");
//            try (InputStream fis1 = new FileInputStream(f);){
//                try (InputStream fis2 = new FileInputStream(q);) {
//            while(true) {
//                if(fis1.read()==fis2.read()){
//                    System.out.println("file " + f + " odinakov s failom " + q);
//                } else {
//                    System.out.println("file " + f + " ne odinakov " + q);
//                }
//            } 
//        } catch (IOException e) {
//            System.err.println("file problem");
//            }             
//            }
//        }                 
//        try (InputStream fis = new FileInputStream("D:\\rto\\a.txt");){
//            while(true) {
//                fis.
//                int c = fis.read();
//                if(c== -1) break;
//                System.out.println((char)c);
//            } 
//        } catch (IOException e) {
//            System.err.println("file problem");
//        }
        
//        RandomAccessFile raf = new RandomAccessFile("C:\\Users\\yo\\Documents\\NetBeansProjects\\Disaster\\src\\disaster\\Disaster.java", "rw");
//        
//        raf.seek(3);
//        byte[]bytes = new byte[3];
//        raf.read(bytes,0,3);
//        raf.write(bytes);
//        System.out.println(Arrays.toString(bytes));
    }   
}
